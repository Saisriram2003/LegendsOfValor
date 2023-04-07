// Interface that is implemented by all cells that boost a hero
public interface boostingCell {
    public void boostHero(Hero hero,double boostAmount);
    public void deboostHero(Hero hero, double boostAmount);

    }
