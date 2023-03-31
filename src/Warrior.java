// Warrior Class - Favored on Strength & Agility
public class Warrior extends Hero{
    public Warrior(String name, double MP,double Strength,double Agility,double Dexterity,double Gold,double startingExp) {
            super(name,MP,Strength,Agility,Dexterity,Gold,startingExp);
            addFavoredAttribute("Strength");
            addFavoredAttribute("Agility");
        }
    public String toString() {
        return "Warrior: " + getName();
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
            // Increase Agility by 10%
            setAgility(getAgility() * 1.1);

            // Increase all other skills by 5%
            setDexterity(getDexterity() * 1.05);
            setGold(getGold() * 1.05);
            setExp(getExp() * 1.05);
    }
}
