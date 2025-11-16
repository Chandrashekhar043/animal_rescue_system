import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddAnimal extends JFrame {

    JTextField name, species, age, rescue_date, status;

    public AddAnimal() {
        UIUtils.applyModernTheme();
        setTitle("Add Animal");
        setSize(460, 380);
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
        g.gridx = 0; g.gridy = 1; form.add(new JLabel("Species:"), g);
        g.gridx = 1; species = new JTextField(20); form.add(species, g);
        g.gridx = 0; g.gridy = 2; form.add(new JLabel("Age:"), g);
        g.gridx = 1; age = new JTextField(20); form.add(age, g);
        g.gridx = 0; g.gridy = 3; form.add(new JLabel("Rescue Date:"), g);
        g.gridx = 1; rescue_date = new JTextField(20); form.add(rescue_date, g);
        g.gridx = 0; g.gridy = 4; form.add(new JLabel("Status:"), g);
        g.gridx = 1; status = new JTextField(20); form.add(status, g);

        g.gridx = 0; g.gridy = 5; g.gridwidth = 2; g.anchor = GridBagConstraints.CENTER;
        UIUtils.RoundedButton addBtn = new UIUtils.RoundedButton("Add");
        form.add(addBtn, g);

        JPanel card = UIUtils.createCard("Add Animal", form);
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
                String sql = "INSERT INTO animals(name, species, age, rescue_date, status) VALUES(?,?,?,?,?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, name.getText());
                    pst.setString(2, species.getText());
                    pst.setInt(3, Integer.parseInt(age.getText()));
                    pst.setString(4, rescue_date.getText());
                    pst.setString(5, status.getText());
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Animal Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
