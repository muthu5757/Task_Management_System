package src.view;

public class Checks {

    public boolean isMobileNumber(String number)
    {
        String regex = "^\\+?91[\\s-]?[6789]\\d{9}$";
        if (number.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isEmailId(String email)
    {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email.matches(regex)) {
           return true;
        } else {
            return false;
        }
    }
    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z][a-zA-Z0-9]*$";
         if (username.matches(regex)) {
           return true;
        } else {
            return false;
        }
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$";
         if (password.matches(regex)) {
           return true;
        } else {
            return false;
        }
    }
}
