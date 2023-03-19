package neo.mtc.mtcdao.utils;

import java.security.SecureRandom;

public class CommonUtils {

    public static String generateRandomString(int length) {
        SecureRandom RANDOM = new SecureRandom();
        String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder returnValue = new StringBuilder();

        for (int i=0; i<length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public static String generateRandomInt(int length) {
        SecureRandom RANDOM = new SecureRandom();
        String ALPHABET = "0123456789";
        StringBuilder returnValue = new StringBuilder();

        for (int i=0; i<length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
