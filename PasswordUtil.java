import java.security.MessageDigest;

public class PasswordUtil {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing error");
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String hashedInput = hashPassword(inputPassword);
        return hashedInput.equals(storedHash);
    }

    public static boolean isHRPassword(String password) {
        return password.length() == 7 && password.charAt(2) == '@';
    }

    public static boolean isGeneralEmployeePassword(String password) {
        if (password.length() != 7) {
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

    public static String getUserTypeFromPassword(String password) {
        if (isHRPassword(password)) {
            return "HR Admin";
        } else if (isGeneralEmployeePassword(password)) {
            return "General Employee";
        } else {
            return "Invalid";
        }
    }
}