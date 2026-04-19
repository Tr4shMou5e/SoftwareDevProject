
import java.sql.*;
import java.util.ArrayList;

public class EmployeeService {
    //HR View
    public static ArrayList<EmployeeInfo> searchEmployees(
    String fname, String dob, String ssn, String empid
) {
    ArrayList<EmployeeInfo> list = new ArrayList<>();

    String query = "SELECT * FROM employees WHERE 1=1";

    if (!fname.isEmpty()) query += " AND Fname = ?";
    if (!dob.isEmpty()) query += " AND DOB = ?";
    if (!ssn.isEmpty()) query += " AND SSN = ?";
    if (!empid.isEmpty()) query += " AND empID = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        int index = 1;

        if (!fname.isEmpty()) ps.setString(index++, fname);
        if (!dob.isEmpty()) ps.setString(index++, dob);
        if (!ssn.isEmpty()) ps.setString(index++, ssn);
        if (!empid.isEmpty()) ps.setInt(index++, Integer.parseInt(empid));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EmployeeInfo e = new EmployeeInfo();
            e.addEmployee(
                rs.getInt("empID"),
                rs.getString("Fname"),
                rs.getString("LName"),
                rs.getString("email"),
                rs.getDouble("Salary"),
                rs.getString("HireDate")
            );
            list.add(e);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    //Employee View
    public static void ViewInfo(int empID) {
        String query = "SELECT * FROM employees WHERE empID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Employee not found.");
            } else {
                EmployeeInfo e = new EmployeeInfo();
                e.addEmployee(empID, 
                rs.getString("Fname"), 
                rs.getString("LName"), 
                rs.getString("email"), 
                rs.getDouble("Salary"),
                null
                );

                System.out.println("Employee Details:");
                System.out.println("ID: " + e.getEmpID());
                System.out.println("Name: " + e.getFname() + " " + e.getLName());
                System.out.println("Email: " + e.getEmail());
                System.out.println("Salary: " + e.getSalary());
                System.out.println("------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}