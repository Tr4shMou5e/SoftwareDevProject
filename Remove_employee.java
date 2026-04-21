import java.sql.*;

public class Remove_employee {
    public Employee findEmployeeById(int empId){
        String sql = "SELECT empID, Fname, Lname, email, HireDate, Salary, SSN " +
        "FROM employees WHERE empID = ?";

        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmpId(rs.getInt("empID"));
                    emp.setFirstName(rs.getString("Fname"));
                    emp.setLastName(rs.getString("Lname"));
                    emp.setEmail(rs.getString("email"));
                    emp.setHireDate(rs.getDate("HireDate"));
                    emp.setSalary(rs.getDouble("Salary"));
                    emp.setSsn(rs.getString("SSN"));
                    return emp;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteEmployeeById(int empId) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);


            deleteByEmpId(conn, "DELETE FROM payroll WHERE empID = ?", empId);
            deleteByEmpId(conn, "DELETE FROM employee_division WHERE empID = ?", empId);
            deleteByEmpId(conn, "DELETE FROM employee_job_titles WHERE empID = ?", empId);


            String deleteEmployeeSql = "DELETE FROM employees WHERE empID = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteEmployeeSql)) {
                ps.setInt(1, empId);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 0) {
                    conn.rollback();
                    return false;
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteByEmpId(Connection conn, String sql, int empId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ps.executeUpdate();
        }
    }
}
