package game;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Grid {

    private static void general(JFrame frame, int ROW_SIZE, int COL_SIZE, int width, int height, int maxBomb) {

        // Create grid panel with GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(ROW_SIZE, COL_SIZE));

        Cell[][] cells = new Cell[ROW_SIZE][COL_SIZE];

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                cells[i][j] = new Cell(i, j);
                gridPanel.add(cells[i][j].getPanel());
            }
        }

        // Place bombs
        int placedBombs = 0;
        Random rand = new Random();
        while (placedBombs < maxBomb) {
            int row = rand.nextInt(ROW_SIZE);
            int col = rand.nextInt(COL_SIZE);
            if (!cells[row][col].isBomb()) {
                cells[row][col].setBomb(true);
                placedBombs++;
            }
        }

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                int count = 0;
                for (int iOffset = -1; iOffset <= 1; iOffset++) {
                    for (int jOffset = -1; jOffset <= 1; jOffset++) {
                        if (iOffset == 0 && jOffset == 0)
                            continue; // Skip the cell itself
                        int neighbourRow = i + iOffset;
                        int neighbourCol = j + jOffset;
                        // Check if the indices are within the grid boundaries
                        if (neighbourRow >= 0 && neighbourCol >= 0 && neighbourRow < ROW_SIZE
                                && neighbourCol < COL_SIZE) {
                            if (cells[neighbourRow][neighbourCol].isBomb()) {
                                count++;
                            }
                        }
                    }
                }
                cells[i][j].setBombsAround(count);
            }
        }

        // Add gridPanel to the center of the frame
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setSize(width, height);
    }

    public static void easy(JFrame frame, int ROW_SIZE, int COL_SIZE) {
        general(frame, ROW_SIZE, COL_SIZE, 400, 450, 10);
    }

    public static void medium(JFrame frame, int ROW_SIZE, int COL_SIZE) {
        general(frame, ROW_SIZE, COL_SIZE, 600, 650, 40);
    }

    public static void hard(JFrame frame, int ROW_SIZE, int COL_SIZE) {
        general(frame, ROW_SIZE, COL_SIZE, 1080, 650, 99);
    }
}
