package com.example.playlists.services;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtSignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.bouncycastle.util.io.pem.PemReader;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class TokenService {

    @ConfigProperty(name = "smallrye.jwt.new-token.issuer")
    String issuer;

    @ConfigProperty(name = "smallrye.jwt.sign.key.location")
    String privateKeyPath;

    private PrivateKey privateKey;

    @PostConstruct
    public void init() {
        try (PemReader pemReader = new PemReader(new FileReader(privateKeyPath))) {
            byte[] keyBytes = pemReader.readPemObject().getContent();
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = kf.generatePrivate(spec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to load private key", e);
        }
    }

    public String generateToken(String username) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            return Jwt.issuer(issuer)
                    .expiresIn(Duration.ofHours(24))
                    .sign(privateKey);
        } catch (JwtSignatureException e) {
            throw new RuntimeException("Failed to sign the JWT token", e);
        }
    }
}
