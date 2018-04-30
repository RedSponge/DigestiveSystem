package com.redsponge.kif.state;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class State {

    public abstract void tick();

    public abstract void render(Graphics2D g);

    public abstract void show();

    public abstract void hide();

    public abstract String getName();

}
