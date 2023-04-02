// Legends Game Class that handles the game and the menu

import java.util.ArrayList;

public class Legends extends Game{
    HeroParty myTeam;
    private ArrayList<Monster> monsterTeam;


    private Board board;
    private LegendsMenu menu;
    private MonsterNexusCell lane1;
    private MonsterNexusCell lane2;
    private MonsterNexusCell lane3;

    public Legends() {
        // Board is size 8 x 8
        board = new Board(8);
    }
    // Start the game
    public void start(){
        System.out.println("\nHello! Welcome to the world of Legends of Valor!");
        System.out.println("\nThis is a battle between Heroes and Monsters in a contest of strategy and skill. \nTake advantage of the" +
                " terrain, coordinate actions between heroes, and use items to outwit and outfight the invading" +
                " waves of monsters.\nCan your heroes destroy the monsters’ Nexus and stop the monster" +
                " invasion? Or will the monsters overrun your own fortress?");


        System.out.println("\nTo start off please choose the 3 Heroes that will aid you in this fight!");
        // Setting Hero Party of size 3
        myTeam = new HeroParty(3,this);
        // Setting Monster Party of size 3 all level 1 for now
        monsterTeam = MonsterFactory.createBattleMonsters(1,3);

        setupPositions();
        menu = new LegendsMenu(this);


        System.out.println("\nGood luck on your adventure!");
        System.out.println(myTeam);
        int rounds = 0;

        while (true) {
            // Spawn Monsters
            if((rounds++ % 5 ) == 0){
                lane1.spawnMonster(myTeam.getMaxLevel());
                lane2.spawnMonster(myTeam.getMaxLevel());
                lane3.spawnMonster(myTeam.getMaxLevel());
            }

            // do hero turn for each hero
            for (Hero hero : myTeam.getHeroList()) {
                if (hero.isAlive()) {
                    menu.displayMenu(hero);
                }
                isGameOver();
            }
            // for every monster move down 1 row
            for(Monster monster: monsterTeam){
                if(monster.isAlive()){
                    monster.moveToCell(monster.getCurrRow() + 1, monster.getCurrCol(), board);
                }
                isGameOver();
            }
            
            }

    }


    // check if game over by checking if any Hero reached monster Nexus or if any Monster reached Hero Nexus
    public boolean isGameOver(){
        // check if any hero reached any of the monster Nexus's which is the first row 
        for(Hero hero: myTeam.getHeroList()){
            // check if the cell Hero is on is a MonsterNexusCell
            if(board.isMonsterNexusCell(hero.getCurrRow(), hero.getCurrCol())){
                System.out.println("Congratulations! You have destroyed the monsters' Nexus and saved the world!");
                System.exit(0);
                return true;
            }
        }
        // check if any monster reached hero Nexus
        for(Monster monster: monsterTeam){
            // check if the cell Monster is on is a HeroNexusCell
            if(board.isHeroNexusCell(monster.getCurrRow(), monster.getCurrCol())){
                System.out.println("Oh no! The monsters have destroyed your Nexus and taken over the world!");
                System.exit(0);
                return true;
            }
        }
        return false;
    }
    // setup hero and monster positions
    public void setupPositions(){
        // Setup Hero Positions to left of each lane
        ArrayList<Hero> heroList = myTeam.getHeroList();
        // set 1st hero to 7,0 and his NexusCell to 7,0
        heroList.get(0).moveToCell(7,0,board);
        heroList.get(0).setMyNexus(7, 0,0);
        // set current lane to 1
        heroList.get(0).setCurrLane(1);


        // set 2nd hero to 7,3
        heroList.get(1).moveToCell(7,3,board);
        heroList.get(1).setMyNexus(7, 3,1);
        // set current lane to 2
        heroList.get(1).setCurrLane(2);

        //set 3rd hero to 7,6
        heroList.get(2).moveToCell(7,6,board);
        heroList.get(2).setMyNexus(7, 6,2);
        // set current lane to 3
        heroList.get(2).setCurrLane(3);

        // Setup Monster Positions to right of each lane
        // set 1st monster to 0,1
        monsterTeam.get(0).moveToCell(0,1,board);

        monsterTeam.get(0).setMyNexus(0, 1,0);
        //set current lane to 1
        monsterTeam.get(0).setCurrLane(1);

        lane1 = (MonsterNexusCell) board.getCell(0,1);


        
        // set 2nd monster to 0,4
        monsterTeam.get(1).moveToCell(0,4,board);
        monsterTeam.get(1).setMyNexus(0, 4,1);
        // set current lane to 2
        monsterTeam.get(1).setCurrLane(2);
        lane2 = (MonsterNexusCell) board.getCell(0,4);


        // set 3rd monster to 0,7
        monsterTeam.get(2).moveToCell(0,7,board);
        monsterTeam.get(2).setMyNexus(0, 7,2);

        // set current lane to 3
        monsterTeam.get(2).setCurrLane(3);
        lane3 = (MonsterNexusCell) board.getCell(0,7);


    }

    // Getters
    public Board getGameBoard(){
        return board;
    }

    public HeroParty getMyTeam() {
        return myTeam;
    }
    public ArrayList<Monster> getMonsterTeam(){
        return monsterTeam;
    }
    public Board getBoard(){
        return board;
    }

    // Quit the game
    public void quit() {
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
}
