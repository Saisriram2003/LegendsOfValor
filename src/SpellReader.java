// Spell Reader class is a file reader than reads Spell files
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpellReader extends MyFileReader<Spell>{
    public ArrayList<Spell> SpellList;
    private String spellType;

    public SpellReader(int numAttributes,String spellType) {
        super(numAttributes);
        this.spellType = spellType;
        this.SpellList = new ArrayList<Spell>();
    }
    public ArrayList<Spell> readFile(String filePath) {
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
                    double Damage = Double.parseDouble(attributes[3]);
                    double manaCost = Double.parseDouble(attributes[4]);
                    switch (spellType) {
                        case "Fire":
                            SpellList.add(new FireSpell(name, cost, level, Damage, manaCost,spellType));
                            break;
                        case "Ice":
                            SpellList.add(new IceSpell(name, cost, level, Damage, manaCost,spellType));
                            break;
                        case "Lightning":
                            SpellList.add(new LightningSpell(name, cost, level,Damage, manaCost,spellType));
                            break;
                    }
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
        return SpellList;
    }


}
