# Railway Station Optimization
This was a problem given in the course [Introduction to Artificial Intelligence](https://guiadoscursos.uab.pt/en/ucs/introducao-a-inteligencia-artificial/) at [Universidade Aberta](https://portal.uab.pt/?lang=en) in the 2023/2024 school year. 
The problem statement is in the pdf provided in the statement folder. But please note that it is in portuguese. 

## Project Overview

This project aims to optimize the placement of railway stations in a given territory to minimize the number of stations and the average travel cost for families. 
The problem is inspired by the expansion of railways in the Old West, transforming transportation and territorial expansion.

## Problem Description

We are given a territory divided into NxM zones, with a specified number of families in each zone. 
The goal is to place railway stations to serve the population efficiently. The criteria for evaluating the station placements are:

1. Minimize the number of stations.
2. Minimize the average travel cost to the nearest station.

The travel cost is determined by the distance to the nearest station, with the following unit costs based on distance (horizontal, vertical, or diagonal):

- Distance 0 or 1: Cost 0
- Distance 2: Cost 1
- Distance 3: Cost 2
- Distance 4: Cost 4
- Distance 5: Cost 8
- Distance 6 or greater: Cost 10

The overall cost to minimize is defined as:

`Cost = 1000A + 100B`

where `A` is the number of stations and `B` is the average travel cost. The average travel cost must be less than 3 for the solution to be valid.

## Implementation

The project uses informed search algorithms to find the optimal placement of railway stations. 
Specifically, the A* algorithm is employed with a heuristic designed to balance the number of stations and the average travel cost.

### Heuristic Function

The heuristic function used is:

`Heuristic = Sum of (Families * MinDistance) * (1 + (Number of Stations / Total Families))`

Where `MinDistance` is the minimum distance from each zone to any station, capped at a maximum of 6.

### Algorithm

The A* algorithm explores the search space by expanding the most promising nodes first, as determined by the heuristic function. 
The goal is to find a solution with the minimum cost as defined above.

### Classes and Methods

- `AbstractState`: Represents the abstract state of the problem.
- `State`: Extends `AbstractState` with specific implementations for the railway station problem.
- `RailwayStation`: Represents the state of the railway station placement, including methods for calculating the heuristic.
- `BestFirst`: Implements the A* search algorithm.
- `Search`: Manages the execution of the search algorithm.
- `Main`: The main entry point for running the project.
- `DistanceMapViewer`: Visualizes the resulting map with stations and distances.

### Visualization

The `DistanceMapViewer` class uses JavaFX to visualize the map. 
Each zone is color-coded based on the distance to the nearest station, and stations are highlighted. 
Additional information such as the instance number, average cost, total cost, number of evaluations, generations, and time taken is displayed.

## Usage

To run the project, execute the `Main` class. 

## Results

For each instance, the program reports:
- Number of evaluations (maximum 100,000).
- Total cost.
- Time taken (maximum 1 minute).

The best solution for each instance is saved and displayed graphically.

## Examples and Instances

### Example 1:

**Map:**
```
0 7 0 0 4
0 0 0 4 0
1 0 0 0 0
4 4 1 0 0
6 0 3 4 4
```

**Stations:**
```
1 1
```

**Calculations:**

In this solution for the given territory, there is only one station located in the specified zone. The zones at a distance of 1 have no travel cost. 
The zones at a distance of 2 have a unit cost of 1. With 14 families, they contribute a total travel cost of 14. 
The zones at a distance of 3 have a cost of 2, and with 17 families, the total cost is 34. Summing the travel costs gives 48. 
With 42 families, the average travel cost is 
`B = 48 / 42 = 1.142`. Therefore, with one station 
`A = 1`, the solution value according to the formula to minimize is 
`1000 * 1 + 1.142 * 100 = 1114`. The solution is valid because the average travel cost is less than 3.


### Example 2:

**Map:**
```
0 7 0 0 4
0 0 0 4 0
1 0 0 0 0
4 4 1 0 0
6 0 3 4 4
```
**Stations:**
```
1 1
3 3
```
**Calculations:**

By adding another station to the solution, there are now 8 families in the zones at a distance of 2, with a travel cost of 8. The zones at a distance of 3 have only 4 families, with a unit cost of 2, resulting in a travel cost of 8. Thus, the total travel cost is 16, divided by 25 gives 
`B = 0.64`. With two stations 
`A = 2`, the solution cost is 2064. This alteration is not favorable as it increases the total solution cost compared to the previous solution.

## Instances

```java
{
// 5x5
{
{0,7,0,0,4},
{0,0,0,4,0},
{1,0,0,0,0},
{4,4,1,0,0},
{6,0,3,4,4},
},
{
{4,0,0,10,1},
{1,0,0,0,0},
{0,0,1,6,3},
{0,4,0,0,2},
{8,0,6,3,0},
},
// 7x7
{
{0,8,0,4,5,10,0},
{0,4,0,7,0,4,0},
{0,2,4,2,0,0,2},
{0,7,0,1,2,0,0},
{2,4,0,0,3,0,2},
{0,4,0,0,3,0,0},
{2,0,0,0,0,0,0},
},
{
{0,0,1,0,7,0,1},
{0,1,4,0,0,0,4},
{0,0,0,0,2,0,0},
{3,1,0,8,5,7,7},
{0,4,0,3,0,0,0},
{0,0,0,3,2,4,2},
{0,8,3,6,3,0,0},
},
// 9x9
{
{6,7,2,0,0,0,0,0,0},
{3,3,6,0,8,4,3,1,0},
{0,0,8,0,0,0,2,4,0},
{0,0,0,1,0,3,2,0,0},
{0,0,0,7,4,0,1,0,0},
{12,8,0,5,4,1,4,3,4},
{8,0,1,2,4,3,3,0,0},
{1,1,0,0,0,0,5,0,0},
{4,0,0,0,4,6,0,13,2},
},
{
{0,0,0,0,0,0,0,0,0},
{4,0,8,4,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0},
{0,0,0,0,3,0,0,1,0},
{0,3,0,0,0,0,0,0,0},
{0,0,0,1,1,0,0,3,0},
{0,0,2,4,0,0,0,1,0},
{0,2,0,0,8,0,4,3,10},
{0,0,3,0,0,4,0,0,0},
},
// 11x11
{
{0,0,0,0,0,3,0,0,0,0,0},
{0,0,11,2,0,0,9,3,0,0,3},
{0,0,0,3,1,0,2,0,0,0,0},
{4,1,2,3,0,4,0,0,4,0,0},
{5,0,0,0,4,0,1,0,4,3,0},
{0,0,0,7,4,0,1,0,0,7,0},
{0,8,0,0,0,0,3,0,1,0,3},
{0,3,0,0,5,2,3,0,0,0,2},
{0,0,0,3,1,0,2,8,0,0,0},
{0,3,4,0,7,0,0,7,0,0,0},
{4,2,0,4,0,3,0,0,5,7,0},
},
{
{1,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0},
{0,0,10,10,0,0,0,4,5,0,0},
{0,4,1,0,8,0,0,0,0,0,5},
{8,0,0,0,0,0,6,0,0,0,0},
{0,0,0,0,13,0,0,0,2,0,3},
{0,0,0,0,4,0,0,0,0,1,0},
{0,0,0,0,0,0,0,0,0,0,0},
{0,0,4,0,0,0,0,3,0,0,0},
{4,1,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0},
},
// 13x13
{
{2,4,0,0,6,7,3,4,0,0,3,0,1},
{0,0,2,0,3,0,0,6,0,0,8,11,3},
{0,3,0,8,0,0,2,0,0,0,0,0,4},
{2,0,0,0,0,0,0,0,0,3,2,0,0},
{0,6,0,8,0,3,0,0,0,0,0,0,1},
{0,3,0,2,0,0,9,0,0,0,0,5,6},
{1,9,4,0,0,2,4,0,0,0,3,2,0},
{2,3,0,4,0,0,0,6,2,0,1,0,3},
{0,0,0,0,0,6,0,0,0,2,2,0,8},
{7,2,4,2,0,0,6,4,1,0,0,0,7},
{0,0,0,11,0,0,0,0,3,4,0,9,0},
{0,0,0,0,1,4,3,4,0,0,0,3,11},
{0,0,4,7,7,0,0,2,0,2,5,0,1},
},
{
{0,0,1,4,0,0,9,0,0,0,12,0,1},
{0,0,0,0,0,0,0,0,0,1,0,0,0},
{1,0,0,0,0,0,2,0,0,2,0,0,0},
{0,0,0,0,0,9,4,0,0,0,6,0,0},
{0,6,9,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,1,6,10,0,1,4},
{0,3,0,0,0,1,0,0,0,0,0,2,0},
{0,0,0,1,3,0,0,0,0,9,0,0,0},
{9,0,0,3,3,0,0,0,0,3,4,0,0},
{0,1,4,0,0,0,0,0,0,5,0,1,0},
{0,0,0,0,0,0,0,0,0,0,0,0,0},
{2,0,0,0,0,3,3,0,0,0,0,0,10},
{0,0,0,0,0,0,0,0,0,4,0,0,0},
},
// 15x13
{
{0, 0, 0, 4, 0, 0, 0, 6, 0, 0, 0, 0, 2, 2, 0},
{ 0,2,12,0,3,0,0,0,0,26,0,0,0,0,4 },
{ 0,0,0,0,0,0,0,0,0,0,0,0,2,2,0 },
{ 0,0,0,0,0,0,0,3,3,1,0,0,0,0,0 },
{ 0,0,0,0,0,1,3,0,0,6,4,0,0,0,0 },
{ 0,0,0,0,0,0,0,5,4,0,0,3,0,0,0 },
{ 9,12,0,0,0,4,1,6,0,0,0,0,0,0,0 },
{ 0,0,0,0,0,0,0,0,4,0,0,0,0,0,0 },
{ 0,3,0,0,0,2,0,0,0,7,0,4,0,0,0 },
{ 0,0,2,0,0,9,2,0,0,0,0,0,0,0,0 },
{ 0,2,0,0,2,16,0,8,0,2,0,0,0,0,7 },
{ 0,0,5,0,6,0,0,0,0,0,8,0,0,0,0 },
{ 0,4,0,0,0,0,0,0,1,2,3,0,0,0,0 },
},
{
{0,0,0,0,0,0,0,10,3,0,0,0,0,2,0},
{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
{0,1,0,3,0,0,0,0,4,0,0,0,4,0,0},
{0,0,0,10,3,8,11,0,0,0,0,0,2,0,0},
{0,4,0,0,0,0,0,0,0,0,0,2,0,0,1},
{0,4,2,0,0,0,4,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,5,0,10,0,0,1,0,0},
{0,0,0,0,0,0,0,0,3,0,0,2,8,0,15},
{0,1,0,0,0,0,0,0,0,0,0,0,11,0,0},
{0,0,0,0,0,3,0,0,0,0,0,1,0,2,0},
{0,0,0,11,0,0,0,0,0,0,0,0,0,0,2},
{8,0,0,0,0,4,0,0,0,0,0,4,2,0,4},
{0,0,0,0,0,0,0,0,0,0,0,0,0,8,1},
},
// 17x13
{
{0,0,0,3,0,0,0,0,5,0,0,0,0,0,0,0,0},
{0,0,0,0,3,0,0,0,4,2,0,3,0,0,0,0,0},
{6,0,3,0,0,0,6,0,30,0,1,8,6,10,0,0,0},
{0,7,0,1,4,0,0,1,0,0,0,0,0,0,0,4,4},
{0,2,0,0,1,0,0,0,0,0,0,0,0,0,0,0,3},
{0,8,0,0,0,0,0,3,0,0,36,0,1,0,0,2,0},
{6,0,0,0,8,2,8,0,0,2,0,0,0,0,0,0,0},
{8,1,0,0,0,0,4,1,0,0,0,0,0,6,7,0,0},
{3,5,0,0,0,0,0,0,0,0,5,0,0,4,0,0,1},
{3,0,0,2,0,4,0,0,0,0,9,0,0,0,8,16,24},
{0,1,0,0,1,1,0,0,2,0,0,0,0,0,6,1,0},
{0,3,4,0,3,4,0,10,0,0,0,0,5,5,8,4,4},
{8,0,0,0,0,0,17,0,0,10,0,2,0,0,2,0,0},
},
{
{0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0,0,1,4,0,0,0},
{0,6,0,0,0,0,8,0,10,0,0,0,0,2,2,3,0},
{0,0,0,0,0,4,0,8,3,0,0,0,0,0,0,0,0},
{0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0},
{4,0,8,1,0,0,7,0,0,0,0,0,5,3,0,0,0},
{0,0,3,0,1,0,0,3,0,0,3,0,3,0,8,0,0},
{0,0,0,0,11,0,0,0,0,0,0,0,0,0,0,1,0},
{0,6,0,0,0,0,0,1,0,2,0,1,0,0,0,0,0},
{0,0,2,0,1,3,0,1,0,4,0,0,6,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
{4,8,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0},
{4,0,0,0,0,0,0,0,0,0,0,0,3,0,0,6,3},
},
// 19x13
{
{0,0,0,0,4,0,0,4,0,0,8,0,6,0,0,0,0,0,4},
{0,0,0,0,0,2,0,6,0,0,0,0,0,0,0,0,0,3,1},
{2,0,8,3,0,0,0,5,0,4,0,0,0,0,0,2,1,4,0},
{0,0,1,0,4,0,0,0,0,0,0,1,0,0,0,18,10,0,0},
{0,0,1,0,0,0,0,3,0,2,0,0,0,7,4,0,0,4,3},
{0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,3,2,0,2},
{0,0,0,1,0,1,0,0,0,2,2,0,0,4,0,0,10,1,0},
{3,0,0,0,0,0,0,4,1,0,0,0,0,0,4,0,0,1,0},
{2,0,2,0,0,0,0,1,0,0,4,1,0,3,0,0,0,3,3},
{0,0,0,0,4,0,1,1,3,0,0,0,0,0,0,0,0,0,0},
{0,0,0,4,0,0,4,2,4,0,0,0,0,0,0,4,0,0,0},
{0,0,2,0,3,22,0,0,0,0,0,2,7,0,0,0,0,0,1},
{0,9,0,0,6,0,0,0,0,0,0,0,0,0,5,1,4,0,8},
},
{
{0,0,0,0,0,0,0,3,0,0,0,2,0,4,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0},
{5,0,0,3,0,0,0,0,0,0,0,2,1,0,0,0,0,0,0},
{0,0,4,0,0,0,0,0,14,0,0,0,0,2,0,7,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,2},
{0,0,3,3,0,0,0,0,0,3,0,0,0,0,0,0,2,0,0},
{5,0,0,0,0,0,6,0,2,2,0,0,0,0,0,0,0,0,0},
{0,0,2,0,0,0,0,0,4,0,0,5,0,0,0,0,0,4,1},
{3,0,4,0,0,0,0,0,0,0,0,7,2,0,0,0,1,0,3},
{0,0,1,0,0,4,11,0,3,0,0,0,0,11,3,0,0,0,0},
{1,0,2,8,0,0,0,0,0,0,4,0,0,3,1,0,0,0,0},
{3,0,11,0,0,0,0,0,0,0,0,0,0,3,0,7,0,0,0},
{0,0,0,0,0,2,0,0,0,4,0,0,0,0,0,0,0,1,0},
},
// 19x15
{
{0,0,0,0,0,0,0,0,16,1,0,5,0,3,0,0,0,4,0},
{0,2,3,0,0,5,0,0,0,0,0,0,0,6,0,0,0,1,0},
{0,9,1,0,0,0,0,4,2,2,0,1,8,2,0,4,24,10,13},
{0,0,3,0,0,0,0,2,0,0,4,0,11,0,0,0,2,1,1},
{0,4,0,1,0,0,0,0,0,0,0,0,0,0,0,4,0,0,2},
{0,3,12,0,4,0,0,0,0,0,0,0,10,0,0,0,0,0,0},
{0,0,2,0,0,0,9,0,0,0,0,0,8,4,0,0,0,0,0},
{3,0,0,0,0,2,0,0,6,0,3,0,6,0,0,0,0,0,0},
{1,0,0,0,0,0,0,2,5,0,0,12,2,4,0,0,7,0,1},
{6,4,4,0,0,8,0,3,2,0,0,0,9,0,0,0,0,0,0},
{0,0,0,0,0,0,4,8,0,0,2,0,0,8,0,0,0,0,2},
{0,4,18,0,0,0,0,0,0,0,4,1,2,0,0,0,0,8,3},
{2,0,7,0,7,0,0,9,0,0,0,0,0,0,0,2,0,0,0},
{0,0,0,0,7,2,0,0,1,0,0,0,0,0,0,11,0,30,0},
{1,0,0,0,0,0,0,7,0,0,0,3,0,0,0,0,0,0,0},
},
{
{0,0,0,0,14,0,0,0,0,0,0,0,3,7,0,0,0,0,0},
{0,2,5,7,2,0,0,0,6,0,0,0,1,0,0,3,0,0,1},
{0,7,0,2,0,0,0,0,0,0,0,10,0,0,4,2,0,0,0},
{0,0,0,4,0,0,0,5,0,0,0,0,0,0,0,0,0,2,0},
{0,0,5,0,4,0,0,3,4,0,0,0,3,0,0,0,0,7,0},
{0,0,0,0,0,0,3,0,6,0,0,5,0,4,0,0,0,0,0},
{2,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,3,0},
{0,0,7,0,4,0,0,0,0,0,1,0,0,0,8,0,0,0,0},
{4,0,0,0,0,0,0,0,7,0,7,0,0,0,0,0,8,0,3},
{8,0,0,0,0,0,2,6,2,0,0,0,0,0,3,0,0,0,0},
{0,0,0,0,0,0,0,12,0,0,0,4,0,0,8,0,0,0,0},
{0,0,4,0,0,0,0,0,0,13,0,2,0,0,0,0,0,1,0},
{0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,3,0,2,0},
{0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,7,0},
{0,0,0,0,0,0,4,0,0,0,2,0,0,0,0,0,2,0,3},
},
// 19x17
{
{0,2,0,0,0,4,4,0,0,4,0,0,1,6,0,1,4,0,0},
{1,0,0,2,0,0,0,0,0,0,0,0,9,3,0,0,0,0,0},
{3,0,0,4,0,9,1,0,0,1,0,0,0,6,0,0,0,0,0},
{0,4,0,0,4,4,0,0,0,0,0,0,12,0,0,0,0,1,0},
{0,0,0,3,0,6,0,0,0,0,3,0,0,11,17,0,0,0,0},
{6,0,1,0,0,6,0,0,1,0,0,0,0,0,0,0,0,0,0},
{0,1,0,1,0,0,0,0,0,0,6,0,0,1,0,0,0,2,0},
{1,0,10,0,0,2,2,0,3,4,8,0,0,9,11,1,0,16,0},
{3,0,0,0,0,0,0,0,4,0,0,7,0,0,7,0,0,0,0},
{0,6,0,1,0,0,0,0,3,5,0,0,2,4,0,0,0,0,0},
{0,0,0,0,0,6,0,3,6,0,10,6,0,0,0,0,0,0,2},
{3,0,0,4,4,0,2,0,0,0,1,0,0,1,2,16,11,0,0},
{7,0,0,3,0,0,0,0,0,10,12,0,0,0,0,0,0,0,0},
{0,2,0,0,0,0,2,2,0,0,0,0,0,0,0,4,1,0,1},
{0,0,0,0,0,0,0,1,2,6,3,0,0,0,0,0,7,0,0},
{0,0,1,0,4,8,0,0,0,0,0,6,0,0,0,6,0,0,0},
{0,0,0,0,0,0,0,2,0,2,0,0,0,7,0,0,0,0,2},
},
{
{3,4,0,0,3,0,0,0,0,6,0,4,4,0,0,0,4,0,0},
{4,0,0,5,0,0,0,0,7,3,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,1,0},
{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
{0,1,4,0,0,0,0,0,0,0,0,2,0,0,0,0,9,0,0},
{0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,4,0},
{4,0,0,5,0,0,0,0,0,0,0,11,0,0,0,0,0,3,0},
{2,0,7,0,0,11,0,0,0,0,0,0,5,0,7,0,0,0,0},
{9,0,0,0,1,0,1,15,0,0,0,0,1,0,0,0,1,4,3},
{0,3,0,0,0,0,0,2,0,0,0,2,0,0,0,0,0,0,0},
{0,0,2,4,0,0,0,0,0,0,0,0,4,0,1,0,0,0,0},
{0,2,0,0,0,7,0,4,0,0,0,0,0,0,0,0,0,9,0},
{0,0,6,0,0,0,2,0,1,0,0,0,0,0,0,0,0,1,0},
{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,3},
{0,0,0,0,0,1,0,0,0,0,0,0,9,0,0,0,7,0,0},
{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0},
{0,0,0,0,0,0,0,0,6,3,0,0,0,0,0,0,1,0,0},
},
};
