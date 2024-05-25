package result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import state.State;

//class that represents the result of the algorithm
@Getter
@Setter
@AllArgsConstructor
public class Result {
    private final State betterState;
    private final int evaluations;
    private final int generations;
    private final long currentTime;



    @Override
    public String toString() {

       return betterState == null ?
               "No solution found. " +
                "Result{" +
                ", evaluations=" + evaluations +
                ", generations=" + generations +
                ", time=" + currentTime/1000 +
                "s}":
               "Result{" +
               betterState +
               ", evaluations=" + evaluations +
               ", generations=" + generations +
               ", time=" + currentTime/1000 +
               "s}";
    }
}
