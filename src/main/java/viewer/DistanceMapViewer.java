package viewer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import result.Result;
import state.RailwayStation;


/**
 * JavaFX application to visualize the distance map of the Railway Station problem.
 */
public class DistanceMapViewer extends Application {
    private static final int CELL_SIZE = 50;
    private static int[][] map;
    private static Result result;
    private static boolean isInitialized = false;
    private static int instanceNumber;

    //map and result to be shown
    public static void setInput(int[][] mapInput, Result resultInput, int instanceNumberInput) {
        map = mapInput;
        result = resultInput;
        instanceNumber = instanceNumberInput;
    }

    //start the JavaFX application
    @Override
    public void start(Stage primaryStage) {
        if (map == null || result == null) {
            throw new IllegalStateException("Map and Result must be set before launching the application.");
        }

        isInitialized = true;

        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    //create the scene with the map and the information
    private Scene createScene() {
        int rows = map.length;
        int cols = map[0].length;
        GridPane gridPane = new GridPane();

        //calculates the distance for the color of the cells
        int[][] distanceMap = calculateDistances(map, ((RailwayStation) result.getBetterState()).getStations());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Canvas canvas = new Canvas(CELL_SIZE, CELL_SIZE);
                GraphicsContext gc = canvas.getGraphicsContext2D();

                Color color = getColorForDistance(distanceMap[row][col]);
                gc.setFill(color);
                gc.fillRect(0, 0, CELL_SIZE, CELL_SIZE);

                //prints the number of families in the cell
                gc.setFill(Color.BLACK);
                gc.setFont(new Font(20));
                gc.fillText(String.valueOf(map[row][col]), CELL_SIZE / 2 - 10, CELL_SIZE / 2 + 10);

                //prints the station as blue
                if (isStation(row, col)) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(0, 0, CELL_SIZE, CELL_SIZE);
                    gc.setFill(Color.WHITE);
                    gc.fillText(String.valueOf(map[row][col]), CELL_SIZE / 2 - 10, CELL_SIZE / 2 + 10);
                }

                gridPane.add(canvas, col, row);
            }
        }

        //adds information in the bottom of the map
        Canvas infoCanvas = new Canvas(cols * CELL_SIZE, 150);
        GraphicsContext infoGc = infoCanvas.getGraphicsContext2D();
        infoGc.setFill(Color.WHITE);
        infoGc.fillRect(0, 0, cols * CELL_SIZE, CELL_SIZE * 2);

        infoGc.setFill(Color.BLACK);
        infoGc.setFont(new Font(20));
        infoGc.fillText("Instance: " + instanceNumber, 10, 25);
        infoGc.fillText("Avg Cost: " + ((RailwayStation)result.getBetterState()).getAverageCost(), 10, 50);
        infoGc.fillText("Total Cost: " + ((RailwayStation)result.getBetterState()).getTotalCost(), 10, 75);
        infoGc.fillText("Evaluations: " + result.getEvaluations(), 10, 100);
        infoGc.fillText("Generations: " + result.getGenerations(), 10, 125);
        infoGc.fillText("Time: " + result.getCurrentTime() / 1000.0 + "s", 10, 150);

        gridPane.add(infoCanvas, 0, rows, cols, 1); // Adiciona as informações no fundo do mapa

        return new Scene(gridPane, cols * CELL_SIZE, (rows + 5) * CELL_SIZE);
    }

    //calculates the distances from the cells to the stations
    private int[][] calculateDistances(int[][] map, int[][] stations) {
        int rows = map.length;
        int cols = map[0].length;
        int[][] distances = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                distances[row][col] = Integer.MAX_VALUE;
                for (int[] station : stations) {
                    int distance = Math.max(Math.abs(row - station[0]), Math.abs(col - station[1]));
                    distances[row][col] = Math.min(distances[row][col], distance);
                }
                //the maximum distance is 6
                if (distances[row][col] > 6) {
                    distances[row][col] = 6;
                }
            }
        }
        return distances;
    }


    private Color getColorForDistance(int distance) {
        switch (distance) {
            case 0: return Color.GREEN;
            case 1: return Color.LIGHTBLUE;
            case 2: return Color.YELLOW;
            case 3: return Color.ORANGE;
            case 4: return Color.LIGHTCORAL;
            case 5: return Color.RED;
            case 6: return Color.LIGHTGRAY;
            default: return Color.BLACK;
        }
    }

    //checks if the cell is a station
    private boolean isStation(int row, int col) {
        int[][] stations = ((RailwayStation) result.getBetterState()).getStations();
        if (stations == null) return false;
        for (int[] station : stations) {
            if (station[0] == row && station[1] == col) {
                return true;
            }
        }
        return false;
    }

}
