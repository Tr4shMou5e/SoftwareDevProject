import java.sql.*;
import java.util.ArrayList;

public class TotalMontlyPayByDivison {
    public static void viewTotalMonthlyPayByDivision(int month, int year) {
    String query = """
        SELECT d.ID AS div_id, d.Name, SUM(p.earnings) AS total_pay
        FROM payroll p
        JOIN employee_division ed ON p.empid = ed.empid
        JOIN division d ON ed.div_ID = d.ID
        WHERE MONTH(p.pay_date) = ? AND YEAR(p.pay_date) = ?
        GROUP BY d.ID, d.Name
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
                rs.getInt("div_id"),
                rs.getString("Name"),
                rs.getDouble("total_pay")
            );
            list.add(p);
        }

        System.out.println("Total Monthly Pay by Division:");
        for (PayRollInfo p : list) {
            System.out.println(p.getName() + " | $" + p.getPay());
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
