import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EMP_ReportsPanel extends JPanel {

    JButton viewPayBtn = new JButton("View Pay History");

    int empID;

    JPanel tablePanel = new JPanel(new BorderLayout());

    public EMP_ReportsPanel(int empID) {
        this.empID = empID;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(viewPayBtn);

        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        viewPayBtn.addActionListener(e -> loadPayHistory(empID));

        
        
    }

    void loadPayHistory(int empID) {

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("EmpID");
        model.addColumn("Pay Date");
        model.addColumn("Earnings");

        ArrayList<PayRollInfo> list =
            PayStatement.getPayStatement(empID);

        for (PayRollInfo p : list) {
            model.addRow(new Object[]{
                p.getEmpID(),
                p.getPayDate(),
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

}
