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
                    System.out.println("B: Attack Monster");
                    System.out.println("I: Display party info");
                    System.out.println("M: Enter market");
                    System.out.println("T: Display Map");
                    System.out.println("R: Recall To Nexus");
                    System.out.println("O: Teleport");
                    System.out.println("Q: Quit game");
                    System.out.println("P: Check Inventory");
                    char input = InputValidation.getValidChar(hero.getName() + " select an option from above: ");
                    turnEnd = processMenuInput(input,hero);
                }while(!turnEnd);
                
                
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
            case 'b':
                Cell cell = board.getCell(hero.getCurrRow(), hero.getCurrCol());
                Cell monsterCell =  board.findCharacterInRange(cell, false);
                    if (monsterCell != null) {
                        // if it is, then check if a battle occurs
                                System.out.println("Time to fight.");
                                Battle battle = new Battle(hero, monsterCell.getMonster());
                                if (!battle.startBattle()) {
                                    System.out.println("You lost the battle! You will be respawned at your Nexus.");
                                    hero.setHP(hero.getMaxHP());
                                    hero.moveToCell(hero.getMyNexusRow(), hero.getMyNexusCol(), board);
                                }
                                else{
                                    Legends.monsterTeam.remove(monsterCell.getMonster());
                                    System.out.println("REMOVED MONSTER");
                                    monsterCell.removeMonster();
                                }
                            }
                    else{
                        System.out.println("There are no Monsters to Attack");
                        return false;
                    }
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
                } else {
                    System.out.println("You are not on a Market please move to one and try again.");
                }
                return false;
            case 'r':
                hero.moveToCell(hero.getMyNexusRow(),hero.getMyNexusCol(),board);
                hero.setCurrLane(hero.getMyNexusLane());
                break;
            case 'p':
                // Ask user to choose a hero to see their inventory
                hero.getInventory().enterInventory(hero);
                return false;
            case 't':
                board.printBoard();
                return false;
            case 'o':
                // Get the heros in other lanes
                ArrayList<Hero> herosInOtherLanes = myTeam.getOtherLaneHeroes(hero);
                // If there are no heros in other lanes
                if(herosInOtherLanes.size() == 0){
                    System.out.println("There are no heros in other lanes to teleport to!");
                    return false;
                }
                // If there are heros in other lanes
                else{
                    // Print out the heros in other lanes
                    System.out.println("Heros in other lanes:");
                    for(int i = 0; i < herosInOtherLanes.size(); i++){
                        System.out.println(i + ": " + herosInOtherLanes.get(i).getName());
                    }
                    // Get the user input for which hero to teleport to
                    int idx = InputValidation.getValidInt("Enter the index of the hero you want to teleport to: ", 0, herosInOtherLanes.size() - 1);
                    Hero heroToTeleportTo = herosInOtherLanes.get(idx);
                    // Teleport the hero to the other hero
                    if(hero.teleportToHero(heroToTeleportTo, board)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            default:
                System.out.println("Invalid input. Please enter a character in the options given!");
                return false;
        }
        return true;
    }
}