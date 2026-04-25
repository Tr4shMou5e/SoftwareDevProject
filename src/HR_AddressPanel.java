import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HR_AddressPanel extends JPanel {

    JTextField streetField = new JTextField(10);
    JTextField cityField = new JTextField(10);
    JTextField stateField = new JTextField(5);
    JTextField zipField = new JTextField(6);

    JButton addBtn = new JButton("Add Address");

    JPanel tablePanel = new JPanel(new BorderLayout());
    DefaultTableModel model;

    public HR_AddressPanel() {
        setLayout(new BorderLayout());

        
        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Street:"));
        inputPanel.add(streetField);

        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);

        inputPanel.add(new JLabel("State:"));
        inputPanel.add(stateField);

        inputPanel.add(new JLabel("Zip:"));
        inputPanel.add(zipField);

        inputPanel.add(addBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        
        model = new DefaultTableModel(
            new String[]{"ID", "Street", "City", "State", "Zip"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(25);

        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        
        loadAddresses();

        
        addBtn.addActionListener(e -> addAddress());
    }

    
    private void loadAddresses() {
        model.setRowCount(0);

        ArrayList<String[]> list = AddressService.getAllAddresses();

        for (String[] row : list) {
            model.addRow(row);
        }
    }

    
    private void addAddress() {
        String street = streetField.getText().trim();
        String city = cityField.getText().trim();
        String state = stateField.getText().trim();
        String zip = zipField.getText().trim();

        if (street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        boolean success = AddressService.addAddress(street, city, state, zip);

        if (success) {
            JOptionPane.showMessageDialog(this, "Address added!");

            streetField.setText("");
            cityField.setText("");
            stateField.setText("");
            zipField.setText("");

            loadAddresses(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error adding address.");
        }
    }
}

    



