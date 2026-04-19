
import java.sql.*;

public class EmployeeHireDate {
    public static void viewHireDates(int start, int end) {
        String query = "SELECT empID, Fname, LName, HireDate FROM employees WHERE YEAR(HireDate) BETWEEN ? AND ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, start);
            ps.setInt(2, end);

            ResultSet rs = ps.executeQuery();
            
            System.out.println("Employee Hire Dates:");
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("empID") + ", Name: " + rs.getString("Fname") + " " + rs.getString("LName") + ", Hire Date: " + rs.getDate("HireDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
