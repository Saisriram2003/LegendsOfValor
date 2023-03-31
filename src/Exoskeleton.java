// Exoskeleton is a type of Monster that has a favored attribute of Defense
public class Exoskeleton extends Monster{
    public Exoskeleton(String name, int level, double baseDamage, double Defense,double dodgeAbility) {
        super(name,level,baseDamage,Defense,dodgeAbility);
        addFavoredAttribute("Defense");
    }
    public String toString() {
        return "Exoskeleton: " + this.getName() + " Level - "+this.getLevel();
    }
}
