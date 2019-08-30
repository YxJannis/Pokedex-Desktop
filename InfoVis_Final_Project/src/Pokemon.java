import java.awt.image.BufferedImage;

/**
 * Class for the Pokemon object
 *
 * @author Max Pagel, Jannis Leuther
 */
public class Pokemon {

    int attack;
    int defense;
    int spAttack;
    int spDefense;
    int speed;
    int health;

    private double attackScale;
    private double defenseScale;
    private double spAttackScale;
    private double spDefenseScale;
    private double speedScale;
    private double healthScale;

    private String name;
    private int number;
    private int generation;
    private boolean legendary;
    private PokeType type1;
    private PokeType type2;
    private BufferedImage sprite;

    private boolean chosenForTeam = false;

    public Pokemon() {
    }

    public Pokemon(int attack, int defense, int spAttack, int spDefense, int speed, int health, String name, int number, int generation, boolean legendary, PokeType type1, PokeType type2) {
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.health = health;
        this.name = name;
        this.number = number;
        this.generation = generation;
        this.legendary = legendary;
        this.type1 = type1;
        this.type2 = type2;
        this.attackScale = DataProvider.getAttackScale(attack);
        this.spAttackScale = DataProvider.getSpAttackScale(spAttack);
        this.defenseScale = DataProvider.getDefenseScale(defense);
        this.spDefenseScale = DataProvider.getSpDefenseScale(spDefense);
        this.speedScale = DataProvider.getSpeedScale(speed);
        this.healthScale = DataProvider.getHealthScale(health);
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

    public PokeType getType1() {
        return type1;
    }

    public void setType1(PokeType type1) {
        this.type1 = type1;
    }

    public PokeType getType2() {
        return type2;
    }

    public void setType2(PokeType type2) {
        this.type2 = type2;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        if (this.generation == 7){
            this.sprite = new ImageHandler().scaleSprite(sprite,sprite.getWidth());
        }
        else
            this.sprite = sprite;
    }

    public boolean isChosenForTeam() {
        return chosenForTeam;
    }

    public void setChosenForTeam(boolean chosenForTeam) {
        this.chosenForTeam = chosenForTeam;
    }

    public double getAttackScale() {
        return attackScale;
    }

    public double getDefenseScale() {
        return defenseScale;
    }

    public double getSpAttackScale() {
        return spAttackScale;
    }

    public double getSpDefenseScale() {
        return spDefenseScale;
    }

    public double getSpeedScale() {
        return speedScale;
    }

    public double getHealthScale() {
        return healthScale;
    }

    public void setAttackScale(double attackScale) {
        this.attackScale = attackScale;
    }

    public void setDefenseScale(double defenseScale) {
        this.defenseScale = defenseScale;
    }

    public void setSpAttackScale(double spAttackScale) {
        this.spAttackScale = spAttackScale;
    }

    public void setSpDefenseScale(double spDefenseScale) {
        this.spDefenseScale = spDefenseScale;
    }

    public void setSpeedScale(double speedScale) {
        this.speedScale = speedScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }
}
