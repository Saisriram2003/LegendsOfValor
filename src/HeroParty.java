// HeroParty class maintains a Party of Heroes and handles all functions related to the Party
import java.util.ArrayList;
import java.util.HashMap;

public class HeroParty {
    private ArrayList<Hero> heroList;
    private Board currBoard;
    private Legends game;
    private LegendGameSetup gamePreparer =  LegendGameSetup.getInstance();

    public HeroParty(int numPlayers,Legends game) {
        this.heroList = chooseHeroParty(numPlayers);
        this.game = game;
        this.currBoard = game.getGameBoard();
    }


    public Hero getHero(){
        System.out.println("Choose a hero");
        for(int i = 0; i < heroList.size(); i++){
            System.out.println(i + ": " + heroList.get(i));
        }
        int heroChoice = InputValidation.getValidInt("Select a hero (with the index): ", 0, heroList.size() - 1);
        return heroList.get(heroChoice);
    }
    
    public ArrayList<Hero> getHeroList(){
        return this.heroList;
    }
    // Method to display information about the party
    public void displayPartyInfo() {
        System.out.println("Party Information:");
        for (Hero hero : heroList) {
            System.out.println("Name: " + hero.getName());
            System.out.println("Level: " + hero.getLevel());
            System.out.println("Experience Points: " + hero.getExp());
            System.out.println("Hit Points: " + hero.getHP());
            System.out.println("Mana Points: " + hero.getMP());
            System.out.println("Strength: " + hero.getStrength());
            System.out.println("Dexterity: " + hero.getDexterity());
            System.out.println("Agility: " + hero.getAgility());
            System.out.println("Gold: " + hero.getGold());
            if(hero.hasArmor()){
                System.out.println("Equipped Armor - " + hero.getArmor());
            }
            if(hero.hasWeapon()){
                System.out.println("Equipped Weapon - " + hero.getWeapon());
            }
            System.out.println("Inventory: " + hero.getInventory());
            System.out.println();
        }
    }
    // Method to display the party's gold
    public void showMoney(){
        for(Hero hero: heroList){
            System.out.println(hero + " - " + "Gold Left: " +hero.getGold());
        }
    }

    // Method to get the max level of Heros in the Party
    public int getMaxLevel() {
        int maxLevel = 0;
        for(Hero hero : heroList){
            if(hero.getLevel() > maxLevel){
                maxLevel = hero.getLevel();
            }
        }
        return maxLevel;
    }
    // Method to get party size
    public int getPartySize(){
        return heroList.size();
    }

    public String toString() {
        // for every hero in the party, print out their name and where they are
        String partyString = "";
        for (Hero hero : heroList) {
            partyString += hero.getName() + " is at " + hero.getCurrRow() + ", " + hero.getCurrCol() + "\n";
        }
        return partyString;
    }
    // Method to choose the Hero Party
    public ArrayList<Hero> chooseHeroParty(int numPlayers){
        HashMap<String, ArrayList<Hero>> HeroMap = gamePreparer.getHeroMap();
        ArrayList<Hero> myTeam = new ArrayList<>();

        // Form Hero Party
        for(int i = 0; i < numPlayers; i++){
            boolean validHeroChoice = false;
            while (!validHeroChoice) {
                System.out.println("These are the Heroes that are available for you to choose: " + HeroMap.keySet());
                String heroClass = InputValidation.getValidString("Please enter the name of the Hero class you would like to choose: ");
                if (!HeroMap.containsKey(heroClass)) {
                    System.out.println("Invalid hero class choice. Please enter a valid hero class name.");
                    continue;
                }

                ArrayList<Hero> heroesAvailable = HeroMap.get(heroClass);
                System.out.println("These are the " + heroClass + " Heroes that are available for you to choose: " + heroesAvailable.toString());
                String heroName = InputValidation.getValidString("Please enter the name of the Hero you would like to choose: ");

                // loop through the available Heroes list to get the Hero user chose
                for(int j = 0; j < heroesAvailable.size(); j++){
                    if(heroName.equals(heroesAvailable.get(j).getName())){
                        myTeam.add(heroesAvailable.get(j));
                        heroesAvailable.remove(j);
                        HeroMap.put(heroClass, heroesAvailable);
                        validHeroChoice = true;
                        break;
                    }
                }
                if (!validHeroChoice) {
                    System.out.println("Invalid hero choice. Please enter a valid hero name.");
                }
            }
        }
        return myTeam;
    }
}
