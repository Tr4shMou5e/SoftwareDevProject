
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UserPass {

    
    public void createUser(int empId, String password) {

    System.out.println("Creating user with empID: " + empId);

    String sql = "INSERT INTO users (emp_id, password_hash) VALUES (?, ?)";
    String hashedPassword = PasswordUtil.hashPassword(password);

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, empId);
        stmt.setString(2, hashedPassword);

        stmt.executeUpdate();

        JOptionPane.showMessageDialog(null, "User created successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public String getHashedPasswordByEmpId(int empId) {
        String sql = "SELECT password_hash FROM users WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password_hash");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}