import java.sql.*;
import java.util.ArrayList;

public class EmployeeService {
    //HR View
    public static void viewEmployee(int empID) {
        String query = "SELECT * FROM employees WHERE empID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Employee not found.");
            } else {
                ArrayList<EmployeeInfo> employees = new ArrayList<>();
                EmployeeInfo e = new EmployeeInfo();
                e.addEmployee(empID, rs.getString("Fname"), rs.getString("LName"), rs.getString("email"), rs.getDouble("Salary"), rs.getString("HireDate"), rs.getInt("job_Title_Id"), rs.getInt("div_Id"));
                employees.add(e);

                System.out.println("Employee Details:");
                System.out.println("ID: " + e.getEmpID());
                System.out.println("Name: " + e.getFname() + " " + e.getLName());
                System.out.println("Email: " + e.getEmail());
                System.out.println("Salary: " + e.getSalary());
                System.out.println("Hire Date: " + e.getHireDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ViewInfo(int empID, String name, String ssn) {
        String query = "SELECT * FROM employees WHERE empID = ? AND Fname = ? AND SSN = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ps.setString(2, name);
            ps.setString(3, ssn);
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
                rs.getString("HireDate"), 
                rs.getInt("job_Title_Id"), 
                rs.getInt("div_Id"));

                System.out.println("Employee Details:");
                System.out.println("ID: " + e.getEmpID());
                System.out.println("Name: " + e.getFname() + " " + e.getLName());
                System.out.println("Email: " + e.getEmail());
                System.out.println("Salary: " + e.getSalary());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}