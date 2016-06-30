package com.dsmc.api.auth;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.dsmc.App;
import com.dsmc.data.tables.AdminUser;
import com.dsmc.data.tables.AuthCredential;
import com.dsmc.data.tables.Company;
import com.dsmc.data.tables.records.AuthCredentialRecord;
import org.apache.commons.codec.binary.Base64;
import org.jooq.DSLContext;
import org.jooq.Record1;
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
import java.util.HashMap;
import java.util.Map;

public class UserAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String TOKEN_SEPARATOR = ":";
    private final static int ITERATION_NUMBER = 1000;
    private final DSLContext context;

    public UserAuthService(DSLContext context) {
        this.context = context;
    }

    private static byte[] base64ToByte(String digest) {
        return Base64.decodeBase64(digest);
    }

    private static String byteToBase64(byte[] hash) {
        return new String(Base64.decodeBase64(hash));
    }

    public boolean createLogin(String login, String password) {
        if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Supplied credentials are invalid");
        }
        try {
            // Uses a secure Random not a simple Random
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // Salt generation 64 bits long
            byte[] bSalt = new byte[8];
            random.nextBytes(bSalt);
            // Digest computation
            byte[] bDigest = getHash(password, bSalt);
            String sDigest = byteToBase64(bDigest);
            String sSalt = byteToBase64(bSalt);
            AuthCredentialRecord credential = context.newRecord(AuthCredential.AUTH_CREDENTIAL);
            credential.setLogin(login);
            credential.setPassword(sDigest);
            credential.setSalt(sSalt);
            credential.store();
            return true;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Credential creation failed", e);
        }
    }

    public com.dsmc.data.tables.pojos.AdminUser getAdminUserFromToken(String token) {
        String[] parts = token.split(TOKEN_SEPARATOR);
        if (parts.length != 2) {
            return null;
        }
        String username = parts[0];
        token = parts[1];
        AuthCredential ac = AuthCredential.AUTH_CREDENTIAL;
        Record1<String> record = context.select(ac.JWT_TOKEN)
                .from(ac)
                .where(ac.LOGIN.equal(username))
                .fetchOne();
        if (record == null) {
            return null;
        }
        String secret = record.value1();
        try {
            JWTVerifier verifier = new JWTVerifier(secret);
            Map<String, Object> claims = verifier.verify(token);
            Integer claimUserId = (Integer) claims.get("userId");
            Integer claimCompanyId = (Integer) claims.get("companyId");
            String claimUsername = (String) claims.get("username");

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
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("userId", rs.value1());
        claims.put("companyId", rs.value2());
        JWTSigner signer = new JWTSigner(rs.value3());
        return username + TOKEN_SEPARATOR + signer.sign(claims);
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
