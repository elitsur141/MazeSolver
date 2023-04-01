/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> s = new Stack<MazeCell>();

        // Push all solutions into a stack with endCell at the bottom
        MazeCell current = maze.getEndCell();
        s.push(current);
        while (current != maze.getStartCell())
        {
            MazeCell parent = current.getParent();
            s.push(parent);
            current = parent;
        }
        // Add each solution from the stack to the ArrayList
        int size = s.size();
        for (int i = 0; i < size; i++)
        {
            solution.add(s.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell current = maze.getStartCell();
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        while (current != maze.getEndCell())
        {
            int row = current.getRow();
            int col = current.getCol();
            // Check north cell
            if (maze.isValidCell(row - 1, col))
            {
                cellsToVisit.push(maze.getCell(row-1, col));
                maze.getCell(row-1, col).setExplored(true);
                maze.getCell(row-1, col).setParent(current);
            }
            // Check east cell
            if (maze.isValidCell(row, col+1))
            {
                cellsToVisit.push(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setExplored(true);
                maze.getCell(row, col+1).setParent(current);
            }
            // Check south cell
            if (maze.isValidCell(row+1, col))
            {
                cellsToVisit.push(maze.getCell(row+1, col));
                maze.getCell(row+1, col).setExplored(true);
                maze.getCell(row+1, col).setParent(current);
            }
            // Check west cell
            if (maze.isValidCell(row, col-1))
            {
                cellsToVisit.push(maze.getCell(row, col-1));
                maze.getCell(row, col-1).setExplored(true);
                maze.getCell(row, col-1).setParent(current);
            }
            current = cellsToVisit.pop();
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        MazeCell current = maze.getStartCell();
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        while (current != maze.getEndCell())
        {
            int row = current.getRow();
            int col = current.getCol();
            // Check north cell
            if (maze.isValidCell(row - 1, col))
            {
                cellsToVisit.add(maze.getCell(row-1, col));
                maze.getCell(row-1, col).setExplored(true);
                maze.getCell(row-1, col).setParent(current);
            }
            // Check east cell
            if (maze.isValidCell(row, col+1))
            {
                cellsToVisit.add(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setExplored(true);
                maze.getCell(row, col+1).setParent(current);
            }
            // Check south cell
            if (maze.isValidCell(row+1, col))
            {
                cellsToVisit.add(maze.getCell(row+1, col));
                maze.getCell(row+1, col).setExplored(true);
                maze.getCell(row+1, col).setParent(current);
            }
            // Check west cell
            if (maze.isValidCell(row, col-1))
            {
                cellsToVisit.add(maze.getCell(row, col-1));
                maze.getCell(row, col-1).setExplored(true);
                maze.getCell(row, col-1).setParent(current);
            }
            current = cellsToVisit.remove();
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
