import java.security.MessageDigest;

public class PasswordUtil {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing error", e);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String hashedInput = hashPassword(inputPassword);
        return hashedInput.equals(storedHash);
    }

    public static boolean isHRPassword(String password) {
        if (password.length() != 7) {
            return false;
        }

        if (password.charAt(2) != '@') {
            return false;
        }

        if (!Character.isLetter(password.charAt(0)) || !Character.isLetter(password.charAt(1))) {
            return false;
        }

        for (int i = 3; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isGeneralEmployeePassword(String password) {
        if (password.length() != 7) {
            return false;
        }

        if (password.charAt(2) == '@') {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPasswordFormat(String password) {
        return isHRPassword(password) || isGeneralEmployeePassword(password);
    }
}