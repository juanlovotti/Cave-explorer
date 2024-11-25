
// class that represents the cell
// it manages the TYPE of cell and if the cell is revealed or not

public class Cell {
    private char type; // T, X, O, ., ?
    private boolean revealed;

    // constructor of the cell
    public Cell(char type) {
        this.type = type;
        this.revealed = false;
    }
    // getter to return the type of cell
    public char getType() {
        return type;
    }
    // setter to set the type of cell
    public void setType(char newType){
        this.type = newType;}

    // returns a boolean value indicating if the cell has been revealed
    public boolean isRevealed() {
        return revealed;
    }
    //update the cell as revealed, representing that the player has explored the cell
    public void reveal() {
        this.revealed = true;
    }
}

