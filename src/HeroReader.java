// Hero Reader class is a file reader than reads Hero files
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HeroReader extends MyFileReader<Hero>{
    private String HeroType;
    public ArrayList<Hero> HeroList;

    public HeroReader(int numAttributes,String HeroType) {
        super(numAttributes);
        this.HeroType = HeroType;
        this.HeroList = new ArrayList<Hero>();
    }
    public ArrayList<Hero> readFile(String filePath) {
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
                    double MP = Double.parseDouble(attributes[1]);
                    double Strength = Double.parseDouble(attributes[2]);
                    double Agility = Double.parseDouble(attributes[3]);
                    double Dexterity = Double.parseDouble(attributes[4]);
                    double Gold = Double.parseDouble(attributes[5]);
                    double Exp = Double.parseDouble(attributes[6]);
                    HeroList.add(HeroFactory.createHero(this.HeroType,name, MP, Strength, Agility, Dexterity, Gold, Exp));
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
        return HeroList;
    }


}
