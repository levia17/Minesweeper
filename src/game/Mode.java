package game;

import javax.swing.*;

public class Mode {

    public static void get(String mode, JFrame frame) {
        switch (mode) {
            case "easy":
                Grid.easy(frame, 8, 8);
                break;
            case "medium":
                Grid.medium(frame, 16, 16);
                break;
            case "hard":
                Grid.hard(frame, 16, 32);
                break;

            default:
                break;
        }
    }
}