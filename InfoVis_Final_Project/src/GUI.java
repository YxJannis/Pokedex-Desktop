import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        int rows = 20;
        int cols = 20;

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
        panel.setLayout(new GridLayout(rows, cols,5,5));


        // create ImageHandler and retrieve images
        ImageHandler imgHandler = new ImageHandler();
        BufferedImage attackImg = imgHandler.getAttackImg();
        BufferedImage defenseImg = imgHandler.getDefenseImg();
        BufferedImage spAttackImg = imgHandler.getSpAttackImg();
        BufferedImage spDefenseImg = imgHandler.getSpDefenseImg();
        BufferedImage speedImg = imgHandler.getSpeedImg();
        BufferedImage healthImg = imgHandler.getHealthImg();

        BufferedImage sortHeaderImg = imgHandler.getSortHeaderImg();
        BufferedImage filterHeaderImg = imgHandler.getFilterHeaderImg();
        BufferedImage yourTeamImg = imgHandler.getYourTeamImg();

        BufferedImage circleImg = imgHandler.getCircleImg();
        BufferedImage testButtonImg = imgHandler.getTestButtonImg();


        // filling grid layout matrix with images according to scaling
        for (int i=0; i<rows*cols; i++){
            BufferedImage pokePicture = imgHandler.generateIconComposition(attackData[i],defenseData[i],spAttackData[i],
                    spDefenseData[i],speedData[i],healthData[i]);

            JLabel picLabel = new JLabel((new ImageIcon(pokePicture)));
            panel.add(picLabel);
        }

        JScrollPane scrollpane = new JScrollPane(panel);


        // create sidebar
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));

        // add sorting elements to sidebar
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new BoxLayout(sortPanel,BoxLayout.Y_AXIS));
        JButton sortByAttackBtn = new JButton(new ImageIcon(testButtonImg));
        sortByAttackBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByAttackBtn.setContentAreaFilled(false);
        JButton sortByDefenseBtn = new JButton(new ImageIcon(testButtonImg));
        sortByDefenseBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByDefenseBtn.setContentAreaFilled(false);
        JButton sortBySpAttackBtn = new JButton(new ImageIcon(testButtonImg));
        sortBySpAttackBtn.setBorder(BorderFactory.createEmptyBorder());
        sortBySpAttackBtn.setContentAreaFilled(false);
        JButton sortBySpDefenseBtn = new JButton(new ImageIcon(testButtonImg));
        sortBySpDefenseBtn.setBorder(BorderFactory.createEmptyBorder());
        sortBySpDefenseBtn.setContentAreaFilled(false);
        JButton sortBySpeedBtn = new JButton(new ImageIcon(testButtonImg));
        sortBySpeedBtn.setBorder(BorderFactory.createEmptyBorder());
        sortBySpeedBtn.setContentAreaFilled(false);
        JButton sortByHealthBtn = new JButton(new ImageIcon(testButtonImg));
        sortByHealthBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByHealthBtn.setContentAreaFilled(false);


        JLabel sortText = new JLabel(new ImageIcon(sortHeaderImg));
        sortPanel.add(sortText);

        sortPanel.add(Box.createRigidArea(new Dimension(0,10)));
        sortPanel.add(sortByAttackBtn);
        sortPanel.add(sortByDefenseBtn);
        sortPanel.add(sortBySpAttackBtn);
        sortPanel.add(sortBySpDefenseBtn);
        sortPanel.add(sortBySpeedBtn);
        sortPanel.add(sortByHealthBtn);

        menuPanel.add(sortPanel);
        //menuPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        //
        // Filtering:
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel,BoxLayout.Y_AXIS));
        filterPanel.add(new JLabel((new ImageIcon(filterHeaderImg))));

        // Filter by generation
        JPopupMenu generationMenu = new JPopupMenu("Generation");
        JMenuItem genOne = new JMenuItem("Generation 1");
        JMenuItem genTwo = new JMenuItem("Generation 2");
        JMenuItem genThree = new JMenuItem("Generation 3");
        JMenuItem genFour = new JMenuItem("Generation 4");
        JMenuItem genFive = new JMenuItem("Generation 5");
        JMenuItem genSix = new JMenuItem("Generation 6");
        JMenuItem genSeven = new JMenuItem("Generation 7");

        generationMenu.add(genOne);
        generationMenu.add(genTwo);
        generationMenu.add(genThree);
        generationMenu.add(genFour);
        generationMenu.add(genFive);
        generationMenu.add(genSix);
        generationMenu.add(genSeven);

        JLabel generationFilter = new JLabel(new ImageIcon(testButtonImg));
        filterPanel.add(generationFilter);
        generationFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                generationMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        // Filter by Type
        JPopupMenu typeMenu = new JPopupMenu("Type");
        JMenuItem typeFire = new JMenuItem("Fire");
        JMenuItem typeWater = new JMenuItem("Water");
        JMenuItem typeGrass = new JMenuItem("Grass");
        //TODO: ...

        typeMenu.add(typeFire);
        typeMenu.add(typeWater);
        typeMenu.add(typeGrass);

        JLabel typeFilter = new JLabel(new ImageIcon(testButtonImg));
        filterPanel.add(typeFilter);
        typeFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                typeMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        // Filter by is_legendary
        JPopupMenu legendaryMenu = new JPopupMenu("Legendary");
        JMenuItem legendaryTrue = new JMenuItem("Legendary");
        JMenuItem legendaryFalse = new JMenuItem("Not Legendary");

        legendaryMenu.add(legendaryTrue);
        legendaryMenu.add(legendaryFalse);

        JLabel legendaryFilter = new JLabel(new ImageIcon(testButtonImg));
        filterPanel.add(legendaryFilter);
        legendaryFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                legendaryMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        menuPanel.add(filterPanel);


        // Add team selection elements to sidebar
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel,BoxLayout.Y_AXIS));
        teamPanel.add(new JLabel((new ImageIcon(yourTeamImg))));
        // Add circles for team selection on sidebar (team size = 6)
        for (int i = 0; i < 6; i++) {
           teamPanel.add(new JLabel(new ImageIcon(circleImg)));
        }

        menuPanel.add(teamPanel);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(scrollpane);
        frame.pack();
        frame.setVisible(true);
    }
}