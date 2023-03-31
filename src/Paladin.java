// Paladin class - Favored on Strength & Dexterity
public class Paladin extends Hero {
    public Paladin(String name, double MP,double Strength,double Agility,double Dexterity,double Gold,double startingExp) {
        super(name,MP,Strength,Agility,Dexterity,Gold,startingExp);
        addFavoredAttribute("Strength");
        addFavoredAttribute("Dexterity");
    }
    public String toString() {
        return "Paladin: " + getName();
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
        // Increase Strength by 10%
        setStrength(getStrength() * 1.1);
        // Increase Dexterity by 10%
        setDexterity(getDexterity() * 1.1);

        // Increase all other skills by 5%
        setAgility(getAgility() * 1.05);
        setGold(getGold() * 1.05);
        setExp(getExp() * 1.05);
    }
}
