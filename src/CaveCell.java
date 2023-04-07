// CaveCell boost hero's agility by boostAmount
public class CaveCell extends CommonCell implements boostingCell{
    public CaveCell(int row, int col){
        super(row, col);
    }
    public void boostHero(Hero hero,double boostAmount){
        hero.setAgility(hero.getAgility() * boostAmount);
    }
    public void deboostHero(Hero hero, double boostAmount){
        hero.setAgility(hero.getAgility() / boostAmount);
    }
}
