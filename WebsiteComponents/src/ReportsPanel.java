package src;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportsPanel extends JPanel {

    JButton jobTitleBtn = new JButton("Pay by Job Title");
    JButton divisionBtn = new JButton("Pay by Division");
    JButton hiresBtn = new JButton("New Hires");

    JPanel tablePanel = new JPanel(new BorderLayout());

    public ReportsPanel() {
        setLayout(new BorderLayout());

        // Top buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jobTitleBtn);
        buttonPanel.add(divisionBtn);
        buttonPanel.add(hiresBtn);

        add(buttonPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        // Button actions
        jobTitleBtn.addActionListener(e -> loadJobTitleReport());

        // You’ll add these later:
        // divisionBtn.addActionListener(...)
        // hiresBtn.addActionListener(...)
    }

    void loadJobTitleReport() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Job Title");
        model.addColumn("Total Pay");

        // TEMP data (replace with DB later)
        model.addRow(new Object[]{"Manager", 250000});
        model.addRow(new Object[]{"Developer", 400000});

        JTable table = new JTable(model);

        tablePanel.removeAll();
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}
