import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class personal_record {

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee ID: ");
        int empID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        String userType = getUserType(enteredPassword);

        if (userType.equals("INVALID")) {
            System.out.println("Invalid password format, please try again");
            scanner.close();
            return;
        }

        String enteredHash = hashPassword(enteredPassword);
        String storedHash = getStoredHashByEmpID(empID);

        if (storedHash == null) {
            System.out.println("Employee ID not found");
            scanner.close();
            return;
        }

        if (enteredHash.equals(storedHash)) {
            System.out.println("Login successful");
            System.out.println("User type: " + userType);
        } else {
            System.out.println("Login failed, password does not match");
        }

        scanner.close();
    }

    public static String getUserType(String password) {
        if (password == null || password.length() != 7) {
            return "INVALID";
        }

        if (password.charAt(2) == '@') {
            return "HR_ADMIN";
        } else {
            return "GENERAL_EMPLOYEE";
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashbytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashbytes) {
                sb.append(String.format("%02x", b));
        }
        
        return sb.toString();   
    } catch(Exception e) {
        throw new RuntimeException("Error hashing password", e);
    }
    }

    public static String getStoredHashByEmpID(int empID) {
        String sql = "SELECT password_hash FROM employees WHERE empid = ?";
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "root";
        String password = "passwordpassword";


        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, empID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password_hash");
                }
            }

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return null;
        
    }
}