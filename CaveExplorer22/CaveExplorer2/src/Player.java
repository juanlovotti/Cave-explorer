
public class Player {
    private int x, y; // player coordinates
    private String direction; // "N: north", "E: east", "S: south", "W: west"
    private int score; //player's score

    // Constructor of the class player
    public Player() {
        this.x = 0; // initial position of the player in X --> started on zero
        this.y = 0; // initial position of the player in Y --> started on zero
        this.direction = "N"; //starts pointing north
        this.score = 0; //initial score zero
    }

    // methods used to change the direction of the Player (this.direction).
    // method to turn left
    public void turnLeft() {
        switch (direction) {
            case "N": // if the player is facing north
                direction = "W";//changes to west
                break;
            case "W":// if the player is facing west
                direction = "S";// changes to south
                break;
            case "S":// if the player is facing south
                direction = "E";// changes to east
                break;
            case "E":// if the player is facing east
                direction = "N";// changes to north
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case "N": // if the player is facing north
                direction = "E"; // changes to east
                break;
            case "E": // if the player is facing east
                direction = "S"; //changes the south
                break;
            case "S": //if player is facing south
                direction = "W"; //changes the west
                break;
            case "W": // if player is facing west
                direction = "N"; //changes north
                break;
        }
    }

    // this method is used to move from one cell to another.
    public void moveForward(Cave cave) {
        int newX = x;
        int newY = y;

        // Update new coordinates according to the direction
        switch (direction) {
            case "N": // Looking north
                newY -= 1; // Move up
                break;
            case "E": // Looking east
                newX += 1; // Move to the right
                break;
            case "S": // Looking south
                newY += 1; // Move down
                break;
            case "W": // Looking west
                newX -= 1; // Move to the left
                break;
        }

        // Verify if the new movement is within the grid limits.
        if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
            Cell targetCell = cave.getCell(newY, newX); // Obtain target cell --> basically the cell where we are going to move
            if (newX == 0 && newY == 0){ // If steps in the cell (0;0) the game ends.
                System.out.println("Game over");
                System.out.println("Final score: " + this.score);
                System.exit(0); // Close the game
            }

            // Check if it is an obstacle
            if (targetCell.getType() == 'O') {
                System.out.println("You have encountered an obstacle");
                return; // if obstacle, the movement is not done and the method finishes here
            }

            // Check if there is a treasure or a trap
            if (targetCell.getType() == 'T') {
                System.out.println("¡You found a treasure! Score: 10");
                targetCell.setType('.'); //treasure disappears, becoming a dot ('.')
                addPoints(10); // add points (10)

            } else if (targetCell.getType() == 'X') {
                System.out.println("You hit a trap! - 5");
                targetCell.setType('.');
                addPoints(-5); // subtract points (-5)
            }

            // Update player position
            setPosition(newX, newY);

        } else {
            System.out.println("You cannot move outside the cave boundaries!");
        }
    }

    public void revealCells(Cave cave, String direction) {
        // saves the current coordinates of the player in new variables to not modify the original ones.
        int currentX = x;
        int currentY = y;

        // Check cells in the specified direction
        switch (direction) {
            case "E": // To the right (east)
                // while the current cell is not an obstacle, and hasn't reached the right grid limit
                while (currentX < 10 && cave.getCell(currentY, currentX).getType() != 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Reveals the cell
                    currentX++; // Move to the next cell to the right
                }
                // Reveal the obstacle cell if necessary.
                if (currentX < 10 && cave.getCell(currentY, currentX).getType() == 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Revealing the obstacle
                }
                break;
            case "S": // Downward (south)
                // while the current cell is not an obstacle, and hasn't reached the lower grid limit
                while (currentY < 10 && cave.getCell(currentY, currentX).getType() != 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Reveals the cell
                    currentY++; // Move to the next cell down
                }
                // Reveal the obstacle cell if necessary.
                if (currentY < 10 && cave.getCell(currentY, currentX).getType() == 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Revealing the obstacle
                }
                break;
            case "N": // Upward (north)
                // while the current cell is not an obstacle, and hasn't reached the upper grid limit
                while (currentY >= 0 && cave.getCell(currentY, currentX).getType() != 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Reveals the cell
                    currentY--; // Move to the next cell up
                }
                // Reveal the obstacle cell if necessary.
                if (currentY >= 0 && cave.getCell(currentY, currentX).getType() == 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Revealing the obstacle
                }
                break;

            case "W": // To the left (west)
                // while the current cell is not an obstacle, and hasn't reached the left grid limit
                while (currentX >= 0 && cave.getCell(currentY, currentX).getType() != 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Reveals the cell
                    currentX--; // reveal the next cell to the left
                }
                // Reveal the obstacle cell if necessary.
                if (currentX >= 0 && cave.getCell(currentY, currentX).getType() == 'O') {
                    cave.getCell(currentY, currentX).reveal(); // Revealing the obstacle
                }
                break;
        }
    }

    // Methods to add points to the player
    public void addPoints(int points) {
        this.score += points;
    }
    // method to return the score
    public int getScore() {
        return score;
    }
    // Method to return the direction where the player is pointing at
    public String getDirection() {
        return direction;
    }
    // Method to return the location in X
    public int getX() {
        return x;
    }
    // method to return the location in X
    public int getY() {
        return y;
    }
    // method to set the position of the player in the map
    public void setPosition(int x, int y) {
        this.x = x; //new position in X
        this.y = y; //new position in Y
    }
}
