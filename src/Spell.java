// Abstract Spell class that is consumbable
public abstract class Spell extends Item implements Consumable {
    private double manaCost;
    private double Damage;
    private String type;
    private boolean consumed;


    public Spell(String name, double price, int level,  double Damage,double manaCost, String type) {
        super(name, price, level);
        this.Damage = Damage;
        this.manaCost = manaCost;
        this.type = type;
        this.consumed = false;
    }
    public double getManaCost() {
        return manaCost;
    }

    public void setManaCost(double manaCost) {
        this.manaCost = manaCost;
    }

    public double getDamage() {
        return Damage;
    }

    public void setDamage(double Damage) {
        this.Damage = Damage;
    }

    public String getType() {
        return type;
    }
    public void use(){
        this.consumed = true;
    }
    public boolean isConsumed(){
        return this.consumed;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String toString() {
        return "Spell: " + this.getName();
    }
}
