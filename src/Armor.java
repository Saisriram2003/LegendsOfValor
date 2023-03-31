// Armor is an Equippable item that can be equipped by a Hero
public class Armor extends Item implements Equippable{
    private double damageReduction;
    private boolean equipped = false;
    public Armor(String name, double price, int level, double damageReduction) {
        super(name, price, level);
        this.damageReduction = damageReduction;
    }
    public String toString(){
        return "Armor: " + this.getName();
    }
    public void equip(){
        equipped = true;
    }

    public void unequip() {
        equipped = false;
    }
    public boolean isEquipped(){
        return equipped;
    }
    public boolean equals(Armor armor) {
        if(this.getName() == armor.getName()){
            return true;
        }
        return false;

    }
    // Returns the damage reduction of the armor
    public double getDamageReduction(){
        return damageReduction;
    }
}
