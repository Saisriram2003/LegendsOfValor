// Weaponry Reader class is a file reader than reads Weapon files
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WeaponryReader extends MyFileReader<Weapon>{
    public ArrayList<Weapon> WeaponList;
    public WeaponryReader(int numAttributes) {
        super(numAttributes);
        this.WeaponList = new ArrayList<Weapon>();
    }
    public ArrayList<Weapon> readFile(String filePath) {
        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean first = true;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(first){
                    String[] AttributeList = line.trim().split("/");
//                    for(String attr : AttributeList){
//                        System.out.print(attr + " ");
//                    }
                    first = false;
                    continue;
                }
                String[] attributes = line.split("\\s+");
                if(attributes.length == numAttributes) {
                    String name = attributes[0];
                    double cost = Double.parseDouble(attributes[1]);
                    int level = Integer.parseInt(attributes[2]);
                    double damage = Double.parseDouble(attributes[3]);
                    int requiredHands = Integer.parseInt(attributes[4]);
                    WeaponList.add(new Weapon(name, cost, level, damage, requiredHands));
                }
                else{
                    break;
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return WeaponList;
    }


}
