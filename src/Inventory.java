// Inventory class maintains the Inventory and methods related to the Inventory
import java.util.ArrayList;
import java.lang.Character;
import java.util.Iterator;

public class Inventory {
    private ArrayList<Item> InventoryList;
    public Inventory(){
        this.InventoryList = new ArrayList<Item>();
    }
    public int getSize(){
        return InventoryList.size();
    }
    public void addItem(Item item){
        InventoryList.add(item);
    }
    public void removeItem(Item item){
        Iterator<Item> iterator = InventoryList.iterator();
        while (iterator.hasNext()) {
            Item currentItem = iterator.next();
            if (currentItem.equals(item)) {
                iterator.remove();
                break;
            }
        }
    }
    public boolean hasItem(Item item){
        for(Item i: InventoryList){
            if(i.equals(item)){
                return true;
            }
        }
        return false;
    }
    public Item getItem(String itemName){
        for(Item i: InventoryList){
            if(i.getName().equals(itemName)){
                return i;
            }
        }
        return null;
    }
    public boolean isEmpty(){
        return InventoryList.isEmpty();
    }
    public String toString(){
            return InventoryList.toString();
    }
    public ArrayList<Spell> getSpells(){
        // Get list of spells in inventory
        ArrayList<Spell> spells = new ArrayList<Spell>();
        for(Item i: InventoryList){
            if(i instanceof Spell){
                Spell spell = (Spell) i;
                if(!spell.isConsumed()){
                    spells.add(spell);
                }
            }
        }
        return spells;
    }
    public ArrayList<Potion> getPotions(){
        // Get list of potions in inventory
        ArrayList<Potion> potions = new ArrayList<Potion>();
        for(Item i: InventoryList){
            if(i instanceof Potion){
                Potion potion = (Potion) i;
                if(!potion.isConsumed()){
                    potions.add(potion);
                }
            }
        }
        return potions;
    }
    public ArrayList<Weapon> getWeapons(){
        // Get list of weapons in inventory
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        for(Item i: InventoryList){
            if(i instanceof Weapon){
                Weapon weapon = (Weapon) i;
                if(!weapon.isEquipped()){
                    weapons.add(weapon);
                }
            }
        }
        return weapons;
    }
    // Checks Available items in inventory and removes consumed/equipped items
    public void setNewInventory() {
        ArrayList<Item> newList = new ArrayList<>();

        for (Item item : this.InventoryList) {
            if (item instanceof Equippable) {
                Equippable equippableItem = (Equippable) item;
                if (!equippableItem.isEquipped()) {
                    newList.add(item);
                }
            } else if (item instanceof Consumable) {
                Consumable consumableItem = (Consumable) item;
                if (!consumableItem.isConsumed()) {
                    newList.add(item);
                }
            }
        }

        InventoryList = newList;
    }
    public ArrayList<Armor> getArmor(){
        // Get list of armor in inventory
        ArrayList<Armor> armor = new ArrayList<Armor>();
        for(Item i: InventoryList){
            if(i instanceof Armor){
                Armor a = (Armor) i;
                if(!a.isEquipped()){
                    armor.add(a);
                }
            }
        }
        return armor;
    }
    //Available Equippable items

    public ArrayList<Item> getEquippableItems() {
        ArrayList<Item> equippableItems = new ArrayList<>();
        setNewInventory();
        if(InventoryList.isEmpty())
        {
            System.out.println("You have no items in your inventory.");
        }
        else
        {
            for (Item item : InventoryList) {
                if (item instanceof Equippable) {
                    if (!((Equippable)item).isEquipped()) {
                        equippableItems.add(item);
                    }
                }
            }
        }
        return equippableItems;

    }
    // Show all available items
    public void showAvailableItems() {
        setNewInventory();
        if(InventoryList.isEmpty()){
            System.out.println("You have no items in your inventory.");
            return;
        }
        else{
            System.out.println("You have the following items in your inventory: ");
            for (Item item : InventoryList) {
                System.out.println(item);
            }
        }

    }
    // method to enter potion menu and ask if user wants to consume potion
    public boolean enterPotionMenu(Hero hero){
        System.out.println("You have entered your potion menu.");
        boolean donePotionMenu = false;
        // get potions using the helper method

        ArrayList<Potion> potions = getPotions();
        if(potions.isEmpty()){
            System.out.println("You have no potions in your inventory.");
            return false;
        }
        do{
            potions = getPotions();
            System.out.println("You have the following potions in your inventory: ");
            if(potions.isEmpty()){
                break;
            }
            for(int i = 0; i < potions.size(); i++){
                System.out.println(i + ": " + potions.get(i));
            }

            char input = InputValidation.getValidChar("Would you like to consume a potion (Y/N)?: ");
            if(Character.toLowerCase(input) == 'y'){
                int potionIndex = InputValidation.getValidInt("Enter the index of the potion you would like to consume: ", 0, potions.size() - 1);
                Potion potion = potions.get(potionIndex);
                hero.usePotion(potion);
                System.out.println("Your new stats are:");
                hero.showStats();
            }
            else{
                donePotionMenu = true;
            }
        }while(!donePotionMenu);
        return true;
    }
    // method to enterSpellMenu and ask if user wants to cast spell returns if user cast a spell or not
    public boolean enterSpellMenu(Hero hero,Monster monster){
        System.out.println("You have entered your spell menu.");

            // get spells using the helper method
            ArrayList<Spell> spells = getSpells();
            if(spells.isEmpty()){
                System.out.println("You have no spells in your inventory.");
                return false;
            }
            else{
                System.out.println("You have the following spells in your inventory: ");
                for(int i = 0; i < spells.size(); i++){
                    System.out.println(i + ": " + spells.get(i));
                }
            }
            char input = InputValidation.getValidChar("Would you like to cast a spell (Y/N)?: ");
            if(Character.toLowerCase(input) == 'y'){

                int spellIndex = InputValidation.getValidInt("Enter the index of the spell you would like to cast: ", 0, spells.size() - 1);
                Spell spell = spells.get(spellIndex);
                if(hero.castSpell(spell,monster)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
    }
// method to enter equipItem menu and ask if user wants to equip item
    public boolean enterEquipItemMenu(Hero hero){
        System.out.println("You have entered your equip item menu.");
        boolean doneEquipItemMenu = false;
        // get equippable items using the helper method
        ArrayList<Item> equippableItems = getEquippableItems();
        if(equippableItems.isEmpty()){
            System.out.println("You have no equippable items in your inventory.");
            return false;
        }
        do{
            equippableItems = getEquippableItems();
            if(equippableItems.isEmpty()){
                break;
            }
            System.out.println("You have the following equippable items in your inventory: ");
            for(int i = 0; i < equippableItems.size(); i++) {
                System.out.println(i + ": " + equippableItems.get(i));
            }
            char input = InputValidation.getValidChar("Would you like to equip an item (Y/N)?: ");
            if(Character.toLowerCase(input) == 'y'){
                // Show list of equippable items with their index 
                int itemIndex = InputValidation.getValidInt("Enter the number of the item you would like to equip: ", 0, equippableItems.size() - 1);
                Item item = equippableItems.get(itemIndex);
                if(item instanceof Weapon){
                    hero.equipWeapon((Weapon)item);
                }
                else if(item instanceof Armor){
                    hero.equipArmor((Armor)item);
                }
            }
            else{
                doneEquipItemMenu = true;
            }
        }while(!doneEquipItemMenu);
        return true;
    }


    
    // method to enter inventory and ask if user wants to equip armor/weapon or consume potion
    public void enterInventory(Hero hero){
        System.out.println("You have entered your inventory.");
        boolean doneInventory = false;
        do{
            showAvailableItems();
            if(this.isEmpty()){
                return;
            }
            char input = InputValidation.getValidChar("Would you like to equip an item or consume a potion (Y/N)?: ");
            if(Character.toLowerCase(input) == 'y'){
                // Have a switch case that calls the appropriate method
                // 1. Equip item, 2. Consume potion
                int choice = InputValidation.getValidInt("Enter 1 to Equip an item \nEnter 2 to Consume a Potion \nEnter your choice: ", 1, 2);
                switch(choice){
                    case 1:
                        enterEquipItemMenu(hero);
                        break;
                    case 2:
                        enterPotionMenu(hero);
                        break;
                }
                if(this.isEmpty()){
                    doneInventory = true;
                }
            }
            else{
                doneInventory = true;
            }
        }while(!doneInventory);
        setNewInventory();
    }
}
