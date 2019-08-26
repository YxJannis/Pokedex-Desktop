import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {

    public static void main(String[] args) {

        // defining rows and columns of grid layout and filling test data arrays
        int rows = 15;
        int cols = 15;

        int [] attackData = new int[rows*cols];
        int [] defenseData = new int[rows*cols];
        int [] spAttackData = new int[rows*cols];
        int [] spDefenseData = new int[rows*cols];
        int [] speedData = new int[rows*cols];
        int [] healthData = new int[rows*cols];
        Random random = new Random();


        attackData[0] = 40;
        defenseData[0] = 40;
        spAttackData[0] = 40;
        spDefenseData[0] = 40;
        speedData[0] = 40;
        healthData[0] = 40;


        for (int j = 1; j < rows*cols; j++) {
            attackData[j] = (random.nextInt()%10)+20;
            defenseData[j] = (random.nextInt()%10)+20;
            spAttackData[j] = (random.nextInt()%10)+20;
            spDefenseData[j] = (random.nextInt()%10)+20;
            speedData[j] = (random.nextInt()%10)+20;
            healthData[j] = (random.nextInt()%10)+20;

        }


        // initializing GUI grid layout
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("GridLayout Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(rows, cols));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));


        // Image handling
        BufferedImage pokePicture = null;
        try {
            pokePicture = ImageIO.read(new File("src/images/Test-Composition-60x60.png"));
        } catch (IOException e) {
            System.out.println("Error while reading Image.");
            e.printStackTrace();
        }

        // filling grid layout
        for (int i=0; i<rows*cols; i++){
            if (pokePicture != null) {
                ImageHandler imgProcessor = new ImageHandler();
                pokePicture = imgProcessor.generateIconComposition(attackData[i],defenseData[i],spAttackData[i],
                        spDefenseData[i],speedData[i],healthData[i]);

                JLabel picLabel = new JLabel((new ImageIcon(pokePicture)));
                panel.add(picLabel);
            }
            else
                frame.add(new JTextField("IMAGE \n ERROR"));
            //frame.add(new JTextField(data[i]));
        }

        JScrollPane scrollpane = new JScrollPane(panel);
        frame.add(scrollpane);

        frame.pack();
        frame.setVisible(true);
    }
}