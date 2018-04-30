package com.redsponge.kif.state;

import com.redsponge.kif.Game;
import com.redsponge.kif.ui.Button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class StateMenu extends State {

    private Button startButton;

    public StateMenu() {
        startButton = new Button("Start", 100, 100, 100, 50, 14, 34, 32);
    }

    @Override
    public void tick() {
        if(Game.instance.mouseInput.isClicked(1)) {
            Game.instance.stateManager.setCurrentState("mouth");
        }
    }

    @Override
    public void render(Graphics2D g) {
        //startButton.render(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic sans MS", Font.BOLD, 50));
        g.drawString("Start", Game.WIDTH / 2-50, Game.HEIGHT / 2);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public String getName() {
        return "menu";
    }
}
