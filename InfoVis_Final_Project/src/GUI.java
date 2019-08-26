import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class GUI {

    public static void main(String[] args) {

        // defining rows and columns of grid layout and filling test data arrays
        int rows = 15;
        int cols = 15;

        double [] attackData = new double[rows*cols];
        double [] defenseData = new double[rows*cols];
        double [] spAttackData = new double[rows*cols];
        double [] spDefenseData = new double[rows*cols];
        double [] speedData = new double[rows*cols];
        double [] healthData = new double[rows*cols];


        attackData[0] = 1;
        defenseData[0] = 1;
        spAttackData[0] = 1;
        spDefenseData[0] = 1;
        speedData[0] = 1;
        healthData[0] = 1;
        attackData[1] = 0.2;
        defenseData[1] = 0.2;
        spAttackData[1] = 0.2;
        spDefenseData[1] = 0.2;
        speedData[1] = 0.2;
        healthData[1] = 0.2;


        for (int j = 2; j < rows*cols; j++) {
            attackData[j] = ThreadLocalRandom.current().nextDouble(0.2, 1.0);
            defenseData[j] = ThreadLocalRandom.current().nextDouble(0.2, 1.0);
            spAttackData[j] = ThreadLocalRandom.current().nextDouble(0.2, 1.0);
            spDefenseData[j] =ThreadLocalRandom.current().nextDouble(0.2, 1.0);
            speedData[j] = ThreadLocalRandom.current().nextDouble(0.2, 1.0);
            healthData[j] = ThreadLocalRandom.current().nextDouble(0.2, 1.0);

        }


        // create grid layout with matrix
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Pokedex Desktop");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));


        // filling grid layout matrix
        for (int i=0; i<rows*cols; i++){
            ImageHandler imgProcessor = new ImageHandler();
            BufferedImage pokePicture = imgProcessor.generateIconComposition(attackData[i],defenseData[i],spAttackData[i],
                    spDefenseData[i],speedData[i],healthData[i]);

            JLabel picLabel = new JLabel((new ImageIcon(pokePicture)));
            panel.add(picLabel);
        }

        JScrollPane scrollpane = new JScrollPane(panel);


        // create sidebar
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(10,1));

        JPanel sortPanel = new JPanel();
        JButton sortButton = new JButton("Sort by Attribute:");
        sortPanel.add(sortButton);
        menuPanel.add(sortPanel);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        JPanel filterPanel = new JPanel();
        JButton filterButton = new JButton("Filter by trait:");
        filterPanel.add(filterButton);
        menuPanel.add(filterPanel);

        JPanel teamPanel = new JPanel();
        teamPanel.add(new JTextField("Hello test"));
        menuPanel.add(teamPanel);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(scrollpane);
        frame.pack();
        frame.setVisible(true);
    }
}