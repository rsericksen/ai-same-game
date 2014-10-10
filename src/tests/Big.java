package tests;

import samegame.Problem;
import jaima.search.Solver;
import jaima.search.strategies.AStar;
import jaima.search.strategies.GraphSQ;
import jaima.search.strategies.IDS;
import jaima.search.strategies.Node;
import jaima.search.strategies.Strategy;
import jaima.search.strategies.TreeSQ;
import jaima.search.strategies.UCost;
import jaima.util.Metrics;
import jaima.util.VirtualTimer;

public class Big extends junit.framework.TestCase {

	Problem problem;

	protected void setUp() {
		Node.reporting.add(Node.Reports.action);
		Node.reporting.add(Node.Reports.pathcost);
		Node.reporting.add(Node.Reports.pretty);
		Metrics.clearAll();

		try {
			problem = new Problem("gggbrr" + "rrbbgg" + "rrrbrg" + "ggbgbb" + "rrrrrb" + "bgrrbb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VirtualTimer.startTimer();
	}

	protected void tearDown() {
		Metrics.set("Seconds elapsed: ", VirtualTimer.elapsed());
		System.out.println(Metrics.getAll());
	}
	
	public void testAStarTreeSQ() {
		Strategy strategy = new AStar(new TreeSQ());
		assertTrue(Solver.test(problem, strategy));
	}
	
	public void testUCost(){
		Strategy strategy = new UCost(new TreeSQ());
		assertTrue(Solver.test(problem, strategy));
	}
	
	public void testIDS(){
		Strategy strategy = new IDS();
		assertTrue(Solver.test(problem, strategy));
	}
	
	

}
