import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class HR_SearchPanel extends JPanel {

    JTextField firstnameField = new JTextField(10);
    JTextField dobField = new JTextField(10);
    JTextField ssnField = new JTextField(10);
    JTextField empidField = new JTextField(10);

    JButton searchemployeeBtn = new JButton("Search Employee");

    JPanel tablePanel = new JPanel(new BorderLayout());

    public HR_SearchPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("First name:"));
        inputPanel.add(firstnameField);

        inputPanel.add(new JLabel("DOB:"));
        inputPanel.add(dobField);

        inputPanel.add(new JLabel("SSN:"));
        inputPanel.add(ssnField);

        inputPanel.add(new JLabel("EmpID:"));
        inputPanel.add(empidField);

        inputPanel.add(searchemployeeBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        searchemployeeBtn.addActionListener(e -> {
            loadEmployeeData(
                firstnameField.getText(),
                dobField.getText(),
                ssnField.getText(),
                empidField.getText()
            );
        });
    }

    void loadEmployeeData(String firstname, String dob, String ssn, String empid) {

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 2; // EmpID not editable
            }
        };

        model.addColumn("FirstName");
        model.addColumn("LastName");
        model.addColumn("EmpID");
        model.addColumn("Division");
        model.addColumn("JobTitle");
        model.addColumn("Salary");

        JTable table = new JTable(model);
        table.setRowHeight(25);

        // hard coded drop down for now until all divsions are shown
        JComboBox<String> jobBox = new JComboBox<>(new String[]{"Manager", "Developer"});
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jobBox));

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();

        ArrayList<EmployeeInfo> list =
            EmployeeService.searchEmployees(firstname, dob, ssn, empid);

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

        model.addTableModelListener(e -> {
            if (e.getType() != TableModelEvent.UPDATE) return;

            int row = e.getFirstRow();
            int column = e.getColumn();

            if (row < 0 || column < 0) return;

            int empID = Integer.parseInt(model.getValueAt(row, 2).toString());
            String newValue = model.getValueAt(row, column).toString();

            boolean success = false;

            try {
                switch (column) {
                    case 0: // First Name
                        success = EmployeeUpdateService.updateEmployeeFirstName(empID, newValue);
                        break;

                    case 1: // Last Name
                        success = EmployeeUpdateService.updateEmployeeLastName(empID, newValue);
                        break;

                    case 3: // Division
                        int divID = getDivisionID(newValue);
                        success = EmployeeUpdateService.updateEmployeeDivision(empID, divID);
                        break;
                    case 4: // Job Title
                        int jobID = getJobTitleID(newValue);
                        success = EmployeeUpdateService.updateEmployeeJobTitle(empID, jobID);
                        break;
                    case 5: // Salary
                        try {
                            double salary = Double.parseDouble(newValue);
                            success = EmployeeUpdateService.updateEmployeeSalary(empID, salary);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid salary.");
                            return;
                        }
                        break;
                }

                if (!success) {
                    JOptionPane.showMessageDialog(null, "Update failed.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.");
            }
        });
    }

    // Helper method for division
    private int getDivisionID(String divisionName) {
        switch (divisionName) {
            case "HR": return 1;
            case "Engineering": return 2;
            default: return 0;
        }
    }

    private int getJobTitleID(String jobTitle) {
    switch (jobTitle) {
        case "Manager": return 1;
        case "Developer": return 2;
        default: return 0;
    }
}
}

    



