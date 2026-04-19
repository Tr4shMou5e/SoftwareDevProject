package src;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HR_ReportsPanel extends JPanel {

    JButton jobTitleBtn = new JButton("Pay by Job Title");
    JButton divisionBtn = new JButton("Pay by Division");
    JButton hiresBtn = new JButton("New Hires");

    
    JTextField jobTitleField = new JTextField(10);
    JTextField divisionField = new JTextField(10);
    JTextField startDateField = new JTextField(10);
    JTextField endDateField = new JTextField(10);

    JButton jobSearchBtn = new JButton("Search Job Title");
    JButton divisionSearchBtn = new JButton("Search Division");
    JButton searchHiresBtn = new JButton("Search Hires");

    JPanel tablePanel = new JPanel(new BorderLayout());

    public HR_ReportsPanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jobTitleBtn);
        buttonPanel.add(divisionBtn);
        buttonPanel.add(hiresBtn);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Job Title:"));
        inputPanel.add(jobTitleField);
        inputPanel.add(jobSearchBtn);

        inputPanel.add(new JLabel("Division:"));
        inputPanel.add(divisionField);
        inputPanel.add(divisionSearchBtn);

        // Row 3: date range
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Start Date:"));
        datePanel.add(startDateField);
        datePanel.add(new JLabel("End Date:"));
        datePanel.add(endDateField);
        datePanel.add(searchHiresBtn);

        // Add all rows
        topPanel.add(buttonPanel);
        topPanel.add(inputPanel);
        topPanel.add(datePanel);

        
        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        

        // Button actions
        jobTitleBtn.addActionListener(e -> loadJobTitleReport(""));

        jobSearchBtn.addActionListener(e -> {
            String input = jobTitleField.getText();
            loadJobTitleReport(input);
        });
        
        divisionBtn.addActionListener(e -> loadDivisionReport(""));

        divisionSearchBtn.addActionListener(e -> {
            String input = divisionField.getText();
            loadDivisionReport(input);
        });
        
        hiresBtn.addActionListener(e -> loadHiresReport("",""));

        searchHiresBtn.addActionListener(e -> {
        String start = startDateField.getText();
        String end = endDateField.getText();

        loadHiresReport(start, end);
});
    }

    void loadJobTitleReport(String jobTitle) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Job Title");
        model.addColumn("Total Pay");

        // TEMP data (replace with DB later)
        if (jobTitle.isEmpty() || jobTitle.equalsIgnoreCase("Manager")) {
            model.addRow(new Object[]{"Manager", 250000});
        }
        if (jobTitle.isEmpty() || jobTitle.equalsIgnoreCase("Developer")) {
            model.addRow(new Object[]{"Developer", 400000});
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    void loadDivisionReport(String division) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("FirstName");
        model.addColumn("LastName");
        model.addColumn("Empid");
        model.addColumn("Divsion");
        model.addColumn("Total Pay");

        // TEMP data (replace with DB later)
        if(division.isEmpty() || division.equalsIgnoreCase("ego")){
        model.addRow(new Object[]{"Meow","Hi",001,"ego", 250000});
        }
        if(division.isEmpty() || division.equalsIgnoreCase("Dev")){
        model.addRow(new Object[]{"bark","La",102,"Dev", 400000});
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    void loadHiresReport(String start, String end) {
         DefaultTableModel model = new DefaultTableModel();
        model.addColumn("FirstName");
        model.addColumn("LastName");
        model.addColumn("Empid");
        model.addColumn("Date employed");

        // TEMP data (replace with DB later)
        if (start.isEmpty() && end.isEmpty()) {
    model.addRow(new Object[]{"John", "Smith", 101, "2025-01-10"});
    }

    if (start.equals("2025-01-01") && end.equals("2025-03-01")) {
        model.addRow(new Object[]{"Anna", "Lee", 102, "2025-02-03"});
    }

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}
