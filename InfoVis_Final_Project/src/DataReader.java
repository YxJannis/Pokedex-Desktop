import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataReader {

    ArrayList<Pokemon> pokemon;

    public DataReader() {
        readPokemon();
        readSprites();
    }

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    private void readPokemon(){
        pokemon = new ArrayList<Pokemon>();

        try(BufferedReader reader = new BufferedReader(new FileReader("src/pokemon.csv"))){
            String line = reader.readLine();
            line = reader.readLine();

            while(line != null){
                String[] attributes = line.split(",");


                /*
                System.out.println(attributes[32] + " " + attributes[30] + " " + attributes[39] + " " + attributes[40] + " "
                        + attributes[36] + " " + attributes[37] + " " + attributes[19] + " " + attributes[25] + " " +
                        attributes[33] + " " + attributes[34] + " " + attributes[35] + " " + attributes[28]);
                */

                Type type2 = null;
                if(!attributes[37].isEmpty()){
                    type2 = Type.valueOf(attributes[37].toUpperCase());
                }

                Pokemon p = new Pokemon(Integer.parseInt(attributes[19]),Integer.parseInt(attributes[25]),Integer.parseInt(attributes[33]),Integer.parseInt(attributes[34]),Integer.parseInt(attributes[35]),Integer.parseInt(attributes[28]),
                        attributes[30], Integer.parseInt(attributes[32]),Integer.parseInt(attributes[39]),"1".equals(attributes[40]),Type.valueOf(attributes[36].toUpperCase()), type2);

                /*
                p.setNumber(Integer.parseInt(attributes[32]));
                p.setName(attributes[30]);
                p.setGeneration(Integer.parseInt(attributes[39]));
                p.setLegendary("1".equals(attributes[40]));
                p.setType1(Type.valueOf(attributes[36].toUpperCase()));
                if(!attributes[37].isEmpty()){
                    p.setType2(Type.valueOf(attributes[37].toUpperCase()));
                }

                p.setAttack(Integer.parseInt(attributes[19]));
                p.setDefense(Integer.parseInt(attributes[25]));
                p.setSpAttack(Integer.parseInt(attributes[33]));
                p.setSpDefense(Integer.parseInt(attributes[34]));
                p.setSpeed(Integer.parseInt(attributes[35]));
                p.setHealth(Integer.parseInt(attributes[28]));
                */

                pokemon.add(p);
                line = reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readSprites(){
        for(Pokemon p : pokemon){
            BufferedImage sprite = null;
            try {
                sprite = ImageIO.read(new File("src/images/sprites/pokemon/" + p.getNumber() + ".png"));
            } catch (IOException e) {
                System.out.println("Error while reading sprite for pokemon " + p.getNumber());
                e.printStackTrace();
            }
            p.setSprite(sprite);
        }
    }

    public static void main(String[] args) {
        //DataReader reader = new DataReader();

        //reader.readPokemon();
        //reader.readSprites();
    }
}
