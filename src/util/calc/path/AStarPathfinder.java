package util.calc.path;
import java.awt.*;
import java.util.*;

/**
 * Created by Suwadith 2015214 on 3/28/2017.
 */


public class AStarPathfinder {

    static AStarNode[][] cell;
    static ArrayList<AStarNode> pathList = new ArrayList<>();
    static ArrayList<AStarNode> closedList = new ArrayList<>();
    static boolean additionalPath = false;

    /**
     * @param matrix         The boolean matrix that the framework generates
     * @param Ai             Starting point's x value
     * @param Aj             Starting point's y value
     * @param Bi             Ending point's x value
     * @param Bj             Ending point's y value
     * @param n              Length of one side of the matrix
     * @param v              Cost between 2 cells located horizontally or vertically next to each other
     * @param d              Cost between 2 cells located Diagonally next to each other
     * @param additionalPath Boolean to decide whether to calculate the cost of through the diagonal path
     * @param h              int value which decides the correct method to choose to calculate the Heuristic value
     */
    public static void generateHValue(boolean matrix[][], int Ai, int Aj, int Bi, int Bj, int n, int v, int d, boolean additionalPath, int h) {

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                //Creating a new AStarNode object for each and every Cell of the Grid (Matrix)
                cell[y][x] = new AStarNode(y, x);
                //Checks whether a cell is Blocked or Not by checking the boolean value
                if (matrix[y][x]) {
                    if (Math.abs(y - Bi) > Math.abs(x - Bj)) {
                        cell[y][x].hValue = Math.abs(y - Bi);
                    } else {
                        cell[y][x].hValue = Math.abs(x - Bj);
                    }
                } else {
                    //If the boolean value is false, then assigning -1 instead of the absolute length
                    cell[y][x].hValue = -1;
                }
            }
        }
        generatePath(cell, Ai, Aj, Bi, Bj, n, v, d, additionalPath);
    }


    public static Point calcPath(boolean[][] matrix, int Ai, int Aj, int Bi, int Bj) {
        //Creation of a AStarNode type 2D array
        cell = new AStarNode[matrix.length][matrix.length];
        
        //Method to generate Chebyshev path. Both Horizontal and Diagonal pathways are possible.
        generateHValue(matrix, Ai, Aj, Bi, Bj, cell.length, 10, 10, true, 1);
        Point firstStep = new Point(-1,-1);
        //Checks whether the end point has been reach (Stored in the pathList)
        if (cell[Ai][Aj].hValue!=-1&&pathList.contains(cell[Bi][Bj])) {
        	firstStep.x = pathList.get(pathList.size()-2).x;
        	firstStep.y = pathList.get(pathList.size()-2).y;
        	System.out.println("found path");
        }
        //Clears Both the pathList and the closedList
        pathList.clear();
        closedList.clear();
        return firstStep;
        
    }
    public static int getDist(boolean[][] matrix, int Ai, int Aj, int Bi, int Bj) {
        cell = new AStarNode[matrix.length][matrix.length];
        
        //Method to generate Chebyshev path. Both Horizontal and Diagonal pathways are possible.
        generateHValue(matrix, Ai, Aj, Bi, Bj, cell.length, 10, 10, true, 1);
        int result = 0;
        //Checks whether the end point has been reach (Stored in the pathList)
        if (cell[Ai][Aj].hValue!=-1&&pathList.contains(cell[Bi][Bj])) {
        	result =  pathList.size();
        }
        //Clears Both the pathList and the closedList
        pathList.clear();
        closedList.clear();
        return result;
    }

    /**
     * @param hValue         AStarNode type 2D Array (Matrix)
     * @param Ai             Starting point's y value
     * @param Aj             Starting point's x value
     * @param Bi             Ending point's y value
     * @param Bj             Ending point's x value
     * @param n              Length of one side of the matrix
     * @param v              Cost between 2 cells located horizontally or vertically next to each other
     * @param d              Cost between 2 cells located Diagonally next to each other
     * @param additionalPath Boolean to decide whether to calculate the cost of through the diagonal path
     */
    public static void generatePath(AStarNode hValue[][], int Ai, int Aj, int Bi, int Bj, int n, int v, int d, boolean additionalPath) {

        //Creation of a PriorityQueue and the declaration of the Comparator
        PriorityQueue<AStarNode> openList = new PriorityQueue<>(11, new Comparator() {
            @Override
            //Compares 2 AStarNode objects stored in the PriorityQueue and Reorders the Queue according to the object which has the lowest fValue
            public int compare(Object cell1, Object cell2) {
                return ((AStarNode) cell1).fValue < ((AStarNode) cell2).fValue ? -1 :
                        ((AStarNode) cell1).fValue > ((AStarNode) cell2).fValue ? 1 : 0;
            }
        });

        //Adds the Starting cell inside the openList
        openList.add(cell[Ai][Aj]);

        //Executes the rest if there are objects left inside the PriorityQueue
        while (true) {

            //Gets and removes the objects that's stored on the top of the openList and saves it inside node
            AStarNode node = openList.poll();

            //Checks if whether node is empty and f it is then breaks the while loop
            if (node == null) {
                break;
            }

            //Checks if whether the node returned is having the same node object values of the ending point
            //If it des then stores that inside the closedList and breaks the while loop
            if (node == cell[Bi][Bj]) {
                closedList.add(node);
                break;
            }

            closedList.add(node);

            //Left Cell
            try {
                if (cell[node.x][node.y - 1].hValue != -1
                        && !openList.contains(cell[node.x][node.y - 1])
                        && !closedList.contains(cell[node.x][node.y - 1])) {
                    double tCost = node.fValue + v;
                    cell[node.x][node.y - 1].gValue = v;
                    double cost = cell[node.x][node.y - 1].hValue + tCost;
                    if (cell[node.x][node.y - 1].fValue > cost || !openList.contains(cell[node.x][node.y - 1]))
                        cell[node.x][node.y - 1].fValue = cost;

                    openList.add(cell[node.x][node.y - 1]);
                    cell[node.x][node.y - 1].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            //Right Cell
            try {
                if (cell[node.x][node.y + 1].hValue != -1
                        && !openList.contains(cell[node.x][node.y + 1])
                        && !closedList.contains(cell[node.x][node.y + 1])) {
                    double tCost = node.fValue + v;
                    cell[node.x][node.y + 1].gValue = v;
                    double cost = cell[node.x][node.y + 1].hValue + tCost;
                    if (cell[node.x][node.y + 1].fValue > cost || !openList.contains(cell[node.x][node.y + 1]))
                        cell[node.x][node.y + 1].fValue = cost;

                    openList.add(cell[node.x][node.y + 1]);
                    cell[node.x][node.y + 1].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            //Bottom Cell
            try {
                if (cell[node.x + 1][node.y].hValue != -1
                        && !openList.contains(cell[node.x + 1][node.y])
                        && !closedList.contains(cell[node.x + 1][node.y])) {
                    double tCost = node.fValue + v;
                    cell[node.x + 1][node.y].gValue = v;
                    double cost = cell[node.x + 1][node.y].hValue + tCost;
                    if (cell[node.x + 1][node.y].fValue > cost || !openList.contains(cell[node.x + 1][node.y]))
                        cell[node.x + 1][node.y].fValue = cost;

                    openList.add(cell[node.x + 1][node.y]);
                    cell[node.x + 1][node.y].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            //Top Cell
            try {
                if (cell[node.x - 1][node.y].hValue != -1
                        && !openList.contains(cell[node.x - 1][node.y])
                        && !closedList.contains(cell[node.x - 1][node.y])) {
                    double tCost = node.fValue + v;
                    cell[node.x - 1][node.y].gValue = v;
                    double cost = cell[node.x - 1][node.y].hValue + tCost;
                    if (cell[node.x - 1][node.y].fValue > cost || !openList.contains(cell[node.x - 1][node.y]))
                        cell[node.x - 1][node.y].fValue = cost;

                    openList.add(cell[node.x - 1][node.y]);
                    cell[node.x - 1][node.y].parent = node;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            if (additionalPath) {

                //TopLeft Cell
                try {
                    if (cell[node.x - 1][node.y - 1].hValue != -1
                            && !openList.contains(cell[node.x - 1][node.y - 1])
                            && !closedList.contains(cell[node.x - 1][node.y - 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x - 1][node.y - 1].gValue = d;
                        double cost = cell[node.x - 1][node.y - 1].hValue + tCost;
                        if (cell[node.x - 1][node.y - 1].fValue > cost || !openList.contains(cell[node.x - 1][node.y - 1]))
                            cell[node.x - 1][node.y - 1].fValue = cost;

                        openList.add(cell[node.x - 1][node.y - 1]);
                        cell[node.x - 1][node.y - 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                //TopRight Cell
                try {
                    if (cell[node.x - 1][node.y + 1].hValue != -1
                            && !openList.contains(cell[node.x - 1][node.y + 1])
                            && !closedList.contains(cell[node.x - 1][node.y + 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x - 1][node.y + 1].gValue = d;
                        double cost = cell[node.x - 1][node.y + 1].hValue + tCost;
                        if (cell[node.x - 1][node.y + 1].fValue > cost || !openList.contains(cell[node.x - 1][node.y + 1]))
                            cell[node.x - 1][node.y + 1].fValue = cost;

                        openList.add(cell[node.x - 1][node.y + 1]);
                        cell[node.x - 1][node.y + 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                //BottomLeft Cell
                try {
                    if (cell[node.x + 1][node.y - 1].hValue != -1
                            && !openList.contains(cell[node.x + 1][node.y - 1])
                            && !closedList.contains(cell[node.x + 1][node.y - 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x + 1][node.y - 1].gValue = d;
                        double cost = cell[node.x + 1][node.y - 1].hValue + tCost;
                        if (cell[node.x + 1][node.y - 1].fValue > cost || !openList.contains(cell[node.x + 1][node.y - 1]))
                            cell[node.x + 1][node.y - 1].fValue = cost;

                        openList.add(cell[node.x + 1][node.y - 1]);
                        cell[node.x + 1][node.y - 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                //BottomRight Cell
                try {
                    if (cell[node.x + 1][node.y + 1].hValue != -1
                            && !openList.contains(cell[node.x + 1][node.y + 1])
                            && !closedList.contains(cell[node.x + 1][node.y + 1])) {
                        double tCost = node.fValue + d;
                        cell[node.x + 1][node.y + 1].gValue = d;
                        double cost = cell[node.x + 1][node.y + 1].hValue + tCost;
                        if (cell[node.x + 1][node.y + 1].fValue > cost || !openList.contains(cell[node.x + 1][node.y + 1]))
                            cell[node.x + 1][node.y + 1].fValue = cost;

                        openList.add(cell[node.x + 1][node.y + 1]);
                        cell[node.x + 1][node.y + 1].parent = node;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }

        /*for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(cell[i][j].fValue + "    ");
            }
            System.out.println();
        }*/

        //Assigns the last Object in the closedList to the endNode variable
        AStarNode endNode = closedList.get(closedList.size() - 1);

        //Checks if whether the endNode variable currently has a parent AStarNode. if it doesn't then stops moving forward.
        //Stores each parent AStarNode to the PathList so it is easier to trace back the final path
        while (endNode.parent != null) {
            AStarNode currentNode = endNode;
            pathList.add(currentNode);
            endNode = endNode.parent;
        }

        pathList.add(cell[Ai][Aj]);
        //Clears the openList
        openList.clear();
    }
}