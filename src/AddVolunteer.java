import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddVolunteer extends JFrame {

    JTextField name, phone, role;

    public AddVolunteer() {
        UIUtils.applyModernTheme();
        setTitle("Add Volunteer");
        setSize(460, 320);
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
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Name:"), g);
        g.gridx = 1; name = new JTextField(20); form.add(name, g);
        g.gridx = 0; g.gridy = 1; form.add(new JLabel("Phone:"), g);
        g.gridx = 1; phone = new JTextField(20); form.add(phone, g);
        g.gridx = 0; g.gridy = 2; form.add(new JLabel("Role:"), g);
        g.gridx = 1; role = new JTextField(20); form.add(role, g);

        g.gridx = 0; g.gridy = 3; g.gridwidth = 2; g.anchor = GridBagConstraints.CENTER;
        UIUtils.RoundedButton addBtn = new UIUtils.RoundedButton("Add Volunteer");
        form.add(addBtn, g);

        JPanel card = UIUtils.createCard("Add Volunteer", form);
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
                String sql = "INSERT INTO volunteers(name, phone, role) VALUES(?,?,?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, name.getText());
                    pst.setString(2, phone.getText());
                    pst.setString(3, role.getText());
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Volunteer Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
