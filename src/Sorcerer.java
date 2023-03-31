// Sorcerer Class - Favored on Dexterity & Agility
public class Sorcerer extends Hero{
    public Sorcerer(String name, double MP,double Strength,double Agility,double Dexterity,double Gold,double startingExp) {
        super(name,MP,Strength,Agility,Dexterity,Gold,startingExp);
        addFavoredAttribute("Dexterity");
        addFavoredAttribute("Agility");
    }
    public String toString() {
        return "Sorcerer: " + getName();
    }
    public void levelUp(){
        // Increase Level
        setLevel(getLevel() + 1);
        // Increase HP
        setHP(getLevel() * 100);
        // Increase MP
        setOriginalMP(getOriginalMP() * 1.1);
        setMP(getOriginalMP());

        // Favored Skills
        // Increase Dexterity by 10%
        setDexterity(getDexterity() * 1.1);
        // Increase Agility by 10%
        setAgility(getAgility() * 1.1);

        // Increase all other skills by 5%
        setStrength(getStrength() * 1.05);
        setGold(getGold() * 1.05);
        setExp(getExp() * 1.05);
}
}
