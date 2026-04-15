import java.io.FileNotFoundException;
import java.util.List;

//This class is responsible for setting up the entire project. Passing in all the appropiate stuff.
class Project2Main{

    public static void main(String[] args){
        Maze inputMaze;
        try {
            inputMaze = new Maze("Maze.txt");
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
            return;
        }

        System.out.println("Unsolved Maze:");
        inputMaze.print();


        MazeSolver solver = new MazeSolver(inputMaze);
        solver.solve();
        List<Cell> path = solver.getStackResult();

        System.out.println("\nDFS Path:");
            for (Cell c : path) {
            System.out.println("(" + c.row + ", " + c.col + ")");
        }

        System.out.println("Path length: " + path.size());

        path = solver.getQeueuResult();
        System.out.println("\nBFS Path:");
            for (Cell c : path) {
            System.out.println("(" + c.row + ", " + c.col + ")");
        }

        System.out.println("Path length: " + path.size());

        System.out.println("Directional now!");
        solver.printDirectionalPath();      
        solver.printAnalysis();
        
    }


} 