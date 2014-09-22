package samegame;

import jaima.search.SearchAction;
import jaima.search.State;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Problem extends jaima.search.Problem{
	
	private String start;
	private String goal = "000000000";
	private ArrayList<Remove> moves;
	private Map<String, BoardState> states;
	
	public Problem(String start) {
		this.start = start;
		moves = new ArrayList<Remove>();
		states = new TreeMap<String, BoardState>();
	}

	@Override
	public State initial() {
		return states.get(start);
	}

	@Override
	public boolean isGoal(State s) {
		return s.toString().equals(goal);
	}

	@Override
	public Map<SearchAction, State> successors(State state) {
		TreeMap<SearchAction, State> possible = new TreeMap<SearchAction, State>();
		
		for(Remove move : moves){
			if(move.from.equals(state)){
				SearchAction action = move;
                State newState = move.to;
                possible.put(action, newState);
			}
		}
		
		return possible;
	}

}
