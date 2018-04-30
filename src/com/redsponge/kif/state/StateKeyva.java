package com.redsponge.kif.state;

import com.redsponge.kif.Game;
import com.redsponge.kif.gfx.Texture;
import com.redsponge.kif.keyva.FoodChunk;
import com.redsponge.kif.keyva.FoodChunk.BreakState;
import com.redsponge.kif.mouth.FoodItem.FoodType;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StateKeyva extends State {

    private FoodType type;
    private List<FoodChunk> chunks;
    private Texture background;

    public KeyvaTool selectedTool;
    private boolean breakingFood;
    private int foodY;
    public enum KeyvaTool {
        PH(Color.YELLOW),
        DEPHROTAZ(Color.BLUE);

        private Color color;
        KeyvaTool(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    private Rectangle phListener, dephroListener;

    public StateKeyva(FoodType type) {
        this.type = type;
        this.chunks = new ArrayList<>();
        this.background = new Texture("/img/keyva_png.png");
        phListener = new Rectangle(0, 440, 240, 60);
        dephroListener = new Rectangle(240, 440, 250, 60);
        selectedTool = KeyvaTool.PH;
        foodY = 0;
    }

    private void generateFoodChunks() {
        float x = FoodChunk.foodBounds.x + FoodChunk.foodBounds.width / 2 - BreakState.BIG_CHUNK.getSize() / 2;
        float y = FoodChunk.foodBounds.y + FoodChunk.foodBounds.height / 2 - BreakState.BIG_CHUNK.getSize() / 2;
        Random r = new Random();
        for(int i = 0; i < type.getNumFoodChunks(); i++) {
            float vx = 2 - r.nextFloat() * 4;
            float vy = 2 - r.nextFloat() * 4;
            chunks.add(new FoodChunk(this, type, BreakState.BIG_CHUNK, x, y, vx, vy));
        }
    }

    @Override
    public void tick() {
        if(!breakingFood) {
            if(foodY < Game.HEIGHT / 2 - type.getHeight() / 2) {
                foodY+=2;
            }
            else if(Game.instance.mouseInput.wasClicked(1)) {
                breakingFood = true;
                generateFoodChunks();
            }
            return;
        }
        List<FoodChunk> toKill = new ArrayList<>();
        for(FoodChunk c : chunks) {
            c.tick();
            if(c.isDead()) toKill.add(c);
        }
        for(FoodChunk c : toKill) {
            chunks.remove(c);
            if(c.generateSplits()) {
                chunks.add(new FoodChunk(this, type, c.getSize().getSmaller(), c.getX(), c.getY(), c.getVx(), c.getVy()));
                chunks.add(new FoodChunk(this, type, c.getSize().getSmaller(), c.getX(), c.getY(), -c.getVx(), -c.getVy()));
            }
        }
        if(chunks.isEmpty()) {
            Game.instance.stateManager.registerState(new StateMei(type));
            Game.instance.stateManager.setCurrentState("mei");
        }
        if(Game.instance.mouseInput.wasClicked(1)) {
            Rectangle mouse = Game.instance.mouseInput.asRectangle();
            if(mouse.intersects(phListener))
                selectedTool = KeyvaTool.PH;
            else if(mouse.intersects(dephroListener))
                selectedTool = KeyvaTool.DEPHROTAZ;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background.getImg(), 0, 0, null);
        if(!breakingFood) {
            if(foodY >= Game.HEIGHT / 2 - type.getHeight() / 2) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Comic sans ms", Font.BOLD, 32));
                g.drawString("Press anywhere to start", 50, 50);
                g.drawString("Breaking the food", 50 + 25, 50 + 32 + 5);
            }
            g.drawImage(type.getTexture().getImg(), Game.WIDTH / 2 - type.getWidth() / 2, foodY, type.getWidth(), type.getHeight(), null);
            return;
        }
        for(FoodChunk c : chunks) {
            c.render(g);
        }
//        g.draw(phListener);
//        g.draw(dephroListener);
        int mouseDotSize = 10;
        g.setColor(selectedTool.getColor());
        g.fillOval(Game.instance.mouseInput.x - mouseDotSize / 2, Game.instance.mouseInput.y - mouseDotSize / 2, mouseDotSize, mouseDotSize);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public String getName() {
        return "keyva";
    }

    public List<FoodChunk> getChunks() {
        return chunks;
    }

    public KeyvaTool getSelectedTool() {
        return selectedTool;
    }
}
