import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAnimals extends JFrame {

    public ViewAnimals() {
        UIUtils.applyModernTheme();
        setTitle("Animals List");
        setSize(700, 450);
        setLocationRelativeTo(null);

        UIUtils.GradientPanel root = new UIUtils.GradientPanel();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        UIUtils.styleTable(table);
        UIUtils.styleTableHeader(table);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Species");
        model.addColumn("Age");
        model.addColumn("Rescue Date");
        model.addColumn("Status");

        JScrollPane scroll = new JScrollPane(table);
        UIUtils.decorateScrollPane(scroll);
        JPanel card = UIUtils.createCard("Animals", scroll);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.BOTH;
        root.add(card, gbc);

        try {
            Connection con = DBConnection.connect();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed");
                return;
            }
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM animals")) {

                while (rs.next()) {
                    model.addRow(new Object[] {
                            rs.getInt("animal_id"),
                            rs.getString("name"),
                            rs.getString("species"),
                            rs.getInt("age"),
                            rs.getString("rescue_date"),
                            rs.getString("status")
                    });
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setVisible(true);
    }
}
