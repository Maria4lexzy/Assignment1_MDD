package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.HashMap;
import java.util.Map;

public class MachineInterpreter {

	private State currentState; // runtime state
	private Machine machine;
	private Map<String,Integer> varStates = new HashMap<>();
	public void run(Machine m) {
		machine = m;
		currentState = machine.getInitialState();
		varStates=machine.getInteger();
	}

	public State getCurrentState() {

		return currentState;
	}

	public void processEvent(String event) {
		for(Transition t: currentState.getTransitions()) {
			if(t.getEvent().equals(event))
			{
				if(t.hasOperation()){
					varStates.put(t.getOperationVariableName(),t.getOperationVar());
				}

				if(t.isConditional()){
					if(t.isConditionEqual()){
						if(varStates.get(t.getConditionVariableName())==t.getConditionComparedValue()){
							currentState = t.getTarget();
							return;
						}
					}
					else if(t.isConditionGreaterThan()){
						if(varStates.get(t.getConditionVariableName())>t.getConditionComparedValue()){
							currentState = t.getTarget();
							return;
						}
					}
					else if(t.isConditionLessThan()){
						if(varStates.get(t.getConditionVariableName())<t.getConditionComparedValue()){
							currentState = t.getTarget();
							return;
						}
					}


				}
				else{
					currentState = t.getTarget();
					return;
				}



				return;
			}
		}

	}

	public int getInteger(String string) {
		return machine.getInteger(string);
	}

}
