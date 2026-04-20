

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EMP_SearchPanel extends JPanel {

    
    JTextField firstnameField = new JTextField(10);
    JTextField dobField = new JTextField(10);
    JTextField ssnField = new JTextField(10);
    JTextField empidField = new JTextField(10);

    JButton searchemployeeBtn = new JButton("Search Employee");

    JPanel tablePanel = new JPanel(new BorderLayout());

    private int loggedInEmpID;
    private boolean isEmployee;

    public EMP_SearchPanel(int empID, boolean isEmployee) {
        setLayout(new BorderLayout());
        this.loggedInEmpID = empID;
        this.isEmployee = isEmployee;

        JPanel topPanel = new JPanel();
        

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
        topPanel.add(inputPanel);
        

        
        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        
        

        // Button actions
        searchemployeeBtn.addActionListener(e -> {
            String fname = firstnameField.getText();
            String dob = dobField.getText();
            String ssn = ssnField.getText();
            String empid = empidField.getText();

            // restrict to only personal employee info
            if (isEmployee) {
                fname = "";
                dob = "";
                ssn = "";
                empid = String.valueOf(loggedInEmpID);
            }

            loadEmployeeData(fname, dob, ssn, empid);
        });
        

    }


    void loadEmployeeData(String firstname, String dob, String ssn, String empid) {

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("EmpID");
        model.addColumn("Division");
        model.addColumn("JobTitle");
        model.addColumn("Salary");


        try {

            if (isEmployee) {
                
                empid = String.valueOf(loggedInEmpID);
            }

                ArrayList<EmployeeInfo> list = EmployeeService.searchEmployees(
                    firstname, dob, ssn, empid
            );

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

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID");
        }

        // Exapmle VERY temporary filtering logic 1
        // if (firstname.equalsIgnoreCase("John") || firstname.isEmpty()) {
        //     model.addRow(new Object[]{"John", "Smith", 101, "HR", 60000});
        // }

        // if (empid.equals("102") || empid.isEmpty()) {
        //     model.addRow(new Object[]{"Anna", "Lee", 102, "Engineering", 75000});
        // }

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
        
        //Depending on how this works with database might have to revise, Check once gotten to this point
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();

                if (row != -1) {
                    String empId = table.getValueAt(row, 2).toString();

                    openEditPanel(empId);
                }
            }
        });
    }

    void openEditPanel(String empId) {
    System.out.println("Selected employee: " + empId);

    // Later:
    // content.removeAll();
    // content.add(new UpdatePanel(empId));
    }

}
