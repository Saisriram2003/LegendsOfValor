// Menu for Legends Game
import java.lang.Character;
import java.util.ArrayList;
import java.util.Random;

public class LegendsMenu extends Menu{
    private Legends legend;
    private HeroParty myTeam;
    private ArrayList<Monster> monsterTeam;
    private Board board;
    private boolean turnEnd;
    public LegendsMenu(Legends legend) {
        this.legend = legend;
        myTeam = legend.getMyTeam();
        monsterTeam = legend.getMonsterTeam();
        board = legend.getBoard();
    }
    
    // Gets user input and processes it
    public void displayMenu(Hero hero) {
        // loop through every hero
        
            board.printBoard();
            // if the hero is alive
            if (hero.isAlive()) {
                do{
                    turnEnd = false;
                    System.out.println("W: Move up");
                    System.out.println("A: Move left");
                    System.out.println("S: Move down");
                    System.out.println("D: Move right");
                    System.out.println("I: Display party info");
                    System.out.println("M: Enter market");
                    System.out.println("T: Display Map");
                    System.out.println("R: Recall To Nexus");
                    System.out.println("Q: Quit game");
                    System.out.println("P: Check Inventory");
                    char input = InputValidation.getValidChar(hero.getName() + " select an option from above: ");
                    turnEnd = processMenuInput(input,hero);
                }while(!turnEnd);
                // check if the current cell is a common cell
                if (board.isCommonCell(hero.getCurrRow(), hero.getCurrCol())) {
                    // if it is, then check if a battle occurs
                    CommonCell currCell = (CommonCell) board.getCell(hero.getCurrRow(), hero.getCurrCol());
                    if (currCell.doesBattleOccur()) {
                        if (currCell.hasMonster()) {
                            System.out.println("Oh no! There are monsters here! Time to fight.");
                            Battle battle = new Battle(hero, currCell.getMonster());
                            if (!battle.startBattle()) {
                                System.out.println("You lost the battle! You will be respawned at your Nexus.");
                            }
                        }
                    }

                }
            }

    }

    // Processor for menu input and returns true if the turn is over
    private boolean processMenuInput(char input,Hero hero) {
        System.out.println();

        int partyRow = hero.getCurrRow();
        int partyCol = hero.getCurrCol();

        switch(Character.toLowerCase(input)) {
            case 'w':
                // if moveToCell returns false, then the move was invalid
                if(!hero.moveToCell(partyRow - 1, partyCol,board)){
                    return false;
                }
                 // move up
                break;
            case 'a':
                
                if(!hero.moveToCell(partyRow, partyCol - 1,board)){
                    return false;
                }
                    // move left
                break;
            case 's':
                if(!hero.moveToCell(partyRow + 1, partyCol,board)){
                    return false;
                } // move down
                break;
            case 'd':
                if(!hero.moveToCell(partyRow, partyCol + 1,board)){
                    return false;
                } // move right
                break;
            case 'q':
                legend.quit(); // quit game
                break;
            case 'i':
                myTeam.displayPartyInfo();
                return false;
            case 'm':
                if (board.isNexusCell(partyRow, partyCol)) {
                    NexusCell marketCell = (NexusCell) board.getCell(partyRow, partyCol);
                    marketCell.enterMarket(hero);
                    return false;
                } else {
                    System.out.println("You are not on a Market please move to one and try again.");
                }
                break;
            case 'r':
                hero.moveToCell(hero.getMyNexusRow(),hero.getMyNexusCol(),board);
                break;
            case 'p':
                // Ask user to choose a hero to see their inventory
                hero.getInventory().enterInventory(hero);
                return false;
            case 't':
                board.printBoard();
                return false;
            default:
                System.out.println("Invalid input. Please enter a character in the options given!");
                break;
        }
        return true;
    }
}