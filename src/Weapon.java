// Weapon class is an Item that can be equipped by a Hero
public class Weapon extends Item implements Equippable{
    private double Damage;
    private int requiredHands;
    private boolean equipped = false;
    public Weapon(String name, double price, int level, double Damage, int requiredHands) {
        super(name, price, level);
        this.Damage = Damage;
        this.requiredHands = requiredHands;
    }
    // returns damage of the Weapon
    public double getDamage() {
        return Damage;
    }

    public String toString() {
        return "Weapon: " + this.getName();
    }
    public void equip(){
        this.equipped = true;
    }

    public void unequip() {
        this.equipped = false;
    }
    public boolean isEquipped(){
        return equipped;
    }
    public boolean equals(Weapon weapon) {
        if(this.getName() == weapon.getName()){
            return true;
        }
        return false;

    }
}
