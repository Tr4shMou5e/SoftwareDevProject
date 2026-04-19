
import java.sql.*;
import java.util.ArrayList;

public class TotalMontlyPayByDivison {
    public static ArrayList<PayRollInfo> viewTotalMonthlyPayByDivision(int month, int year) {
    String query = """
        SELECT d.ID AS div_id, d.Name, SUM(p.earnings) AS total_pay
        FROM payroll p
        JOIN employee_division ed ON p.empid = ed.empid
        JOIN division d ON ed.div_ID = d.ID
        WHERE MONTH(p.pay_date) = ? AND YEAR(p.pay_date) = ?
        GROUP BY d.ID, d.Name
    """;

    ArrayList<PayRollInfo> list = new ArrayList<>();
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, month);
        ps.setInt(2, year);

        ResultSet rs = ps.executeQuery();

        

        while (rs.next()) {
            PayRollInfo p = new PayRollInfo();
            p.addPayRollInfo(
                rs.getInt("div_id"),
                rs.getString("Name"),
                rs.getDouble("total_pay"),
                null
            );
            list.add(p);
        }

        

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return list;
}
    }   
    
