package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    private State current;
    private State initial;
    private String currentEvent;
    private Map<String,Integer> integer = new HashMap<>();
    private Map<String,State> states = new HashMap<>();

    public Machine build() {
        return new Machine(new ArrayList<>(states.values()),initial,  integer);
    }
    private State getState(String name) {
        if(!states.containsKey(name)) states.put(name, new State(name));
        return states.get(name);
    }
    public StateMachine  state(String string) {
        current = getState(string);
        return this;
    }

    public StateMachine initial() {
        // TODO Auto-generated method stub
        initial =current;
        return this;
    }

    public StateMachine when(String string) {
        currentEvent = string;
        return this;
    }

    public StateMachine to(String name) {
        Transition t = new Transition(currentEvent,getState(name));
       current.addTransition(t);
        return this;
    }

    public StateMachine integer(String name) {
        integer.put(name, 0);
        return this;
    }

    public StateMachine set(String name, int i) {
        current.getTransitions().get(current.getTransitions().size()-1).setOperationVar(name, i);
        current.getTransitions().get(current.getTransitions().size()-1).setOperations("set");
        integer.put(name, i);
        return this;
    }

    public StateMachine increment(String string) {
        current.getTransitions().get(current.getTransitions().size()-1).setOperationVar(string, 1);
        current.getTransitions().get(current.getTransitions().size()-1).setOperations("increment");
        integer.put(string,integer.get(string)+1);
        return this;
    }

    public StateMachine decrement(String string) {
        current.getTransitions().get(current.getTransitions().size()-1).setOperationVar(string, -1);
        current.getTransitions().get(current.getTransitions().size()-1).setOperations("decrement");
        integer.put(string,integer.get(string)-1);
        return this;
    }

    public StateMachine ifEquals(String name, int i) {
        current.getTransitions().get(current.getTransitions().size()-1).setConditionalOp(name, i);
        current.getTransitions().get(current.getTransitions().size()-1).setConditions("ifEquals");
        return this;
    }

    public StateMachine ifGreaterThan(String name, int i) {

        current.getTransitions().get(current.getTransitions().size()-1).setConditionalOp(name, i);
        current.getTransitions().get(current.getTransitions().size()-1).setConditions("ifGreaterThan");
        return this;
    }

    public StateMachine ifLessThan(String name, int i) {
        current.getTransitions().get(current.getTransitions().size()-1).setConditionalOp(name, i);
        current.getTransitions().get(current.getTransitions().size()-1).setConditions("ifLessThan");
        return this;
    }

}