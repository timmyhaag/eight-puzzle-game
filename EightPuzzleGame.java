/*
 * Author Dr. Junxiu Zhou, Timothy Haag
 */

public class EightPuzzleGame {
	int[][] init;
	int[][] goal; 
	int tiles = 8;
	
	public EightPuzzleGame(int[][] init, int[][] goal, int tiles){
		this.init = init;
		this.goal = goal;
		this.tiles = tiles;
	}

	public static void start(){
		//init the start state 
		int [][] init_tiles = {{1,2,3},{0,4,6},{7,5,8}};
		State init_state = new State(init_tiles, 0, 0);

		int [][] init_tiles2 = {{2,3,6},{1,4,8},{7,5,0}};
		State init_state2 = new State(init_tiles2, 0, 0);

		int [][] init_tiles3 = {{7,1,5},{4,0,2},{8,6,3}};
		State init_state3 = new State(init_tiles3, 0, 0);

		int [][] init_tiles4 = {{8,7,0},{1,3,2},{6,4,5}};
		State init_state4 = new State(init_tiles4, 0, 0);

		int [][] init_tiles5 = {{0,2,6},{1,7,5},{4,8,3}};
		State init_state5 = new State(init_tiles5, 0, 0);
		
		System.out.println("Start State:");
		System.out.println(init_state.toString());
		
		//init the goal state 
		int [][] goal_tiles = {{1,2,3},{4,5,6},{7,8,0}};
		State goal_state = new State(goal_tiles, 0, 0);
		
		System.out.println("Expect Goal State:");
		System.out.println(goal_state.toString());

		//Uncomment the solver you wish to test

		//***************************************************************************
		//Uninformed search solver and Informed search solver for the input puzzle:
		//{{1,2,3},{0,4,6},{7,5,8}}

		//UninformedSearchSolver solve = new UninformedSearchSolver(init_state, goal_state);
		//long startTime = System.currentTimeMillis();
		//solve.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));

		//InformedSearchSolver solveInf = new InformedSearchSolver(init_state, goal_state);
		//long startTime = System.currentTimeMillis();
		//solveInf.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));
		//***************************************************************************


		//***************************************************************************
		//Uninformed search solver and Informed search solver for the input puzzle:
		//{{2,3,6},{1,4,8},{7,5,0}}

		//UninformedSearchSolver solve = new UninformedSearchSolver(init_state2, goal_state);
		//long startTime = System.currentTimeMillis();
		//solve.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));

		//InformedSearchSolver solveInf = new InformedSearchSolver(init_state2 , goal_state);
		//long startTime = System.currentTimeMillis();
		//solveInf.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));
        //***************************************************************************


		//***************************************************************************
		//Uninformed search solver and Informed search solver for the input puzzle:
		//{{7,1,5},{4,0,2},{8,6,3}}

		//UninformedSearchSolver solve = new UninformedSearchSolver(init_state3, goal_state);
		//long startTime = System.currentTimeMillis();
		//solve.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));

		//InformedSearchSolver solveInf = new InformedSearchSolver(init_state3 , goal_state);
		//long startTime = System.currentTimeMillis();
		//solveInf.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));
		//***************************************************************************


		//***************************************************************************
		//Uninformed search solver and Informed search solver for the input puzzle:
		//{{8,7,0},{1,3,2},{6,4,5}}

		//UninformedSearchSolver solve = new UninformedSearchSolver(init_state4, goal_state);
		//long startTime = System.currentTimeMillis();
		//solve.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));

		//InformedSearchSolver solveInf = new InformedSearchSolver(init_state4 , goal_state);
		//long startTime = System.currentTimeMillis();
		//solveInf.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));
		//***************************************************************************


		//***************************************************************************
		//Uninformed search solver and Informed search solver for the input puzzle:
		//{{0,2,6},{1,7,5},{4,8,3}}

		//UninformedSearchSolver solve = new UninformedSearchSolver(init_state5, goal_state);
		//long startTime = System.currentTimeMillis();
		//solve.run();
		//long stopTime = System.currentTimeMillis();
		//System.out.println("Computational time for solver in ms: " + (stopTime - startTime));

		InformedSearchSolver solveInf = new InformedSearchSolver(init_state5 , goal_state);
		long startTime = System.currentTimeMillis();
		solveInf.run();
		long stopTime = System.currentTimeMillis();
		System.out.println("Computational time for solver in ms: " + (stopTime - startTime));
		//***************************************************************************
	}
	
	public static void main(String[] args) {
		start();

	}
}
