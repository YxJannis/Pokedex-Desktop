import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    static int imageBaseWidth = 40;
    static int imageBaseHeight = 40;

    BufferedImage attack = null;
    BufferedImage defense = null;
    BufferedImage spAttack = null;
    BufferedImage spDefense = null;
    BufferedImage speed = null;
    BufferedImage health = null;

    public ImageHandler(){
        try {
            attack = ImageIO.read(new File("src/images/Attack-40x40.png"));
            defense = ImageIO.read(new File("src/images/Defense-40x40.png"));
            spAttack = ImageIO.read(new File("src/images/SpAttack-40x40.png"));
            spDefense = ImageIO.read(new File("src/images/SpDefense-40x40.png"));
            speed = ImageIO.read(new File("src/images/Speed-40x40.png"));
            health = ImageIO.read(new File("src/images/Health-40x40.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage generateIconComposition(double attack_scale, double defense_scale, double spAttack_scale,
                                                 double spDefense_scale, double speed_scale, double health_scale){
        BufferedImage attack_scaled = scaleImage(attack, attack_scale);
        BufferedImage defense_scaled = scaleImage(defense, defense_scale);
        BufferedImage spAttack_scaled = scaleImage(spAttack, spAttack_scale);
        BufferedImage spDefense_scaled = scaleImage(spDefense, spDefense_scale);
        BufferedImage speed_scaled = scaleImage(speed, speed_scale);
        BufferedImage health_scaled = scaleImage(health, health_scale);

        BufferedImage finalImage = joinImages(attack_scaled, spAttack_scaled, speed_scaled, defense_scaled,spDefense_scaled, health_scaled);

        return drawBorder(finalImage);
    }

    public static BufferedImage scaleImage2(BufferedImage imageToScale, int scaleFactor) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(scaleFactor, scaleFactor, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, scaleFactor, scaleFactor, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }

    public static BufferedImage scaleImage(BufferedImage imageToScale, double scaleFactor){
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

    public static BufferedImage joinImages(BufferedImage img1, BufferedImage img2, BufferedImage img3,
                                           BufferedImage img4, BufferedImage img5, BufferedImage img6){
        int offset = 0;
        int width = imageBaseWidth*3+offset;
        int height = imageBaseHeight*2+offset;

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.WHITE);
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
    public static BufferedImage drawBorder(BufferedImage image){
        if (image != null) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setStroke(new BasicStroke(2));
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, image.getWidth(), image.getHeight());
        }
        return image;
    }
}

