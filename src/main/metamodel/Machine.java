package main.metamodel;

import java.util.*;

public class Machine {

	private List<State> states = new ArrayList<State>();
	private State initialState;
	private Map<String,Integer> integer = new HashMap<>();
	public Machine(List<State> states, State initialState, Map<String,Integer>  integer) {
		this.states=states;
		this.initialState = initialState;
		this.integer=integer;
	}
	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		State s=null;
		for (int i=0; i< states.size(); i++){
			if(states.get(i).getName().equals(string)){
				s=states.get(i);

			}
		}
		return s;
	}

	public int numberOfIntegers() {
		return integer.size();
	}

	public Map<String, Integer> getInteger() {
		return integer;
	}

	public int getInteger(String key) {
		return integer.get(key);
	}
	public boolean hasInteger(String string) {
		return integer.containsKey(string);

	}

}
