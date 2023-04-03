import java.util.ArrayList;

public class MonsterNexusCell extends NexusCell{
    public MonsterNexusCell(int row, int col){
        super(row, col);
    }
    public void spawnMonster(int level) {
        Monster newMonster = MonsterFactory.createRandomMonster(level);
        this.setMonster(newMonster);
        newMonster.setCurrRow(this.getRow());
        newMonster.setCurrCol(this.getCol());
        Legends.monsterTeam.add(newMonster);
    }

}
