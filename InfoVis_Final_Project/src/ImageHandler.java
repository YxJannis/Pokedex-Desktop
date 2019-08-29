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

    // attribute images
    private BufferedImage attackImg = null;
    private BufferedImage defenseImg = null;
    private BufferedImage spAttackImg = null;
    private BufferedImage spDefenseImg = null;
    private BufferedImage speedImg = null;
    private BufferedImage healthImg = null;

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

            sortHeaderImg = ImageIO.read(new File("src/images/sort-header.png"));
            filterHeaderImg = ImageIO.read(new File("src/images/filter-header.png"));
            pokeBallImg = ImageIO.read(new File("src/images/pokeball-1.png"));
            yourTeamImg = ImageIO.read(new File("src/images/your-team-header.png"));

            testButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            numberSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            attackSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            spAttackSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            defenseSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            spDefenseSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            speedSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            healthSortButtonImg = ImageIO.read(new File("src/images/test-button.png"));

            generationFilterButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            typeFilterButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            isLegendaryFilterButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            resetFilterButtonImg = ImageIO.read(new File("src/images/test-button.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage generateIconComposition(double attack_scale, double defense_scale, double spAttack_scale,
                                                 double spDefense_scale, double speed_scale, double health_scale, Color backgroundColor){
        BufferedImage attack_scaled = scaleImage(getAttackImg(), attack_scale);
        BufferedImage defense_scaled = scaleImage(getDefenseImg(), defense_scale);
        BufferedImage spAttack_scaled = scaleImage(getSpAttackImg(), spAttack_scale);
        BufferedImage spDefense_scaled = scaleImage(getSpDefenseImg(), spDefense_scale);
        BufferedImage speed_scaled = scaleImage(getSpeedImg(), speed_scale);
        BufferedImage health_scaled = scaleImage(getHealthImg(), health_scale);

        BufferedImage finalImage = joinImages(attack_scaled, spAttack_scaled, speed_scaled, defense_scaled,spDefense_scaled, health_scaled, backgroundColor);

        return drawBorder(finalImage);
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


    private static BufferedImage joinImages(BufferedImage img1, BufferedImage img2, BufferedImage img3,
                                           BufferedImage img4, BufferedImage img5, BufferedImage img6, Color backgroundColor){
        int offset = 0;
        int width = imageBaseWidth*3+offset;
        int height = imageBaseHeight*2+offset;

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(backgroundColor);
        g2.fillRect(0,0,width,height);
        g2.setColor(oldColor);

        g2.drawImage(img1,null,(imageBaseWidth-img1.getWidth())/2,
                (imageBaseHeight-img1.getHeight())/2);
        g2.drawImage(img2,null, imageBaseWidth+(imageBaseWidth-img2.getWidth())/2+offset,
                (imageBaseHeight-img2.getHeight())/2);
        g2.drawImage(img3, null, imageBaseWidth*2+(imageBaseWidth-img3.getWidth())/2+offset,
                (imageBaseHeight-img3.getHeight())/2);
        g2.drawImage(img4, null, (imageBaseWidth-img4.getWidth())/2,
                imageBaseHeight + (imageBaseHeight-img4.getHeight())/2+offset );
        g2.drawImage(img5, null, imageBaseWidth+(imageBaseWidth-img5.getWidth())/2+offset,
                imageBaseHeight+(imageBaseHeight-img5.getHeight())/2+offset);
        g2.drawImage(img6, null, imageBaseWidth*2+(imageBaseWidth-img6.getWidth())/2+offset,
                imageBaseHeight+(imageBaseHeight-img6.getHeight())/2+offset);
        g2.dispose();
        return newImage;
    }

    // Drawing rectangle around image
    private BufferedImage drawBorder(BufferedImage image){
        if (image != null) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setStroke(new BasicStroke(2));
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, image.getWidth(), image.getHeight());
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
}

