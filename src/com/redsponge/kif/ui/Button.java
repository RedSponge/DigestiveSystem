package com.redsponge.kif.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button extends Rectangle {

    private String text;
    private int offsetX, offsetY;
    private int fontSize;
    private boolean selected;
    public Button(String text, int x, int y, int width, int height, int offsetX, int offsetY, int fontSize) {
        super(x, y, width, height);
        this.text = text;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.fontSize = fontSize;
        selected = false;
    }

    public void tick() {

    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(10));
        g.drawRect(x, y, width, height);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.drawString(text, x + offsetX, y + offsetY);
    }

}
