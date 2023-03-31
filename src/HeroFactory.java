// Hero Factory to create new Heros
public class HeroFactory {
    public static Hero createHero(String type, String name, double MP,double Strength,double Agility,double Dexterity,double Gold,double Exp){
        switch (type) {
            case "Warrior":
                return new Warrior(name, MP, Strength, Agility, Dexterity, Gold, Exp);
            case "Paladin":
                return new Paladin(name, MP, Strength, Agility, Dexterity, Gold, Exp);
            case "Sorcerer":
                return new Sorcerer(name, MP, Strength, Agility, Dexterity, Gold, Exp);

            default:
                throw new IllegalArgumentException("Invalid Hero type: " + type);
        }
    }
}
