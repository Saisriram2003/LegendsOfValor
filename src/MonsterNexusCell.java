public class MonsterNexusCell extends NexusCell{
    public MonsterNexusCell(int row, int col){
        super(row, col);
    }
    public void spawnMonster(int level){
        Monster newMonster = MonsterFactory.createRandomMonster(level);
        this.setMonster(newMonster);
    }



}
