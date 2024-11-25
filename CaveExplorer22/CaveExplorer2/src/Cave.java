import java.util.Random;

public class Cave {
    // create a two-dimensional array, cell type to represent the cave map
    private Cell[][] grid;
    // contructor of the class cave
    public Cave() {
        grid = new Cell[10][10]; // Initialize the grid
        initializeGrid(); // call the method to initialize the cells with 'Cells' objects
    }

    //this is going to return an object type cell, basically the cell in the position specified
    public Cell getCell(int y, int x) {
        return grid[y][x];
    }

    private void initializeGrid() {
        //Random rand = new Random();

        // Fill the grid with empty cells initially
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Cell('.'); // Start with empty cells (represented by '.')
            }
        }

        // Place treasures, traps, and obstacles randomly
        placeItems('T', 5); // Place 5 treasures
        placeItems('X', 5); // Place 5 traps
        placeItems('O', 5); // Place 5 obstacles
    }

    //method that place a type of cell in random cells
    private void placeItems(char itemType, int count) {
        Random rand = new Random();
        int placed = 0; // items placed
        //placed the items until reach the amount specified
        while (placed < count) {
            //generates random coordinates inside the grid --> (NO 0,0)
            int x = rand.nextInt(9) + 1; // Generate a random number between 1 and 9 (No zero)
            int y = rand.nextInt(9) + 1; // Generate a random number between 1 and 9 (No zero)

            // Only place the item if the cell is empty ('.'){
            if (grid[y][x].getType() == '.') {
                grid[y][x] = new Cell(itemType); // Place the item
                placed++; // increase 1
            }
        }
    }

    // method that shows the grid, and the current position of the player
    public void displayGrid(Player player) {
        // traverse the grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // If the player is in the cell, it shows a little arrow pointing to the direction where it is looking at
                if (i == player.getY() && j == player.getX()) {
                    switch (player.getDirection()) { // depending on the player direction, an arrow is show
                        case "N": System.out.print("^ "); break; //north
                        case "E": System.out.print("> "); break; //east
                        case "S": System.out.print("v "); break; //south
                        case "W": System.out.print("< "); break; //West
                    }
                } else {
                    // If the cell is not the player's cell, we show the contents of the cell
                    Cell cell = grid[i][j];
                    if (cell.isRevealed()) {
                        System.out.print(cell.getType() + " "); // if the cell was revealed, shows the type of the cell
                    } else {
                        System.out.print("? "); // if it was not revealed shows "?"
                    }
                }
            }
            System.out.println();
        }
    }
}