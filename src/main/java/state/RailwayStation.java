package state;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

//class that represents the state of the railway station problem
@Getter
@Setter
public class RailwayStation extends AbstractState{

    int [][] map;
    int [][] stations;
    double averageCost;
    int totalCost;

    //costs for moving families to the nearest station
    public static final int[] COSTS = new int[] {0, 0, 1, 2, 4, 8, 10};

    //calculates the total cost and average cost of the state
    @Override
    public void evaluate() {
        //if there is no station we atribute a very high cost
        if(this.stations==null || stations.length==0){
            this.averageCost = Integer.MAX_VALUE;
            this.totalCost = Integer.MAX_VALUE;
            return;
        }
        int totalFamilies = 0;
        int movingCost = 0;


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int families = map[i][j];
                if(families==0)
                    continue;
                totalFamilies += families;
                int minDistance = Integer.MAX_VALUE;

                //finds the nearest station and calculates the distance
                for (int[] station : stations) {
                    int distance = Math.abs(station[0] - i) + Math.abs(station[1] - j);
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }

                //if the distance is greater than 6 we set it to 6 since the cost is the same
                minDistance = Math.min(minDistance, 6);

                movingCost += families * COSTS[minDistance];
            }
        }

        //the average cost is the moving cost divided by the number of families
        this.averageCost = (double) movingCost / totalFamilies;
        //the total cost is the sum of the moving cost and the cost of the stations with the respective penalties
        this.totalCost = (int) (stations.length*1000 + averageCost*100);
    }

    //checks if the state is a valid solution
    @Override
    public boolean isValidSolution() {
        return this.averageCost<3;
    }

    //calculates the heuristic of the state
    @Override
    public void calculateHeuristic() {
        double heuristicSum = 0;
        int totalFamilies = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int families = map[i][j];

                if(families==0)
                    continue;

                double minDistance = Double.MAX_VALUE;

                for (int[] station : stations) {
                    double distance = Math.sqrt(Math.pow(station[0] - i, 2) + Math.pow(station[1] - j, 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }
                if(minDistance>6)
                    minDistance = 6;
                totalFamilies += families;
                heuristicSum += families * minDistance;
            }
        }

        double normalizedFactor = 1 + (stations.length-1)/(double)totalFamilies;
        this.heuristic = heuristicSum * normalizedFactor;
        //System.out.println("Heuristic: "+this.heuristic);
    }


    //returns the children of the state
    @Override
    public List<State> getChildren(Set<State> visited) {
        List<State> children = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int[] newStation = new int[]{i, j};
                //if the station is not already in the list of stations we add it
                if (stations == null || Arrays.stream(stations)
                        .noneMatch(array -> Arrays.equals(array, newStation))) {
                    //adds a new station
                    int[][] newStations = duplicateArray(stations);
                    newStations[newStations.length-1]= newStation;
                    RailwayStation newState = new RailwayStation();
                    newState.setMap(this.map);
                    newState.setStations(newStations);
                    //if the state is not already visited we add it to the children
                    if (!visited.contains(newState)) {
                        newState.calculateHeuristic();
                        children.add(newState);
                    }
                }
            }
        }

        return children;
    }

    //checks if the state is better than the current state
    @Override
    public boolean isBetter(State betterState) {
        return this.totalCost < ((RailwayStation) betterState).getTotalCost();
    }

    //creates a copy of the array of the stations
    public int[][] duplicateArray(int[][] original) {
        if (original == null)
            return new int[1][];


        int[][] copy = new int[original.length+1][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    //has hash we use the stations array
    @Override
    public int hashCode() {
        return Objects.hashCode(stations);
    }

    //checks if two states are equal using the stations array
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RailwayStation) {
            return Arrays.deepEquals(stations, ((RailwayStation) obj).getStations());
        }
        return false;
    }

    //prints the state
    @Override
    public String toString() {
        return String.format("stations=%s, averageCost=%.2f, totalCost=%d", Arrays.deepToString(stations), averageCost, totalCost);
    }
}
