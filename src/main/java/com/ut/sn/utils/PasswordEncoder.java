package com.ut.sn.utils;

import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {
    private static final int log_rounds = 10;

    public static String hashPassword(String passwordPlaintext, String salt) {
        return BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt(log_rounds, new SecureRandom(salt.getBytes())));
    }

    public static boolean isPasswordValid(String passwordPlaintext, String storedHash) {
        if (passwordPlaintext == null || passwordPlaintext.isEmpty() || storedHash == null || storedHash.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(passwordPlaintext, storedHash);
    }

}