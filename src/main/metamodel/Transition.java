package main.metamodel;

import java.util.HashMap;
import java.util.Map;

public class Transition {
	private String event;
	private State to;
	private Map<String,Integer> operationVar = new HashMap<>();
	private Map<String,Integer> conditionalOp = new HashMap<>();
	private String operations;
	private String conditions;





	public Transition(String currentEvent, State state) {
		to=state;
		event=currentEvent;

	}

	public void setOperations(String operations) {
		this.operations = operations;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public int getOperationVar() {
		return operationVar.get(getOperationVariableName());
	}

	public void setOperationVar(String name, int i) {
		operationVar.put(name, i);
	}
	public Map<String, Integer> getConditionalOp() {
		return conditionalOp;
	}

	public void setConditionalOp(String name, int i) {
		conditionalOp.put(name, i);
	}

	public String getEvent() {
		return event;
	}

	public State getTarget() {
		return to;
	}

	public boolean hasSetOperation() {
		if(operations.equals("set"))
			return true;
		else return false;
	}

	public boolean hasIncrementOperation() {
		if(operations.equals("increment")){
			return true;
		}
		else return false;
	}

	public boolean hasDecrementOperation() {
		if(operations.equals("decrement")){
			return true;
		}
		else return false;
	}

	public String getOperationVariableName() {
		return operationVar.keySet().toArray()[0].toString();
	}

	public boolean isConditional() {

		if(this.conditions!=null)
			return true;
		else return false;
	}

	public String getConditionVariableName() {
		return conditionalOp.keySet().toArray()[0].toString();
	}

	public Integer getConditionComparedValue() {

		return conditionalOp.get(getConditionVariableName());
	}

	public boolean isConditionEqual() {
		if(conditions.equals("ifEquals")){
			return true;
		}
		else return false;
	}

	public boolean isConditionGreaterThan() {
		if(conditions.equals("ifGreaterThan")){
			return true;
		}
		else return false;
	}

	public boolean isConditionLessThan() {
		if(conditions.equals("ifLessThan")){
			return true;
		}
		else return false;
	}

	public boolean hasOperation() {
		if(operationVar.size()==0)
			return false;
		else return true;
	}


}
