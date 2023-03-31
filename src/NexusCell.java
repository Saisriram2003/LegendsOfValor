// NexusCell is the parent class to Nexus cells and is the homebase where Items can be bought
import java.util.ArrayList;
import java.lang.Character;
import java.util.Iterator;

public class NexusCell extends Cell{
    private Market marketCreator = new Market();
    private ArrayList<Item> itemsToSell;
    public NexusCell() {
        super("N");
        this.itemsToSell = marketCreator.createMarketInstance(2);
    }
    public void showItemsToSell(){
        System.out.println("Here are the items that are at Sale at this Market:");
        for(Item i : itemsToSell){
            // Show weapons
            if(i instanceof Weapon){
                System.out.println(i.getName() + ": " + "$"+i.getPrice() + " (Damage: " + ((Weapon) i).getDamage() + ")");
            }
            // Show armors
            else if(i instanceof Armor){
                System.out.println(i.getName() + ": " + "$"+i.getPrice() + " (Defense: " + ((Armor) i).getDamageReduction() + ")");
            }
            // Show potions
            else if(i instanceof Potion){
                System.out.println(i.getName() + ": " + "$"+i.getPrice() + " (Healing: " + ((Potion) i).getEffectAmount() + ")");
            }
            // Show Spells
            else if(i instanceof Spell){
                System.out.println(i.getName() + ": " + "$"+i.getPrice() + " (Damage: " + ((Spell) i).getDamage() + ")");
            }
        }
    }
    public void addItem(Item item){
        itemsToSell.add(item);
    }
    public void removeItem(Item item){
        itemsToSell.remove(item);
    }

    // getItem method to get an item from the Market using an Iterator
    public Item getItem(String name){
        Iterator<Item> iterator = itemsToSell.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName().equals(name)) {
                iterator.remove();
                return item;
            }
        }
        System.out.println("That item does not exist");
        return null;
    }
    // enterMarket method to enter the Market and buy or sell items
    public void enterMarket(Hero hero) {
        boolean keepShopping = true;

        do {
            System.out.println("Welcome to the market!");
            System.out.println("What would you like to do?");
            System.out.println("1. Buy Items");
            System.out.println("2. Sell Items");
            System.out.println("3. Exit Market");

            int choice = InputValidation.getValidInt("Enter your choice: ",1,3);

            switch (choice) {
                case 1:
                    this.showItemsToSell();
                    char marketBuy = InputValidation.getValidChar("Would you like to buy any of these Items? (Y/N): ");

                    if (Character.toLowerCase(marketBuy) == 'y') {
                        for (int i = 0; i < itemsToSell.size(); i++) {
                            System.out.println(i + ". " + itemsToSell.get(i).getName() + " - " + ""+itemsToSell.get(i).getPrice());
                        }
                        int itemIndex = InputValidation.getValidInt("Enter the index of the item you would like to buy: ", 0, itemsToSell.size() - 1);
                        Item item = itemsToSell.get(itemIndex);
                        System.out.println("You have " + hero.getGold() + " gold.");
                        hero.buyItem(item);
                        this.removeItem(item);
                    } else {
                        System.out.println("Have a good day!");
                        keepShopping = false;
                    }
                    break;

                case 2:
                    // Check if Hero has no items
                    if (hero.getInventory().isEmpty()) {
                        System.out.println("You have no items to sell.");
                        break;
                    }
                    System.out.println("Inventory: " + hero.getInventory());
                    String itemName = InputValidation.getValidString("Enter the name of the item you would like to sell: ");
                    Item item = hero.getInventory().getItem(itemName);
                    if (item != null) {
                        hero.sellItem(item);
                        System.out.println("You have sold " + item.getName() + " for " + item.getPrice()/2 + " gold!");
                        this.addItem(item);
                    } else {
                        System.out.println("You do not have that item.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for shopping!");
                    keepShopping = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (keepShopping);
    }

}
