// Legends GameSetup class that handles the game setup for Legends
import java.util.ArrayList;
import java.util.HashMap;

public class LegendGameSetup extends GameSetup{
    private ArrayList<Hero> HeroList = new ArrayList<Hero>();
    private ArrayList<Monster> MonsterList = new ArrayList<Monster>();
    private ArrayList<Spell> SpellList = new ArrayList<Spell>();
    private ArrayList<Potion> PotionList = new ArrayList<Potion>();
    private ArrayList<Armor> ArmorList = new ArrayList<Armor>();
    private ArrayList<Weapon> WeaponList = new ArrayList<Weapon>();
    private ArrayList<Item> ItemList = new ArrayList<Item>();

    private HashMap<String, ArrayList<Hero>> HeroMap = new HashMap<>();
    private HashMap<Integer, ArrayList<Monster>> MonsterLevelMap = new HashMap<>();
    private HashMap<String, ArrayList<Item>> itemMap = new HashMap<>();



    private HeroReader warriorReader = new HeroReader(7,"Warrior");
    private HeroReader paladinReader = new HeroReader(7,"Paladin");
    private HeroReader sorcererReader = new HeroReader(7,"Sorcerer");

    private MonsterReader dragonReader = new MonsterReader(5,"Dragon");
    private MonsterReader exoskeletonReader = new MonsterReader(5,"Exoskeleton");
    private MonsterReader spiritReader = new MonsterReader(5,"Spirit");

    private SpellReader fireReader = new SpellReader(5,"Fire");
    private SpellReader iceReader = new SpellReader(5,"Ice");
    private SpellReader lightningReader = new SpellReader(5,"Lightning");
    private PotionReader potionReader = new PotionReader(5);
    private WeaponryReader weaponryReader = new WeaponryReader(5);
    private ArmoryReader armoryReader = new ArmoryReader(4);


    // Singleton Design Pattern as we only will have one instance of the LegendGameSetup used globally
    private static LegendGameSetup instance = null;
    // Private constructor to prevent other classes from instantiating
    private LegendGameSetup(){
        setHeroList();
        setMonsterList();
        setSpellList();
        setPotionList();
        setArmorList();
        setWeaponList();
    }
    // Public static method to get the single instance of the class
    public static LegendGameSetup getInstance() {
        if (instance == null) {
            instance = new LegendGameSetup();
        }
        return instance;
    }
    // Setters
    public void setHeroList(){
        HeroMap.put("Warrior", warriorReader.readFile("LegendsTXT/Warriors.txt"));
        HeroMap.put("Paladin", paladinReader.readFile("LegendsTXT/Paladins.txt"));
        HeroMap.put("Sorcerer", sorcererReader.readFile("LegendsTXT/Sorcerers.txt"));
        for (String key : HeroMap.keySet()) {
            HeroList.addAll(HeroMap.get(key));
        }
    }
    public void setMonsterList(){
        MonsterList.addAll(dragonReader.readFile("LegendsTXT/Dragons.txt"));
        MonsterList.addAll(exoskeletonReader.readFile("LegendsTXT/Exoskeletons.txt"));
        MonsterList.addAll(spiritReader.readFile("LegendsTXT/Spirits.txt"));

        for (Monster monster : MonsterList) {
            int level = monster.getLevel();

            if (MonsterLevelMap.containsKey(level)) {
                MonsterLevelMap.get(level).add(monster);
            } else {
                ArrayList<Monster> monstersAtLevel = new ArrayList<>();
                monstersAtLevel.add(monster);
                MonsterLevelMap.put(level, monstersAtLevel);
            }
        }

    }
    public void setPotionList(){
        PotionList.addAll(potionReader.readFile("LegendsTXT/Potions.txt"));
    }
    public void setArmorList(){
        ArmorList.addAll(armoryReader.readFile("LegendsTXT/Armory.txt"));
    }
    public void setWeaponList(){
        WeaponList.addAll(weaponryReader.readFile("LegendsTXT/Weaponry.txt"));
    }
    public void setSpellList(){
        SpellList.addAll(fireReader.readFile("LegendsTXT/FireSpells.txt"));
        SpellList.addAll(iceReader.readFile("LegendsTXT/IceSpells.txt"));
        SpellList.addAll(lightningReader.readFile("LegendsTXT/LightningSpells.txt"));
    }

    // Getters
    public ArrayList<Hero> getAllHeroList() {
        return HeroList;
    }
    public HashMap<String, ArrayList<Hero>> getHeroMap() {
        return HeroMap;
    }
    public HashMap<Integer, ArrayList<Monster>> getMonsterLevelMap() {
        return MonsterLevelMap;
    }

    public ArrayList<Spell> getSpellList(){
        return SpellList;
    }

    public ArrayList<Weapon> getWeaponList() {
        return WeaponList;
    }

    public ArrayList<Potion> getPotionList() {
        return PotionList;
    }

    public ArrayList<Armor> getArmorList() {
        return ArmorList;
    }

    // Printing methods
    public void showHeroes(){
        System.out.println(HeroList.toString());
    }

    public void printMonsterLevelMap() {
        for (int level : MonsterLevelMap.keySet()) {
            System.out.println("Level " + level + ":");
            ArrayList<Monster> monsters = MonsterLevelMap.get(level);
            for (Monster monster : monsters) {
                System.out.println("\t" + monster.getName());
            }
        }
    }

    public void showMonsters(){
        System.out.println(MonsterList.toString());
    }

    public void showSpells(){
        System.out.println(SpellList.toString());
    }

    public void showPotions(){
        System.out.println(PotionList.toString());
    }


}
