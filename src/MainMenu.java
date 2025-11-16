import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        UIUtils.applyModernTheme();
        setTitle("Animal Rescue System");
        setSize(520, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UIUtils.GradientPanel root = new UIUtils.GradientPanel();
        root.setLayout(new BorderLayout());
        setContentPane(root);

        JPanel grid = new JPanel(new GridLayout(3, 2, 12, 12));
        grid.setOpaque(false);

        UIUtils.RoundedButton addAnimal = new UIUtils.RoundedButton("Add Animal");
        UIUtils.RoundedButton viewAnimals = new UIUtils.RoundedButton("View Animals");
        UIUtils.RoundedButton addVolunteer = new UIUtils.RoundedButton("Add Volunteer");
        UIUtils.RoundedButton viewVolunteers = new UIUtils.RoundedButton("View Volunteers");
        UIUtils.RoundedButton addMedical = new UIUtils.RoundedButton("Add Medical Record");
        UIUtils.RoundedButton viewMedical = new UIUtils.RoundedButton("View Medical Records");

        grid.add(addAnimal);
        grid.add(viewAnimals);
        grid.add(addVolunteer);
        grid.add(viewVolunteers);
        grid.add(addMedical);
        grid.add(viewMedical);

        JPanel card = UIUtils.createCard("Main Menu", grid);
        root.add(UIUtils.createHeader("Animal Rescue System"), BorderLayout.NORTH);
        root.add(card, BorderLayout.CENTER);

        addAnimal.addActionListener(e -> new AddAnimal());
        viewAnimals.addActionListener(e -> new ViewAnimals());
        addVolunteer.addActionListener(e -> new AddVolunteer());
        viewVolunteers.addActionListener(e -> new ViewVolunteers());
        addMedical.addActionListener(e -> new AddMedicalRecord());
        viewMedical.addActionListener(e -> new ViewMedicalRecords());

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
