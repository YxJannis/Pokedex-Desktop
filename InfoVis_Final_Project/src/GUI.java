import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Initializes and handles the user interface
 *
 * @author Jannis Leuther, Max Pagel
 */
public class GUI extends JFrame{

    private int dimensionFactor = 1;
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

    private GridLayout pokeGridLayout;

    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel sortPanel;
    private JPanel filterPanel;

    private JPopupMenu pokeInfoPopup;

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

    private JMenuItem legendaryTrue;
    private JMenuItem legendaryFalse;

    private JButton resetFilterBtn;

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
        rows = (int)Math.floor((1.0/dimensionFactor)*Math.sqrt(dataProvider.getPokemon().size()));
        cols = (int)Math.ceil(dimensionFactor*Math.sqrt(dataProvider.getPokemon().size()));

        picLabelMap = new LinkedHashMap<>();

        new JFrame("Pokedex Desktop Edition");
        setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize panels
        mainPanel = new JPanel();

        pokeGridLayout = new GridLayout(rows,cols,5,5);
        mainPanel.setLayout(pokeGridLayout);

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
        fillDataGrid();

        JScrollPane scrollpane = new JScrollPane(mainPanel);
        add(menuPanel, BorderLayout.WEST);
        add(scrollpane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width,screenSize.height);
        //pack();
        setVisible(true);
    }

    private void updateGridLayout(){
        if (dataProvider.getPokemon().size() > 0) {
            rows = (int) Math.floor((1.0/dimensionFactor)*Math.sqrt(dataProvider.getPokemon().size()));
            cols = (int) Math.ceil(dimensionFactor*Math.sqrt(dataProvider.getPokemon().size()));
        }

        pokeGridLayout.setRows(rows);
        pokeGridLayout.setColumns(cols);
    }

    private void initButtons(){
        sortByNumberBtn = new JButton(new ImageIcon(imgHandler.getNumberSortButtonImg()));
        sortByAttackBtn = new JButton(new ImageIcon(imgHandler.getAttackSortButtonImg()));
        sortByDefenseBtn = new JButton(new ImageIcon(imgHandler.getDefenseSortButtonImg()));
        sortBySpAttackBtn = new JButton(new ImageIcon(imgHandler.getSpAttackSortButtonImg()));
        sortBySpDefenseBtn = new JButton(new ImageIcon(imgHandler.getSpDefenseSortButtonImg()));
        sortBySpeedBtn = new JButton(new ImageIcon(imgHandler.getSpeedSortButtonImg()));
        sortByHealthBtn = new JButton(new ImageIcon(imgHandler.getHealthSortButtonImg()));
        resetFilterBtn = new JButton(new ImageIcon(imgHandler.getResetFilterButtonImg()));

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

        resetFilterBtn.setBorder(BorderFactory.createEmptyBorder());
        resetFilterBtn.setContentAreaFilled(false);
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

        // initialize filter JMenuItems
        initFilterMenuItems();

        // Create JPopupMenus for each filter type
        JPopupMenu generationMenu = new JPopupMenu("Generation");
        JPopupMenu typeMenu = new JPopupMenu("PokeType");
        JPopupMenu legendaryMenu = new JPopupMenu("Legendary");

        // Filter by generation
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

        // Filter by type
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

        // filter by legendary status
        legendaryMenu.add(legendaryTrue);
        legendaryMenu.add(legendaryFalse);

        JLabel legendaryFilter = new JLabel(new ImageIcon(imgHandler.getIsLegendaryFilterButtonImg()));
        filterPanel.add(legendaryFilter);
        legendaryFilter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                legendaryMenu.show(filterPanel,e.getX(),e.getY());
            }
        });

        handleFilterBtns();

        // add resetFilter button
        filterPanel.add(resetFilterBtn);

        // add everything to the menu panel
        menuPanel.add(filterPanel);
    }

    private void initTeamPanel(){
        JPanel teamPanel = new JPanel();
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
    
    private void addMouseListenersToPokeBall(JLabel pokeBall){
        pokeBall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(teamMap.get(pokeBall)==dummy)){
                    pokeBall.setIcon(new ImageIcon(imgHandler.getPokeBallImg()));
                    Pokemon pokEntity = teamMap.get(pokeBall);
                    teamMap.get(pokeBall).setChosenForTeam(false);
                    teamMap.put(pokeBall, dummy);
                    pokEntity.setPokeColor(Color.WHITE);
                    BufferedImage coloredPokePicture = imgHandler.generateIcon(pokEntity);
                    picLabelMap.get(pokEntity).setIcon(new ImageIcon(coloredPokePicture));
                    circleCount--;
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (!(teamMap.get(pokeBall)==dummy)){
                    Pokemon pokEntity = teamMap.get(pokeBall);
                    fillPokeInfoPopup(pokEntity);
                    pokeInfoPopup.show(pokeBall, pokeBall.getWidth(), 0);
                }
            }
        });
    }

    private void fillDataGrid(){

        mainPanel.removeAll();
        updateGridLayout();

        for (Pokemon pokEntity: dataProvider.getPokemon()) {

            BufferedImage pokePicture = imgHandler.generateIcon(pokEntity);


            ImageIcon pokeIcon = new ImageIcon(pokePicture);
            JLabel picLabel = new JLabel(pokeIcon);

            mainPanel.add(picLabel);
            picLabelMap.put(pokEntity, picLabel);

            fillPokeInfoPopup(pokEntity);
            pokemonActions(pokEntity, picLabel, pokeInfoPopup);

        }

        if (initialFill) {
            initialFill = false;
            descending = true;
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void fillPokeInfoPopup(Pokemon pokEntity){

        JMenuItem attackMenuItem = new JMenuItem("Attack:" + pokEntity.getAttack());
        JMenuItem defenseMenuItem = new JMenuItem("Defense:" + pokEntity.getDefense());
        JMenuItem spAttackMenuItem = new JMenuItem("Special Atk:" + pokEntity.getSpAttack());
        JMenuItem spDefenseMenuItem = new JMenuItem("Special Def:" + pokEntity.getSpDefense());
        JMenuItem speedMenuItem = new JMenuItem("Speed:" + pokEntity.getSpeed());
        JMenuItem healthMenuItem = new JMenuItem("Health:" + pokEntity.getHealth());

        String typeString = pokEntity.getType1().toString().substring(0,1).toUpperCase() + pokEntity.getType1().toString().substring(1).toLowerCase();
        if (pokEntity.getType2() != null){
            typeString = typeString + ", " + pokEntity.getType2().toString().substring(0,1).toUpperCase() + pokEntity.getType2().toString().substring(1).toLowerCase();
        }
        JMenuItem typesMenuItem = new JMenuItem("Types: " + typeString);

        String titleString = pokEntity.getName() + " - Nr: " + pokEntity.getNumber();

        if (pokEntity.isLegendary()){
            titleString = pokEntity.getName() + " " + (char) 9733 + " - Nr: " + pokEntity.getNumber();
        }

        pokeInfoPopup = new JPopupMenu("Pokemon Info");
        pokeInfoPopup.add(new JMenuItem(titleString));
        pokeInfoPopup.add(attackMenuItem);
        pokeInfoPopup.add(defenseMenuItem);
        pokeInfoPopup.add(spAttackMenuItem);
        pokeInfoPopup.add(spDefenseMenuItem);
        pokeInfoPopup.add(speedMenuItem);
        pokeInfoPopup.add(healthMenuItem);
        pokeInfoPopup.add(typesMenuItem);


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
                        if (teamMap.get(pokeBallOne)==dummy) {
                            pokEntity.setPokeColor(Color.ORANGE);
                        }
                        else if (teamMap.get(pokeBallTwo)==dummy){
                            pokEntity.setPokeColor(new Color(52, 152, 219));
                        }
                        else if (teamMap.get(pokeBallThree)==dummy){
                            pokEntity.setPokeColor(new Color(231, 76, 60));
                        }
                        else if (teamMap.get(pokeBallFour)==dummy){
                            pokEntity.setPokeColor(new Color(46, 204, 113));
                        }
                        else if (teamMap.get(pokeBallFive)==dummy){
                            pokEntity.setPokeColor(new Color(200, 0, 255));
                        }
                        else if (teamMap.get(pokeBallSix)==dummy){
                            pokEntity.setPokeColor(Color.YELLOW);
                        }

                        BufferedImage coloredPokePicture = imgHandler.generateIcon(pokEntity);
                        picLabel.setIcon(new ImageIcon(coloredPokePicture));

                        pokEntity.setChosenForTeam(true);

                        for (Map.Entry<JLabel, Pokemon> entry: teamMap.entrySet()) {
                            if (entry.getValue()==dummy){
                                teamMap.put(entry.getKey(), pokEntity);
                                entry.getKey().setIcon(new ImageIcon(ImageHandler.drawColorRectangle(pokEntity)));
                                break;
                            }
                        }
                        circleCount++;
                    }
                }

                else if (pokEntity.isChosenForTeam()){
                    pokEntity.setPokeColor(Color.WHITE);
                    BufferedImage coloredPokePicture = imgHandler.generateIcon(pokEntity);
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
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;

        });
        sortByAttackBtn.addActionListener(e -> {
            sortBy="attack";
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;

        });
        sortByDefenseBtn.addActionListener(e -> {
            sortBy="defense";
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;
        });
        sortBySpAttackBtn.addActionListener(e -> {
            sortBy="spAttack";
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;
        });
        sortBySpDefenseBtn.addActionListener(e -> {
            sortBy="spDefense";
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;
        });
        sortBySpeedBtn.addActionListener(e -> {
            sortBy="speed";
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;
        });
        sortByHealthBtn.addActionListener(e -> {
            sortBy="health";
            dataProvider.sortByAttribute(sortBy, descending);
            fillDataGrid();
            descending = !descending;
        });
    }

    private void handleGenerationFilters(JMenuItem menuItem, int generation){
        menuItem.addActionListener(e -> {
            dataProvider.filterByGeneration(generation);
            fillDataGrid();
        });
    }

    private void handleTypeFilters(JMenuItem menuItem, PokeType type){
        menuItem.addActionListener(e -> {
            dataProvider.filterByType(type);
            fillDataGrid();
        });
    }

    private void handleIsLegendaryFilters(JMenuItem menuItem, boolean isLegendary){
        menuItem.addActionListener(e -> {
            dataProvider.filterByIsLegendary(isLegendary);
            fillDataGrid();
        });
    }

    private void handleFilterBtns(){

        List<JMenuItem> generationMenuItems = new ArrayList<>(Arrays.asList(genOne, genTwo, genThree, genFour,
                genFive, genSix, genSeven));

        for (int i = 0; i < generationMenuItems.size(); i++) {
            handleGenerationFilters(generationMenuItems.get(i),i+1);
        }

        List<JMenuItem> typeMenuItems = new ArrayList<>(Arrays.asList(typeWater,typeNormal,typeFlying,typeGrass,
                typePsychic,typeBug,typeFire,typePoison,typeGround,typeRock,typeFighting,typeDark, typeSteel,
                typeElectric, typeDragon, typeFairy, typeGhost, typeIce));

        List<PokeType> typeMenuPokeTypes = new ArrayList<>(Arrays.asList(PokeType.WATER, PokeType.NORMAL, PokeType.FLYING,
                PokeType.GRASS, PokeType.PSYCHIC, PokeType.BUG, PokeType.FIRE, PokeType.POISON, PokeType.GROUND, PokeType.ROCK,
                PokeType.FIGHTING, PokeType.DARK, PokeType.STEEL, PokeType.ELECTRIC, PokeType.DRAGON, PokeType.FAIRY,
                PokeType.GHOST, PokeType.ICE));

        for (int i = 0; i < typeMenuItems.size(); i++) {
            handleTypeFilters(typeMenuItems.get(i),typeMenuPokeTypes.get(i));
        }

        handleIsLegendaryFilters(legendaryTrue, true);
        handleIsLegendaryFilters(legendaryFalse, false);

        resetFilterBtn.addActionListener(e -> {
            dataProvider.resetFilter();
            fillDataGrid();
        });

    }

    public static void main(String[] args) {
        new GUI();
    }
}