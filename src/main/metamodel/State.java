package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	private String name;
	private List<Transition> trans = new ArrayList<>();

	public State(String name) {
		this.name = name;
	}
	public String getName() {

		return name;
	}

	public List<Transition> getTransitions() {
		return trans;
	}

	public Transition getTransitionByEvent(String string) {

		Transition transitionByEvent=null;
		for (int i=0; i< trans.size(); i++){
			if(trans.get(i).getEvent().equals(string)){
				transitionByEvent=trans.get(i);

			}
		}
		return transitionByEvent;
	}

	public void addTransition(Transition t) {
		this.trans.add(t);
	}
}
