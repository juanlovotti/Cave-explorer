
import java.util.Scanner;

// Create class game
public class Game {
    // attribute Player type to represent the player
    private Player player;
    // attribute Cave type to represent the cave
    private Cave cave;

    // constructor
    public Game() {
        //initialize a new object Player
        player = new Player();
        //initialize a new object cave
        cave = new Cave();
    }

    public void start() {
        // Instance of the Scanner class.
        Scanner scanner = new Scanner(System.in);

        // Display welcome message and rules
        System.out.println("Welcome to Cave Explorer!");
        System.out.println("Collect treasures (T) and avoid traps (X)!");
        System.out.println("Commands: <L>: turn left <R>: turn right <F>: move forward <E>: exit");

        // Initial display of the grid
        cave.displayGrid(player);

        // Here we will store the users input, I put it as String to avoid errors.
        String move;

        while (true) {
            System.out.print("Score: " + player.getScore());
            System.out.println(" pts");
            System.out.print("Enter your move: ");
            // Here I read the input using the scanner and then with the .toUpperCase
            // I convert it into a capital word.
            // this allows that every time a letter is entered, it does not matter if
            // it is lowercase or uppercase since the program automatically converts the letter to uppercase.
            move = scanner.nextLine().toUpperCase();

            switch (move) {
                case "L":
                    // if L was entered, we call the method "turnLeft" of the player class to turn left
                    player.turnLeft();
                    // reveals the cells in the player's new direction after turning
                    player.revealCells(cave, player.getDirection());
                    break;
                case "R":
                    // if R was entered, we call the method "turnRight" of the player class to turn right
                    player.turnRight();
                    // reveals the cells in the player's new direction after turning
                    player.revealCells(cave, player.getDirection());
                    break;
                case "F":
                    // If “F” was entered, we call the “moveForward” method of the Player class to move the player forward.
                    player.moveForward(cave);
                    break;
                case "E":
                    // if 'move' equals to 'E', game displays the player's final score and exits the loop
                    System.out.println("Exiting the game. Final score: " + player.getScore());
                    return; // allows get off the loop
                //if movement is invalid
                default:
                    //shows a message
                    System.out.println("Invalid move.");
            }

            // Reveal visibility and display grid after each move
            cave.displayGrid(player);
        }
    }
}