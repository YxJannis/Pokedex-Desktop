import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;

public class GUI extends JFrame{

    private int rows;
    private int cols;
    private int circleCount;
    private boolean initialFill = true;

    private boolean descending = false;
    private String sortBy;

    private LinkedHashMap<JLabel, Pokemon> teamMap;
    private Pokemon dummy;

    private DataProvider dataProvider;
    private ImageHandler imgHandler;

    private LinkedHashMap<Pokemon, JLabel> picLabelMap;

    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel sortPanel;
    private JPanel filterPanel;
    private JPanel teamPanel;

    private JScrollPane scrollpane;

    private JButton sortByNumberBtn;
    private JButton sortByAttackBtn;
    private JButton sortByDefenseBtn;
    private JButton sortBySpAttackBtn;
    private JButton sortBySpDefenseBtn;
    private JButton sortBySpeedBtn;
    private JButton sortByHealthBtn;

    private JMenuItem genOne;
    private JMenuItem genTwo;
    private JMenuItem genThree;
    private JMenuItem genFour;
    private JMenuItem genFive;
    private JMenuItem genSix;
    private JMenuItem genSeven;

    private JMenuItem typeWater;
    private JMenuItem typeNormal;
    private JMenuItem typeFlying;
    private JMenuItem typeGrass;
    private JMenuItem typePsychic;
    private JMenuItem typeBug;
    private JMenuItem typeFire;
    private JMenuItem typePoison;
    private JMenuItem typeGround;
    private JMenuItem typeRock;
    private JMenuItem typeFighting;
    private JMenuItem typeDark;
    private JMenuItem typeSteel;
    private JMenuItem typeElectric;
    private JMenuItem typeDragon;
    private JMenuItem typeFairy;
    private JMenuItem typeGhost;
    private JMenuItem typeIce;
    //...

    private JMenuItem legendaryTrue;
    private JMenuItem legendaryFalse;

    private JLabel pokeBallOne;
    private JLabel pokeBallTwo;
    private JLabel pokeBallThree;
    private JLabel pokeBallFour;
    private JLabel pokeBallFive;
    private JLabel pokeBallSix;

    public GUI(){
        init();
    }

    private void init(){
        dataProvider = new DataProvider();
        imgHandler = new ImageHandler();

        sortBy = "number";

        circleCount = 0;
        rows = 28;
        cols = 28;

        picLabelMap = new LinkedHashMap<>();

        new JFrame("Pokedex Desktop Edition");
        setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize panels
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(rows, cols, 5,5));

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));

        sortPanel = new JPanel();
        sortPanel.setLayout(new BoxLayout(sortPanel,BoxLayout.Y_AXIS));


        initButtons();
        initSortPanel();
        initFilterPanel();
        initTeamPanel();

        dummy = new Pokemon();
        dummy.setName("DummyPokemonEntity");

        teamMap = new LinkedHashMap<>();
        teamMap.put(pokeBallOne,dummy);
        teamMap.put(pokeBallTwo,dummy);
        teamMap.put(pokeBallThree,dummy);
        teamMap.put(pokeBallFour,dummy);
        teamMap.put(pokeBallFive,dummy);
        teamMap.put(pokeBallSix,dummy);

        for (Map.Entry<JLabel, Pokemon> entry: teamMap.entrySet()) {
            addMouseListenersToPokeBall(entry.getKey());
        }

        handleSortAttributeBtns();
        fillDataGrid(sortBy);

        scrollpane = new JScrollPane(mainPanel);
        add(menuPanel, BorderLayout.WEST);
        add(scrollpane);
        pack();
        setVisible(true);

    }

    // initializes Buttons
    private void initButtons(){
        sortByNumberBtn = new JButton(new ImageIcon(imgHandler.getNumberSortButtonImg()));
        sortByAttackBtn = new JButton(new ImageIcon(imgHandler.getAttackSortButtonImg()));
        sortByDefenseBtn = new JButton(new ImageIcon(imgHandler.getDefenseSortButtonImg()));
        sortBySpAttackBtn = new JButton(new ImageIcon(imgHandler.getSpAttackSortButtonImg()));
        sortBySpDefenseBtn = new JButton(new ImageIcon(imgHandler.getSpDefenseSortButtonImg()));
        sortBySpeedBtn = new JButton(new ImageIcon(imgHandler.getSpeedSortButtonImg()));
        sortByHealthBtn = new JButton(new ImageIcon(imgHandler.getHealthSortButtonImg()));

        sortByNumberBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByNumberBtn.setContentAreaFilled(false);
        sortByAttackBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByAttackBtn.setContentAreaFilled(false);
        sortByDefenseBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByDefenseBtn.setContentAreaFilled(false);
        sortBySpAttackBtn.setBorder(BorderFactory.createEmptyBorder());
        sortBySpAttackBtn.setContentAreaFilled(false);
        sortBySpDefenseBtn.setBorder(BorderFactory.createEmptyBorder());
        sortBySpDefenseBtn.setContentAreaFilled(false);
        sortBySpeedBtn.setBorder(BorderFactory.createEmptyBorder());
        sortBySpeedBtn.setContentAreaFilled(false);
        sortByHealthBtn.setBorder(BorderFactory.createEmptyBorder());
        sortByHealthBtn.setContentAreaFilled(false);
    }

    private void initSortPanel(){
        JLabel sortText = new JLabel(new ImageIcon(imgHandler.getSortHeaderImg()));
        sortPanel.add(sortText);

        sortPanel.add(Box.createRigidArea(new Dimension(0,10)));
        sortPanel.add(sortByNumberBtn);
        sortPanel.add(sortByAttackBtn);
        sortPanel.add(sortByDefenseBtn);
        sortPanel.add(sortBySpAttackBtn);
        sortPanel.add(sortBySpDefenseBtn);
        sortPanel.add(sortBySpeedBtn);
        sortPanel.add(sortByHealthBtn);

        menuPanel.add(sortPanel);
    }

    private void initFilterMenuItems(){
        genOne = new JMenuItem("Generation 1");
        genTwo = new JMenuItem("Generation 2");
        genThree = new JMenuItem("Generation 3");
        genFour = new JMenuItem("Generation 4");
        genFive = new JMenuItem("Generation 5");
        genSix = new JMenuItem("Generation 6");
        genSeven = new JMenuItem("Generation 7");

        typeWater = new JMenuItem("Water");
        typeNormal = new JMenuItem("Normal");
        typeFlying = new JMenuItem("Flying");
        typeGrass  = new JMenuItem("Grass");
        typePsychic = new JMenuItem("Psychic");
        typeBug = new JMenuItem("Bug");
        typeFire = new JMenuItem("Fire");
        typePoison = new JMenuItem("Poison");
        typeGround = new JMenuItem("Ground");
        typeRock = new JMenuItem("Rock");
        typeFighting = new JMenuItem("Fighting");
        typeDark = new JMenuItem("Dark");
        typeSteel = new JMenuItem("Steel");
        typeElectric = new JMenuItem("Electric");
        typeDragon = new JMenuItem("Dragon");
        typeFairy = new JMenuItem("Fairy");
        typeGhost = new JMenuItem("Ghost");
        typeIce = new JMenuItem("Ice");

        legendaryTrue = new JMenuItem("Legendary");
        legendaryFalse = new JMenuItem("Not Legendary");
    }

    private void initFilterPanel(){
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel,BoxLayout.Y_AXIS));
        filterPanel.add(new JLabel((new ImageIcon(imgHandler.getFilterHeaderImg()))));

        // Filter by generation
        JPopupMenu generationMenu = new JPopupMenu("Generation");
        JPopupMenu typeMenu = new JPopupMenu("Type");
        JPopupMenu legendaryMenu = new JPopupMenu("Legendary");

        initFilterMenuItems();

        generationMenu.add(genOne);
        generationMenu.add(genTwo);
        generationMenu.add(genThree);
        generationMenu.add(genFour);
        generationMenu.add(genFive);
        generationMenu.add(genSix);
        generationMenu.add(genSeven);

        JLabel generationFilter = new JLabel(new ImageIcon(imgHandler.getGenerationFilterButtonImg()));
        filterPanel.add(generationFilter);
        generationFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                generationMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        typeMenu.add(typeWater);
        typeMenu.add(typeNormal);
        typeMenu.add(typeFlying);
        typeMenu.add(typeGrass);
        typeMenu.add(typePsychic);
        typeMenu.add(typeBug);
        typeMenu.add(typeFire);
        typeMenu.add(typePoison);
        typeMenu.add(typeGround);
        typeMenu.add(typeRock);
        typeMenu.add(typeFighting);
        typeMenu.add(typeDark);
        typeMenu.add(typeSteel);
        typeMenu.add(typeElectric);
        typeMenu.add(typeDragon);
        typeMenu.add(typeFairy);
        typeMenu.add(typeGhost);
        typeMenu.add(typeIce);

        JLabel typeFilter = new JLabel(new ImageIcon(imgHandler.getTypeFilterButtonImg()));
        filterPanel.add(typeFilter);
        typeFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                typeMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        legendaryMenu.add(legendaryTrue);
        legendaryMenu.add(legendaryFalse);

        JLabel legendaryFilter = new JLabel(new ImageIcon(imgHandler.getIsLegendaryFilterButtonImg()));
        filterPanel.add(legendaryFilter);
        legendaryFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                legendaryMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        menuPanel.add(filterPanel);
    }

    private void initTeamPanel(){
        teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel,BoxLayout.Y_AXIS));
        teamPanel.add(new JLabel((new ImageIcon(imgHandler.getYourTeamImg()))));

        // Add pokeballs for team selection on sidebar (team size = 6)
        pokeBallOne = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        pokeBallTwo = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        pokeBallThree = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        pokeBallFour = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        pokeBallFive = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));
        pokeBallSix = new JLabel(new ImageIcon(imgHandler.getPokeBallImg()));


        teamPanel.add(pokeBallOne);
        teamPanel.add(pokeBallTwo);
        teamPanel.add(pokeBallThree);
        teamPanel.add(pokeBallFour);
        teamPanel.add(pokeBallFive);
        teamPanel.add(pokeBallSix);

        menuPanel.add(teamPanel);
    }
    
    public void addMouseListenersToPokeBall(JLabel pokeBall){
        pokeBall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(teamMap.get(pokeBall)==dummy)){
                    pokeBall.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                    Pokemon pokEntity = teamMap.get(pokeBall);
                    teamMap.get(pokeBall).setChosenForTeam(false);
                    teamMap.put(pokeBall, dummy);
                    BufferedImage coloredPokePicture = imgHandler.generateIconComposition(pokEntity.getAttackScale(), pokEntity.getDefenseScale(), pokEntity.getSpAttackScale(), pokEntity.getSpDefenseScale(),
                            pokEntity.getSpeedScale(), pokEntity.getHealthScale(), Color.WHITE);
                    picLabelMap.get(pokEntity).setIcon(new ImageIcon(coloredPokePicture));
                    circleCount--;
                }
            }
        });
    }

    private void fillDataGrid(String attribute){

        dataProvider.sortByAttribute(attribute, descending);
        System.out.println("Filling grid");

        mainPanel.removeAll();

        for (Pokemon pokEntity: dataProvider.getPokemon()) {
            BufferedImage pokePicture = imgHandler.generateIconComposition(pokEntity.getAttackScale(), pokEntity.getDefenseScale(), pokEntity.getSpAttackScale(), pokEntity.getSpDefenseScale(),
                    pokEntity.getSpeedScale(), pokEntity.getHealthScale(), Color.WHITE);
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

            pokemonActions(pokEntity, picLabel, pokeInfoPopup);

            mainPanel.add(picLabel);
            picLabelMap.put(pokEntity, picLabel);
        }

        if (initialFill) {
            initialFill = false;
            descending = true;
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void pokemonActions(Pokemon pokEntity, JLabel picLabel, JPopupMenu pokeInfoPopup){
        picLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pokeInfoPopup.show(picLabel,e.getX()+5,e.getY()+5);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!pokEntity.isChosenForTeam()) {
                    if (circleCount <=5) {
                        BufferedImage coloredPokePicture = imgHandler.generateIconComposition(pokEntity.getAttackScale(), pokEntity.getDefenseScale(), pokEntity.getSpAttackScale(), pokEntity.getSpDefenseScale(),
                                pokEntity.getSpeedScale(), pokEntity.getHealthScale(), Color.ORANGE);
                        picLabel.setIcon(new ImageIcon(coloredPokePicture));
                        pokEntity.setChosenForTeam(true);

                        for (Map.Entry<JLabel, Pokemon> entry: teamMap.entrySet()) {
                            if (entry.getValue()==dummy){
                                teamMap.put(entry.getKey(), pokEntity);
                                entry.getKey().setIcon(new ImageIcon(pokEntity.getSprite()));
                                break;
                            }
                        }

                        circleCount++;
                    }
                }

                else if (pokEntity.isChosenForTeam()){
                    BufferedImage coloredPokePicture = imgHandler.generateIconComposition(pokEntity.getAttackScale(), pokEntity.getDefenseScale(), pokEntity.getSpAttackScale(), pokEntity.getSpDefenseScale(),
                            pokEntity.getSpeedScale(), pokEntity.getHealthScale(), Color.WHITE);
                    picLabel.setIcon(new ImageIcon(coloredPokePicture));
                    pokEntity.setChosenForTeam(false);
                    circleCount--;

                    for (Map.Entry<JLabel, Pokemon> entry: teamMap.entrySet()) {
                        if (entry.getValue()==pokEntity){
                            teamMap.put(entry.getKey(), dummy);
                            entry.getKey().setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                            break;
                        }
                    }

                }
            }
        });


    }

    private void handleSortAttributeBtns(){
        sortByNumberBtn.addActionListener(e -> {
            sortBy = "number";
            fillDataGrid(sortBy);
            descending = !descending;

        });
        sortByAttackBtn.addActionListener(e -> {
            sortBy="attack";
            fillDataGrid(sortBy);
            descending = !descending;

        });
        sortByDefenseBtn.addActionListener(e -> {
            sortBy="defense";
            fillDataGrid(sortBy);
            descending = !descending;
        });
        sortBySpAttackBtn.addActionListener(e -> {
            sortBy="spAttack";
            fillDataGrid(sortBy);
            descending = !descending;
        });
        sortBySpDefenseBtn.addActionListener(e -> {
            sortBy="spDefense";
            fillDataGrid(sortBy);
            descending = !descending;
        });
        sortBySpeedBtn.addActionListener(e -> {
            sortBy="speed";
            fillDataGrid(sortBy);
            descending = !descending;
        });
        sortByHealthBtn.addActionListener(e -> {
            sortBy="health";
            fillDataGrid(sortBy);
            descending = !descending;
        });
    }

    public static void main(String[] args) {
        new GUI();
    }
}