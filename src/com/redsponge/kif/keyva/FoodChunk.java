package com.redsponge.kif.keyva;

import com.redsponge.kif.Game;
import com.redsponge.kif.mouth.FoodItem.FoodType;
import com.redsponge.kif.state.StateKeyva;
import com.redsponge.kif.state.StateKeyva.KeyvaTool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class FoodChunk {

    private FoodType type;
    private float vx, vy, x, y;
    private int width, height;
    private BreakState size;
    private boolean dead;
    private boolean generateSplits;
    public static final Rectangle foodBounds = new Rectangle(30, 50, 475 - 30, 400 - 50);
    public enum BreakState {
        BIG_CHUNK(Color.GREEN, 75, "MEDIUM_CHUNK"),
        MEDIUM_CHUNK(Color.ORANGE, 50, "SMALL_CHUNK"),
        SMALL_CHUNK(Color.BLUE, 25, null);

        private Color color;
        private int size;
        private String  smaller;
        BreakState(Color color, int size, String smaller) {
            this.color = color;
            this.size = size;
            this.smaller = smaller;
        }

        public Color getColor() {
            return color;
        }

        public int getSize() {
            return size;
        }

        public BreakState getSmaller() {
            return valueOf(smaller);
        }
    }

    private StateKeyva keyva;

    public FoodChunk(StateKeyva keyva, FoodType type, BreakState size, float x, float y, float vx, float vy) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.width = size.getSize();
        this.height = size.getSize();
        this.size = size;
        this.keyva = keyva;
    }

    public void tick() {
        this.x += vx;
        this.y += vy;
        if(this.x + width > foodBounds.x + foodBounds.width || this.x < foodBounds.x) {
            this.x -= vx;
            this.vx *= -1;
        }
        if(this.y + height > foodBounds.y + foodBounds.height || this.y < foodBounds.y) {
            this.y -= vy;
            this.vy *= -1;
        }
        if(Game.instance.mouseInput.asRectangle().intersects(new Rectangle((int) x, (int) y, width, height))) {
            if(keyva.selectedTool == KeyvaTool.PH && size != BreakState.SMALL_CHUNK) {
                if (Game.instance.mouseInput.wasClicked(1)) {
                    split();
                }
            } else if(keyva.selectedTool == KeyvaTool.DEPHROTAZ && size == BreakState.SMALL_CHUNK) {
                if(Game.instance.mouseInput.wasClicked(1))
                    kill();
            }
        }
    }

    public void render(Graphics2D g) {
        g.setColor(size.getColor());
        g.fillRect((int) x, (int) y, width, height);
    }

    public void split() {
        dead = true;
        generateSplits = true;
    }

    public void kill() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public BreakState getSize() {
        return size;
    }

    public boolean generateSplits() {
        return generateSplits;
    }
}
