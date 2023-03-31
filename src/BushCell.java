public class BushCell extends CommonCell implements boostingCell{
    public void boostHero(Hero hero,double boostAmount){
        hero.setDexterity(hero.getDexterity() * boostAmount);
    }
    public void deboostHero(Hero hero, double boostAmount){
        hero.setDexterity(hero.getDexterity() / boostAmount);
    }
}
