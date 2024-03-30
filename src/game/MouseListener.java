package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class CellMouseListener implements MouseListener {
    private final int i;
    private final int j;

    public CellMouseListener(int i, int j, String message) {
        super();
        this.i = i;
        this.j = j;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Cell " + i + " " + j + " clicked!");
        System.out.println(e);
        // Insert your own game logic here
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement if needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement if needed
    }
}
