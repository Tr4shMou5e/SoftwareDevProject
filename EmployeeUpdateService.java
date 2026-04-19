import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeUpdateService {

    // UPDATE SALARY
    public static boolean updateEmployeeSalary(int empID, double newSalary) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE payroll SET salary = ? WHERE empid = ?";
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


    // UPDATE JOB TITLE
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

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // UPDATE DIVISION
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

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
