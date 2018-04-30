package com.redsponge.kif.state;

import com.redsponge.kif.Game;
import com.redsponge.kif.gfx.Texture;
import com.redsponge.kif.mouth.FoodItem.FoodType;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Random;

public class StateVeshet extends State{

    private FoodType type;
    private int currentToPress;
    private int foodY;
    private int displayedFoodY;
    private Random random;
    private static final int[] acceptedToPresses = {37, 38, 39, 40};

    private Texture background, foreground;

    public enum Arrow {
        UP(new Texture("/img/arrow_up.png"),38),
        RIGHT(new Texture("/img/arrow_right.png"),39),
        DOWN(new Texture("/img/arrow_down.png"),40),
        LEFT(new Texture("/img/arrow_left.png"),37);

        private Texture texture;
        private int id;
        private static HashMap<Integer, Arrow> arrows;
        Arrow(Texture texture, int id) {
            this.texture = texture;
            this.id = id;
            register();
        }
        private void register() {
            if(arrows == null) arrows = new HashMap<>();
            arrows.put(id, this);
        }

        public Texture getTexture() {
            return texture;
        }

        public static Arrow fromKeyCode(int keyCode) {
            return arrows.get(keyCode);
        }
    }

    public StateVeshet(FoodType type) {
        this.type = type;
        this.currentToPress = 37;
        this.foodY = 0;
        this.displayedFoodY = 0;
        this.random = new Random();
        this.background = new Texture("/img/veshet_back.png");
        this.foreground = new Texture("/img/veshet_front.png");
    }

    @Override
    public void tick() {
        if(Game.instance.keyInput.wasPressed(currentToPress)) {
            currentToPress = acceptedToPresses[random.nextInt(4)];
            this.foodY += 25;
        }
        if(this.displayedFoodY < this.foodY) {
            this.displayedFoodY++;
        }
        if(displayedFoodY > Game.WIDTH - 50) {
            Game.instance.stateManager.registerState(new StateKeyva(type));
            Game.instance.stateManager.setCurrentState("keyva");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background.getImg(), 0, 0, null);
        g.drawImage(type.getTexture().getImg(), Game.WIDTH / 2 - type.getWidth() / 2, displayedFoodY, type.getWidth(), type.getHeight(), null);
        g.drawImage(foreground.getImg(), 0, 0, null);
        g.drawImage(Arrow.fromKeyCode(currentToPress).getTexture().getImg(), 50, Game.HEIGHT / 2 - 50, 100, 100,  null);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public String getName() {
        return "veshet";
    }
}
