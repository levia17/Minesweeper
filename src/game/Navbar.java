package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Navbar {

    public static void create(JFrame frame) {
        frame.setLayout(new BorderLayout()); // Set the main layout to BorderLayout

        // Create navbar panel with FlowLayout (you can customize it as needed)
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navbar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add buttons or other components to navbar
        JButton restartButton = new JButton("Restart");
        navbar.add(restartButton);

        JButton chooseLevelButton = new JButton("Levels");
        chooseLevelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Present an option pane when the button is clicked
                Object[] options = { "easy", "medium", "hard" };
                String level = (String) JOptionPane.showInputDialog(
                        frame,
                        "Choose the difficulty level:",
                        "Level Selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        "easy");

                // Logic to handle level selection
                if (level != null && level.length() > 0) {
                    Mode.get(level, frame);
                }
            }
        });
        navbar.add(chooseLevelButton);
        // ...add more buttons or controls as needed

        frame.add(navbar, BorderLayout.PAGE_START); // Add navbar to the top of the frame
    }
}