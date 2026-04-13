import java.sql.*;
import java.util.ArrayList;

public class TotalMontlyPayByJobTitle {
    public static void viewTotalMonthlyPayByJobTitle(int month, int year) {
        String query = """
            SELECT jt.job_title_id, jt.job_title, SUM(p.earnings) AS total_pay
            FROM payroll p
            JOIN employee_job_titles ejt ON p.empid = ejt.empid
            JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id
            WHERE MONTH(p.pay_date) = ? AND YEAR(p.pay_date) = ?
            GROUP BY jt.job_title_id, jt.job_title
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, month);
        ps.setInt(2, year);

        ResultSet rs = ps.executeQuery();

        ArrayList<PayRollInfo> list = new ArrayList<>();
        
        while (rs.next()) {
            PayRollInfo p = new PayRollInfo();
            p.addPayRollInfo(
                rs.getInt("job_title_id"),
                rs.getString("job_title"),
                rs.getDouble("total_pay"),
                null
            );
            list.add(p);
        }
        
        System.out.println("Total Monthly Pay by Job Title:");
        for (PayRollInfo p : list) {
            System.out.println(p.getName() + " | $" + p.getPay());
            System.out.println("------------------------------------------");

        }
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
}