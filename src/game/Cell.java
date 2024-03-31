
package game;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell {
    private JPanel panel;
    private boolean isBomb;
    private int bombsAround;
    private JLabel bombCountLabel;
    private int i;
    private int j;
    private boolean reveal;
    private MouseHandleClick mouseHandleClick;
    private int ROW_SIZE;
    private int COL_SIZE;
    private Cell[][] grid;

    public Cell(int i, int j, int ROW_SIZE, int COL_SIZE, Cell[][] grid) {
        this.grid = grid;
        this.i = i;
        this.j = j;
        this.ROW_SIZE = ROW_SIZE;
        this.COL_SIZE = COL_SIZE;
        this.reveal = false;
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.isBomb = false;
        this.bombsAround = 0;
        this.bombCountLabel = new JLabel("");
        this.panel.add(bombCountLabel);
        this.mouseHandleClick = new MouseHandleClick(this);
        this.getPanel().setPreferredSize(new Dimension(30, 30));

        // Add the MouseListener here
        this.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseHandleClick.handleClick(e);
            }
        });
    }

    public boolean isBomb() {
        return this.isBomb;
    }

    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public void setBombsAround(int count) {
        this.bombsAround = count;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void mark() {
        if (!this.reveal) {
            this.panel.setBackground(Color.GREEN);
        }
    }

    // public void reveal() {
    // if (isBomb) {
    // // Show bomb visually and handle game over condition
    // bombCountLabel.setText("B");
    // this.panel.setBackground(Color.RED);
    // this.reveal = true;
    // } else {
    // // Show number of bombs around and handle cell reveal logic
    // if (bombsAround > 0) {
    // bombCountLabel.setText(String.valueOf(bombsAround));
    // this.reveal = true;

    // }

    // // System.out.println("Hello!");
    // for (int iOffset = -1; iOffset <= 1; iOffset++) {
    // for (int jOffset = -1; jOffset <= 1; jOffset++) {
    // if (bombsAround == 0) {
    // if (iOffset == 0 && jOffset == 0)
    // continue; // Skip the cell itself
    // int neighbourRow = i + iOffset;
    // int neighbourCol = j + jOffset;

    // // Check if the indices are within the grid boundaries
    // if (neighbourRow >= 0 && neighbourCol >= 0 && neighbourRow < this.ROW_SIZE
    // && neighbourCol < this.COL_SIZE) {
    // Cell neighbor = this.grid[neighbourRow][neighbourCol];
    // if (!this.isBomb) {
    // neighbor.panel.setBackground(Color.GRAY);
    // }
    // }
    // }
    // }
    // }
    // }
    // this.panel.setBackground(Color.GRAY);
    // }

    public void reveal() {
        // If already revealed, skip to prevent infinite recursion
        if (this.reveal) {
            return;
        }

        // Reveal this cell
        this.reveal = true;

        if (isBomb) {
            // Show bomb visually and handle game over condition
            bombCountLabel.setText("B");
            this.panel.setBackground(Color.RED);

            SwingUtilities.invokeLater(() -> {
                // Display the popup on game over
                JOptionPane.showMessageDialog(panel, "Game Over! You hit a bomb!", "Game Over",
                        JOptionPane.ERROR_MESSAGE);
            });
        } else {
            // Update appearance for cells that are not bombs
            if (bombsAround > 0) {
                bombCountLabel.setText(String.valueOf(bombsAround));
                this.panel.setBackground(Color.GRAY);
            } else {
                // No bombs around this cell, so reveal this cell and its neighbors
                bombCountLabel.setText("");
                this.panel.setBackground(Color.GRAY);
                revealNeighboringCells();
            }
        }
    }

    private void revealNeighboringCells() {
        for (int iOffset = -1; iOffset <= 1; iOffset++) {
            for (int jOffset = -1; jOffset <= 1; jOffset++) {
                if (iOffset == 0 && jOffset == 0)
                    continue; // Skip the cell itself
                int neighbourRow = i + iOffset;
                int neighbourCol = j + jOffset;
                // Check if the indices are within the grid boundaries
                if (neighbourRow >= 0 && neighbourCol >= 0 && neighbourRow < this.ROW_SIZE
                        && neighbourCol < this.COL_SIZE) {
                    // Get the neighboring cell
                    Cell neighbor = this.grid[neighbourRow][neighbourCol];
                    // Recursively reveal the neighbor if it has not been revealed yet
                    if (!neighbor.reveal) {
                        neighbor.reveal();
                    }
                }
            }
        }
    }

}
