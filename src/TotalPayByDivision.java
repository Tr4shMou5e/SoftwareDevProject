import java.sql.*;

public class TotalPayByDivision {
    public static void viewTotalPayByDivision() {
        String query = """
            SELECT d.ID AS div_id, d.Name, SUM(e.Salary) AS total_pay
            FROM employees e
            JOIN employee_division ed ON e.empid = ed.empid
            JOIN division d ON ed.div_ID = d.ID
            GROUP BY d.ID, d.Name
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {

            System.out.println("Total Pay by Division:");

            while (rs.next()) {
                Division d = new Division(
                    rs.getInt("div_id"),
                    rs.getString("Name")
                );
                double totalPay = rs.getDouble("total_pay");
                System.out.println(d.getName() + " | $" + totalPay);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}