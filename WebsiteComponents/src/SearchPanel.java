package src;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchPanel extends JPanel {

    
    JTextField firstnameField = new JTextField(10);
    JTextField dobField = new JTextField(10);
    JTextField ssnField = new JTextField(10);
    JTextField empidField = new JTextField(10);

    JButton searchemployeeBtn = new JButton("Search Employee");

    JPanel tablePanel = new JPanel(new BorderLayout());

    // Ryan's fun helpful thingy
    private Object[][] stored_employees = {
        new Object[]{"John", "Smith", 101, "HR", 60000},
        new Object[]{"Anna", "Lee", 102, "Engineering", 75000}
    };

    public SearchPanel() {
        setLayout(new BorderLayout());

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

            loadEmployeeData(fname,dob,ssn,empid);
        });
        

    }


    void loadEmployeeData(String firstname, String dob, String ssn, String empid) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("EmpID");
        model.addColumn("Division");
        model.addColumn("Salary");

        // TEMP filtering logic

        // Example very temporty filtering logic 2
        for (Object[] tuple : stored_employees) {
            // TODO: Apply filitering logic right here

            // Determine if you add this based on that filter.
            model.addRow(tuple);
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
