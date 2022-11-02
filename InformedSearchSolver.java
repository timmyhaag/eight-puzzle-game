/*
 * Author Dr. Junxiu Zhou, Timothy Haag
 */

import java.util.ArrayList;
import java.util.List;

/*
*This class implements the Best-First-Search (BFS) algorithm along with the Heuristic search strategies
*
*In this algorithm, an Open list is used to store the unexplored states and 
*a Closed list is used to store the visited state. Open list is a priority queue (First-In-First-Out). 
*The priority is insured through sorting the Open list each time after new states are generated 
*and added into the list. The heuristics are used to decide which node should be visited next.
*
*In this informed search, reducing the state space search complexity is the main criterion. 
*We define heuristic evaluations to reduce the states that need to be checked every iteration. 
*Evaluation function is used to express the quality of informedness of a heuristic algorithm. 
*/

public class InformedSearchSolver {
	State current = new State();
	State goal = new State();
	List<State> openlist;
	List<State> closed;
	int depth = 0;
	int flag = 0;
	
	public InformedSearchSolver(State current, State goal){
		this.current = current;
		this.goal = goal;
		
		openlist = new ArrayList<State>();
		closed = new ArrayList<State>();
		
		openlist.add(current);
	}
	
	public int sortFun(State e){
		return e.getWeight();
	}

	public int[] check_inclusive(State s) {
	   /*
        * The check_inclusive function is designed to check if the expanded
        * state is in open list or closed list This is done to prevent looping.
        *
        * @param s
        * @return
        */
		int in_open = 0;
		int in_closed = 0;
		int[] ret = {-1, -1};

		//Checks if open or closed contains the state
		if (openlist.contains(s)) {
			in_open = 1;

		} else if (closed.contains(s)) {
			in_closed = 1;
		}

		if (in_open == 0 && in_closed == 0) {
			ret[0] = 1;//he child is not on open or closed
		} else if (in_open == 1 && in_closed == 0) {
			ret[0] = 2;//the child is already on open
			ret[1] = openlist.indexOf(s);
		} else if (in_open == 0 && in_closed == 1) {
			ret[0] = 3;//the child is already on closed
			ret[1] = closed.indexOf(s);
		}


		return ret;
	}


	public void state_walk() {
		/*
		 * The following state_walk function is designed to
		 * move the blank tile --> perform actions
		 * There are four types of possible actions/walks of for
		 * the blank tile, i.e.,
		 * (move up, move down, move left, move right)
		 * Note that in this framework the blank tile is represent by '0'
		 */

		openlist.remove(current);
		closed.add(current);
		int[][] walk_state = current.getTile_seq();
		int row = 0;
		int col = 0;

		//Loop to find the location of the blank space
		for (int i = 0; i < walk_state.length; i++) {
			for (int j = 0; j < walk_state[i].length; j++) {
				if (walk_state[i][j] == 0) {
					row = i;
					col = j;
					break;
				}
			}
		}

		depth += 1;

		/*The following program is used to do the state space actions
		 *The 4 conditions for moving the tiles all use similar logic,
		 *they only differ in the location of the
		 *tile that needs to be swapped.
		 */

		//###(move up) action ###
		//#(row - 1) is checked to prevent out of bounds errors,
		//the tile is swapped with the one above it


		if (row - 1 >= 0) {
			int[][] temp_tiles = new int[3][3];

			for (int i = 0; i < current.getTile_seq().length; i++) {
				for (int j = 0; j < current.getTile_seq()[i].length; j++) {
					temp_tiles[i][j] = current.getTile_seq()[i][j];
				}
			}

			int temp1 = temp_tiles[row - 1][col];
			int temp2 = temp_tiles[row][col];

			State tempState = new State(temp_tiles, depth);

			tempState.getTile_seq()[row][col] = temp1;
			tempState.getTile_seq()[row - 1][col] = temp2;

			int[] tempFlag = check_inclusive(tempState);
			InformedSearchSolver solve = new InformedSearchSolver(current, goal);

			if (tempFlag[0] == 1) {
				solve.heuristic_test(tempState);
				openlist.add(tempState);
			}

			if (tempFlag[0] == 2) {
				openlist.get(tempFlag[1]).setDepth(tempState.getDepth());
			}

			if (tempFlag[0] == 3) {
				if (closed.get(tempFlag[1]).getDepth() > tempState.getDepth()) {
					closed.remove(closed.get(tempFlag[1]));
					openlist.add(tempState);
				}
			}
		}

		// (move down) action
		// Only differences from here on is the position that the tiles are swapped
		if (row + 1 <= 2) {
			int[][] temp_tiles = new int[3][3];

			for (int i = 0; i < current.getTile_seq().length; i++) {
				for (int j = 0; j < current.getTile_seq()[i].length; j++) {
					temp_tiles[i][j] = current.getTile_seq()[i][j];
				}
			}

			int temp1 = temp_tiles[row + 1][col];
			int temp2 = temp_tiles[row][col];

			State tempState = new State(temp_tiles, depth);

			tempState.getTile_seq()[row][col] = temp1;
			tempState.getTile_seq()[row + 1][col] = temp2;

			int[] tempFlag = check_inclusive(tempState);
			InformedSearchSolver solve = new InformedSearchSolver(current, goal);

			if (tempFlag[0] == 1) {
				solve.heuristic_test(tempState);
				openlist.add(tempState);
			}

			if (tempFlag[0] == 2) {
				openlist.get(tempFlag[1]).setDepth(tempState.getDepth());
			}

			if (tempFlag[0] == 3) {
				if (closed.get(tempFlag[1]).getDepth() > tempState.getDepth()) {
					closed.remove(closed.get(tempFlag[1]));
					openlist.add(tempState);
				}
			}
		}

		// (move left) action

		if (col - 1 >= 0) {
			int[][] temp_tiles = new int[3][3];

			for (int i = 0; i < current.getTile_seq().length; i++) {
				for (int j = 0; j < current.getTile_seq()[i].length; j++) {
					temp_tiles[i][j] = current.getTile_seq()[i][j];
				}
			}

			int temp1 = temp_tiles[row][col - 1];
			int temp2 = temp_tiles[row][col];

			State tempState = new State(temp_tiles, depth);

			tempState.getTile_seq()[row][col] = temp1;
			tempState.getTile_seq()[row][col - 1] = temp2;

			int[] tempFlag = check_inclusive(tempState);
			InformedSearchSolver solve = new InformedSearchSolver(current, goal);

			if (tempFlag[0] == 1) {
				solve.heuristic_test(tempState);
				openlist.add(tempState);
			}

			if (tempFlag[0] == 2) {
				openlist.get(tempFlag[1]).setDepth(tempState.getDepth());
			}

			if (tempFlag[0] == 3) {
				if (closed.get(tempFlag[1]).getDepth() > tempState.getDepth()) {
					closed.remove(closed.get(tempFlag[1]));
					openlist.add(tempState);
				}
			}
		}

		// (move right) action

		if (col + 1 <= 2) {
			int[][] temp_tiles = new int[3][3];

			for (int i = 0; i < current.getTile_seq().length; i++) {
				for (int j = 0; j < current.getTile_seq()[i].length; j++) {
					temp_tiles[i][j] = current.getTile_seq()[i][j];
				}
			}

			int temp1 = temp_tiles[row][col + 1];
			int temp2 = temp_tiles[row][col];

			State tempState = new State(temp_tiles, depth);

			tempState.getTile_seq()[row][col] = temp1;
			tempState.getTile_seq()[row][col + 1] = temp2;

			int[] tempFlag = check_inclusive(tempState);
			InformedSearchSolver solve = new InformedSearchSolver(current, goal);

			if (tempFlag[0] == 1) {
				solve.heuristic_test(tempState);
				openlist.add(tempState);
			}

			if (tempFlag[0] == 2) {
				openlist.get(tempFlag[1]).setDepth(tempState.getDepth());
			}

			if (tempFlag[0] == 3) {
				if (closed.get(tempFlag[1]).getDepth() > tempState.getDepth()) {
					closed.remove(closed.get(tempFlag[1]));
					openlist.add(tempState);
				}
			}
		}
		// sort the open list first by h(n) then g(n)
		//Set the next current state

		for (int i = 0; i < openlist.size(); i++) {
			for (int j = i + 1; j < openlist.size(); j++) {
				if ((openlist.get(i).getWeight() - openlist.get(i).getDepth())
						> openlist.get(j).getWeight() - openlist.get(j).getDepth()) {
					List<State> tempList = new ArrayList<State>();
					tempList.add(openlist.get(i));
					openlist.set(i, openlist.get(j));
					openlist.set(j, tempList.get(0));
				}
			}
		}

		current = openlist.get(0);
	}


	/*
	 * this function is used to calculate the weight of the state 
	 * to determine the best state to chosen in the state_walk method
	 */
	public void heuristic_test(State current){
	   /*
        * Solve the game using heuristic search strategies

        * There are three types of heuristic rules:
        * (1) Tiles out of place
        * (2) Sum of distances out of place
        * (3) 2 x the number of direct tile reversals

        * evaluation function
        * f(n) = g(n) + h(n)
        * g(n) = depth of path length to start state
        * h(n) = (1) + (2) + (3)
        */
		
		int [][]current_seq = current.getTile_seq();
		int [][]goal_seq = goal.getTile_seq();
		
		//(1) Tiles out of place
        int h1 = 0;

		for(int i = 0;i<current_seq.length;i++){
			for(int j = 0; j<current_seq[i].length;j++){
				if(current_seq[i][j] != goal_seq[i][j]){
					h1++;
				}
			}
		}

        //(2) Sum of distances out of place
        int h2 = 0;

		for(int i = 0;i<current_seq.length;i++){
			for(int j = 0; j<current_seq[i].length;j++){
				for(int k = 0;k<goal_seq.length;k++){
					for(int l = 0; l<goal_seq[k].length;l++){
						if(current_seq[i][j] == goal_seq[k][l]){
							int row = current_seq[i][j];
							int col = current_seq[i][j];

							int offset1 = k - i;
							int offset2 = l - j;

							h2 += Math.abs(offset1);
							h2 += Math.abs(offset2);

							break;
						}
					}
				}
			}
		}
        
        //(3) 2 x the number of direct tile reversals
        int h3 = 0;

		for(int i = 0;i<current_seq.length;i++){
			for(int j = 0; j<current_seq[i].length;j++){
				if((i + 1) < current_seq.length){ //check if it is the boundary of the row
					if ((current_seq[i + 1][j] != 0) && (current_seq[i][j] != 0)) {
						if ((current_seq[i + 1][j] == goal_seq[i][j]) && (current_seq[i][j] == goal_seq[i + 1][j])) {
							h3 += 1;
						}
					}
				}

				if ((j + 1) < current_seq[i].length) { //check if it is the boundary of the col
					if ((current_seq[i][j + 1] != 0) && (current_seq[i][j] != 0)) {
						if ((current_seq[i][j + 1] == goal_seq[i][j]) && (current_seq[i][j] == goal_seq[i][j + 1])) {
							h3 += 1;
						}
					}
				}

			}
		}

		int newWeight = depth + h1 + h2 + h3;
		current.setWeight(newWeight);
	}
	

	public void run(){
		//# output the goal state
		System.out.println("Reached goal state:");
		System.out.println(goal.toString());
		
		System.out.println("\n The visited states are: ");
		int path = 0;
		while(!current.equals(goal)){
			state_walk();
			System.out.println("\n Visited State number "+ path+1);
			System.out.println(current.toString());
			path += 1;
		}
		System.out.println("\n It took "+ path + " iterations to reach to the goal state");
		System.out.println("The length of the path is: "+ current.getDepth());
	}
}
