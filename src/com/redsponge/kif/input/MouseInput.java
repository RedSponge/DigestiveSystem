package com.redsponge.kif.input;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    public int x, y;
    private boolean[] clicked = new boolean[10];
    private boolean[] wasClicked = new boolean[10];
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked[e.getButton()] = true;
        System.out.println("CLICK");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked[e.getButton()] = false;
        System.out.println("RELEASE");
    }

    public void tick() {
        for(int i = 0; i < 10; i++) {
            wasClicked[i] = clicked[i];
        }
    }

    public boolean wasClicked(int button) {
        return clicked[button] && !wasClicked[button];
    }

    public boolean isClicked(int button) {
        return clicked[button];
    }

    public Rectangle asRectangle() {
        return new Rectangle(x, y, 1, 1);
    }
}
