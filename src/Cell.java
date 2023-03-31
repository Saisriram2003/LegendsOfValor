// Abstract Cell class that is extended by the different types of cells
public abstract class Cell {
    private String type;

    // hasHero is true if a hero is on the cell
    private boolean hasHero;
    // hasMonster is true if a monster is on the cell
    private boolean hasMonster;
    private Monster monster;
    private Hero hero;

    public Cell(String type){
        this.type = type;
        this.hasHero = false;
        this.hasMonster = false;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean hasHero() {
        return this.hasHero;
    }
    public void setHero(Hero hero){
        this.hero = hero;
        this.hasHero = true;
    }
    public Hero removeHero(){
        Hero removedHero = this.hero;
        this.hero = null;
        this.hasHero = false;
        return removedHero;
    }
    public boolean hasMonster() {
        return this.hasMonster;
    }

    public Monster getMonster() {
        return monster;
    }
    public Monster removeMonster(){
        Monster removedMonster = this.monster;
        this.monster = null;
        this.hasMonster = false;
        return removedMonster;
    }
    public void setMonster(Monster monster) {
        this.hasMonster = true;
        this.monster = monster;
    }

    public String toString() {
        return type;
    }


}
