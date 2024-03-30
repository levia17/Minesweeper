
package game;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell {
    private JPanel panel;
    private boolean isBomb;
    private int bombsAround;
    private JLabel bombCountLabel;
    private String message;
    private int i;
    private int j;
    private boolean reveal;
    private MouseHandleClick mouseHandleClick;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        this.reveal = false;
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.isBomb = false;
        this.bombsAround = 0;
        this.bombCountLabel = new JLabel("");
        this.panel.add(bombCountLabel);
        this.message = message;
        this.mouseHandleClick = new MouseHandleClick(this);

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
        this.panel.setBackground(Color.GREEN);
    }

    public void reveal() {
        if (isBomb) {
            // Show bomb visually and handle game over condition
            bombCountLabel.setText("B");
            this.panel.setBackground(Color.RED);
        } else {
            // Show number of bombs around and handle cell reveal logic
            if (bombsAround > 0) {
                bombCountLabel.setText(String.valueOf(bombsAround));
            }
            this.panel.setBackground(Color.GRAY);
        }
    }
}