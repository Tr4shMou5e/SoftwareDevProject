import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeUpdateService {

    
    public static boolean updateEmployeeFirstName(int empID, String newFirstName) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE employees SET Fname = ? WHERE empid = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, newFirstName);
            stmt.setInt(2, empID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("First name updated successfully.");
                return true;
            } else {
                System.out.println("Employee not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean updateEmployeeLastName(int empID, String newLastName) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE employees SET LName = ? WHERE empid = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, newLastName);
            stmt.setInt(2, empID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Last name updated successfully.");
                return true;
            } else {
                System.out.println("Employee not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean updateEmployeeName(int empID, String newFirstName, String newLastName) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE employees SET Fname = ?, LName = ? WHERE empid = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setInt(3, empID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Employee name updated successfully.");
                return true;
            } else {
                System.out.println("Employee not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean updateEmployeeSalary(int empID, double newSalary) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE employees SET Salary = ? WHERE empid = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, newSalary);
            stmt.setInt(2, empID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Salary updated successfully.");
                return true;
            } else {
                System.out.println("Employee not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean updateEmployeeJobTitle(int empID, int jobTitleID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE employee_job_titles SET job_title_id = ? WHERE empid = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, jobTitleID);
            stmt.setInt(2, empID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Job title updated successfully.");
                return true;
            } else {
                System.out.println("Employee not found or no job title row exists.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean updateEmployeeDivision(int empID, int divisionID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE employee_division SET div_ID = ? WHERE empid = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, divisionID);
            stmt.setInt(2, empID);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Division updated successfully.");
                return true;
            } else {
                System.out.println("Employee not found or no division row exists.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
