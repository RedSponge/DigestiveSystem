package com.redsponge.kif.state;

import com.redsponge.kif.Game;
import com.redsponge.kif.mouth.FoodItem;
import com.redsponge.kif.mouth.FoodItem.FoodType;

import java.awt.Color;
import java.awt.Graphics2D;

public class StateMouth extends State {

    private FoodItem BURGER = new FoodItem(FoodType.HAMBURGER, 30, Game.WIDTH / 2 - 100, 200, 200);
    private FoodItem CANDY = new FoodItem(FoodType.CANDY, 220, Game.WIDTH / 2 - 100, 120, 200);
    private FoodItem BROCCOLI = new FoodItem(FoodType.BROCCOLI, 350, Game.WIDTH / 2 - 100, 150, 200);

    @Override
    public void tick() {
        CANDY.tick();
        BURGER.tick();
        BROCCOLI.tick();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        BROCCOLI.render(g);
        BURGER.render(g);
        CANDY.render(g);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public String getName() {
        return "mouth";
    }
}
