import java.awt.image.BufferedImage;

public class Pokemon {

    int attack;
    int defense;
    int spAttack;
    int spDefense;
    int speed;
    int health;

    String name;
    int number;
    int generation;
    boolean legendary;
    Type type1;
    Type type2;
    BufferedImage sprite;

    boolean chosenForTeam = false;

    public Pokemon() {
    }

    public Pokemon(int attack, int defense, int spAttack, int spDefense, int speed, int health, String name, int number, boolean legendary, Type type1, Type type2, BufferedImage sprite) {
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.health = health;
        this.name = name;
        this.number = number;
        this.legendary = legendary;
        this.type1 = type1;
        this.type2 = type2;
        this.sprite = sprite;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(int spAttack) {
        this.spAttack = spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(int spDefense) {
        this.spDefense = spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public boolean isChosenForTeam() {
        return chosenForTeam;
    }

    public void setChosenForTeam(boolean chosenForTeam) {
        this.chosenForTeam = chosenForTeam;
    }
}
