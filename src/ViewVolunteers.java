import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewVolunteers extends JFrame {

    public ViewVolunteers() {
        UIUtils.applyModernTheme();
        setTitle("Volunteer List");
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
        model.addColumn("Phone");
        model.addColumn("Role");

        JScrollPane scroll = new JScrollPane(table);
        UIUtils.decorateScrollPane(scroll);
        JPanel card = UIUtils.createCard("Volunteers", scroll);
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
                 ResultSet rs = st.executeQuery("SELECT * FROM volunteers")) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("volunteer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("role")
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setVisible(true);
    }
}
