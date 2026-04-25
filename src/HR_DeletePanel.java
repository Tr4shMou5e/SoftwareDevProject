import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HR_DeletePanel extends JPanel {

    JTextField empidField = new JTextField(10);
    JButton deleteBtn = new JButton("Delete Employee");

    JPanel tablePanel = new JPanel(new BorderLayout());
    JTable table;
    DefaultTableModel model;

    public HR_DeletePanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("EmpID:"));
        inputPanel.add(empidField);
        inputPanel.add(deleteBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        
        model = new DefaultTableModel(
            new String[]{"FirstName", "LastName", "EmpID", "Division", "JobTitle", "Salary"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        
        deleteBtn.addActionListener(e -> {
            deleteEmployee();
        });
    }

    private void deleteEmployee() {
        try {
            int empID = Integer.parseInt(empidField.getText());

          
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            
            RemoveEmployee remover = new RemoveEmployee();
            boolean success = remover.deleteEmployeeById(empID);

            if (success) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found or delete failed.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID.");
        }
    }

    
    private void refreshTable() {
        model.setRowCount(0);

        ArrayList<EmployeeInfo> list =
            EmployeeService.searchEmployees("", "", "", "");

        for (EmployeeInfo emp : list) {
            model.addRow(new Object[]{
                emp.getFname(),
                emp.getLname(),
                emp.getEmpID(),
                emp.getDivision(),
                emp.getJobTitle(),
                emp.getSalary()
            });
        }
    }
}

    



