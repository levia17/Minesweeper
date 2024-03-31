package game;

import javax.swing.*;

public class Mode {

    public static void get(String mode, JFrame frame) {
        switch (mode) {
            case "easy":
                Grid gridEasy = new Grid(8, 8);
                gridEasy.easy(gridEasy.gridPanel, frame);
                break;
            case "medium":
                Grid gridMedium = new Grid(16, 16);
                gridMedium.medium(gridMedium.gridPanel, frame);
                break;
            case "hard":
                Grid gridHard = new Grid(16, 32);
                gridHard.hard(gridHard.gridPanel, frame);
                break;

            default:
                break;
        }
    }
}