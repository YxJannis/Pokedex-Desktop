import csv

csv_file = ""

data = {}


def import_csv():
    with open(csv_file, 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            print(row)
    file.close()


def import_csv2():
    with open(csv_file, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            data[row["pokedex_number"]] = [int(row["attack"]), row["defense"], row["hp"], row["name"], row["sp_attack"],
                                           row["sp_defense"], row["speed"], row["type1"], row["type2"],
                                           row["generation"], row["is_legendary"]]
            print(dict(row))
    file.close()


# name,

# attack, defense, hp, name, pokedex_number, sp_attack, sp_defense, speed, type1, type2, generation, is_legendary

# int: attack, defense, hp, pokedex_number, sp_attack, sp_defense, speed, generation
# string: name, type1, type2 (can be empty string)
# bool: is_legendary
