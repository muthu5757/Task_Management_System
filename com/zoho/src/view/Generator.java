package src.view;

import java.util.Random;
import java.util.UUID;

public class Generator {
    static String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    static String digits = "0123456789";
    static  String specialCharacters = "$@&";

    public static String generateEmployeeId() {
        return "EMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateUsername(String firstName, String lastName) {
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900);

        String username = (firstName.charAt(0) + lastName + randomNumber).toLowerCase();

        return username.length() > 8 ? username.substring(0, 8) : username;
    }

    public static String generatePassword() {

        String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;
        Random random = new Random();
        StringBuilder password = new StringBuilder(8);

        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 4; i < 8; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return password.toString();
    }
    public static String generateCode() {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        StringBuilder code = new StringBuilder("T-");
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(alphanumeric.length());
            code.append(alphanumeric.charAt(randomIndex));
        }

        return code.toString();
    }
}
