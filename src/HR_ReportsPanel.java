
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HR_ReportsPanel extends JPanel {

    JButton jobTitleBtn = new JButton("Pay by Job Title");
    JButton divisionBtn = new JButton("Pay by Division");
    JButton hiresBtn = new JButton("New Hires");

    
    JTextField monthField = new JTextField(5);
    JTextField yearField = new JTextField(5);
    JTextField startDateField = new JTextField(10);
    JTextField endDateField = new JTextField(10);

    JButton searchHiresBtn = new JButton("Search Hires");

    JPanel tablePanel = new JPanel(new BorderLayout());

    public HR_ReportsPanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jobTitleBtn);
        buttonPanel.add(divisionBtn);
        // buttonPanel.add(hiresBtn);

        
        JPanel monthPanel = new JPanel();
        monthPanel.add(new JLabel("Month:"));
        monthPanel.add(monthField);

        monthPanel.add(new JLabel("Year:"));
        monthPanel.add(yearField);

        monthPanel.add(jobTitleBtn);
        monthPanel.add(divisionBtn);

       
        JPanel hiresPanel = new JPanel();
        hiresPanel.add(new JLabel("Start Date:"));
        hiresPanel.add(startDateField);

        hiresPanel.add(new JLabel("End Date:"));
        hiresPanel.add(endDateField);

        hiresPanel.add(searchHiresBtn);

        
        topPanel.add(buttonPanel);
        topPanel.add(monthPanel);
        topPanel.add(hiresPanel);

        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        

        // Button actions
            jobTitleBtn.addActionListener(e -> {
            int month, year;

            try {
                month = Integer.parseInt(monthField.getText());
                year = Integer.parseInt(yearField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid month and year.");
                return;
            }

            loadJobTitleReport(month, year);
        });

        divisionBtn.addActionListener(e -> {
            int month, year;

            try {
                month = Integer.parseInt(monthField.getText());
                year = Integer.parseInt(yearField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid month and year.");
                return;
            }

            loadDivisionReport(month, year);
        });

        searchHiresBtn.addActionListener(e -> {
            String start = startDateField.getText();
            String end = endDateField.getText();

            if (start.isEmpty() || end.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter start and end dates.");
                return;
            }

            loadHiresReport(start, end);
        });
    }

    void loadJobTitleReport(int month, int year) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Job Title");
        model.addColumn("Total Pay");

        // // TEMP data (replace with DB later)
        // if (jobTitle.isEmpty() || jobTitle.equalsIgnoreCase("Manager")) {
        //     model.addRow(new Object[]{"Manager", 250000});
        // }
        // if (jobTitle.isEmpty() || jobTitle.equalsIgnoreCase("Developer")) {
        //     model.addRow(new Object[]{"Developer", 400000});
        // }

        ArrayList<PayRollInfo> list =
        TotalMontlyPayByJobTitle.viewTotalMonthlyPayByJobTitle(month, year);

        for (PayRollInfo p : list) {
            model.addRow(new Object[]{
                p.getName(),
                p.getPay()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

     void loadDivisionReport(int month, int year) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Divsion");
        model.addColumn("Total Pay");



        ArrayList<PayRollInfo> list =
        TotalMontlyPayByDivison.viewTotalMonthlyPayByDivision(month, year);

        for (PayRollInfo p : list) {
            model.addRow(new Object[]{
                p.getName(),
                p.getPay()
            });
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
    //     if (start.isEmpty() && end.isEmpty()) {
    // model.addRow(new Object[]{"John", "Smith", 101, "2025-01-10"});
    // }

    // if (start.equals("2025-01-01") && end.equals("2025-03-01")) {
    //     model.addRow(new Object[]{"Anna", "Lee", 102, "2025-02-03"});
    // }

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}
