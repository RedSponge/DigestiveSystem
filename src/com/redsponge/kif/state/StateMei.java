package com.redsponge.kif.state;

import com.redsponge.kif.Game;
import com.redsponge.kif.gfx.Texture;
import com.redsponge.kif.mouth.FoodItem.FoodType;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

public class StateMei extends State {

    private FoodType type;

    public enum Material {
        FAT(new Texture("/img/fat.png"),new Texture("/img/fat_broken.npg")),
        HELBON(new Texture("/img/helbon.png"), new Texture("/img/helbon_broken.png")),
        AMILAN(new Texture("/img/amilan.png"), new Texture("/img/amilan_broken.png"));

        private Texture regular, broken;

        Material(Texture regular, Texture broken) {
            this.regular = regular;
            this.broken = broken;
        }
    }

    private List<Material> materialsToSort;
    private Texture backgroundEnzimes = new Texture("/img/mei_background_1.png");
    private Texture backgroundTypes = new Texture("/img/mei_background_2.png");

    public StateMei(FoodType type) {
        this.type = type;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic sans ms", Font.BOLD, 40));
        g.drawString("YOU WIN!", 100, Game.HEIGHT / 2 - 20);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public String getName() {
        return "mei";
    }
}
