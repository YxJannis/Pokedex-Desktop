import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class GUI {

    static int circleCount = 0;

    private static void fillGridWithPokemon(DataProvider dataProvider, ImageHandler imgHandler, JPanel panel, JLabel pokeBallOne, JLabel pokeBallTwo, JLabel pokeBallThree, JLabel pokeBallFour, JLabel pokeBallFive,JLabel pokeBallSix, String sortBy){

        dataProvider.sortByAttribute(sortBy);

        for (Pokemon pokEntity: dataProvider.getPokemon()) {
            double attackScale = dataProvider.getAttackScale(pokEntity.getAttack());
            double defenseScale = dataProvider.getDefenseScale(pokEntity.getDefense());
            double spAttackScale = dataProvider.getSpAttackScale(pokEntity.getSpAttack());
            double spDefenseScale = dataProvider.getSpDefenseScale(pokEntity.getSpDefense());
            double speedScale = dataProvider.getSpeedScale(pokEntity.getSpeed());
            double healthScale = dataProvider.getHealthScale(pokEntity.getHealth());
            BufferedImage pokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.WHITE);
            ImageIcon pokeIcon = new ImageIcon(pokePicture);
            JLabel picLabel = new JLabel(pokeIcon);

            JPopupMenu pokeInfoPopup = new JPopupMenu("Pokemon Info");
            pokeInfoPopup.add(new JMenuItem(pokEntity.getName() + " - Nr: " + pokEntity.getNumber()));
            pokeInfoPopup.add(new JMenuItem("Attack:" + pokEntity.getAttack()));
            pokeInfoPopup.add(new JMenuItem("Defense:" + pokEntity.getDefense()));
            pokeInfoPopup.add(new JMenuItem("Special Atk:" + pokEntity.getSpAttack()));
            pokeInfoPopup.add(new JMenuItem("Special Def:" + pokEntity.getSpDefense()));
            pokeInfoPopup.add(new JMenuItem("Speed:" + pokEntity.getSpeed()));
            pokeInfoPopup.add(new JMenuItem("Health:" + pokEntity.getHealth()));


            // Team Management
            picLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pokeInfoPopup.show(picLabel,e.getX()+5,e.getY()+5);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!pokEntity.isChosenForTeam()) {
                        if (circleCount <=5) {
                            BufferedImage coloredPokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.ORANGE);
                            picLabel.setIcon(new ImageIcon(coloredPokePicture));
                            pokEntity.setChosenForTeam(true);

                            switch(circleCount){
                                case 0:
                                    pokeBallOne.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallOne set");
                                    break;
                                case 1:
                                    pokeBallTwo.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallTwo set");
                                    break;
                                case 2:
                                    pokeBallThree.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallThree set");
                                    break;
                                case 3:
                                    pokeBallFour.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallFour set");
                                    break;
                                case 4:
                                    pokeBallFive.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    break;
                                case 5:
                                    pokeBallSix.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    break;
                            }
                            circleCount++;
                        }
                    }

                    else if (pokEntity.isChosenForTeam()){
                        BufferedImage coloredPokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.WHITE);
                        picLabel.setIcon(new ImageIcon(coloredPokePicture));
                        pokEntity.setChosenForTeam(false);
                        circleCount--;

                        switch(circleCount){
                            case 0:
                                pokeBallOne.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                System.out.println("pokeBallOne unset");
                                break;
                            case 1:
                                pokeBallTwo.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                System.out.println("pokeBallTwo unset");
                                break;
                            case 2:
                                pokeBallThree.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                break;
                            case 3:
                                pokeBallFour.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                break;
                            case 4:
                                pokeBallFive.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                break;
                            case 5:
                                pokeBallSix.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                System.out.println("pokeBallSix unset");
                                break;
                        }
                    }
                }
            });

            panel.add(picLabel);
            JScrollPane scrollpane = new JScrollPane(panel);
        }
    }

    public static void main(String[] args) {


        DataProvider dataProvider = new DataProvider();

        // defining rows and columns of grid layout
        int rows = 28;
        int cols = 28;

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

        BufferedImage pokeBallImg = imgHandler.getPokeBallImg();
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

        // Add pokeballs for team selection on sidebar (team size = 6)
        JLabel pokeBallOne = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        JLabel pokeBallTwo = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        JLabel pokeBallThree = new JLabel(new ImageIcon(pokeBallImg));
        JLabel pokeBallFour = new JLabel(new ImageIcon(pokeBallImg));
        JLabel pokeBallFive = new JLabel(new ImageIcon(pokeBallImg));
        JLabel pokeBallSix = new JLabel(new ImageIcon(pokeBallImg));

        teamPanel.add(pokeBallOne);
        teamPanel.add(pokeBallTwo);
        teamPanel.add(pokeBallThree);
        teamPanel.add(pokeBallFour);
        teamPanel.add(pokeBallFive);
        teamPanel.add(pokeBallSix);

        menuPanel.add(teamPanel);

        fillGridWithPokemon(dataProvider,imgHandler,panel,pokeBallOne,pokeBallTwo,pokeBallThree,pokeBallFour,pokeBallFive,pokeBallSix, "number");

        sortByAttackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillGridWithPokemon(dataProvider,imgHandler,panel,pokeBallOne,pokeBallTwo,pokeBallThree,pokeBallFour,pokeBallFive,pokeBallSix, "attack");
            }
        });


        /*
        // Fill images
        for (Pokemon pokEntity: dataProvider.getPokemon()) {
            double attackScale = dataProvider.getAttackScale(pokEntity.getAttack());
            double defenseScale = dataProvider.getDefenseScale(pokEntity.getDefense());
            double spAttackScale = dataProvider.getSpAttackScale(pokEntity.getSpAttack());
            double spDefenseScale = dataProvider.getSpDefenseScale(pokEntity.getSpDefense());
            double speedScale = dataProvider.getSpeedScale(pokEntity.getSpeed());
            double healthScale = dataProvider.getHealthScale(pokEntity.getHealth());
            BufferedImage pokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.WHITE);
            ImageIcon pokeIcon = new ImageIcon(pokePicture);
            JLabel picLabel = new JLabel(pokeIcon);

            JPopupMenu pokeInfoPopup = new JPopupMenu("Pokemon Info");
            pokeInfoPopup.add(new JMenuItem(pokEntity.getName() + " - Nr: " + pokEntity.getNumber()));
            pokeInfoPopup.add(new JMenuItem("Attack:" + pokEntity.getAttack()));
            pokeInfoPopup.add(new JMenuItem("Defense:" + pokEntity.getDefense()));
            pokeInfoPopup.add(new JMenuItem("Special Atk:" + pokEntity.getSpAttack()));
            pokeInfoPopup.add(new JMenuItem("Special Def:" + pokEntity.getSpDefense()));
            pokeInfoPopup.add(new JMenuItem("Speed:" + pokEntity.getSpeed()));
            pokeInfoPopup.add(new JMenuItem("Health:" + pokEntity.getHealth()));


            // Team Management
            picLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pokeInfoPopup.show(picLabel,e.getX()+5,e.getY()+5);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!pokEntity.isChosenForTeam()) {
                        if (circleCount <=5) {
                            BufferedImage coloredPokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.ORANGE);
                            picLabel.setIcon(new ImageIcon(coloredPokePicture));
                            pokEntity.setChosenForTeam(true);

                            switch(circleCount){
                                case 0:
                                    pokeBallOne.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallOne set");
                                    break;
                                case 1:
                                    pokeBallTwo.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallTwo set");
                                    break;
                                case 2:
                                    pokeBallThree.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallThree set");
                                    break;
                                case 3:
                                    pokeBallFour.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    System.out.println("pokeBallFour set");
                                    break;
                                case 4:
                                    pokeBallFive.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    break;
                                case 5:
                                    pokeBallSix.setIcon(new ImageIcon(imgHandler.paintCircleWithSprite(pokEntity)));
                                    break;
                            }
                            circleCount++;
                        }
                    }

                    else if (pokEntity.isChosenForTeam()){
                        BufferedImage coloredPokePicture = imgHandler.generateIconComposition(attackScale, defenseScale, spAttackScale, spDefenseScale, speedScale, healthScale, Color.WHITE);
                        picLabel.setIcon(new ImageIcon(coloredPokePicture));
                        pokEntity.setChosenForTeam(false);
                        circleCount--;

                        switch(circleCount){
                            case 0:
                                pokeBallOne.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                System.out.println("pokeBallOne unset");
                                break;
                            case 1:
                                pokeBallTwo.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                System.out.println("pokeBallTwo unset");
                                break;
                            case 2:
                                pokeBallThree.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                break;
                            case 3:
                                pokeBallFour.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                break;
                            case 4:
                                pokeBallFive.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                break;
                            case 5:
                                pokeBallSix.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                                System.out.println("pokeBallSix unset");
                                break;
                        }
                    }
                }
            });

            panel.add(picLabel);
        }
*/
        JScrollPane scrollpane = new JScrollPane(panel);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(scrollpane);
        frame.pack();
        frame.setVisible(true);
    }
}