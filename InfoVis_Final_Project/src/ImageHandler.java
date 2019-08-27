import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    private static int imageBaseWidth = 40;
    private static int imageBaseHeight = 40;

    private BufferedImage attackImg = null;
    private BufferedImage defenseImg = null;
    private BufferedImage spAttackImg = null;
    private BufferedImage spDefenseImg = null;
    private BufferedImage speedImg = null;
    private BufferedImage healthImg = null;

    private BufferedImage sortHeaderImg = null;
    private BufferedImage filterHeaderImg = null;
    private BufferedImage yourTeamImg = null;
    private BufferedImage circleImg = null;
    private BufferedImage testButtonImg = null;

    public ImageHandler(){
        try {
            attackImg = ImageIO.read(new File("src/images/Attack-40x40.png"));
            defenseImg = ImageIO.read(new File("src/images/Defense-40x40.png"));
            spAttackImg = ImageIO.read(new File("src/images/SpAttack-40x40.png"));
            spDefenseImg = ImageIO.read(new File("src/images/SpDefense-40x40.png"));
            speedImg = ImageIO.read(new File("src/images/Speed-40x40.png"));
            healthImg = ImageIO.read(new File("src/images/Health-40x40.png"));
            circleImg = ImageIO.read(new File("src/images/Circle.png"));
            testButtonImg = ImageIO.read(new File("src/images/test-button.png"));
            sortHeaderImg = ImageIO.read(new File("src/images/sort-header.png"));
            filterHeaderImg = ImageIO.read(new File("src/images/filter-header.png"));
            yourTeamImg = ImageIO.read(new File("src/images/your-team-header.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage generateIconComposition(double attack_scale, double defense_scale, double spAttack_scale,
                                                 double spDefense_scale, double speed_scale, double health_scale, Color backgroundColor){
        BufferedImage attack_scaled = scaleImage(attackImg, attack_scale);
        BufferedImage defense_scaled = scaleImage(defenseImg, defense_scale);
        BufferedImage spAttack_scaled = scaleImage(spAttackImg, spAttack_scale);
        BufferedImage spDefense_scaled = scaleImage(spDefenseImg, spDefense_scale);
        BufferedImage speed_scaled = scaleImage(speedImg, speed_scale);
        BufferedImage health_scaled = scaleImage(healthImg, health_scale);

        BufferedImage finalImage = joinImages(attack_scaled, spAttack_scaled, speed_scaled, defense_scaled,spDefense_scaled, health_scaled, backgroundColor);

        return drawBorder(finalImage);
    }


    public BufferedImage scaleImage(BufferedImage imageToScale, double scaleFactor){
        // scale factor can range from 0.1(min) to 1.0 (max)
        // min factor maybe has to be changed
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


    public static int getImageBaseWidth() {
        return imageBaseWidth;
    }

    public static int getImageBaseHeight() {
        return imageBaseHeight;
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

    public BufferedImage getCircleImg() {
        return circleImg;
    }

    public BufferedImage getTestButtonImg() {
        return testButtonImg;
    }


}

