// Spirit is a type of Monster that has a favored attribute of Dodge ability
public class Spirit extends Monster{
    public Spirit(String name, int level, double baseDamage, double Defense,double dodgeAbility) {
        super(name,level,baseDamage,Defense,dodgeAbility);
        addFavoredAttribute("Dodge");
    }


    public String toString() {
        return "Spirit: " + this.getName() + " Level - "+this.getLevel();
    }
}
