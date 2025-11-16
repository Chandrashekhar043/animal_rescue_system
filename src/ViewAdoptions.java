import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAdoptions extends JFrame {

    public ViewAdoptions() {
        UIUtils.applyModernTheme();
        setTitle("Adoption Records");
        setSize(800, 450);
        setLocationRelativeTo(null);

        UIUtils.GradientPanel root = new UIUtils.GradientPanel();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        UIUtils.styleTable(table);
        UIUtils.styleTableHeader(table);

        model.addColumn("Adoption ID");
        model.addColumn("Animal ID");
        model.addColumn("Adopter Name");
        model.addColumn("Adoption Date");

        JScrollPane scroll = new JScrollPane(table);
        UIUtils.decorateScrollPane(scroll);
        JPanel card = UIUtils.createCard("Adoptions", scroll);
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
                 ResultSet rs = st.executeQuery("SELECT * FROM adoptions")) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("adoption_id"),
                        rs.getInt("animal_id"),
                        rs.getString("adopter_name"),
                        rs.getString("adoption_date")
                    });
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setVisible(true);
    }
}
