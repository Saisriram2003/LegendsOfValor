import java.util.Random;

// Cells that are of Common type
public class CommonCell extends Cell{
    public CommonCell(int row, int col){
        super("C", row, col);
    }

    // Function to check whether on a current cell a Battle occurs or not: 50% probability
    public boolean doesBattleOccur(){
        Random random = new Random();
        if(random.nextInt() > 0.5){
            return true;
        }
        return false;
    }


}
