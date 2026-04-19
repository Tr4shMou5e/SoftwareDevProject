
import java.sql.*;

public class TotalPayByJobTitle {
    public static void viewTotalPayByJobTitle() {
        String query = """
            SELECT jt.job_title_id, jt.job_title, SUM(e.Salary) AS total_pay
            FROM employees e
            JOIN employee_job_titles ejt ON e.empid = ejt.empid
            JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id
            GROUP BY jt.job_title_id, jt.job_title
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {

            System.out.println("Total Pay by Job Title:");

            while (rs.next()) {
                JobTitle jt = new JobTitle(
                    rs.getInt("job_title_id"),
                    rs.getString("job_title")
                );

                double totalPay = rs.getDouble("total_pay");
                System.out.println(jt.getJobTitle() + " | $" + totalPay);
                System.out.println("------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
