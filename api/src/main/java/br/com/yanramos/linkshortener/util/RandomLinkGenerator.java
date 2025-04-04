package br.com.yanramos.linkshortener.util;

import java.util.Random;

public class RandomLinkGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 5;

    public static String generateRandomLink() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(STRING_LENGTH);

        for(int i = 0; i < STRING_LENGTH; i++) {
            int index  = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }
}
