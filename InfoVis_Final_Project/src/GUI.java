import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class GUI {

    public static void main(String[] args) {

        DataProvider dataProvider = new DataProvider();

        // defining rows and columns of grid layout and filling test data arrays
        int rows = 28;
        int cols = 28;

        double [] attackData = new double[rows*cols];
        double [] defenseData = new double[rows*cols];
        double [] spAttackData = new double[rows*cols];
        double [] spDefenseData = new double[rows*cols];
        double [] speedData = new double[rows*cols];
        double [] healthData = new double[rows*cols];

        for (int i = 0; i < 17; i++) {
            attackData[i] = 0.2 + i*0.05;
            defenseData[i] = 0.2 + i*0.05;
            spAttackData[i] = 0.2 + i*0.05;
            spDefenseData[i] = 0.2 + i*0.05;
            speedData[i] = 0.2 + i*0.05;
            healthData[i] = 0.2 + i*0.05;
        }


        for (int j = 17; j < rows*cols; j++) {
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
        JLabel circleOne = new JLabel(new ImageIcon(circleImg));
        JLabel circleTwo = new JLabel(new ImageIcon(circleImg));
        JLabel circleThree = new JLabel(new ImageIcon(circleImg));
        JLabel circleFour = new JLabel(new ImageIcon(circleImg));
        JLabel circleFive = new JLabel(new ImageIcon(circleImg));
        JLabel circleSix = new JLabel(new ImageIcon(circleImg));

        teamPanel.add(circleOne);
        teamPanel.add(circleTwo);
        teamPanel.add(circleThree);
        teamPanel.add(circleFour);
        teamPanel.add(circleFive);
        teamPanel.add(circleSix);

        menuPanel.add(teamPanel);

        for (Pokemon pokEntitiy: dataProvider.getPokemon()) {
            double attackScale = dataProvider.getAttackScale(pokEntitiy.getAttack());
            double defenseScale = dataProvider.getDefenseScale(pokEntitiy.getDefense());
            double spAttackScale = dataProvider.getSpAttackScale(pokEntitiy.getSpAttack());
            double spDefenseScale = dataProvider.getSpDefenseScale(pokEntitiy.getSpDefense());
            double speedScale = dataProvider.getSpeedScale(pokEntitiy.getSpeed());
            double healthScale = dataProvider.getHealthScale(pokEntitiy.getHealth());
            BufferedImage pokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.WHITE);
            ImageIcon pokeIcon = new ImageIcon(pokePicture);
            JLabel picLabel = new JLabel(pokeIcon);

            JPopupMenu pokeInfoPopup = new JPopupMenu("Pokemon Info");
            pokeInfoPopup.add(new JMenuItem(pokEntitiy.getName() + ", Nr: " + pokEntitiy.getNumber()));
            pokeInfoPopup.add(new JMenuItem("Attack: " + pokEntitiy.getAttack()));
            pokeInfoPopup.add(new JMenuItem("Defense: " + pokEntitiy.getDefense()));
            pokeInfoPopup.add(new JMenuItem("Special Atk: " + pokEntitiy.getSpAttack()));
            pokeInfoPopup.add(new JMenuItem("Special Def: " + pokEntitiy.getSpDefense()));
            pokeInfoPopup.add(new JMenuItem("Speed: " + pokEntitiy.getSpeed()));
            pokeInfoPopup.add(new JMenuItem("Health: " + pokEntitiy.getHealth()));


            picLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                            pokeInfoPopup.show(picLabel,e.getX(),e.getY());
                        }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!pokEntitiy.isChosenForTeaM()) {
                        BufferedImage coloredPokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.ORANGE);
                        picLabel.setIcon(new ImageIcon(coloredPokePicture));
                        pokEntitiy.setChosenForTeaM(true);
                        
                    }
                    else{
                        BufferedImage coloredPokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.WHITE);
                        picLabel.setIcon(new ImageIcon(coloredPokePicture));
                        pokEntitiy.setChosenForTeaM(false);
                    }
                }
            });



            panel.add(picLabel);
        }

        /*
        // filling grid layout matrix with dummy images using test arrays
        for (int i=0; i<rows*cols; i++){
            BufferedImage pokePicture = imgHandler.generateIconComposition(attackData[i],defenseData[i],spAttackData[i],
                    spDefenseData[i],speedData[i],healthData[i]);

            JLabel picLabel = new JLabel((new ImageIcon(pokePicture)));
            panel.add(picLabel);
        }*/

        JScrollPane scrollpane = new JScrollPane(panel);



        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(scrollpane);
        frame.pack();
        frame.setVisible(true);
    }
}