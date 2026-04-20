
import java.sql.*;
import java.util.ArrayList;

public class PayStatement {
    public static ArrayList<PayRollInfo> getPayStatement(int empID) {
           ArrayList<PayRollInfo> list = new ArrayList<>();

        String query = "SELECT * FROM payroll WHERE empID = ? ORDER BY pay_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PayRollInfo p = new PayRollInfo();

                p.addPayRollInfo(
                    empID,
                    "",
                    rs.getDouble("earnings"),
                    rs.getString("pay_date")
                );

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
