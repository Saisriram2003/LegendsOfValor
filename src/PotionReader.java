// Potion Reader class is a file reader than reads Potion files

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PotionReader extends MyFileReader<Potion>{
    public ArrayList<Potion> PotionList;
    private String[] listOfAttributes;
    private ArrayList<String> attributesAffected;
    public PotionReader(int numAttributes) {
        super(numAttributes);
        this.PotionList = new ArrayList<Potion>();
    }
    public ArrayList<Potion> readFile(String filePath) {
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
                if(attributes.length == numAttributes || attributes.length == numAttributes + 1) {
                    String name = attributes[0];
                    double cost = Double.parseDouble(attributes[1]);
                    int level = Integer.parseInt(attributes[2]);
                    double effectAmount = Double.parseDouble(attributes[3]);
                    attributesAffected = new ArrayList<String>();
                    if (attributes.length == numAttributes) {
                        listOfAttributes = attributes[4].split("/");
                    }
                    else{
                        listOfAttributes = attributes[5].split("/");
                    }
                    for (String attribute : listOfAttributes) {
                        attributesAffected.add(attribute);
                    }
                    Potion potion = new Potion(name, cost, level, effectAmount, attributesAffected);
                    PotionList.add(potion);
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
        return PotionList;
    }


}
