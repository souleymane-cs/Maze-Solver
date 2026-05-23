import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
//This class is responsible for generating a maze.
public class MazeGenerator {

    public MazeGenerator(){}

    //This method is responsible for generating the maze. 
    public char[][] generateMaze(int rows, int cols, double wallDensity, String outputFile){
        Random rand = new Random();

        validate(rows, cols, wallDensity);
        char[][] maze = new char[rows][cols];

        //This loop is responsible for building the maze.
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(row == 0 && col == 0){
                maze[row][col] = 'S';
                }
                else if(row == rows - 1 && col == cols - 1){
                    maze[row][col] = 'E';
                }
                else{
                    if(rand.nextDouble() < wallDensity){
                        maze[row][col] = '#';
                    }
                    else{
                        maze[row][col] = '.';
                    }
                }

            }
        }

        try{
            FileWriter fileWriter = new FileWriter(outputFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(rows + " " + cols);

            //This loop is responsible for writing the maze to the file.
            for(int row = 0; row < rows; row++){
                for(int col = 0; col < cols; col++){
                    printWriter.print(maze[row][col]);
                    if(col < cols - 1){
                        printWriter.print(" ");
                    }
                }
                printWriter.println();
            }

            printWriter.close();

        } catch (IOException e){
            System.out.println("There was an error writing to the file.");
        }
        
        return maze;
    }        
    private void validate(int rows, int cols, double wallDensity){
        if(rows < 3 || cols < 3){
            throw new IllegalArgumentException("Dimensions of the maze require both the row and column to be atleast 3!");
        }

        if(wallDensity < 0.0 || wallDensity > 1.0){
            throw new IllegalArgumentException("Wall density must be between 0.0 and 1.0");
        }

    }
}
