// Abstract class for the game setup
public abstract class GameSetup {
    // Method to get the number of players
    public int getNumPlayers(int lb, int ub) {
        int numPlayers = InputValidation.getValidInt("Please enter how many heroes you would like on your team ("+lb+" - "+ub+"): ", lb, ub);
        return numPlayers;
    }
    // Method to make the board
    public Board makeBoard(int lb,int ub) {
        int boardSize = InputValidation.getValidInt("Please enter the desired size for your board in the range ("+lb+" - "+ub+"): ", lb, ub);
        return new Board(boardSize);
    }


}
