// Dragons are a type of Monster that has a favored attribute: Base Damage
public class Dragon extends Monster{
    public Dragon(String name, int level, double baseDamage, double Defense,double dodgeAbility) {
        super(name,level,baseDamage,Defense,dodgeAbility);
        addFavoredAttribute("Damage");
    }

    public String toString() {
        return "Dragon: " + this.getName() + " Level - "+this.getLevel();
    }

}
