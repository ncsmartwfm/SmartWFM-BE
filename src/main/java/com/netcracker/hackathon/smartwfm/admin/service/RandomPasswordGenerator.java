package com.netcracker.hackathon.smartwfm.admin.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomPasswordGenerator {
    public static String generateRandomPassword(int length) {
        // Define the character set from which to generate the password
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()_+[]{}|;:,.<>?";

        // Combine all character sets
        String allChars = upperChars + lowerChars + digits + specialChars;

        // Use SecureRandom for cryptographic randomness
        SecureRandom random = new SecureRandom();

        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            char randomChar = allChars.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }
}
