import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddMedicalRecord extends JFrame {

    JTextField animal_id, illness, treatment, doctor_name, checkup_date;

    public AddMedicalRecord() {
        UIUtils.applyModernTheme();
        setTitle("Add Medical Record");
        setSize(500, 420);
        setLocationRelativeTo(null);

        UIUtils.GradientPanel root = new UIUtils.GradientPanel();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Animal ID:"), g);
        g.gridx = 1; animal_id = new JTextField(20); form.add(animal_id, g);
        g.gridx = 0; g.gridy = 1; form.add(new JLabel("Illness:"), g);
        g.gridx = 1; illness = new JTextField(20); form.add(illness, g);
        g.gridx = 0; g.gridy = 2; form.add(new JLabel("Treatment:"), g);
        g.gridx = 1; treatment = new JTextField(20); form.add(treatment, g);
        g.gridx = 0; g.gridy = 3; form.add(new JLabel("Doctor Name:"), g);
        g.gridx = 1; doctor_name = new JTextField(20); form.add(doctor_name, g);
        g.gridx = 0; g.gridy = 4; form.add(new JLabel("Checkup Date:"), g);
        g.gridx = 1; checkup_date = new JTextField(20); form.add(checkup_date, g);

        g.gridx = 0; g.gridy = 5; g.gridwidth = 2; g.anchor = GridBagConstraints.CENTER;
        UIUtils.RoundedButton addBtn = new UIUtils.RoundedButton("Add Record");
        form.add(addBtn, g);

        JPanel card = UIUtils.createCard("Add Medical Record", form);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.NONE;
        root.add(card, gbc);

        addBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.connect();
                if (con == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed");
                    return;
                }
                String sql = "INSERT INTO medical_records(animal_id, illness, treatment, doctor_name, checkup_date) VALUES(?,?,?,?,?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, Integer.parseInt(animal_id.getText()));
                    pst.setString(2, illness.getText());
                    pst.setString(3, treatment.getText());
                    pst.setString(4, doctor_name.getText());
                    pst.setString(5, checkup_date.getText());
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Medical Record Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
