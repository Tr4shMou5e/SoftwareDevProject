import java.sql.*;

public class PayStatement {
    public static void viewPayStatement(int empID) {
        String query = "SELECT * FROM payroll WHERE empID = ? ORDER BY pay_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();
            
            boolean found = false;
            
            System.out.println("Pay Statement:");

            while (rs.next()) {
                found = true;
                PayRollInfo p = new PayRollInfo();
                p.addPayRollInfo(empID, null, rs.getDouble("earnings"), rs.getString("pay_date"));

                System.out.println("Earnings: $" + p.getPay());
                System.out.println("Pay Date: " + p.getPayDate());
                System.out.println("------------------------------------------");
            }
            if (!found) {
                System.out.println("No pay statement found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
