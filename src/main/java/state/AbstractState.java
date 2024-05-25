package state;

import lombok.Getter;
import lombok.Setter;

//Abstract class that implements the State interface
@Getter
@Setter
public abstract class AbstractState implements State, Comparable<State> {

    //heristic
    protected double heuristic;

    //compares using heuristic, so that we can use the priority queue
    @Override
    public int compareTo(State state) {
        return Double.compare(this.heuristic, ((AbstractState)state).getHeuristic());
    }
}
