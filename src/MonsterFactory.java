// Class to create new Monsters
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MonsterFactory {
    // Create a new Monster given all the parameters
    public static Monster createMonster(String type, String name, int level, double baseDamage, double defense, double dodgeAbility) {
        switch (type) {
            case "Dragon":
                return new Dragon(name, level, baseDamage, defense, dodgeAbility);
            case "Spirit":
                return new Spirit(name, level, baseDamage, defense, dodgeAbility);
            case "Exoskeleton":
                return new Exoskeleton(name, level, baseDamage, defense, dodgeAbility);
            default:
                throw new IllegalArgumentException("Invalid monster type: " + type);
        }
    }
    // Create Monsters for a battle based on level and quantity
    public static ArrayList<Monster> createBattleMonsters(int level, int n) {
//        System.out.println("Creating battle monsters at level: " + level + ", : " + n + " monsters");
        Random random = new Random();
        LegendGameSetup gameSetup = LegendGameSetup.getInstance();
        HashMap<Integer, ArrayList<Monster>> MonsterLevelMap =  gameSetup.getMonsterLevelMap();
        ArrayList<Monster> monsters = MonsterLevelMap.get(level);
        ArrayList<Monster> battleMonsters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(monsters.size());
            Monster monster = monsters.get(index);
            // createNewMonster is a method that creates a new Monster with the same name, level, baseDamage, Defense, and dodgeAbility
            battleMonsters.add(createMonster(monster.getClass().getSimpleName(), monster.getName(), monster.getLevel(), monster.getBaseDamage(), monster.getDefense(), monster.getDodgeAbility()));
        }
        return battleMonsters;
    }
    public static Monster createRandomMonster(int level) {
        Random random = new Random();
        LegendGameSetup gameSetup = LegendGameSetup.getInstance();
        HashMap<Integer, ArrayList<Monster>> MonsterLevelMap = gameSetup.getMonsterLevelMap();
        ArrayList<Monster> monsters = MonsterLevelMap.get(level);
        int index = random.nextInt(monsters.size());
        Monster monster = monsters.get(index);
        Monster newMonster =  createMonster(monster.getClass().getSimpleName(), monster.getName(), monster.getLevel(), monster.getBaseDamage(), monster.getDefense(), monster.getDodgeAbility());
        return newMonster;
    }
}