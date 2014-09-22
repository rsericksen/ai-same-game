package samegame;

import jaima.search.SearchAction;
import jaima.search.State;

import java.util.Map;

public class Problem extends jaima.search.Problem {

	private BoardState board;
	private BoardState goal;

	public Problem(String start) {
		board = new BoardState(start);
	}

	public void setGoal(String s) {
		goal = new BoardState(s);
	}

	public void setBoard(String s) {
		board = new BoardState(s);
	}

	@Override
	public State initial() {
		return (State) board;
	}

	public State goal() {
		return goal;
	}

	@Override
	public boolean isGoal(State s) {
		if (goal == null) {
			BoardState state = (BoardState) s;
			return state.isEmpty();
		}

		return s.equals(goal);
	}

	@Override
	public Map<SearchAction, State> successors(State state) {
		return ((BoardState) state).successors();
	}

}
