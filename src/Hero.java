// Class that maintains Hero attributes and methods related to Hero
import java.util.ArrayList;
import java.util.Random;

public abstract class Hero extends Character implements LevelUp, Dodgeable,hasNexus{
    private double Exp;
    private double MP;
    private double originalMP;
    private double Strength;
    private double Dexterity;
    private double Agility;
    private double Gold;
    private Weapon weapon;
    private Armor armor;
    private Random random = new Random();
    private Inventory inventory;

    private int NexusRow = 0;
    private int NexusCol = 0;
    private int NexusLane = 0;


    public Hero(String name, double MP,double Strength,double Agility,double Dexterity,double Gold,double startingExp) {
        super(name,1);
        this.Exp = startingExp;
        this.originalMP = MP;
        this.MP = MP;
        this.Strength = Strength;
        this.Dexterity = Dexterity;
        this.Agility = Agility;
        this.Gold = Gold;
        this.inventory = new Inventory();
        this.weapon = null;
        this.armor = null;
    }

    public int getMyNexusCol() {
        return this.NexusCol;
    }

    public int getMyNexusRow() {
        return this.NexusRow;
    }

    public int getMyNexusLane() {
        return this.NexusLane;
    }

    public double getOriginalMP() {
        return originalMP;
    }
    public void setOriginalMP(double originalMP) {
        this.originalMP = originalMP;
    }
    public double getExp() {
        return Exp;
    }

    public double getExpToLevel() {
        return (getLevel() * 10);
    }

    public double getMP() {
        return MP;
    }

    public double getStrength() {
        return Strength;
    }

    public double getDexterity() {
        return Dexterity;
    }

    public double getAgility() {
        return Agility;
    }

    public double getGold() {
        return Gold;
    }

    // Gets Inventory that only has non-equipped items and non-consumed itmes
    public Inventory getInventory() {
        inventory.setNewInventory();
        return inventory;
    }


    public void setMyNexus(int row, int col, int lane) {
        this.NexusRow = row;
        this.NexusCol = col;
        this.NexusLane = lane;
    }

    public void setExp(double Exp) {
        this.Exp = Exp;
    }

    public void setMP(double MP) {
        // If MP is greater than original cap it at that
        if(MP > getOriginalMP()){
            this.MP = getOriginalMP();
        }
        else{
            this.MP = MP;
        }
    }

    public void setStrength(double Strength) {
        this.Strength = Strength;
    }

    public void setDexterity(double Dexterity) {
        this.Dexterity = Dexterity;
    }

    public void setAgility(double Agility) {
        this.Agility = Agility;
    }

    public void setGold(double Gold) {
        this.Gold = Gold;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    // Heros have the ability to level up and require this method as they implement LevelUp Interface
    public abstract void levelUp();

    // Add item to Hero's inventory
    public void addToInventory(Item item){
        inventory.addItem(item);
    }
    // Buy an Item and reduce gold based on price
    public void buyItem(Item item){
        if(this.getGold() >= item.getPrice()){
            this.setGold(this.getGold() - item.getPrice());
            this.addToInventory(item);
        }
        else{
            System.out.println("You do not have enough money to buy this Item");
        }
    }
    // Method to show Hero Stats
    public void showStats() {
        System.out.println(getName() + ": Level - " + getLevel() + ", Exp - " + getExp());
        System.out.println("Strength - " + getStrength() + ", HP - " + getHP() + ", MP - " + getMP() + ", Dexterity - " + getDexterity() + ", Agility - " + getAgility());
        if(hasWeapon())
            System.out.println("Weapon - " + getWeapon().getName());
        if(hasArmor())
            System.out.println("Armor - " + getArmor().getName());
        System.out.println("Inventory - " + getInventory());
    }
    // sell Item from inventory
    public void sellItem(Item item){
        if(inventory.hasItem(item)){
            this.setGold(this.getGold() + item.getPrice()/2);
            inventory.removeItem(item);
        }
        else{
            System.out.println("You do not have this Item in your inventory");
        }
    }
    public Weapon getWeapon(){
        return this.weapon;
    }
    public Armor getArmor(){
        return this.armor;
    }
    public boolean hasWeapon() {
        return (this.weapon != null);
    }
    public boolean hasArmor() {
        return (this.armor != null);
    }
    // Methods to equip Weapon and Armor
    public void equipWeapon(Weapon weapon){
        if(this.hasWeapon()){
            Weapon oldWeapon = this.weapon;
            oldWeapon.unequip();
            this.inventory.addItem(oldWeapon);
        }
        this.weapon = weapon;
        weapon.equip();
        System.out.println("\033[0;32m" + this.getName() + " equipped " + weapon.getName() +"!" +  "\033[0m");
    }
    public void equipArmor(Armor armor){
        if(this.hasArmor()){
            Armor oldArmor = this.armor;
            oldArmor.unequip();
            this.inventory.addItem(oldArmor);
        }
        this.armor = armor;
        armor.equip();
        System.out.println("\033[0;32m" + this.getName() + " equipped " + armor.getName() +"!" +  "\033[0m");

    }
    // Heros have the ability to dodge and require this method as they implement Dodgeable Interface
    public boolean dodgedAttack(){
        return (random.nextInt(1000) < this.getAgility());
    }
    // cast spell on a monster
    public boolean castSpell(Spell spell,Monster monster){
        if(this.getLevel() >= spell.getLevel()){
            if(this.getMP() >= spell.getManaCost()){
                spell.use();
                if(!monster.dodgedAttack()){

                    this.setMP(this.getMP() - spell.getManaCost());
                    double damage = spell.getDamage() + (this.getDexterity()/10000 * spell.getDamage());
                    damage = damage - monster.getDefense()/100;
                    if(damage < 0)
                        damage = 0;
                    monster.setHP(monster.getHP() - damage); // Reduce damage done due to Monster Defense
                    if(monster.isAlive()){
                        System.out.println(this.getName() + " casted " + spell.getName() + " and dealt " + spell.getDamage() + " damage");
                    }
                    else{
                        System.out.println(this.getName() + " killed the " + monster.getName() + "!");
                    }
                }
                else{
                    System.out.println(monster.getName() + " dodged the attack!");
                }
                return true;
            }
            else{
                System.out.println("You do not have enough MP to cast this spell, you need at least: " + spell.getManaCost());
                return false;
            }
        }
        else{
            System.out.println("You cannot cast this spell yet! You need to be at least level: "+spell.getLevel());
            return false;
        }

    }
    // Use a potion and increase attribute stats
    public boolean usePotion(Potion potion){
        if(this.getLevel() >= potion.getLevel()){
            ArrayList<String> attributesAffected = potion.getAttributesAffected();
            double effectAmount = potion.getEffectAmount();
            for(String attribute : attributesAffected){
                if(attribute.equals("Health")){
                    this.setHP(this.getHP() + effectAmount);
                }
                if(attribute.equals("Mana")){
                    this.setMP(this.getMP() + effectAmount);
                }
                if(attribute.equals("Strength")){
                    this.setStrength(this.getStrength() + effectAmount);
                }
                if(attribute.equals("Dexterity")){
                    this.setDexterity(this.getDexterity() + effectAmount);
                }
                if(attribute.equals("Agility")){
                    this.setAgility(this.getAgility() + effectAmount);
                }

            }
            potion.use();
            System.out.println("\033[0;32m" + this.getName() + " drank " + potion.getName() +"!" +  "\033[0m");
            return true;
        }
        else{
            System.out.println("Your level is too low for this Potion! You need to be at least level " + potion.getLevel());
            return false;
        }

     }
     // Attack a monster
    public void heroAttack(Monster monster){
        double damage = 0;
        if(this.hasWeapon()){
             damage = (this.getStrength() + this.getWeapon().getDamage()) * 0.05;
        }
        else{
            damage = this.getStrength() * 0.05;
        }
        // See if Monster dodged the attack
        if(monster.dodgedAttack()){
            System.out.println(monster.getName() + " dodged the attack!");
            return;
        }
        else{
            damage = damage - monster.getDefense()/100; // Reduce damage done due to Monster Defense
            if(damage < 0)
                damage = 0;
            monster.setHP(monster.takeHit(damage));

            if(monster.isAlive()){
                System.out.println(this.getName() + " attacked the " + monster.getName() + " for " + damage + " damage!");
            }
            else{
                System.out.println(this.getName() + " killed the " + monster.getName() + "!");
            }
        }

    }
    @Override
    public boolean moveToCell(int row, int col, Board currBoard) {
        boolean result = super.moveToCell(row, col, currBoard);
        if (result) {
            // if cell already has a monster return false
            if (currBoard.getCell(row, col).hasHero()) {
                System.out.println("There is already a Hero on this cell!");
                return false;
            }

            // If moving up the board and there is a monster on current cell or next to it, return false
            if (row < this.getCurrRow()) {
                if (currBoard.getCell(this.getCurrRow(), this.getCurrCol()).hasMonster()) {
                    System.out.println("There is a monster on this cell!");
                    return false;
                }
                if (this.getCurrCol() > 0 && currBoard.getCell(this.getCurrRow(), this.getCurrCol() - 1).hasMonster()) {
                    System.out.println("There is a monster on the cell to the left!");
                    return false;
                }
                if (this.getCurrCol() < currBoard.getNumCols() - 1 && currBoard.getCell(this.getCurrRow(), this.getCurrCol() + 1).hasMonster()) {
                    System.out.println("There is a monster on the cell to the right!");
                    return false;
                }
            }
     

            currBoard.getCell(this.getCurrRow(), this.getCurrCol()).removeHero();
            // If the old cell had a boost, remove it
            if(currBoard.isBushCell(this.getCurrRow(),this.getCurrCol())){
                BushCell bushCell = (BushCell) currBoard.getCell(this.getCurrRow(), this.getCurrCol());
                bushCell.deboostHero(this, 1.1);
            }
            if(currBoard.isCaveCell(this.getCurrRow(),this.getCurrCol())){
                CaveCell caveCell = (CaveCell) currBoard.getCell(this.getCurrRow(), this.getCurrCol());
                caveCell.deboostHero(this, 1.1);
            }
            if(currBoard.isKoulouCell(this.getCurrRow(),this.getCurrCol())){
                KoulouCell koulouCell = (KoulouCell) currBoard.getCell(this.getCurrRow(), this.getCurrCol());
                koulouCell.deboostHero(this, 1.1);
            }


            this.setCurrRow(row);
            this.setCurrCol(col);
            currBoard.getCell(row, col).setHero(this);

            // if the cell is Bush, Cave, or Koulou I want to boost hero stats with Cell's boostHero method
            if(currBoard.isBushCell(row,col)){
                BushCell bushCell = (BushCell) currBoard.getCell(row, col);
                bushCell.boostHero(this, 1.1);
            }
            if(currBoard.isCaveCell(row,col)){
                CaveCell caveCell = (CaveCell) currBoard.getCell(row, col);
                caveCell.boostHero(this, 1.1);
            }
            if(currBoard.isKoulouCell(row,col)){
                KoulouCell koulouCell = (KoulouCell) currBoard.getCell(row, col);
                koulouCell.boostHero(this, 1.1);
            }
        }
        return result;
    }
    // teleport method should move current Hero to an adjacent cell to chosen Hero
    public boolean teleportToHero(Hero hero, Board currBoard) {
        int heroRow = hero.getCurrRow();
        int heroCol = hero.getCurrCol();
        
        // Check all adjacent cells to the hero
        System.out.println("\nAnalyzing the spaces...");
        int[][] adjacentCells = {{heroRow+1, heroCol}, {heroRow, heroCol-1}, {heroRow, heroCol+1}};
        for (int[] cell : adjacentCells) {
            int row = cell[0];
            int col = cell[1];
            if (this.moveToCell(row, col, currBoard)) {
                // Move to the first valid cell found if it's not diagonal
                System.out.println("\u001B[32m" + "You have been teleported!\n" + "\u001B[0m");
                this.setCurrLane(hero.getCurrLane());
                return true;
            }
        }
        System.out.println("Could not teleport to Hero. No valid adjacent cells found.");
        return false;
    }
    // recall method should return Hero to myNexus cell
    public void recall(Board currBoard){
        this.moveToCell(this.NexusRow,this.NexusCol, currBoard);
    }


}
