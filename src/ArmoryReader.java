// Armory reader is a file reader that reads Armor files
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArmoryReader extends MyFileReader<Armor>{
    public ArrayList<Armor> ArmorList;
    public ArmoryReader(int numAttributes) {
        super(numAttributes);
        this.ArmorList = new ArrayList<Armor>();
    }
    public ArrayList<Armor> readFile(String filePath) {
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
                    double damageReduction = Double.parseDouble(attributes[3]);
                    ArmorList.add(new Armor(name, cost, level, damageReduction));
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
        return ArmorList;
    }


}
