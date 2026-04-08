package src;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalaryPanel extends JPanel {

    JButton upSalBtn = new JButton("Update Salary");

    
    JTextField percentageField = new JTextField(10);
    JTextField startRangeField = new JTextField(10);
    JTextField endRangeField = new JTextField(10);


    JPanel tablePanel = new JPanel(new BorderLayout());

    public SalaryPanel() {
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

            UpdateEmployInfo(startRange,endRange,percent);
        });
}

    void UpdateEmployInfo(String startRange, String endRange, String percent) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Empid");
        model.addColumn("Salary");

        // TEMP data (replace with DB later)
       

        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("Name");
        model2.addColumn("Empid");
        model2.addColumn("Salary");

        // TEMP data (replace with DB later)

        JPanel tablesContainer = new JPanel(new GridLayout(4, 1));
        JPanel labelPanel = new JPanel();

        JTable table = new JTable(model);
        JTable table2 = new JTable(model2);
        tablesContainer.add(new JScrollPane(table));
        tablesContainer.add(new JScrollPane(table2));

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(tablesContainer), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

}
