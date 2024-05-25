import javafx.application.Platform;
import javafx.stage.Stage;
import result.Result;
import state.RailwayStation;
import viewer.DistanceMapViewer;

import java.util.List;

public class Search {


    public void search() {
        List<int[][]> maps = generateMaps();
        BestFirst bestFirst = new BestFirst();
        int i = 1;

        Platform.startup(() -> {});

        for (int[][] map : maps) {
            RailwayStation instance = new RailwayStation();
            instance.setMap(map);
            instance.setStations(null);
            Result result = bestFirst.BestFirst(instance);
            System.out.println(String.format("Instance %d", i));
            System.out.println(result);

            //we pass the result to the viewer
            DistanceMapViewer.setInput(map, result, i++);

            //we use the Platform.runLater to start the viewer
            Platform.runLater(() -> {
                try {
                    new DistanceMapViewer().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //we wait 2 seconds to show the next instance
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }



    //generates the maps
    private static List<int[][]> generateMaps() {
        return List.of(
                // 5x5
                new int[][]{
                        {0, 7, 0, 0, 4},
                        {0, 0, 0, 4, 0},
                        {1, 0, 0, 0, 0},
                        {4, 4, 1, 0, 0},
                        {6, 0, 3, 4, 4}
                },
                new int[][]{
                        {4, 0, 0, 10, 1},
                        {1, 0, 0, 0, 0},
                        {0, 0, 1, 6, 3},
                        {0, 4, 0, 0, 2},
                        {8, 0, 6, 3, 0}
                },
                // 7x7
                new int[][]{
                        {0, 8, 0, 4, 5, 10, 0},
                        {0, 4, 0, 7, 0, 4, 0},
                        {0, 2, 4, 2, 0, 0, 2},
                        {0, 7, 0, 1, 2, 0, 0},
                        {2, 4, 0, 0, 3, 0, 2},
                        {0, 4, 0, 0, 3, 0, 0},
                        {2, 0, 0, 0, 0, 0, 0}
                },
                new int[][]{
                        {0, 0, 1, 0, 7, 0, 1},
                        {0, 1, 4, 0, 0, 0, 4},
                        {0, 0, 0, 0, 2, 0, 0},
                        {3, 1, 0, 8, 5, 7, 7},
                        {0, 4, 0, 3, 0, 0, 0},
                        {0, 0, 0, 3, 2, 4, 2},
                        {0, 8, 3, 6, 3, 0, 0}
                },
                // 9x9
                new int[][]{
                        {6, 7, 2, 0, 0, 0, 0, 0, 0},
                        {3, 3, 6, 0, 8, 4, 3, 1, 0},
                        {0, 0, 8, 0, 0, 0, 2, 4, 0},
                        {0, 0, 0, 1, 0, 3, 2, 0, 0},
                        {0, 0, 0, 7, 4, 0, 1, 0, 0},
                        {12, 8, 0, 5, 4, 1, 4, 3, 4},
                        {8, 0, 1, 2, 4, 3, 3, 0, 0},
                        {1, 1, 0, 0, 0, 0, 5, 0, 0},
                        {4, 0, 0, 0, 4, 6, 0, 13, 2}
                },
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {4, 0, 8, 4, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 3, 0, 0, 1, 0},
                        {0, 3, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 1, 0, 0, 3, 0},
                        {0, 0, 2, 4, 0, 0, 0, 1, 0},
                        {0, 2, 0, 0, 8, 0, 4, 3, 10},
                        {0, 0, 3, 0, 0, 4, 0, 0, 0}
                },
                // 11x11
                new int[][]{
                        {0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                        {0, 0, 11, 2, 0, 0, 9, 3, 0, 0, 3},
                        {0, 0, 0, 3, 1, 0, 2, 0, 0, 0, 0},
                        {4, 1, 2, 3, 0, 4, 0, 0, 4, 0, 0},
                        {5, 0, 0, 0, 4, 0, 1, 0, 4, 3, 0},
                        {0, 0, 0, 7, 4, 0, 1, 0, 0, 7, 0},
                        {0, 8, 0, 0, 0, 0, 3, 0, 1, 0, 3},
                        {0, 3, 0, 0, 5, 2, 3, 0, 0, 0, 2},
                        {0, 0, 0, 3, 1, 0, 2, 8, 0, 0, 0},
                        {0, 3, 4, 0, 7, 0, 0, 7, 0, 0, 0},
                        {4, 2, 0, 4, 0, 3, 0, 0, 5, 7, 0}
                },
                new int[][]{
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 10, 10, 0, 0, 0, 4, 5, 0, 0},
                        {0, 4, 1, 0, 8, 0, 0, 0, 0, 0, 5},
                        {8, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0},
                        {0, 0, 0, 0, 13, 0, 0, 0, 2, 0, 3},
                        {0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0},
                        {4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                },
                // 13x13
                new int[][]{
                        {2, 4, 0, 0, 6, 7, 3, 4, 0, 0, 3, 0, 1},
                        {0, 0, 2, 0, 3, 0, 0, 6, 0, 0, 8, 11, 3},
                        {0, 3, 0, 8, 0, 0, 2, 0, 0, 0, 0, 0, 4},
                        {2, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0},
                        {0, 6, 0, 8, 0, 3, 0, 0, 0, 0, 0, 0, 1},
                        {0, 3, 0, 2, 0, 0, 9, 0, 0, 0, 0, 5, 6},
                        {1, 9, 4, 0, 0, 2, 4, 0, 0, 0, 3, 2, 0},
                        {2, 3, 0, 4, 0, 0, 0, 6, 2, 0, 1, 0, 3},
                        {0, 0, 0, 0, 0, 6, 0, 0, 0, 2, 2, 0, 8},
                        {7, 2, 4, 2, 0, 0, 6, 4, 1, 0, 0, 0, 7},
                        {0, 0, 0, 11, 0, 0, 0, 0, 3, 4, 0, 9, 0},
                        {0, 0, 0, 0, 1, 4, 3, 4, 0, 0, 0, 3, 11},
                        {0, 0, 4, 7, 7, 0, 0, 2, 0, 2, 5, 0, 1}
                },
                new int[][]{
                        {0, 0, 1, 4, 0, 0, 9, 0, 0, 0, 12, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                        {1, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0},
                        {0, 0, 0, 0, 0, 9, 4, 0, 0, 0, 6, 0, 0},
                        {0, 6, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 6, 10, 0, 1, 4},
                        {0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0},
                        {0, 0, 0, 1, 3, 0, 0, 0, 0, 9, 0, 0, 0},
                        {9, 0, 0, 3, 3, 0, 0, 0, 0, 3, 4, 0, 0},
                        {0, 1, 4, 0, 0, 0, 0, 0, 0, 5, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {2, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 10},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0}
                },
                // 15x13
                new int[][]{
                        {0, 0, 0, 4, 0, 0, 0, 6, 0, 0, 0, 0, 2, 2, 0},
                        {0, 2, 12, 0, 3, 0, 0, 0, 0, 26, 0, 0, 0, 0, 4},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0},
                        {0, 0, 0, 0, 0, 0, 0, 3, 3, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 3, 0, 0, 6, 4, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 5, 4, 0, 0, 3, 0, 0, 0},
                        {9, 12, 0, 0, 0, 4, 1, 6, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0},
                        {0, 3, 0, 0, 0, 2, 0, 0, 0, 7, 0, 4, 0, 0, 0},
                        {0, 0, 2, 0, 0, 9, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 2, 16, 0, 8, 0, 2, 0, 0, 0, 0, 7},
                        {0, 0, 5, 0, 6, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0},
                        {0, 4, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 0, 0, 0}
                },
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 10, 3, 0, 0, 0, 0, 2, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                        {0, 1, 0, 3, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0},
                        {0, 0, 0, 10, 3, 8, 11, 0, 0, 0, 0, 0, 2, 0, 0},
                        {0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1},
                        {0, 4, 2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 5, 0, 10, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 2, 8, 0, 15},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0},
                        {0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0, 2, 0},
                        {0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                        {8, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 2, 0, 4},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 1}
                },
                // 17x13
                new int[][]{
                        {0, 0, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 3, 0, 0, 0, 4, 2, 0, 3, 0, 0, 0, 0, 0},
                        {6, 0, 3, 0, 0, 0, 6, 0, 30, 0, 1, 8, 6, 10, 0, 0, 0},
                        {0, 7, 0, 1, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 4, 4},
                        {0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {0, 8, 0, 0, 0, 0, 0, 3, 0, 0, 36, 0, 1, 0, 0, 2, 0},
                        {6, 0, 0, 0, 8, 2, 8, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
                        {8, 1, 0, 0, 0, 0, 4, 1, 0, 0, 0, 0, 0, 6, 7, 0, 0},
                        {3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 4, 0, 0, 1},
                        {3, 0, 0, 2, 0, 4, 0, 0, 0, 0, 9, 0, 0, 0, 8, 16, 24},
                        {0, 1, 0, 0, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 6, 1, 0},
                        {0, 3, 4, 0, 3, 4, 0, 10, 0, 0, 0, 0, 5, 5, 8, 4, 4},
                        {8, 0, 0, 0, 0, 0, 17, 0, 0, 10, 0, 2, 0, 0, 2, 0, 0}
                },
                new int[][]{
                        {0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 0, 0, 0},
                        {0, 6, 0, 0, 0, 0, 8, 0, 10, 0, 0, 0, 0, 2, 2, 3, 0},
                        {0, 0, 0, 0, 0, 4, 0, 8, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {4, 0, 8, 1, 0, 0, 7, 0, 0, 0, 0, 0, 5, 3, 0, 0, 0},
                        {0, 0, 3, 0, 1, 0, 0, 3, 0, 0, 3, 0, 3, 0, 8, 0, 0},
                        {0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {0, 6, 0, 0, 0, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 2, 0, 1, 3, 0, 1, 0, 4, 0, 0, 6, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {4, 8, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
                        {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 6, 3}
                },
                // 19x13
                new int[][]{
                        {0, 0, 0, 0, 4, 0, 0, 4, 0, 0, 8, 0, 6, 0, 0, 0, 0, 0, 4},
                        {0, 0, 0, 0, 0, 2, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
                        {2, 0, 8, 3, 0, 0, 0, 5, 0, 4, 0, 0, 0, 0, 0, 2, 1, 4, 0},
                        {0, 0, 1, 0, 4, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 18, 10, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0, 3, 0, 2, 0, 0, 0, 7, 4, 0, 0, 4, 3},
                        {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 2},
                        {0, 0, 0, 1, 0, 1, 0, 0, 0, 2, 2, 0, 0, 4, 0, 0, 10, 1, 0},
                        {3, 0, 0, 0, 0, 0, 0, 4, 1, 0, 0, 0, 0, 0, 4, 0, 0, 1, 0},
                        {2, 0, 2, 0, 0, 0, 0, 1, 0, 0, 4, 1, 0, 3, 0, 0, 0, 3, 3},
                        {0, 0, 0, 0, 4, 0, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 4, 0, 0, 4, 2, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                        {0, 0, 2, 0, 3, 22, 0, 0, 0, 0, 0, 2, 7, 0, 0, 0, 0, 0, 1},
                        {0, 9, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 4, 0, 8}
                },
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 2, 0, 4, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                        {5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0},
                        {0, 0, 4, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 2, 0, 7, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 2},
                        {0, 0, 3, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 2, 0, 0},
                        {5, 0, 0, 0, 0, 0, 6, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 2, 0, 0, 0, 0, 0, 4, 0, 0, 5, 0, 0, 0, 0, 0, 4, 1},
                        {3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 7, 2, 0, 0, 0, 1, 0, 3},
                        {0, 0, 1, 0, 0, 4, 11, 0, 3, 0, 0, 0, 0, 11, 3, 0, 0, 0, 0},
                        {1, 0, 2, 8, 0, 0, 0, 0, 0, 0, 4, 0, 0, 3, 1, 0, 0, 0, 0},
                        {3, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 7, 0, 0, 0},
                        {0, 0, 0, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1, 0}
                },
                // 19x15
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 16, 1, 0, 5, 0, 3, 0, 0, 0, 4, 0},
                        {0, 2, 3, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 1, 0},
                        {0, 9, 1, 0, 0, 0, 0, 4, 2, 2, 0, 1, 8, 2, 0, 4, 24, 10, 13},
                        {0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 4, 0, 11, 0, 0, 0, 2, 1, 1},
                        {0, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 2},
                        {0, 3, 12, 0, 4, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0},
                        {0, 0, 2, 0, 0, 0, 9, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0},
                        {3, 0, 0, 0, 0, 2, 0, 0, 6, 0, 3, 0, 6, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 2, 5, 0, 0, 12, 2, 4, 0, 0, 7, 0, 1},
                        {6, 4, 4, 0, 0, 8, 0, 3, 2, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 4, 8, 0, 0, 2, 0, 0, 8, 0, 0, 0, 0, 2},
                        {0, 4, 18, 0, 0, 0, 0, 0, 0, 0, 4, 1, 2, 0, 0, 0, 0, 8, 3},
                        {2, 0, 7, 0, 7, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
                        {0, 0, 0, 0, 7, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 11, 0, 30, 0},
                        {1, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}
                },
                new int[][]{
                        {0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 3, 7, 0, 0, 0, 0, 0},
                        {0, 2, 5, 7, 2, 0, 0, 0, 6, 0, 0, 0, 1, 0, 0, 3, 0, 0, 1},
                        {0, 7, 0, 2, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 4, 2, 0, 0, 0},
                        {0, 0, 0, 4, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0},
                        {0, 0, 5, 0, 4, 0, 0, 3, 4, 0, 0, 0, 3, 0, 0, 0, 0, 7, 0},
                        {0, 0, 0, 0, 0, 0, 3, 0, 6, 0, 0, 5, 0, 4, 0, 0, 0, 0, 0},
                        {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0},
                        {0, 0, 7, 0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 8, 0, 0, 0, 0},
                        {4, 0, 0, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 8, 0, 3},
                        {8, 0, 0, 0, 0, 0, 2, 6, 2, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 4, 0, 0, 8, 0, 0, 0, 0},
                        {0, 0, 4, 0, 0, 0, 0, 0, 0, 13, 0, 2, 0, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 2, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0}
                },
                // 19x17
                new int[][]{
                        {0, 2, 0, 0, 0, 4, 4, 0, 0, 4, 0, 0, 1, 6, 0, 1, 4, 0, 0},
                        {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 9, 3, 0, 0, 0, 0, 0},
                        {3, 0, 0, 4, 0, 9, 1, 0, 0, 1, 0, 0, 0, 6, 0, 0, 0, 0, 0},
                        {0, 4, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 3, 0, 6, 0, 0, 0, 0, 3, 0, 0, 11, 17, 0, 0, 0, 0},
                        {6, 0, 1, 0, 0, 6, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 6, 0, 0, 1, 0, 0, 0, 2, 0},
                        {1, 0, 10, 0, 0, 2, 2, 0, 3, 4, 8, 0, 0, 9, 11, 1, 0, 16, 0},
                        {3, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 7, 0, 0, 7, 0, 0, 0, 0},
                        {0, 6, 0, 1, 0, 0, 0, 0, 3, 5, 0, 0, 2, 4, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 6, 0, 3, 6, 0, 10, 6, 0, 0, 0, 0, 0, 0, 2},
                        {3, 0, 0, 4, 4, 0, 2, 0, 0, 0, 1, 0, 0, 1, 2, 16, 11, 0, 0},
                        {7, 0, 0, 3, 0, 0, 0, 0, 0, 10, 12, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 4, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1, 2, 6, 3, 0, 0, 0, 0, 0, 7, 0, 0},
                        {0, 0, 1, 0, 4, 8, 0, 0, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 7, 0, 0, 0, 0, 2}
                },
                new int[][]{
                        {3, 4, 0, 0, 3, 0, 0, 0, 0, 6, 0, 4, 4, 0, 0, 0, 4, 0, 0},
                        {4, 0, 0, 5, 0, 0, 0, 0, 7, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 9, 0, 0},
                        {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0},
                        {4, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 3, 0},
                        {2, 0, 7, 0, 0, 11, 0, 0, 0, 0, 0, 0, 5, 0, 7, 0, 0, 0, 0},
                        {9, 0, 0, 0, 1, 0, 1, 15, 0, 0, 0, 0, 1, 0, 0, 0, 1, 4, 3},
                        {0, 3, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 1, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 7, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0},
                        {0, 0, 6, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 3},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 7, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 6, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0}
                }
        );

    }
}
