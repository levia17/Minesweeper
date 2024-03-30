package game;

import javax.swing.JFrame;

public class Window extends JFrame {
    public void main() {
        this.setTitle("Minesweeper");

        Navbar.create(this);
        Mode.get("medium", this);

        // Center the window and make it visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
