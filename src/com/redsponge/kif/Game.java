package com.redsponge.kif;

import com.redsponge.kif.input.KeyInput;
import com.redsponge.kif.input.MouseInput;
import com.redsponge.kif.state.StateManager;
import com.redsponge.redutils.GraphicsApp;

import java.awt.Graphics2D;

public class Game extends GraphicsApp {

    public StateManager stateManager;
    public MouseInput mouseInput;
    public KeyInput keyInput;
    public static final int WIDTH = 500, HEIGHT = 500;
    public static Game instance;

    public Game() {
        super("Thing", WIDTH, HEIGHT, 60, 60);
        instance = this;
        start();
    }

    @Override
    public void init() {
        mouseInput = new MouseInput();
        keyInput = new KeyInput();
        stateManager = new StateManager();
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
        display.getFrame().addKeyListener(keyInput);
    }

    @Override
    public void tick() {
        stateManager.getCurrentState().tick();
        mouseInput.tick();
        keyInput.tick();
    }

    @Override
    public void render() {

        stateManager.getCurrentState().render((Graphics2D) g);

    }
}
