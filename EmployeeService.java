import java.sql.*;

public class EmployeeService {

    public static void viewEmployee(int empID) {
        String query = "SELECT * FROM employees WHERE empID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("Fname") + " " + rs.getString("Lname"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Salary: " + rs.getDouble("Salary"));
            } else {
                System.out.println("Employee not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}