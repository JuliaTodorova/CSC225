package solution;
import java.io.*;
import java.util.*;

public class Solution {
    static final char BLOCKED_INTERSECTION_CHAR = '#';
    static final char STARTING_POINT_CHAR = 'A';
    static final char FINAL_POINT_CHAR = 'B';
    static final int DEBUG_LEVEL = 0;
    
    public static void main(String[] args) {
         Object[] userInput = getCityMap();
         
         Character [][] cityMapArray = (Character [][])userInput[0];
         Cell startingPoint = (Cell)userInput[1];
         
         if(DEBUG_LEVEL >= 4){
            printMe(cityMapArray);
         }
         int shortestDistance = findShortestDistance(cityMapArray, startingPoint);
        
         if (shortestDistance == -1) {
             System.out.print("IMPOSSIBLE");
         } 
         else{
             System.out.println(shortestDistance);
         }
    }
    
    public static Object[] getCityMap(){
        FastReader userInput = new FastReader();
        int sizeOfArray = userInput.nextInt();
        debug("Building maxtrix of size " + sizeOfArray, 4);
        Character [][] cityMapArray = new Character [sizeOfArray][sizeOfArray];
        Cell startingPoint = null;
        
        for(int i=0;i<sizeOfArray;i++){
            String cityMapRow = userInput.next();
            debug("Current row" + cityMapRow, 4);
            
            for(int j=0;j<sizeOfArray;j++){
                char value = cityMapRow.charAt(j);
                cityMapArray[j][i] = value;
                
                if(value == STARTING_POINT_CHAR){
                    startingPoint = new Cell(j,i);
                }
            }   
        }
        return new Object[] {cityMapArray, startingPoint};
    }
    
    public static int findShortestDistance(Character [][] cityMapArray, Cell startingCell){
        Integer [][] distances = new Integer [cityMapArray.length][cityMapArray.length];
        Boolean [][] visited = initializeVisted(cityMapArray.length);
        Queue<Cell> q = new ArrayDeque<>();
        
        // Set up starting cell
        q.add(startingCell);
        setDistance(distances, startingCell, 0);
        
        while(!q.isEmpty()){
            Cell cell = q.poll();
            if (visited[cell.col][cell.row]) {
                continue;
            }
            
            debug("==========================", 1);
            debug("Current cell " + cell, 1);
            debug("==========================", 1);

            // Mark current cell
            visited[cell.col][cell.row] = true;
            
            debug("Visited", 2);
            if(DEBUG_LEVEL >= 2){
                printMe(visited);
            }
            
            int currentCellDistance = getDistance(distances, cell);

            if(cityMapArray[cell.col][cell.row] == FINAL_POINT_CHAR){
                return currentCellDistance;
            }
            
            // Check neighbours if visited
            ArrayList <Cell> neighbours = getNeighbours(cityMapArray, cell);
            printNeighbours(neighbours);
            
            for(Cell neighbour : neighbours){
                if(!visited[neighbour.col][neighbour.row]){
                    debug("Adding neighbour " + neighbour, 3);
                    q.add(neighbour);
                    setDistance(distances, neighbour, currentCellDistance + 1);
                    
                    debug("Distances", 1);
                    if(DEBUG_LEVEL >= 1){
                        printMe(distances);
                    }
                }
            }
        }
        return -1;
    }
    
    public static void printNeighbours(ArrayList <Cell> neighbours) {
        debug("Neightbours:", 3);
        debug("[ ", 3);
        for (Cell neighbour : neighbours) {
            debug("[" + neighbour.col + ", " + neighbour.row + "]", 3);
        }
        debug(" ]\n", 3);
    }
    
    public static void debug(String string, int level){
        if(DEBUG_LEVEL >= level){
            System.out.println(string);
        }
    }
    
    public static void setDistance(Integer [][] distances, Cell cell, int value){
        distances[cell.col][cell.row] = value;
    }
    
    public static int getDistance(Integer [][] distances, Cell cell){
        return distances[cell.col][cell.row];
    }
    
    public static ArrayList<Cell> getNeighbours(Character [][] cityMapArray, Cell currentCell){
        int row = currentCell.row;
        int col = currentCell.col;
        ArrayList<Cell> neighbours = new ArrayList<Cell>();
        
        // Top Neighbour
        if(row - 1 >= 0){
            int rowAbove = row - 1 ;
            if(cityMapArray[col][rowAbove] != BLOCKED_INTERSECTION_CHAR){
                Cell cellAbove = new Cell(col, rowAbove);
                debug("Adding top neighbour " + cellAbove, 4);
                neighbours.add(cellAbove);
            }
        }
        
        // Neighbour to the right
        if(col + 1 < cityMapArray.length ){
            int colToRight = col + 1;
            
            debug("Right element is " + cityMapArray[colToRight][row], 4);
            if(cityMapArray[colToRight][row] != BLOCKED_INTERSECTION_CHAR){
                Cell cellToRight = new Cell(colToRight, row);
                debug("Adding right neighbour " + cellToRight, 4);
                neighbours.add(cellToRight);
            }
        }
        else{
            debug("Skipping right neighbour " + col + 1 + ", " + row, 4);
        }
        
        // Neighbour below
        if((row + 1 < cityMapArray.length)){
            int rowBelow = row + 1;
            if(cityMapArray[col][rowBelow] != BLOCKED_INTERSECTION_CHAR){
                Cell cellBelow = new Cell(col, rowBelow);
                debug("Adding bottom neighbour " + cellBelow, 4);
                neighbours.add(cellBelow);
            }
        }
        
        // Neighbour to the left
        if(col - 1 >= 0){
            int colToLeft = col - 1;
            if(cityMapArray[colToLeft][row] != BLOCKED_INTERSECTION_CHAR){
                Cell cellToLeft = new Cell(colToLeft, row);
                debug("Adding left neighbour " + cellToLeft, 4);
                neighbours.add(cellToLeft);
            }
        }
        return neighbours;
    }
    
    
    public static Boolean [][] initializeVisted(int size){
        Boolean [][] visited = new Boolean [size][size];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                 visited[i][j] = false;
            }
        }
        return visited;
    }
    
    public static void printMe(Object [][] array){
        for(int col = 0; col<array.length;col++){
            for(int row =0; row<array.length;row++){
               System.out.print(array[row][col] + "\t");
            }
           System.out.println();
        }
    }
    private static class Cell {

        int row;
        int col;

        Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }
        
        public String toString(){
            return "[" + this.col + ", " + this.row + "]";
        }
    }
    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }     
}
