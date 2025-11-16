import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddAdoption extends JFrame {

    JTextField animal_id, adopter_name, adoption_date;

    public AddAdoption() {
        UIUtils.applyModernTheme();
        setTitle("Add Adoption");
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
        g.gridx = 0; g.gridy = 0; form.add(new JLabel("Animal ID:"), g);
        g.gridx = 1; animal_id = new JTextField(20); form.add(animal_id, g);
        g.gridx = 0; g.gridy = 1; form.add(new JLabel("Adopter Name:"), g);
        g.gridx = 1; adopter_name = new JTextField(20); form.add(adopter_name, g);
        g.gridx = 0; g.gridy = 2; form.add(new JLabel("Adoption Date:"), g);
        g.gridx = 1; adoption_date = new JTextField(20); form.add(adoption_date, g);

        g.gridx = 0; g.gridy = 3; g.gridwidth = 2; g.anchor = GridBagConstraints.CENTER;
        UIUtils.RoundedButton addBtn = new UIUtils.RoundedButton("Add Adoption");
        form.add(addBtn, g);

        JPanel card = UIUtils.createCard("Add Adoption", form);
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
                String sql = "INSERT INTO adoptions(animal_id, adopter_name, adoption_date) VALUES(?,?,?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, Integer.parseInt(animal_id.getText()));
                    pst.setString(2, adopter_name.getText());
                    pst.setString(3, adoption_date.getText());
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Adoption Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
