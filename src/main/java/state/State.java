package state;

import java.util.List;
import java.util.Set;


//interface for the State class with the methods necessary for the A* algorithm
public interface State extends Comparable<State> {

    void calculateHeuristic();

    void evaluate();

    boolean isValidSolution();

    List<State> getChildren(Set<State> visited);

    boolean isBetter(State betterState);
}
