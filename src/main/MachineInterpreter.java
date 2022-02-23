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

boolean conditionalPass=true;
				 if(t.isConditional()){
					 conditionalPass=false;
					if(t.isConditionEqual()){

						if(varStates.get(t.getConditionVariableName())==t.getConditionComparedValue()){
							currentState = t.getTarget();
							conditionalPass=true;
						}
					}
					else if(t.isConditionGreaterThan()){
						if(varStates.get(t.getConditionVariableName())>t.getConditionComparedValue()){
							currentState = t.getTarget();
							conditionalPass=true;

						}
					}
					else if(t.isConditionLessThan()){
						if(varStates.get(t.getConditionVariableName())<t.getConditionComparedValue()){
							currentState = t.getTarget();
							conditionalPass=true;

						}
					}
				}
				if(t.hasOperation()) {
				 if(conditionalPass)
				 {

						 if (t.hasSetOperation()) {
							 varStates.put(t.getOperationVariableName(), t.getOperationVar());
						 }
						 if (t.hasIncrementOperation()) {
							 varStates.put(t.getOperationVariableName(), varStates.get(t.getOperationVariableName()) + t.getOperationVar());

						 }
						 if (t.hasDecrementOperation()) {
							 varStates.put(t.getOperationVariableName(), varStates.get(t.getOperationVariableName()) + t.getOperationVar());
						 }
					 currentState = t.getTarget();
					 return;
				 }
				 }
				else{
					if(conditionalPass)
					{
						currentState = t.getTarget();
						return;
					}
				}



			}
		}

	}

	public int getInteger(String string) {
		return machine.getInteger(string);
	}

}
