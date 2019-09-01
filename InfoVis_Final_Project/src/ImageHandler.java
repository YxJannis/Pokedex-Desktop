import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * handles and provides image resources for usage in GUI
 *
 * @author Jannis Leuther, Max Pagel
 */

public class ImageHandler {

    private static final int imageBaseWidth = 30;
    private static final int imageBaseHeight = 45;

    // attribute images uncolored
    private BufferedImage attackImg = null;
    private BufferedImage defenseImg = null;
    private BufferedImage spAttackImg = null;
    private BufferedImage spDefenseImg = null;
    private BufferedImage speedImg = null;
    private BufferedImage healthImg = null;

    // attribute images colored
    private BufferedImage attackImgColored = null;
    private BufferedImage defenseImgColored = null;
    private BufferedImage spAttackImgColored = null;
    private BufferedImage spDefenseImgColored = null;
    private BufferedImage speedImgColored = null;
    private BufferedImage healthImgColored = null;

    // menu images
    private BufferedImage sortHeaderImg = null;
    private BufferedImage filterHeaderImg = null;
    private BufferedImage yourTeamImg = null;
    private BufferedImage pokeBallImg = null;

    // sort button images
    private BufferedImage testButtonImg = null;
    private BufferedImage numberSortButtonImg = null;
    private BufferedImage attackSortButtonImg = null;
    private BufferedImage spAttackSortButtonImg = null;
    private BufferedImage defenseSortButtonImg = null;
    private BufferedImage spDefenseSortButtonImg = null;
    private BufferedImage speedSortButtonImg = null;
    private BufferedImage healthSortButtonImg = null;

    // filter button images
    private BufferedImage generationFilterButtonImg = null;
    private BufferedImage typeFilterButtonImg = null;
    private BufferedImage isLegendaryFilterButtonImg = null;
    private BufferedImage resetFilterButtonImg = null;



    public ImageHandler(){
        try {
            attackImg = ImageIO.read(new File("src/images/attack.png"));
            defenseImg = ImageIO.read(new File("src/images/defense.png"));
            spAttackImg = ImageIO.read(new File("src/images/spAttack.png"));
            spDefenseImg = ImageIO.read(new File("src/images/spDefense.png"));
            speedImg = ImageIO.read(new File("src/images/speed.png"));
            healthImg = ImageIO.read(new File("src/images/health.png"));

            attackImgColored = ImageIO.read(new File("src/images/attack_colored.png"));
            defenseImgColored = ImageIO.read(new File("src/images/defense.png"));
            spAttackImgColored = ImageIO.read(new File("src/images/spAttack.png"));
            spDefenseImgColored = ImageIO.read(new File("src/images/spDefense.png"));
            speedImgColored = ImageIO.read(new File("src/images/speed_colored.png"));
            healthImgColored = ImageIO.read(new File("src/images/health.png"));


            sortHeaderImg = ImageIO.read(new File("src/images/sort-header.png"));
            filterHeaderImg = ImageIO.read(new File("src/images/filter-header.png"));
            pokeBallImg = ImageIO.read(new File("src/images/pokeball-1.png"));
            yourTeamImg = ImageIO.read(new File("src/images/your-team-header.png"));

            testButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            numberSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonNumber.png"));
            attackSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonAttack.png"));
            spAttackSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonSpAtk.png"));
            defenseSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonDefense.png"));
            spDefenseSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonSpDef.png"));
            speedSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonSpeed.png"));
            healthSortButtonImg = ImageIO.read(new File("src/images/buttons/buttonHealth.png"));

            generationFilterButtonImg = ImageIO.read(new File("src/images/buttons/buttonGeneration.png"));
            typeFilterButtonImg = ImageIO.read(new File("src/images/buttons/buttonType.png"));
            isLegendaryFilterButtonImg = ImageIO.read(new File("src/images/buttons/buttonLegendary.png"));
            resetFilterButtonImg = ImageIO.read(new File("src/images/buttons/buttonResetFilter.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage generateIcon (Pokemon pokEntity, String attributeToColor){
        double attack_scale = DataProvider.getAttackScale(pokEntity.getAttack());
        double defense_scale = DataProvider.getDefenseScale(pokEntity.getDefense());
        double spAttack_scale = DataProvider.getSpAttackScale(pokEntity.getSpAttack());
        double spDefense_scale = DataProvider.getSpDefenseScale(pokEntity.getSpDefense());
        double speed_scale = DataProvider.getSpeedScale(pokEntity.getSpeed());
        double health_scale = DataProvider.getHealthScale(pokEntity.getHealth());
        Color backgroundColor = pokEntity.getPokeColor();
        return generateIconComposition(attack_scale, defense_scale, spAttack_scale, spDefense_scale, speed_scale, health_scale, backgroundColor, attributeToColor);
    }

    public BufferedImage generateIcon (Pokemon pokEntity){
        double attack_scale = DataProvider.getAttackScale(pokEntity.getAttack());
        double defense_scale = DataProvider.getDefenseScale(pokEntity.getDefense());
        double spAttack_scale = DataProvider.getSpAttackScale(pokEntity.getSpAttack());
        double spDefense_scale = DataProvider.getSpDefenseScale(pokEntity.getSpDefense());
        double speed_scale = DataProvider.getSpeedScale(pokEntity.getSpeed());
        double health_scale = DataProvider.getHealthScale(pokEntity.getHealth());
        Color backgroundColor = pokEntity.getPokeColor();
        return generateIconComposition(attack_scale, defense_scale, spAttack_scale, spDefense_scale, speed_scale, health_scale, backgroundColor);
    }


    private BufferedImage generateIconComposition(double attack_scale, double defense_scale, double spAttack_scale,
                                                                      double spDefense_scale, double speed_scale, double health_scale, Color backgroundColor, String attributeToColor){
        BufferedImage attack_scaled = scaleImage(getAttackImg(), attack_scale);
        BufferedImage defense_scaled = scaleImage(getDefenseImg(), defense_scale);
        BufferedImage spAttack_scaled = scaleImage(getSpAttackImg(), spAttack_scale);
        BufferedImage spDefense_scaled = scaleImage(getSpDefenseImg(), spDefense_scale);
        BufferedImage speed_scaled = scaleImage(getSpeedImg(), speed_scale);
        BufferedImage health_scaled = scaleImage(getHealthImg(), health_scale);

        switch(attributeToColor){
            case "attack":
                attack_scaled = scaleImage(getAttackImgColored(),attack_scale);
                break;
            case "defense":
                defense_scaled = scaleImage(getDefenseImgColored(), defense_scale);
                break;
            case "spAttack":
                spAttack_scaled = scaleImage(getSpAttackImgColored(), spAttack_scale);
                break;
            case "spDefense":
                spDefense_scaled = scaleImage(getSpDefenseImgColored(), spDefense_scale);
                break;
            case "speed":
                speed_scaled = scaleImage(getSpeedImgColored(), speed_scale);
                break;
            case "health":
                health_scaled = scaleImage(getHealthImgColored(), health_scale);
                break;
            default:
                break;
        }

        BufferedImage finalImage = joinImages(attack_scaled, spAttack_scaled, speed_scaled, defense_scaled,spDefense_scaled, health_scaled, backgroundColor);

        return drawBorder(finalImage, Color.BLACK);
    }

    private BufferedImage generateIconComposition(double attack_scale, double defense_scale, double spAttack_scale,
                                                 double spDefense_scale, double speed_scale, double health_scale, Color backgroundColor){
        BufferedImage attack_scaled = scaleImage(getAttackImg(), attack_scale);
        BufferedImage defense_scaled = scaleImage(getDefenseImg(), defense_scale);
        BufferedImage spAttack_scaled = scaleImage(getSpAttackImg(), spAttack_scale);
        BufferedImage spDefense_scaled = scaleImage(getSpDefenseImg(), spDefense_scale);
        BufferedImage speed_scaled = scaleImage(getSpeedImg(), speed_scale);
        BufferedImage health_scaled = scaleImage(getHealthImg(), health_scale);

        BufferedImage finalImage = joinImages(attack_scaled, spAttack_scaled, speed_scaled, defense_scaled,spDefense_scaled, health_scaled, backgroundColor);

        return drawBorder(finalImage, Color.BLACK);
    }


    public BufferedImage scaleImage(BufferedImage imageToScale, double scaleFactor){
        // scale factor can range from 0.2(min) to 1.0 (max)
        BufferedImage scaledImage = null;

        if (imageToScale != null){
            scaledImage = new BufferedImage((int)Math.round(imageBaseWidth*scaleFactor),
                    (int)Math.round(imageBaseHeight*scaleFactor), imageToScale.getType());
            Graphics2D g2 = scaledImage.createGraphics();
            g2.drawImage(imageToScale,0,0, (int)Math.round(imageBaseWidth*scaleFactor),
                    (int)Math.round(imageBaseHeight*scaleFactor),null);
            g2.dispose();
        }
        return scaledImage;
    }

    public BufferedImage scaleSprite(BufferedImage spriteToScale, int initialDim){
        BufferedImage scaledSprite = null;

        double scaleFactor = 96.0/initialDim;

        if (spriteToScale != null){
            scaledSprite = new BufferedImage(96,96, spriteToScale.getType());
            Graphics2D g2 = scaledSprite.createGraphics();
            g2.drawImage(spriteToScale,0,0, (int)Math.round(initialDim*scaleFactor),
                    (int)Math.round(initialDim*scaleFactor),null);
            g2.dispose();
        }

        return scaledSprite;
    }


    private static BufferedImage joinImages(BufferedImage img1, BufferedImage img2, BufferedImage img3,
                                           BufferedImage img4, BufferedImage img5, BufferedImage img6, Color backgroundColor){
        int offset = 0;
        int width = imageBaseWidth*3+offset;
        int height = imageBaseHeight*2+offset;

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        Color oldColor = g2d.getColor();
        g2d.setPaint(backgroundColor);
        g2d.fillRect(0,0,width,height);
        g2d.setColor(oldColor);

        g2d.drawImage(img1,null,(imageBaseWidth-img1.getWidth())/2,
                (imageBaseHeight-img1.getHeight())/2);
        g2d.drawImage(img2,null, imageBaseWidth+(imageBaseWidth-img2.getWidth())/2+offset,
                (imageBaseHeight-img2.getHeight())/2);
        g2d.drawImage(img3, null, imageBaseWidth*2+(imageBaseWidth-img3.getWidth())/2+offset,
                (imageBaseHeight-img3.getHeight())/2);
        g2d.drawImage(img4, null, (imageBaseWidth-img4.getWidth())/2,
                imageBaseHeight + (imageBaseHeight-img4.getHeight())/2+offset );
        g2d.drawImage(img5, null, imageBaseWidth+(imageBaseWidth-img5.getWidth())/2+offset,
                imageBaseHeight+(imageBaseHeight-img5.getHeight())/2+offset);
        g2d.drawImage(img6, null, imageBaseWidth*2+(imageBaseWidth-img6.getWidth())/2+offset,
                imageBaseHeight+(imageBaseHeight-img6.getHeight())/2+offset);
        g2d.dispose();
        return newImage;
    }


    public static BufferedImage drawColorRectangle(Pokemon pokEntity) {
        BufferedImage image = pokEntity.getSprite();
        final Color rectColor = pokEntity.getPokeColor();

        BufferedImage newImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        Color oldColor = g2d.getColor();
        g2d.setPaint(new Color(0f,0f,0f,0f));
        g2d.fillRect(0,0,100,100);
        g2d.setColor(oldColor);

        g2d.drawImage(image,null,0,0);
        g2d.setColor(rectColor);
        g2d.fillRect(80,5,16,16);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(80,5,16,16);
        return newImage;
    }

    // Drawing rectangle around image
    public static BufferedImage drawBorder(BufferedImage image, Color borderColor){
        if (image != null && borderColor != null) {
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(borderColor);
            g2d.drawRect(0, 0, image.getWidth(), image.getHeight());
            g2d.dispose();
        }
        return image;
    }


    public BufferedImage getAttackImg() {
        return attackImg;
    }

    public BufferedImage getDefenseImg() {
        return defenseImg;
    }

    public BufferedImage getSpAttackImg() {
        return spAttackImg;
    }

    public BufferedImage getSpDefenseImg() {
        return spDefenseImg;
    }

    public BufferedImage getSpeedImg() {
        return speedImg;
    }

    public BufferedImage getHealthImg() {
        return healthImg;
    }

    public BufferedImage getSortHeaderImg() {
        return sortHeaderImg;
    }

    public BufferedImage getFilterHeaderImg() {
        return filterHeaderImg;
    }

    public BufferedImage getYourTeamImg() {
        return yourTeamImg;
    }

    public BufferedImage getPokeBallImg() {
        return pokeBallImg;
    }

    public BufferedImage getNumberSortButtonImg() {
        return numberSortButtonImg;
    }

    public BufferedImage getAttackSortButtonImg() {
        return attackSortButtonImg;
    }

    public BufferedImage getSpAttackSortButtonImg() {
        return spAttackSortButtonImg;
    }

    public BufferedImage getDefenseSortButtonImg() {
        return defenseSortButtonImg;
    }

    public BufferedImage getSpDefenseSortButtonImg() {
        return spDefenseSortButtonImg;
    }

    public BufferedImage getSpeedSortButtonImg() {
        return speedSortButtonImg;
    }

    public BufferedImage getHealthSortButtonImg() {
        return healthSortButtonImg;
    }

    public BufferedImage getGenerationFilterButtonImg() {
        return generationFilterButtonImg;
    }

    public BufferedImage getTypeFilterButtonImg() {
        return typeFilterButtonImg;
    }

    public BufferedImage getIsLegendaryFilterButtonImg() {
        return isLegendaryFilterButtonImg;
    }

    public BufferedImage getResetFilterButtonImg() {
        return resetFilterButtonImg;
    }

    public BufferedImage getAttackImgColored() {
        return attackImgColored;
    }

    public BufferedImage getDefenseImgColored() {
        return defenseImgColored;
    }

    public BufferedImage getSpAttackImgColored() {
        return spAttackImgColored;
    }

    public BufferedImage getSpDefenseImgColored() {
        return spDefenseImgColored;
    }

    public BufferedImage getSpeedImgColored() {
        return speedImgColored;
    }

    public BufferedImage getHealthImgColored() {
        return healthImgColored;
    }
}

