package co.grandcircus.Maze.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//This class contains all the code for a breadth-first search algorithm to determine the shortest path through any given maze
public class ShortestPathChecker {
	
	//Possible Coordinate directions (x, y) to implement BFS
	private static final int[][] POSSIBLE_DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	//Algorithm to solve the maze 
    public List<Coordinate> solve(Maze maze) {
    	
    	//Initialize a nextToVisit LinkedList and add the starting point
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate startPoint = maze.getMazeStart();
        nextToVisit.add(startPoint);

        //While the nextToVisitList is populated with viable coordinates, this checks the state of them, removing each coordinate as it's evaluated 
        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            //if this point isn't valid or has already been explored, continue to the next point
            if (!maze.isThisValidLocation(cur.getX(), cur.getY()) || maze.isThisCellExplored(cur.getX(), cur.getY())) {
                continue;
            }
            
            //If this point is a wall, set it as visited and continue
            if (maze.isThisWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }
            
            //If this point is the end of the maze, call the backtrackPath method to add the shortest path to an ArrayList
            if (maze.isThisMazeEnd(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            //Adds all possible direction coordinates from the current coordinate (cur) via enhanced for loop. Also uses the Coordinate 
            //constructor with parentCoordinate to log the current coordinate's parent. 
            for (int[] direction : POSSIBLE_DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    //Starts at the end point of the maze (cur) and traces the parentage back to the beginning
    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate pathCoordinate = cur;

        while (pathCoordinate != null) {
            path.add(pathCoordinate);
            pathCoordinate = pathCoordinate.parentCoordinate;
        }

        return path;
    }
}

