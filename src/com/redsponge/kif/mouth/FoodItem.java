package com.redsponge.kif.mouth;

import com.redsponge.kif.Game;
import com.redsponge.kif.gfx.Texture;
import com.redsponge.kif.state.StateVeshet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FoodItem {

    public enum FoodType {
        HAMBURGER(new Texture("/img/burger.png"), 200, 200, 10),
        CANDY(new Texture("/img/candy.png"), 120, 200, 4),
        BROCCOLI(new Texture("/img/broccoli.png"), 150, 200, 6);


        private Texture texture;
        private int width, height;
        private int numFoodChunks;
        FoodType(Texture texture, int width, int height, int numFoodChunks) {
            this.texture = texture;
            this.width = width;
            this.height = height;
            this.numFoodChunks = numFoodChunks;
        }

        public Texture getTexture() {
            return texture;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int getNumFoodChunks() {
            return numFoodChunks;
        }
    }


    private FoodType type;
    private boolean selected;
    private final int x,y, width, height;
    public FoodItem(FoodType type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick() {
        if(Game.instance.mouseInput.asRectangle().intersects(new Rectangle(x, y, width, height))) {
            selected = true;
        } else {
            selected = false;
        }
        if(selected && Game.instance.mouseInput.wasClicked(1)) {
            Game.instance.stateManager.registerState(new StateVeshet(type));
            Game.instance.stateManager.setCurrentState("veshet");
        }
    }

    public void render(Graphics2D g) {
        if(selected)
            g.drawImage(type.getTexture().getImg(), x - 10, y - 10, width + 10, height + 10, null);
            //System.out.println("SELECTED");
        else
            g.drawImage(type.getTexture().getImg(), x, y, width, height, null);
    }

}
