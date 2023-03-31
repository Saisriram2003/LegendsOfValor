public class CaveCell extends CommonCell implements boostingCell{
    public void boostHero(Hero hero,double boostAmount){
        hero.setAgility(hero.getAgility() * boostAmount);
    }
    public void deboostHero(Hero hero, double boostAmount){
        hero.setAgility(hero.getAgility() / boostAmount);
    }
}
