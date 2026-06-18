package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordUtil {

    private static final int SALT_LENGTH = 16;

    
    public static String hash(String password) {
        try {
            SecureRandom rng  = new SecureRandom();
            byte[] salt       = new byte[SALT_LENGTH];
            rng.nextBytes(salt);
            byte[] digest     = sha256(concat(salt, password.getBytes("UTF-8")));
            return Base64.getEncoder().encodeToString(salt)
                   + ":" +
                   Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    
    public static boolean verify(String password, String stored) {
        try {
            String[] parts = stored.split(":", 2);
            if (parts.length != 2) return false;
            byte[] salt   = Base64.getDecoder().decode(parts[0]);
            byte[] stored256 = Base64.getDecoder().decode(parts[1]);
            byte[] attempt   = sha256(concat(salt, password.getBytes("UTF-8")));
            return MessageDigest.isEqual(stored256, attempt);
        } catch (Exception e) {
            return false;
        }
    }

    
    public static String generateToken() {
        byte[] bytes = new byte[24];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }


    private static byte[] sha256(byte[] data) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(data);
    }

    private static byte[] concat(byte[] a, byte[] b) {
        byte[] out = new byte[a.length + b.length];
        System.arraycopy(a, 0, out, 0, a.length);
        System.arraycopy(b, 0, out, a.length, b.length);
        return out;
    }
}
