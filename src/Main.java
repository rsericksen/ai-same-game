import jaima.search.SearchAction;
import jaima.search.State;

import java.util.Map;

import samegame.BoardState;
import samegame.Problem;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Problem problem;
		try {
			problem = new Problem("gggbbbrrr");
			problem.setGoal("EEEEEEEEE");
			Map<SearchAction, State> stateMap = problem.successors(problem.initial());
			
			BoardState state;
			
			System.out.println("States:");
			for(SearchAction key : stateMap.keySet()){
				state = (BoardState) stateMap.get(key);
				System.out.println(state.prettyPrint());
			}
		} catch (Exception e) {
			System.out.println("Error! Make sure your input is a square");
		}
		
	}

}
