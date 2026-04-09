import java.sql.*;
import java.util.ArrayList;

public class EmployeeService {

    public static void viewEmployee(int empID) {
        String query = "SELECT * FROM employees WHERE empID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, empID);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Employee not found.");
            } else {
                ArrayList<Employee> employees = new ArrayList<>();
                Employee e = new Employee();
                e.addEmployee(empID, rs.getString("Fname"), rs.getString("LName"), rs.getString("email"), rs.getDouble("Salary"));
                employees.add(e);

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