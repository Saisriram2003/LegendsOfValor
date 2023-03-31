/*
This is the parent Board class that is used to create Boards for the games
 */
import java.util.Random;

public class Board {
    private int numRows;
    private int numCols;

    private Cell[][] board;
    private Random random = new Random();

    // Constructor for Board with allowing for different rows and columns to allow extendability
    public Board(int rows, int cols) {
        this.numRows = rows;
        this.numCols = cols;
        this.board = new Cell[numRows][numCols];

        // Initializing the Board
        resetBoard();
    }
    public Board(int num) {
        this.numRows = num;
        this.numCols = num;
        this.board = new Cell[numRows][numCols];

        // Initializing the Board
        resetBoard();
    }

    public Cell[][] getBoard() {
        return this.board;
    }


    // printBoard prints the Board in a neat manner
    public void printBoard() {
        System.out.println("Color Key for Board: (H is current Hero Party position)");
        System.out.print("\u001B[34mNexus - Blue \n\u001B[32mCommon Space - Green(B for Bush, P for Plainland, K for Koulou) \n\u001B[31mInaccessible Space - Red \u001B[0m\n");
        StringBuilder line = new StringBuilder("");
        for (int i = 0; i < this.numCols; ++i) {
            line.append("*---");
        }
        line.append("*");
    
        for (Cell[] row : this.board) {
            System.out.println(line);
    
            for (Cell cell : row) {
                String colorCode = "";
                String cellSymbol = " ";
    
                if (cell instanceof InaccessibleCell) {
                    colorCode = "\u001B[41m"; // red background
                } else if (cell instanceof NexusCell) {
                    colorCode = "\u001B[44m"; // blue background
                } else if (cell instanceof CommonCell) {
                    // if cell is a Plain cell
                    colorCode = "\u001B[42m"; // green background
    
                    // set the symbol for each type of CommonCell
                    if (cell instanceof PlainCell) {
                        cellSymbol = "P";
                    } else if (cell instanceof BushCell) {
                        cellSymbol = "B";
                    } else if (cell instanceof CaveCell) {
                        cellSymbol = "C";
                    } else if (cell instanceof KoulouCell) {
                        cellSymbol = "K";
                    }
                }
    
                if (cell.hasHero()) {
                    cellSymbol = "H";
                } else if (cell.hasMonster()) {
                    cellSymbol = "M";
                }
    
                System.out.print(colorCode + "| " + cellSymbol + " \u001B[0m"); // reset color
            }
            System.out.println("|");
        }
        System.out.println(line);
        System.out.println();
    }
    

    // Cell type checkers
    // isBushCell
    public boolean isBushCell(int row,int col){
        if(board[row][col] instanceof BushCell){
            return true;
        }
        else{
            return false;
        }
    }
    // isCaveCell
    public boolean isCaveCell(int row,int col){
        if(board[row][col] instanceof CaveCell){
            return true;
        }
        else{
            return false;
        }
    }
    // isKoulouCell
    public boolean isKoulouCell(int row,int col){
        if(board[row][col] instanceof KoulouCell){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isNexusCell(int row,int col){
        if(board[row][col] instanceof NexusCell){
            return true;
        }
        else{
            return false;
        }
    }
    // isMonsterNexusCell checks if the cell is a MonsterNexusCell
    public boolean isMonsterNexusCell(int row,int col){
        if(board[row][col] instanceof MonsterNexusCell){
            return true;
        }
        else{
            return false;
        }
    }
    // isHeroNexusCell checks if the cell is a HeroNexusCell
    public boolean isHeroNexusCell(int row,int col){
        if(board[row][col] instanceof HeroNexusCell){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isInaccessibleCell(int row,int col){
        if(board[row][col] instanceof InaccessibleCell){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isCommonCell(int row,int col){
        if(board[row][col] instanceof CommonCell){
            return true;
        }
        else{
            return false;
        }
    }

    // isValid checks if the row,col placement is possible
    public boolean isValid(int row,int col){
        if(row < 0 || row >= this.numRows){
            return false;
        }
        if(col < 0 || col >= this.numCols){
            return false;
        }

        return true;
    }

    //getCell
    public Cell getCell(int row, int col) {
        return board[row][col];
    }


    // resetBoard resets the board to a new random state
    public void resetBoard(){
        // first and last row are NexusCell
        for (int i = 0; i < this.numCols; ++i) {
            board[0][i] = new MonsterNexusCell();
            board[this.numRows - 1][i] = new HeroNexusCell();
        }
        // 3rd and 6th column are InaccessibleCell
        for (int i = 0; i < this.numRows; ++i) {
            board[i][2] = new InaccessibleCell();
            board[i][5] = new InaccessibleCell();
        }
        // rest of the board is composed of 20% of each special space type (Bush, Cave, and Koulou) and 40% Plain spaces
        for (int i = 1; i < this.numRows - 1; ++i) {
            for (int j = 0; j < this.numCols; ++j) {
                if (j == 2 || j == 5) {
                    continue;
                }
                double rand = random.nextDouble();
                if (rand < 0.2) {
                    board[i][j] = new BushCell();
                } else if (rand < 0.4) {
                    board[i][j] = new CaveCell();
                } else if (rand < 0.6) {
                    board[i][j] = new KoulouCell();
                } else {
                    board[i][j] = new PlainCell();
                }
            }
        }


    }

}
