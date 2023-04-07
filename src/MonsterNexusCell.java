// MonsterNexusCell is a NexusCell that spawns monsters and where Hero's need to reach
public class MonsterNexusCell extends NexusCell{
    public MonsterNexusCell(int row, int col){
        super(row, col);
    }

    // spawnMonster spawns a monster in the cell
    public void spawnMonster(int level) {
        Monster newMonster = MonsterFactory.createRandomMonster(level);
        this.setMonster(newMonster);
        newMonster.setCurrRow(this.getRow());
        newMonster.setCurrCol(this.getCol());
        Legends.monsterTeam.add(newMonster);
    }

}
