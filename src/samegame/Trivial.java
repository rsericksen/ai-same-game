package samegame;

import jaima.search.Solver;
import jaima.search.strategies.BreadthFS;
import jaima.search.strategies.DLS;
import jaima.search.strategies.GraphSQ;
import jaima.search.strategies.IDS;
import jaima.search.strategies.Node;
import jaima.search.strategies.SearchQueue;
import jaima.search.strategies.Strategy;
import jaima.util.Metrics;
import jaima.util.VirtualTimer;

public class Trivial extends junit.framework.TestCase {
	
	Problem problem;
	
	protected void setUp(){
		Node.reporting.add(Node.Reports.action); 
        Node.reporting.add(Node.Reports.pathcost);
        Node.reporting.add(Node.Reports.pretty);
        Metrics.clearAll();
        
        problem = new Problem("bbbbbbbbb");
        
        VirtualTimer.startTimer();
	}
	
	protected void tearDown(){
		Metrics.set("Seconds elapsed: ", VirtualTimer.elapsed());
        System.out.println(Metrics.getAll());
	}
	
	public void testBFS(){
        Strategy strategy = new DLS(6);
        assertTrue(Solver.test(problem, strategy));
	}

}
