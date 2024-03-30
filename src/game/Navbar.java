package game;

import java.awt.*;
import javax.swing.*;

public class Navbar {

    public static void create(JFrame frame) {
        frame.setLayout(new BorderLayout()); // Set the main layout to BorderLayout

        // Create navbar panel with FlowLayout (you can customize it as needed)
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navbar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add buttons or other components to navbar
        JButton newGameButton = new JButton("Restart");
        navbar.add(newGameButton);
        // ...add more buttons or controls as needed

        frame.add(navbar, BorderLayout.PAGE_START); // Add navbar to the top of the frame
    }
}