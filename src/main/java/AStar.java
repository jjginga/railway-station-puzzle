import result.Result;
import state.State;

import java.util.*;

/**
 * A* algorithm
 */
public class AStar {

    //Max time and evaluations
    private static final long MAX_TIME = 60000;
    private static final int MAX_EVALUATIONS = 100000;

    /**
     * A* algorithm
     * @param state initial state
     * @return result
     */
    public Result AStar(State state) {
        //Priority queue the state must be Comparable and override compareTo
        PriorityQueue<State> queue = new PriorityQueue<>();
        //Set must override equals
        Set<State> visited = new HashSet<>();

        //Solution
        State betterState = null;
        //number of evaluations and generated states
        int evaluations = 0, generations = 0;
        long startTime = System.currentTimeMillis();
        long currentTime = 0;

        //we had the start state to the queue
        queue.add(state);

        //while the queue is not empty and the time and evaluations are not over
        while (!queue.isEmpty()
                && currentTime < MAX_TIME
                && evaluations < MAX_EVALUATIONS){

            //we remove the state from the queue
            //since is a priority queue the state with the lowest heuristic value is removed
            State currState = queue.poll();

            //we evaluate the state
            currState.evaluate();
            evaluations++;

            //if the state is a valid solution and is better than the current solution
            //we update the solution
            if(currState.isValidSolution() && (betterState == null || currState.isBetter(betterState)))
                betterState = currState;

            currentTime = System.currentTimeMillis() - startTime;

            //we get the children of the state
            List<State> children = currState.getChildren(visited);
            for(State child : children){
                queue.add(child);
                generations++;
            }

        }

        //we return the best solution found
        return new Result(betterState, evaluations, generations, currentTime);
    }
}
