import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataProvider {
    private ArrayList<Pokemon> pokemon;


    public DataProvider(){
        DataReader dataReader = new DataReader();
        this.pokemon = dataReader.getPokemon();
    }

    public void sortByAttribute(String attribute){
        switch(attribute){
            case "attack":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getAttack() == o2.getAttack()){
                        return 0;
                    }
                    return o1.getAttack() < o2.getAttack() ? -1 : 1;
                });
                for (int i = 0; i < pokemon.size(); i++) {

                    System.out.println(pokemon.get(i).getAttack());
                }
                break;
            case "defense":
                Collections.sort(pokemon, new Comparator<Pokemon>() {
                    @Override
                    public int compare(Pokemon o1, Pokemon o2) {
                        if (o1.getDefense() == o2.getDefense()){
                            return 0;
                        }
                        return o1.getDefense() < o2.getDefense() ? -1 : 1;
                    }
                });
                break;
            case "spAttack":
                Collections.sort(pokemon, new Comparator<Pokemon>() {
                    @Override
                    public int compare(Pokemon o1, Pokemon o2) {
                        if (o1.getSpAttack() == o2.getSpAttack()){
                            return 0;
                        }
                        return o1.getSpAttack() < o2.getSpAttack() ? -1 : 1;
                    }
                });
                break;
            case "number":
                Collections.sort(pokemon, new Comparator<Pokemon>() {
                    @Override
                    public int compare(Pokemon o1, Pokemon o2) {
                        if (o1.getNumber() == o2.getNumber()){
                            return 0;
                        }
                        return o1.getNumber() < o2.getNumber() ? -1 : 1;
                    }
                });
                break;

        }
    }

    public double getAttackScale(int attack){
        if (attack > 185){
            attack = 185;
        }
        if (attack < 5){
            attack = 5;
        }
        double baseValue = 0.2;
        double scale = (Math.ceil((attack-4)/10.7)-1)*0.05+baseValue;
        return scale;
    }

    public double getDefenseScale(int defense){
        if (defense > 230){
            defense = 230;
        }
        if (defense < 5){
            defense = 5;
        }
        double baseValue = 0.2;
        double scale = (Math.ceil((defense-4)/13.3)-1)*0.05+baseValue;
        return scale;
    }

    public double getSpAttackScale(int spAttack){
        if (spAttack > 194){
            spAttack = 194;
        }
        if (spAttack < 10){
            spAttack = 10;
        }
        double baseValue = 0.2;
        double scale = (Math.ceil((spAttack-9)/10.9)-1)*0.05+baseValue;
        return scale;
    }

    public double getSpDefenseScale(int spDefense){
        if (spDefense > 230){
            spDefense = 230;
        }
        if (spDefense < 20){
            spDefense = 20;
        }
        double baseValue = 0.2;
        double scale = (Math.ceil((spDefense-19)/12.5)-1)*0.05+baseValue;
        return scale;
    }

    public double getSpeedScale(int speed){
        if (speed > 180){
            speed = 180;
        }
        if (speed < 5){
            speed = 5;
        }
        double baseValue = 0.2;
        double scale = (Math.ceil((speed-4)/10.4)-1)*0.05+baseValue;
        return scale;
    }

    public double getHealthScale(int health){
        if (health > 255){
            health = 255;
        }
        if (health < 1){
            health = 1;
        }
        double baseValue = 0.2;
        double scale = (Math.ceil((health)/15.0)-1)*0.05+baseValue;
        return scale;
    }

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    // Main method for data analysis and testing
    public static void main(String[] args) {
        DataProvider p = new DataProvider();

        ArrayList<Integer> attack = new ArrayList<>();
        ArrayList<Integer> defense = new ArrayList<>();
        ArrayList<Integer> spAttack = new ArrayList<>();
        ArrayList<Integer> spDefense = new ArrayList<>();
        ArrayList<Integer> speed = new ArrayList<>();
        ArrayList<Integer> health = new ArrayList<>();

        for (int i = 0; i < p.pokemon.size(); i++) {
            attack.add(p.pokemon.get(i).attack);
            defense.add(p.pokemon.get(i).defense);
            spAttack.add(p.pokemon.get(i).spAttack);
            spDefense.add(p.pokemon.get(i).spDefense);
            speed.add(p.pokemon.get(i).speed);
            health.add(p.pokemon.get(i).health);
        }

        Collections.sort(attack);
        Collections.sort(defense);
        Collections.sort(spAttack);
        Collections.sort(spDefense);
        Collections.sort(speed);
        Collections.sort(health);


    }
}
