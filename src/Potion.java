// Potion class is a consumable item that can be used to increase a hero's attributes
import java.util.ArrayList;

public class Potion extends Item implements Consumable{
    private double effectAmount;
    private boolean consumed;
    private ArrayList<String> attributesAffected;
    public Potion(String name, double price, int level, double effectAmount, ArrayList<String> attributesAffected) {
        super(name, price, level);
        this.effectAmount = effectAmount;
        this.attributesAffected = attributesAffected;
        this.consumed = false;
    }
    public double getEffectAmount() {
        return effectAmount;
    }

    public void setEffectAmount(double effectAmount) {
        this.effectAmount = effectAmount;
    }

    public ArrayList<String> getAttributesAffected() {
        return attributesAffected;
    }

    public void setAttributesAffected(ArrayList<String> attributesAffected) {
        this.attributesAffected = attributesAffected;
    }
    public String toString() {
        return "Potion: " + this.getName();
    }
    public void use(){
        this.consumed = true;
    }
    public boolean isConsumed(){
        return this.consumed;
    }

}
