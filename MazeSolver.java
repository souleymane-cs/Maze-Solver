import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

//This class is responsible for solving the maze.
public class MazeSolver {
    Maze mainMaze;

    List<Cell> stackResult;
    char[][] stackResultMaze;
    int stackPopCount = 0;
    int finalStackPathLength;
    int largestStackSize;

    List<Cell> queueResult;
    char[][] queueResultMaze;
    int queuePollCount = 0;
    int finalQueuePathLength;
    int largestQueueSize;

    public MazeSolver(Maze maze){mainMaze = maze;}

    public void solve(){
        stackResult = solveWithStack(mainMaze.getMaze());
        finalStackPathLength = stackResult.size();
        queueResult = solveWithQueue(mainMaze.getMaze());
        finalQueuePathLength = queueResult.size();
    }
    //This method is responsible for calling the appropiate methods to setup for the Directional Path method. After it recieves the directional path mazes it then prints it.
    public void printDirectionalPath(){
        stackResultMaze = drawDirectonalPath(stackResult);
        queueResultMaze = drawDirectonalPath(queueResult);
        System.out.println("Stack:");
        print(stackResultMaze);
        System.out.println("\nQueue");
        print(queueResultMaze);

    }

    //This method is responsible for printing a 2D Matrix.
    public void print(char[][] matrix){
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[row].length; col++){
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
       }
    }

    //This method is responsible for printing the analysis required for part 5
    public void printAnalysis(){
        System.out.println("---Analysis---");
        System.out.println("Cells Visited:\nStack: " + stackPopCount +"\nQueue: " + queuePollCount);
        System.out.println("Final Path Count:\nStack: " + finalStackPathLength +"\nQueue: " + finalQueuePathLength);
        System.out.println("Max Frontier Size:\nStack: " + largestStackSize +"\nQueue: " + largestQueueSize);
    }

    //This method is responsible for validating the path
    public void validatePath(List<Cell> path, char[][] maze, String label){
        System.out.println("---Path Validation---");
        boolean allPass = false;
        //Check 1:
        boolean check1 = false;
        if(path != null && !(path.isEmpty())){
            System.out.println("[PASS] Check 1: Path is non-null and non-empty. (" + path.size() + " cells)");
            check1 = true;
        }
        else{
            System.out.println("[FAIL] Check 1: Path is not non-null and non-empty!");
        }

        //Check 2
        boolean check2 = false;
        Cell firstPos = path.get(0);
        if(maze[firstPos.row][firstPos.col] == 'S'){
            System.out.println("[PASS] Check 2: Path correctly starts at \'S\' (0,0).");
            check2 = true;            
        }
        else{
            System.out.println("[FAIL] Check 2: Path failed to start at S.");
        }

        //Check 3
        boolean check3 = false;
        Cell lastPos = path.get(path.size() - 1);
        if(maze[lastPos.row][lastPos.col] == 'E'){
            System.out.println("[PASS] Check 3: Path correctly ends at \'E\' (" + lastPos.row + "," + lastPos.col + ")");
            check3 = true;            
        }
        else{
            System.out.println("[FAIL] Check 3: Path failed to end at E.");
        }

        //Check 4
        boolean check4 = true;
        for (Cell c : path) {
            if (maze[c.row][c.col] == '#') {
                check4 = false;

        }

    }
        if(check4){
            System.out.println("[PASS] Check 4: No wall cells found in path.");
        }
        else{
            System.out.println("[FAIL] Check 4: Wall cells found in path.");
        }
    

}

    //Getters for the paths
    public List<Cell> getStackResult(){return stackResult;}
    public List<Cell> getQeueuResult(){return queueResult;}

    //This method is resposible for solving the maze using a Stack.
    public List<Cell> solveWithStack(char[][] maze){
        Stack<Cell>stack = new Stack<>();
        boolean[][] visited = new boolean[mainMaze.getRows()][mainMaze.getCols()];
        int max = 0;

        Cell start = new Cell(0,0, null);
        stack.push(start);
        visited[0][0] = true;

        //This loop keeps running as long as the stack isn't empty, it pops elements and checks its neighbors to see if they're valid moves. If they are it adds them to the stack and keeps track of the path.
        while(!(stack.isEmpty())){
            if(stack.size() > max) max = stack.size();
            Cell poppedElement = stack.pop();
            stackPopCount++;

            
            //This checks to see if the current cell is the finish line.
            if(mainMaze.getMaze()[poppedElement.row][poppedElement.col] == 'E'){
                largestStackSize = max;
                return buildPath(poppedElement);
            }


            //This checks above the current cell
            if(isValid((poppedElement.row) - 1, poppedElement.col, visited)){
                int newRow = poppedElement.row - 1;
                int newCol = poppedElement.col;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                stack.push(temp);
            }

            //This checks below the currect cell
            if(isValid((poppedElement.row) + 1, poppedElement.col, visited)){
                int newRow = poppedElement.row + 1;
                int newCol = poppedElement.col;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                stack.push(temp);
            }            
            //This checks to the left of the current cell
            if(isValid((poppedElement.row), poppedElement.col - 1, visited)){
                int newRow = poppedElement.row;
                int newCol = poppedElement.col - 1;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                stack.push(temp);
            }                                 
            //This checks to the right of the current cell
            if(isValid((poppedElement.row), poppedElement.col + 1, visited)){
                int newRow = poppedElement.row;
                int newCol = poppedElement.col + 1;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                stack.push(temp);
            }                  


        }
        largestStackSize = max;
        return new LinkedList<Cell>();
    }

    //This method is resposible for solving the maze using a Queue.
    public List<Cell> solveWithQueue(char[][] maze){
        Queue<Cell> queue = new LinkedList<>();
       boolean[][] visited = new boolean[mainMaze.getRows()][mainMaze.getCols()];
       int max = 0;

        Cell start = new Cell(0,0, null);
        queue.offer(start);
        visited[0][0] = true;

        //This loop keeps running as long as the queue isn't empty, it polls elements and checks its neighbors to see if they're valid moves. If they are it adds them to the queue and keeps track of the path.
        while(!(queue.isEmpty())){
            Cell poppedElement = queue.poll();
            queuePollCount++;
            if(queue.size() > max) max = queue.size();
            
            //This checks to see if the current cell is the finish line.
            if(mainMaze.getMaze()[poppedElement.row][poppedElement.col] == 'E'){
                largestQueueSize = max;
                return buildPath(poppedElement);
            }


            //This checks above the current cell
            if(isValid((poppedElement.row) - 1, poppedElement.col, visited)){
                int newRow = poppedElement.row - 1;
                int newCol = poppedElement.col;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                queue.offer(temp);
            }

            //This checks below the currect cell
            if(isValid((poppedElement.row) + 1, poppedElement.col, visited)){
                int newRow = poppedElement.row + 1;
                int newCol = poppedElement.col;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                queue.offer(temp);
            }            
            //This checks to the left of the current cell
            if(isValid((poppedElement.row), poppedElement.col - 1, visited)){
                int newRow = poppedElement.row;
                int newCol = poppedElement.col - 1;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                queue.offer(temp);
            }                                 
            //This checks to the right of the current cell
            if(isValid((poppedElement.row), poppedElement.col + 1, visited)){
                int newRow = poppedElement.row;
                int newCol = poppedElement.col + 1;
                Cell temp = new Cell(newRow, newCol, poppedElement);
                visited[newRow][newCol] = true;
                queue.offer(temp);
            }                  


        }
        largestQueueSize = max;
        return new LinkedList<Cell>();
    }

    //This method is responsible for checking if a move in the maze is possible and has not been visited already.
    private boolean isValid(int row, int col, boolean[][] visited){
        return  row >= 0 && col >= 0 && row < mainMaze.getRows() && col < mainMaze.getCols() 
        && !(visited[row][col]) && mainMaze.getMaze()[row][col] != '#';
    }

    //This method is responsible for building the winning path by traversing through the cells found.
    public List<Cell> buildPath(Cell lastCell){
        List<Cell> path = new LinkedList<>();
        
        //This loop is responsible for backtracking through the cells and adding it to a linked list. The linkedlist is then later reversed using a  method from collecitons.
        while(lastCell!= null){
            path.add(lastCell);
            lastCell = lastCell.parent;
        }
        Collections.reverse(path);
        return path;
    }
    //This method is resposible for drawing directional path.
    public char[][] drawDirectonalPath(List<Cell> path){
        char[][] directionalMaze = new char[mainMaze.getRows()][mainMaze.getCols()];

        //This loop is responsible for copying over the values of the maze.
        for(int row = 0; row < directionalMaze.length; row++){
            for(int col = 0; col < directionalMaze[row].length; col++){
                directionalMaze[row][col] = mainMaze.getMaze()[row][col];
            }
        }

        //This loops is responsible for checking whether or not two cells are in the same row. If they are then it moved left to right and the symbol is -, otherwise it is |.
        for(int index = 1; index < path.size() - 1; index++){
            Cell current = path.get(index);
            Cell compare =  path.get(index + 1);

            if(current.row == compare.row){
                directionalMaze[current.row][current.col] = '-';
            }
            else{
                directionalMaze[current.row][current.col] = '|';
            }
        }

        return directionalMaze;
    }
}
