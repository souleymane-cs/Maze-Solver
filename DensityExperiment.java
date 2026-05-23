
//This class is responsible for task 10. The density experiment.
public class DensityExperiment {
    MazeGenerator generator;

    //This constructor is resposnible for initializing the Maze generator.
    public DensityExperiment(){
        generator = new MazeGenerator();
    }

    //This method is responsible for running the generator and counting how many methods are solveable.
    public int run(double density){
        int solvableCounter = 0;
        //This loop is responsible for running the solver 50 times to check if it is possible for a generated maze to be solved.
        System.out.println("The density for this maze is currently: " + density);
        for(int count = 0; count < 5; count++){
            char[][] arrayMaze = generator.generateMaze(5, 7, density, "Output");
            Maze maze = new Maze(arrayMaze);
            MazeSolver solver = new MazeSolver(maze);
            maze.print();
            if(solver.isReachable()){solvableCounter++;}

        }

        return solvableCounter;
    }
}
