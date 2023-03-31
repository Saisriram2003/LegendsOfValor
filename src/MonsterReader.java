// Monster Reader class is a file reader than reads Monster files

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MonsterReader extends MyFileReader<Monster>{
    private String MonsterType;
    public ArrayList<Monster> MonsterList;
    MonsterFactory monsterFactory = new MonsterFactory();

    public MonsterReader(int numAttributes,String MonsterType) {
        super(numAttributes);
        this.MonsterType = MonsterType;
        this.MonsterList = new ArrayList<Monster>();
    }
    public ArrayList<Monster> readFile(String filePath) {
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
                    int level = Integer.parseInt(attributes[1]);
                    double baseDamage = Double.parseDouble(attributes[2]);
                    double Defense = Double.parseDouble(attributes[3]);
                    double dodgeAbility = Double.parseDouble(attributes[4]);
                    MonsterList.add(MonsterFactory.createMonster(this.MonsterType,name, level, baseDamage, Defense, dodgeAbility));
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
        return MonsterList;
    }

}

