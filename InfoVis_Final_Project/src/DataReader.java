import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

    ArrayList<Pokemon> pokemon;

    public DataReader() {
    }

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    private void readPokemonFromFile(){
        pokemon = new ArrayList<Pokemon>();
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
        DataReader reader = new DataReader();
    }
}
