// Market class to maintain the Market items and create Markets for cells
import java.util.ArrayList;
import java.util.Random;

public class Market {
    private LegendGameSetup gameSetup = LegendGameSetup.getInstance();
    private Random random;

    public Market() {
        this.random = new Random();
    }
    // createMarketInstance method to create a Market for a cell with n items from each category
    public ArrayList<Item> createMarketInstance(int n) {
        ArrayList<Item> market = new ArrayList<>();
        market.addAll(getRandomItems(gameSetup.getWeaponList(), n));
        market.addAll(getRandomItems(gameSetup.getArmorList(), n));
        market.addAll(getRandomItems(gameSetup.getPotionList(), n));
        market.addAll(getRandomItems(gameSetup.getSpellList(), n));
        return market;
    }
    // getRandomItems method to get n random items from a list
    private ArrayList<Item> getRandomItems(ArrayList<? extends Item> items, int n) {
        ArrayList<Item> randomItems = new ArrayList<>();
        ArrayList<Item> remainingItems = new ArrayList<>(items); // make a copy of the original list

        for (int i = 0; i < n; i++) {
            int randomIndex = random.nextInt(remainingItems.size());
            randomItems.add(remainingItems.remove(randomIndex)); // remove the item from the original list after adding it
        }

        return randomItems;
    }
}