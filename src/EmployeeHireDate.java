import java.sql.*;
import java.util.ArrayList;

public class EmployeeHireDate {

    public static ArrayList<EmployeeInfo> getEmployeesByHireDateRange(String start, String end) {

        ArrayList<EmployeeInfo> list = new ArrayList<>();

        String query = "SELECT empID, Fname, LName, HireDate FROM employees WHERE HireDate BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, start);
            ps.setString(2, end);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeInfo e = new EmployeeInfo();
                e.addEmployee(
                    rs.getInt("empID"),
                    rs.getString("Fname"),
                    rs.getString("LName"),
                    "",
                    0.0,
                    rs.getString("HireDate"),
                    0,
                    "N/A",                      
                    "N/A" 
                );
                list.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
