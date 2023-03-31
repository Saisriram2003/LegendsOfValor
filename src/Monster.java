// Monster is a type of Character that is the villain in this game
import java.util.ArrayList;
import java.util.Random;

public abstract class Monster extends Character implements Dodgeable,hasNexus {
    private double baseDamage;
    private double Defense;
    private double dodgeAbility;
    private int NexusRow = 0;
    private int NexusCol = 0;
    private int NexusLane = 0;


    public Monster(String name, int level, double baseDamage, double Defense, double dodgeAbility) {
        super(name, level);
        this.baseDamage = baseDamage;
        this.Defense = Defense;
        this.dodgeAbility = dodgeAbility;
    }

    // Getters
    public int getMyNexusCol() {
        return this.NexusCol;
    }

    public int getMyNexusRow() {
        return this.NexusRow;
    }

    // Monsters drop 500 * level when they are defeated
    public double goldDropped(){
        return 500 * this.getLevel();
    }

    // Monsters give 2 * level exp when they are defeated
    public double expRecieved(){
        return 2 * this.getLevel();
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public double getDefense() {
        return Defense;
    }

    public double getDodgeAbility() {
        return dodgeAbility;
    }

    // Setters
    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setDefense(double Defense) {
        this.Defense = Defense;
    }

    public void setDodgeAbility(double dodgeAbility) {
        this.dodgeAbility = dodgeAbility;
    }

    public void setMyNexus(int row, int col,int lane) {
        this.NexusRow = row;
        this.NexusCol = col;
        this.NexusLane = lane;
    }
    
    public boolean dodgedAttack(){
        Random random = new Random();
        if(random.nextInt(100) + 1 > this.getDodgeAbility()){
            return true;
        }
        return false;
    }
    // Show Monster stats
    public void showStats() {
        System.out.println(getName() + ": Level - " + getLevel());
        System.out.println("Defense - " + getDefense() + ", HP - " + getHP() + ", Defense - " + getDefense() + ", Dodge Ability - " + getDodgeAbility());
    }
    // Attack a hero
    public void monsterAttack(Hero hero){
        Random random = new Random();
        double damage = this.getBaseDamage() * 0.05;

        if(hero.hasArmor()){
            // Reduce damage by armor and set min damage to 0
            damage -= hero.getArmor().getDamageReduction()/100;
            if(damage < 0){
                damage = 0;
            }
        }
        if(hero.dodgedAttack()){
            System.out.println(hero.getName() + " dodged the attack!");
            return;
        }
        else{
            hero.setHP(hero.takeHit(damage));
            if(hero.isAlive()){
                System.out.println(this.getName() + " attacked " + hero.getName() + " for " + damage + " damage!");
            }
            else{
                System.out.println(this.getName() + " killed " + hero.getName() + "!");
            }
        }

    }
    @Override
    public boolean moveToCell(int row, int col, Board currBoard) {
        boolean result = super.moveToCell(row, col, currBoard);
        if (result) {
            // If moving down the board and there is a hero on current cell or next to it, return false
            if (row > this.getCurrRow()) {
                if (currBoard.getCell(this.getCurrRow(), this.getCurrCol()).hasHero()) {
                    System.out.println("There is a hero on this cell!");
                    return false;
                }
                if (this.getCurrCol() > 0 && currBoard.getCell(this.getCurrRow(), this.getCurrCol() - 1).hasHero()) {
                    System.out.println("There is a hero on the cell to the left!");
                    return false;
                }
                if (this.getCurrCol() < currBoard.getNumCols() - 1 && currBoard.getCell(this.getCurrRow(), this.getCurrCol() + 1).hasHero()) {
                    System.out.println("There is a hero on the cell to the right!");
                    return false;
                }
            }
            // if cell already has a monster return false
            if (currBoard.getCell(row, col).hasMonster()) {
                System.out.println("There is already a monster on this cell!");
                return false;
            }
            currBoard.getCell(this.getCurrRow(), this.getCurrCol()).removeMonster();
            currBoard.getCell(row, col).setMonster(this);
            this.setCurrRow(row);
            this.setCurrCol(col);
        }
        return result;
    }
}

