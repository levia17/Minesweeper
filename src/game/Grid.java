package game;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Grid {
    JPanel gridPanel;
    int ROW_SIZE;
    int COL_SIZE;

    public Grid(int ROW_SIZE, int COL_SIZE) {
        this.gridPanel = new JPanel(new GridLayout(ROW_SIZE, COL_SIZE));
        this.ROW_SIZE = ROW_SIZE;
        this.COL_SIZE = COL_SIZE;
    }

    private static void general(JPanel panel, JFrame frame, int ROW_SIZE, int COL_SIZE, int width, int height,
            int maxBomb) {

        panel.removeAll();
        // panel.setLayout(new GridLayout(ROW_SIZE, COL_SIZE));
        // Create grid panel with GridLayout
        Cell[][] cells = new Cell[ROW_SIZE][COL_SIZE];

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                cells[i][j] = new Cell(i, j, ROW_SIZE, COL_SIZE, cells);
                panel.add(cells[i][j].getPanel());
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
        frame.add(panel, BorderLayout.CENTER);

        // Revalidate and repaint the panel after removing components and repopulating
        // it
        panel.revalidate();
        panel.repaint();
        frame.revalidate();
        frame.repaint();

        // Set the frame size as needed, or consider using pack() for automatic sizing
        frame.setSize(width, height);
        frame.pack();

        // Update the frame content after making changes
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void easy(JPanel panel, JFrame frame) {
        general(panel, frame, this.ROW_SIZE, this.COL_SIZE, 400, 450, 10);
    }

    public void medium(JPanel panel, JFrame frame) {
        general(panel, frame, this.ROW_SIZE, this.COL_SIZE, 600, 650, 40);
    }

    public void hard(JPanel panel, JFrame frame) {
        general(panel, frame, this.ROW_SIZE, this.COL_SIZE, 1080, 650, 99);
    }

    public JPanel getPanel() {
        return this.gridPanel;
    }
}
