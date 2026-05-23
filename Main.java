import java.io.FileNotFoundException;
import java.util.List;

//This class is responsible for setting up the entire project. Passing in all the appropiate stuff.
class Main{

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

        solver.validatePath(path, inputMaze.getMaze(), "Queue 1");
        System.out.println("\nWall Density Experiment:");
        wallDenisty();

    }

    //This method conducts the wall density experiment.
    public static void wallDenisty(){
        DensityExperiment exp = new DensityExperiment();
        double[] densities = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        System.out.println("Wall Density | Solvable | Solvability%");

        //This loop is responsible for printing the findings in the wall density experiment.
        for(int index = 0; index < densities.length; index++){
            int solvable = exp.run(densities[index]);
            System.out.printf("%.2f            %d / 50        %.1f%%\n", densities[index], solvable,(solvable/50.0)*100.0 );
        }
    }
   


} 