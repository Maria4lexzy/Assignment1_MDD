package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {

	private State currentState; // runtime state
	private Machine machine;
	public void run(Machine m) {
		machine = m;
		currentState = machine.getInitialState();
	}

	public State getCurrentState() {

		return currentState;
	}

	public void processEvent(String event) {
		for(Transition t: currentState.getTransitions()) {
			if(t.getEvent().equals(event)) {
				currentState = t.getTarget();

				return;
			}
		}

	}

	public int getInteger(String string) {
		return machine.getInteger(string);
	}

}
