package com.dsmc.api.auth;

import com.dsmc.App;
import com.dsmc.data.tables.AdminUser;
import com.dsmc.data.tables.AuthCredential;
import com.dsmc.data.tables.Company;
import com.dsmc.data.tables.records.AuthCredentialRecord;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.binary.Base64;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private final static int ITERATION_NUMBER = 1000;
    private final DSLContext context;
    private final AuthSigningKeyResolver signingKeyResolver;
    private final SecureRandom defaultRandom;

    public UserAuthService(DSLContext context, AuthSigningKeyResolver signingKeyResolver) {
        this.context = context;
        this.signingKeyResolver = signingKeyResolver;
        defaultRandom = new SecureRandom();
        LOGGER.info("defaultRandom algorithm: {}", defaultRandom.getAlgorithm());
    }

    private static byte[] base64ToByte(String digest) {
        return Base64.decodeBase64(digest);
    }

    private static String byteToBase64(byte[] hash) {
        return new String(Base64.decodeBase64(hash));
    }

    public void createLogin(String login, String password) {
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Supplied credentials are invalid");
        }
        try {
            // Salt generation 64 bits long
            byte[] bSalt = new byte[8];
            defaultRandom.nextBytes(bSalt);
            // Digest computation
            byte[] bDigest = getHash(password, bSalt);
            String sDigest = byteToBase64(bDigest);
            String sSalt = byteToBase64(bSalt);
            AuthCredentialRecord credential = context.newRecord(AuthCredential.AUTH_CREDENTIAL);
            credential.setLogin(login);
            credential.setPassword(sDigest);
            credential.setSalt(sSalt);
            credential.store();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Credential creation failed", e);
        }
    }

    public com.dsmc.data.tables.pojos.AdminUser getAdminUserFromToken(String token) {
        try {
            Jwt<Header, Claims> jwt = Jwts.parser()
                    .setSigningKeyResolver(signingKeyResolver)
                    .parseClaimsJwt(token);

            Claims claims = jwt.getBody();
            Integer claimUserId = claims.get("userId", Integer.TYPE);
            Integer claimCompanyId = claims.get("companyId", Integer.TYPE);
            String claimUsername = claims.get("username", String.class);

            return new com.dsmc.data.tables.pojos.AdminUser(claimUserId,
                    claimCompanyId, claimUsername,
                    null, null, null, null);
        } catch (Exception e) {
            LOGGER.error("Token verification failed.", e);
        }
        return null;
    }

    public String getJsonWebToken(String username) {
        AdminUser a = AdminUser.ADMIN_USER;
        AuthCredential ac = AuthCredential.AUTH_CREDENTIAL;
        Company c = Company.COMPANY;
        Record3<Integer, Integer, String> rs = context
                .select(a.ID, c.ID, ac.JWT_TOKEN)
                .from(a)
                .join(c).on(a.COMPANY_ID.equal(c.ID))
                .join(ac).on(a.USERNAME.equal(a.USERNAME))
                .where(a.USERNAME.equal(username))
                .fetchOne();
        if (rs == null) {
            return null;
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("userId", rs.value1());
        claims.put("companyId", rs.value2());
        Map<String, Object> headers = new HashMap<>();
        AuthSigningKeyResolver.KeyDTO key = signingKeyResolver.getRandomKey();
        headers.put("kid", key.getKeyId());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setHeader(headers)
                .setClaims(claims)
                .signWith(key.getAlgo(), key.getKey())
                .compact();
    }

    public boolean authenticate(String login, String password) {
        try {
            boolean userExist = true;
            if (login == null || password == null) {
                userExist = false;
                login = "";
                password = "";
            }

            AuthCredential p = AuthCredential.AUTH_CREDENTIAL;
            AuthCredentialRecord rs = context.selectFrom(p)
                    .where(p.LOGIN.equal(login))
                    .fetchOne();

            String digest, salt;
            if (rs != null) {
                digest = rs.getPassword();
                salt = rs.getSalt();
                if (digest == null || salt == null) {
                    throw new RuntimeException("Database inconsistent Salt or Digested Password altered");
                }
            } else {
                digest = "000000000000000000000000000=";
                salt = "00000000000=";
                userExist = false;
            }

            byte[] bDigest = base64ToByte(digest);
            byte[] bSalt = base64ToByte(salt);

            // Compute the new DIGEST
            byte[] proposedDigest = getHash(password, bSalt);

            boolean authenticated = Arrays.equals(proposedDigest, bDigest) && userExist;
            if (authenticated) {
                rs.setJwtToken(new BigInteger(130, new SecureRandom()).toString(32));
                rs.store();
            }
            return authenticated;
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    /**
     * From a password, a number of iterations and a salt,
     * returns the corresponding digest
     *
     * @param password String The password to encrypt
     * @param salt     byte[] The salt
     * @return byte[] The digested password
     * @throws NoSuchAlgorithmException     If the algorithm does not exist
     * @throws UnsupportedEncodingException If the char encoding does not exist
     */
    private byte[] getHash(String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        if (salt != null) {
            digest.update(salt);
        }
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < ITERATION_NUMBER; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }


}
