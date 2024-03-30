package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MouseHandleClick {
    private int clickCount;
    private Timer doubleClickTimer;
    private final int doubleClickDelay = 200; // Milliseconds for double-click interval
    private Cell cell;

    public MouseHandleClick(Cell cell) {
        this.clickCount = 0;
        this.cell = cell;

        // Timer for double-click
        doubleClickTimer = new Timer(doubleClickDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSingleClick(); // The time elapsed without a second click
                clickCount = 0;
                doubleClickTimer.stop();
            }
        });
    }

    public void handleClick(MouseEvent e) {
        clickCount++;
        if (clickCount == 1) {
            doubleClickTimer.start();
        } else if (clickCount == 2) {
            doubleClickTimer.stop();
            handleDoubleClick();
            clickCount = 0; // Reset click count after handling double-click
        }
    }

    private void handleSingleClick() {
        cell.reveal();
    }

    private void handleDoubleClick() {
        cell.mark();
    }
}