import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewMedicalRecords extends JFrame {

    public ViewMedicalRecords() {
        UIUtils.applyModernTheme();
        setTitle("Medical Records");
        setSize(800, 480);
        setLocationRelativeTo(null);

        UIUtils.GradientPanel root = new UIUtils.GradientPanel();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        UIUtils.styleTable(table);
        UIUtils.styleTableHeader(table);

        model.addColumn("Record ID");
        model.addColumn("Animal ID");
        model.addColumn("Illness");
        model.addColumn("Treatment");
        model.addColumn("Doctor");
        model.addColumn("Checkup Date");

        JScrollPane scroll = new JScrollPane(table);
        UIUtils.decorateScrollPane(scroll);
        JPanel card = UIUtils.createCard("Medical Records", scroll);
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
                 ResultSet rs = st.executeQuery("SELECT * FROM medical_records")) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("record_id"),
                        rs.getInt("animal_id"),
                        rs.getString("illness"),
                        rs.getString("treatment"),
                        rs.getString("doctor_name"),
                        rs.getString("checkup_date")
                    });
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setVisible(true);
    }
}
