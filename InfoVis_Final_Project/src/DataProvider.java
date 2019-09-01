import java.util.ArrayList;
import java.util.Collections;

/**
 * handles and provides data for usage in GUI
 *
 * @author Jannis Leuther, Max Pagel
 */

public class DataProvider {

    private ArrayList<Pokemon> pokemon;
    private final ArrayList<Pokemon> allPokemon;


    public DataProvider(){
        DataReader dataReader = new DataReader();
        this.pokemon = dataReader.getPokemon();
        this.allPokemon = this.pokemon;
    }

    public void sortByAttribute(String attribute, boolean descending){
        switch(attribute){
            case "number":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getNumber() == o2.getNumber()){
                        return 0;
                    }
                    if (descending)
                        return o1.getNumber() > o2.getNumber() ? -1 : 1;
                    else
                        return o1.getNumber() < o2.getNumber() ? -1 : 1;
                });
                break;
            case "attack":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getAttack() == o2.getAttack()){
                        return 0;
                    }
                    if (descending)
                        return o1.getAttack() > o2.getAttack() ? -1 : 1;
                    else
                        return o1.getAttack() < o2.getAttack() ? -1 : 1;
                });

                break;
            case "defense":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getDefense() == o2.getDefense()){
                        return 0;
                    }
                    if (descending)
                        return o1.getDefense() > o2.getDefense() ? -1 : 1;
                    else
                        return o1.getDefense() < o2.getDefense() ? -1 : 1;
                });
                break;
            case "spAttack":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getSpAttack() == o2.getSpAttack()){
                        return 0;
                    }
                    if (descending)
                        return o1.getSpAttack() > o2.getSpAttack() ? -1 : 1;
                    else
                        return o1.getSpAttack() < o2.getSpAttack() ? -1 : 1;
                });
                break;
            case "spDefense":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getSpDefense() == o2.getSpDefense()){
                        return 0;
                    }
                    if (descending)
                        return o1.getSpDefense() > o2.getSpDefense() ? -1 : 1;
                    else
                        return o1.getSpDefense() < o2.getSpDefense() ? -1 : 1;
                });
                break;
            case "speed":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getSpeed() == o2.getSpeed()){
                        return 0;
                    }
                    if (descending)
                        return o1.getSpeed() > o2.getSpeed() ? -1 : 1;
                    else
                        return o1.getSpeed() < o2.getSpeed() ? -1 : 1;
                });
                break;
            case "health":
                Collections.sort(pokemon, (o1, o2) -> {
                    if (o1.getHealth() == o2.getHealth()){
                        return 0;
                    }
                    if (descending)
                        return o1.getHealth() > o2.getHealth() ? -1 : 1;
                    else
                        return o1.getHealth() < o2.getHealth() ? -1 : 1;
                });
        }
    }

    public void filterByGeneration(int generation){
        ArrayList<Pokemon> filteredList = new ArrayList<>();
        for (Pokemon pokemon: pokemon) {
            if (pokemon.getGeneration()==generation){
                filteredList.add(pokemon);
            }
        }
        this.pokemon = filteredList;
    }

    public void filterByType(PokeType type){
        ArrayList<Pokemon> filteredList = new ArrayList<>();
        for (Pokemon pokemon: pokemon) {
            if (pokemon.getType1()==type || pokemon.getType2()==type){
                filteredList.add(pokemon);
            }
        }
        this.pokemon = filteredList;
    }

    public void filterByIsLegendary(boolean isLegendary){
        ArrayList<Pokemon> filteredList = new ArrayList<>();
        for (Pokemon pokemon: pokemon) {
            if (pokemon.isLegendary() == isLegendary){
                filteredList.add(pokemon);
            }
        }
        this.pokemon = filteredList;
    }

    public void resetFilter(){
        this.pokemon = allPokemon;
    }

    public static double getAttackScale(int attack){
        if (attack > 185){
            attack = 185;
        }
        if (attack < 5){
            attack = 5;
        }
        double baseValue = 0.2;
        return (Math.ceil((attack-4)/10.7)-1)*0.05+baseValue;
    }

    public static double getDefenseScale(int defense){
        if (defense > 230){
            defense = 230;
        }
        if (defense < 5){
            defense = 5;
        }
        double baseValue = 0.2;
        return (Math.ceil((defense-4)/13.3)-1)*0.05+baseValue;
    }

    public static double getSpAttackScale(int spAttack){
        if (spAttack > 194){
            spAttack = 194;
        }
        if (spAttack < 10){
            spAttack = 10;
        }
        double baseValue = 0.2;
        return (Math.ceil((spAttack-9)/10.9)-1)*0.05+baseValue;
    }

    public static double getSpDefenseScale(int spDefense){
        if (spDefense > 230){
            spDefense = 230;
        }
        if (spDefense < 20){
            spDefense = 20;
        }
        double baseValue = 0.2;
        return (Math.ceil((spDefense-19)/12.5)-1)*0.05+baseValue;
    }

    public static double getSpeedScale(int speed){
        if (speed > 180){
            speed = 180;
        }
        if (speed < 5){
            speed = 5;
        }
        double baseValue = 0.2;
        return (Math.ceil((speed-4)/10.4)-1)*0.05+baseValue;
    }

    public static double getHealthScale(int health){
        if (health > 255){
            health = 255;
        }
        if (health < 1){
            health = 1;
        }
        double baseValue = 0.2;
        return (Math.ceil((health)/15.0)-1)*0.05+baseValue;
    }

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    public void diagonalTraversal(int rows, int cols) {

        //workaround for gui having not enough cols
        if(rows*cols < pokemon.size()){
            cols++;
        }

        //matrix to save pokemon at right place for ordering
        Pokemon[][] matrix = new Pokemon[rows][cols];

        ArrayList<Pokemon> oldOrder = new ArrayList<>();
        for (Pokemon p : pokemon) {
            oldOrder.add(p);
        }

        //add dummy pokemon for empty matrix positions
        while(oldOrder.size() < rows*cols){
            oldOrder.add(new Pokemon());
        }

        int maxSum = rows + cols - 2;

        //save pokemon to ordered pos in matrix
        for (int sum = 0; sum <= maxSum; sum++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i + j - sum == 0) {
                        if(!oldOrder.isEmpty()){
                            matrix[i][j] = oldOrder.get(0);
                            oldOrder.remove(0);
                        }
                    }
                }
            }
        }

        ArrayList<Pokemon> newOrder = new ArrayList<>();

        //copy non dummy pokemon from matrix to new arrayList
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j].getNumber() != -1){
                    newOrder.add(matrix[i][j]);
                }
            }
        }

        pokemon = newOrder;
    }

    // Main method for data analysis and testing
    /*
    public static void main(String[] args) {
        DataProvider p = new DataProvider();

        ArrayList<Integer> attack = new ArrayList<>();
        ArrayList<Integer> defense = new ArrayList<>();
        ArrayList<Integer> spAttack = new ArrayList<>();
        ArrayList<Integer> spDefense = new ArrayList<>();
        ArrayList<Integer> speed = new ArrayList<>();
        ArrayList<Integer> health = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        HashSet<String> typeSet = new HashSet<>();

        for (int i = 0; i < p.pokemon.size(); i++) {
            attack.add(p.pokemon.get(i).attack);
            defense.add(p.pokemon.get(i).defense);
            spAttack.add(p.pokemon.get(i).spAttack);
            spDefense.add(p.pokemon.get(i).spDefense);
            speed.add(p.pokemon.get(i).speed);
            health.add(p.pokemon.get(i).health);
            typeSet.add(p.pokemon.get(i).getType1().toString());
            if (!(p.pokemon.get(i).getType2() == null)) {
                typeSet.add(p.pokemon.get(i).getType2().toString());
            }
        }

        System.out.println(typeSet);


    }*/
}
