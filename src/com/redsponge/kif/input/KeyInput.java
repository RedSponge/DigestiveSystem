package com.redsponge.kif.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private boolean[] keys = new boolean[1024];
    private boolean[] pressed = new boolean[1024];
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println("Pressed key " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void tick() {
        for(int i = 0; i < 1024; i++) {
            pressed[i] = keys[i];
        }
    }

    public boolean isPressed(int key) {
        return keys[key];
    }

    public boolean wasPressed(int key) {
        return keys[key] && !pressed[key];
    }

}
