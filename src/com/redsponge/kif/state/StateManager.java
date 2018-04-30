package com.redsponge.kif.state;

import java.util.HashMap;

public class StateManager {

    private State currentState;

    private HashMap<String, State> states;

    public StateManager() {
        initStates();
    }

    private void initStates() {
        states = new HashMap<>();
        registerState(new StateMenu());
        registerState(new StateMouth());
        setCurrentState("menu");
    }

    public void registerState(State s) {
        states.put(s.getName(), s);
    }

    public void setCurrentState(String name) {
        if(currentState != null) currentState.hide();
        currentState = states.get(name);
        currentState.show();
    }



    public State getCurrentState() {
        return currentState;
    }
}
