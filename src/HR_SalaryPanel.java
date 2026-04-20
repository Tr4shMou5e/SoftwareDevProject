

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HR_SalaryPanel extends JPanel {

    JButton upSalBtn = new JButton("Update Salary");

    
    JTextField percentageField = new JTextField(10);
    JTextField startRangeField = new JTextField(10);
    JTextField endRangeField = new JTextField(10);


    JPanel tablePanel = new JPanel(new BorderLayout());

    public HR_SalaryPanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        
        JPanel buttonPanel = new JPanel();
        

        JPanel inputPanel = new JPanel();
        
        inputPanel.add(new JLabel("Start Range:"));
        inputPanel.add(startRangeField);

        inputPanel.add(new JLabel("End Range:"));
        inputPanel.add(endRangeField);

        inputPanel.add(new JLabel("Percentage:"));
        inputPanel.add(percentageField);

        inputPanel.add(upSalBtn);
        

        topPanel.add(inputPanel);

        
        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        

        // Button actions
         upSalBtn.addActionListener(e -> {
            String startRange = startRangeField.getText();
            String endRange = endRangeField.getText();
            String percent = percentageField.getText();

            updateEmployInfo(startRange,endRange,percent);
        });
}

    void updateEmployInfo(String startRange, String endRange, String percent) {

    double start, end, pct;

    try {
        start = Double.parseDouble(startRange);
        end = Double.parseDouble(endRange);
        pct = Double.parseDouble(percent);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Enter valid numbers.");
        return;
    }

    
    ArrayList<EmployeeInfo> beforeList =
        EmployeeService.getEmployeesInRange(start, end);

    
    EmployeeService.updateSalary(start, end, pct);

   
    ArrayList<EmployeeInfo> afterList =
        EmployeeService.getEmployeesInRange(start, end);

    // setup tables
    DefaultTableModel modelBefore = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

    modelBefore.addColumn("First Name");
    modelBefore.addColumn("Last Name");
    modelBefore.addColumn("EmpID");
    modelBefore.addColumn("Salary");

    DefaultTableModel modelAfter = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

    modelAfter.addColumn("First Name");
    modelAfter.addColumn("Last Name");
    modelAfter.addColumn("EmpID");
    modelAfter.addColumn("Salary");

    // Before table
    for (EmployeeInfo e : beforeList) {
        modelBefore.addRow(new Object[]{
            e.getFname(),
            e.getLname(),
            e.getEmpID(),
            e.getSalary()
        });
    }

    // After table
    for (EmployeeInfo e : afterList) {
        modelAfter.addRow(new Object[]{
            e.getFname(),
            e.getLname(),
            e.getEmpID(),
            e.getSalary()
        });
    }

    JTable tableBefore = new JTable(modelBefore);
    JTable tableAfter = new JTable(modelAfter);

    JScrollPane sp1 = new JScrollPane(tableBefore);
    JScrollPane sp2 = new JScrollPane(tableAfter);

    sp1.setBorder(BorderFactory.createTitledBorder("Before Update"));
    sp2.setBorder(BorderFactory.createTitledBorder("After Update"));

    JPanel container = new JPanel(new GridLayout(2, 1));
    container.add(sp1);
    container.add(sp2);

    tablePanel.removeAll();
    tablePanel.add(container, BorderLayout.CENTER);
    tablePanel.revalidate();
    tablePanel.repaint();
}

}
