package com.dsmc.api.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolverAdapter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.List;
import java.util.Random;

/**
 * Created by charlesmarvin on 7/9/16.
 */
public class AuthSigningKeyResolver extends SigningKeyResolverAdapter {
    private final List<String> keys;

    public AuthSigningKeyResolver(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public Key resolveSigningKey(JwsHeader header, Claims claims) {
        String key = keys.get(Integer.parseInt(header.getKeyId()));
        return new SecretKeySpec(key.getBytes(), header.getAlgorithm());
    }

    public KeyDTO getRandomKey() {
        int randomKeyId = new Random().nextInt(keys.size());
        String randomKey = keys.get(randomKeyId);
        SignatureAlgorithm algo = SignatureAlgorithm.HS512;
        Key key = new SecretKeySpec(randomKey.getBytes(), algo.getJcaName());
        return new KeyDTO(randomKeyId, key, algo);
    }

    public static class KeyDTO {
        private final int keyId;
        private final Key key;
        private SignatureAlgorithm algo;

        public KeyDTO(int keyId, Key key, SignatureAlgorithm algo) {
            this.keyId = keyId;
            this.key = key;
            this.algo = algo;
        }

        public int getKeyId() {
            return keyId;
        }

        public Key getKey() {
            return key;
        }

        public SignatureAlgorithm getAlgo() {
            return algo;
        }
    }
}
