import java.sql.*;

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
                EmployeeInfo e = new EmployeeInfo();
                e.addEmployee(empID, rs.getString("Fname"), rs.getString("LName"), rs.getString("email"), rs.getDouble("Salary"), rs.getString("HireDate"));
                System.out.println("Employee Details:");
                System.out.println("ID: " + e.getEmpID());
                System.out.println("Name: " + e.getFname() + " " + e.getLName());
                System.out.println("Email: " + e.getEmail());
                System.out.println("Salary: " + e.getSalary());
                System.out.println("Hire Date: " + e.getHireDate());
                System.out.println("------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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