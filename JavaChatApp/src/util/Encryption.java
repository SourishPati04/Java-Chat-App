package util;

public class Encryption {
    private static final int KEY = 3;

    public static String encrypt(String msg) {
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            sb.append((char)(c + KEY));
        }
        return sb.toString();
    }

    public static String decrypt(String msg) {
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            sb.append((char)(c - KEY));
        }
        return sb.toString();
    }
}
