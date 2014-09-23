package samegame;

import jaima.search.SearchAction;
import jaima.search.State;

import java.util.Map;

/**
 * 
 * @author Stephen Bain and Ryan Ericksen
 * 
 * http://www.chiark.greenend.org.uk/~sgtatham/puzzles/js/samegame.html
 * 
 * The problem inputs a String of letters which correspond to color blocks on the game board. 
 * We'll say that the 'r' character represents red, the 'b' character blue, and the 'g' character green. 
 * For example, the following game board
 * 
 * g g b
 * r r b
 * b b g
 * 
 * would be represented as "ggbrrbbbg"
 * 
 * GOAL STATE:
 * 
 * E E E
 * E E E
 * E E E
 *
 * represented as "EEEEEEEEE"
 * 
 */
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
