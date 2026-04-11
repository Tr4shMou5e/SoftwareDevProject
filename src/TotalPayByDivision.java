import java.sql.*;

public class TotalPayByDivision {
    public static void viewTotalPayByDivision() {
        String query = """
            SELECT d.Name, SUM(e.Salary) AS total_pay
            FROM employees e
            JOIN employee_division ed ON e.empid = ed.empid
            JOIN division d ON ed.div_ID = d.ID
            GROUP BY d.Name
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {

            System.out.println("Total Pay by Division:");

            while (rs.next()) {
                System.out.println(
                    rs.getString("Name") + " | $" +
                    rs.getDouble("total_pay")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}