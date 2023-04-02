public class KoulouCell extends CommonCell implements boostingCell{
    public KoulouCell(int row, int col){
        super(row, col);
    }
    public void boostHero(Hero hero,double boostAmount){
        hero.setStrength(hero.getStrength() * boostAmount);
    }
    public void deboostHero(Hero hero, double boostAmount){
        hero.setStrength(hero.getStrength() / boostAmount);
    }

}
