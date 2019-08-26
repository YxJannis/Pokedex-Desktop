import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GridLayoutTest {

    // base measures for images may be important for scaling as to not mess up the joined images?
    static int imageBaseWidth = 40;
    static int imageBaseHeight = 40;

    public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }

    // join images without using image base stats
    /*
    public static BufferedImage joinBufferedImage(BufferedImage img1,
                                                  BufferedImage img2, boolean paintUnder) {
        int offset = 2;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = 0;
        if (!paintUnder) {
            height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        }
        else{
            height = img1.getHeight() + img2.getHeight() + offset;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        if (!paintUnder){
            g2.drawImage(img1, null, 0, 0);
            g2.drawImage(img2, null, img1.getWidth() + offset, 0);
            g2.dispose();
        }
        else {
            g2.drawImage(img1, null, 0, 0);
            g2.drawImage(img2, null, 0, img1.getHeight()+offset);
            g2.dispose();
        }

        return newImage;
    }
    */

    public static BufferedImage joinAllImages(BufferedImage[] images){
        BufferedImage imageOne = joinBufferedImageBaseStats(images[0],images[1], true, false);
        BufferedImage imageTwo = joinBufferedImageBaseStats(images[2],images[3], true, false);

        BufferedImage finalImage = joinBufferedImageBaseStats(imageOne,imageTwo,false,true);
        return finalImage;
    }

    // join images using image base stats
    public static BufferedImage joinBufferedImageBaseStats(BufferedImage img1,
                                                           BufferedImage img2, boolean atomic, boolean paintUnder) {
        int offset = 0;
        int width = imageBaseWidth*2;
        int height = 0;
        if (!paintUnder) {
            height = imageBaseHeight+offset;
        }
        else{
            height = imageBaseHeight*2+offset;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        if (!paintUnder){
            if (!atomic) {
                g2.drawImage(img1, null, 0, 0);
                g2.drawImage(img2, null, img1.getWidth() + offset, 0);
                g2.dispose();
            }
            else{
                g2.drawImage(img1, null, 0, 0);
                g2.drawImage(img2, null, imageBaseWidth + (imageBaseWidth - img2.getWidth()) + offset, 0);
                g2.dispose();
            }
        }
        else {
            g2.drawImage(img1, null, 0, 0);
            g2.drawImage(img2, null, 0, img1.getHeight()+offset);
            g2.dispose();
        }

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

    public static void main(String[] args) {

        // defining rows and columns of grid layout and filling test data arrays
        int rows = 5;
        int cols = 10;

        /*
        String[]names = new String[rows*rows];
        for (int i = 0; i < rows; i++) {
            names[i] = "Pokemon Nr. " + i;
        }*/

        int [] attackData = new int[rows*cols];
        int [] defenseData = new int[rows*cols];
        int [] healthData = new int[rows*cols];
        int [] sp_attackData = new int[rows*cols];
        Random random = new Random();


        attackData[0] = 40;
        defenseData[0] = 40;
        healthData[0] = 40;
        sp_attackData[0] = 40;

        for (int j = 1; j < rows*cols; j++) {
            attackData[j] = (random.nextInt()%10)+20;
            defenseData[j] = (random.nextInt()%10)+20;
            healthData[j] = (random.nextInt()%10)+20;
            sp_attackData[j] = (random.nextInt()%10)+20;

        }


        // initializing GUI grid layout
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("GridLayout Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, cols));


        // Image handling
        BufferedImage pokePicture = null;
        try {
            pokePicture = ImageIO.read(new File("src/images/Test-Composition-60x60.png"));
        } catch (IOException e) {
            System.out.println("Error while reading Image.");
            e.printStackTrace();
        }


        BufferedImage attack = null;
        BufferedImage defense = null;
        BufferedImage health = null;
        BufferedImage sp_attack = null;
        BufferedImage sp_defense = null;
        BufferedImage speed = null;
        // BufferedImage total_power = null;

        // Testing image composition
        try {
            attack = ImageIO.read(new File("src/images/Sword-40x40.png"));
            defense = ImageIO.read(new File("src/images/Shield-40x40.png"));
            health = ImageIO.read(new File("src/images/Heart-40x40.png"));
            sp_attack = ImageIO.read(new File("src/images/Sword-40x40.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }


        // filling grid layout
        for (int i=0; i<rows*cols; i++){
            if (pokePicture != null) {
                BufferedImage attack_scaled = scale(attack,attackData[i],attackData[i]);
                BufferedImage defense_scaled = scale(defense,defenseData[i],defenseData[i]);
                BufferedImage health_scaled = scale(health,healthData[i],healthData[i]);
                BufferedImage sp_attack_scaled = scale(sp_attack,sp_attackData[i],sp_attackData[i]);
                BufferedImage joinedImg1 = joinBufferedImageBaseStats(attack_scaled,defense_scaled, true,false);
                BufferedImage joinedImg2 = joinBufferedImageBaseStats(health_scaled,sp_attack_scaled, true,false);
                BufferedImage joinedImg = joinBufferedImageBaseStats(joinedImg1, joinedImg2, false,true);
                pokePicture = joinedImg;
                pokePicture = drawBorder(pokePicture);

                JLabel picLabel = new JLabel((new ImageIcon(pokePicture)));
                frame.add(picLabel);
            }
            else
                frame.add(new JTextField("IMAGE \n ERROR"));
            //frame.add(new JTextField(data[i]));
        }

        frame.pack();
        frame.setVisible(true);
    }
}