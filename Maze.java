import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//This class is responsible for reading in the maze and storing its appropiate content.
public class Maze {
    char[][] maze;
    int rows;
    int cols;

        //This method is responsible for reading in the input file.
        public Maze(String inputFile) throws FileNotFoundException{
            File file = new File(inputFile);
            Scanner sc = new Scanner(file);
            rows = sc.nextInt();
            cols = sc.nextInt();
            
            maze = new char[rows][cols];

         boolean sFound = false;
         boolean eFound = false;
         //This loop uses a scanner to read in each individual token of the Maze. It checks if there are more than one S's and E's. Throwing the appropiate exception if that is the case.
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                String token = sc.next();
                char tok = token.charAt(0);

                maze[row][col] = (tok);
                if(tok == 'S'){
                    if(sFound){
                        sc.close();
                        throw new IllegalArgumentException("S appears more than once in the text file!");
                    }
                    sFound = true;
                }
                if(tok == 'E'){
                    if(eFound){
                        sc.close();
                        throw new IllegalArgumentException("E appears more than once in the text file!");
                    }
                    eFound = true;
                }        
            
            }
        }

        sc.close();
        if(!sFound){throw new IllegalArgumentException("S does not appear to be in the text file!");}
        if(!eFound){throw new IllegalArgumentException("E does not appear to be in the text file!");}
    }
    public Maze(char[][] maze){
        this.maze = maze;
        rows = maze.length;
        cols = maze[0].length;
    }

    //This method is responsible for printing the maze.
    public void print(){
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                System.out.print(maze[row][col] + " ");
            }
            System.out.println();
        }
    }

    //These are getters for the maze, row size, and column size.
    public char[][] getMaze(){return maze;};
    public int getRows(){return rows;}
    public int getCols(){return cols;};

}

