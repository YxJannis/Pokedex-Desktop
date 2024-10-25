# Pokedex Desktop

This is an interactive visualization of the dataset of all 801 Pokémon from Generations 1 to 7. Discover the strongest or weakest Pokémon based on 6 attributes: Attack, Special Attack, Defence, Special Defence, Speed and Health.

Analyse, compare and discover all Pokémon interactively in this easy-to-use visualization.

## Requirements

To run the `jar` file or compile the source code, you must have java installed.

## Usage

The `.jar` can be found in the latest release or in `src/Pokedex-Desktop.jar`. On Linux, run it by calling `java -jar Pokedex-Desktop.jar`.

To generate the `.jar`, run the following command in the `src` directory:

```bash
jar cfm Pokedex-Desktop.jar Manifest.txt *.class
```

You can also compile the source code and run it this way:

```bash
cd src
javac *.class
java GUI
```

## Details

The Pokedex Desktop is programmed exclusively in Java using Swing as Gui library.

## Disclaimer

The data in `pokemon.csv` as well as all sprites for pokemon are not our creation.
