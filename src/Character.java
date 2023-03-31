import java.util.ArrayList;

// Abstract Character Class that is extended by the different types of characters such as Heros and Monsters
public abstract class Character {
    private String name;
    private int level;
    private double HP;
    private int currCol;
    private int currRow;

    private int currLane;

    private ArrayList<String> favoredAttributes;
    public Character(String name,int level){
        this.name = name;
        this.level = level;
        this.HP = level * 100;
        this.favoredAttributes = new ArrayList<String>();
        this.currRow = 0;
        this.currCol = 0;
    }
    
    // get current lane 
    public int getCurrLane(){
        return this.currLane;
    }
    // set current lane
    public void setCurrLane(int currLane){
        this.currLane = currLane;
    }

    // return true if HP > 0, else false
     public boolean isAlive() {
        return HP > 0;
    }

    // return HP after taking damage
    public double takeHit(double damage) {
         if(HP - damage < 0){
             return -1;
         }
         else{
             return HP - damage;
         }
    }
    public String toString() {
        return this.name;
    }

    // Getters
    public int getCurrCol(){
        return currCol;
    }
    public int getCurrRow(){
        return currRow;
    }
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getHP() {
        return HP;
    }
    public int getMaxHP(){
        return this.level * 100;
    }
    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHP(double HP) {
        // If HP is greater than level * 100, set HP to level * 100
        if(HP > this.level * 100){
            this.HP = this.level * 100;
        }
        else{
            this.HP = HP;
        }

    }
    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }
    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }
    public ArrayList<String> getFavoredAttributes(){
        return this.favoredAttributes;
    }
    // add to favored attributes
    public void addFavoredAttribute(String attribute){
        this.favoredAttributes.add(attribute);
    }
    // Method to move the hero to a new cell on the map
    public boolean moveToCell(int row,int col,Board currBoard){
        if (!currBoard.isValid(row, col)) {
            System.out.println("Please enter a valid row, column");
            return false;
        }
        if(currBoard.isInaccessibleCell(row,col)){
            System.out.println("\u001B[31m" + "Restricted Area - The Gods have forbade you from entering here" + "\u001B[0m");
            return false;
        }
        // If cell already hasHero and hasMonster return false
        if (currBoard.getCell(row, col).hasHero() && currBoard.getCell(row, col).hasMonster()) {
            System.out.println("There is already a hero and a monster on this cell!");
            return false;
        }
        

        return true;

    }

}
