/*
 * Author Dr. Junxiu Zhou, Timothy Haag
 */

import java.util.ArrayList;
import java.util.List;

public class UninformedSearchSolver {
    State current = new State();
    State goal = new State();
    List<State> openlist;
    List<State> closed;
    int depth = 0;
    int flag = 0;

    public UninformedSearchSolver(State current, State goal) {
        this.current = current;
        this.goal = goal;

        openlist = new ArrayList<State>();
        closed = new ArrayList<State>();

        openlist.add(current);
    }

    public int[] check_inclusive(State s) {
        /*
         * The check_inclusive function is designed
         * to check if the expanded state is or is not
         * in open list or closed list
         * This is done to prevent looping
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
            ret[0] = 1;//the child is not on open or closed
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

            if (tempFlag[0] == 1) {
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

            if (tempFlag[0] == 1) {
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

            if (tempFlag[0] == 1) {
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

            if (tempFlag[0] == 1) {
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
        //Set the next current state
        current = openlist.get(0);
    }

    public void run() {
        //# output the goal state
        System.out.println("Reached goal state:");
        System.out.println(goal.toString());

        System.out.println("\n The visited states are: ");
        int path = 0;
        while (!current.equals(goal)) {
            state_walk();
            System.out.println("\n Visited State number " + path + 1);
            System.out.println(current.toString());
            path += 1;
        }
        System.out.println("\n It took " + path + " iterations to reach to the goal state");
        System.out.println("The length of the path is: " + current.getDepth());
    }
}
