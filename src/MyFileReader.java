// Abstract Class to read files
import java.util.ArrayList;

public abstract class MyFileReader<T> {
    protected int numAttributes;
    public MyFileReader(int numAttributes){
        this.numAttributes = numAttributes;
    }
    public abstract ArrayList<T> readFile(String filePath);

}
