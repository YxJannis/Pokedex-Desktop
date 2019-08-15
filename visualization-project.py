import csv
from operator import itemgetter

# Easy acces to list indices of data_list for all attributes
# pokedex_number,name,generation,type1,type2,attack,sp_attack,defense,sp_defense,hp,speed,total_power,is_legendary
list_index = {"pokedex_number": 0, "name": 1, "generation": 2, "type1": 3, "type2": 4, "attack": 5, "sp_attack": 6,
              "defense": 7, "sp_defense": 8, "hp": 9, "speed": 10, "total_power": 11, "is_legendary": 12}

csv_file = "pokemon.csv"

# contain main data
data = {}
data_list = []


def import_csv():
    with open(csv_file, 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            print(row)
    file.close()


def import_csv2(file):
    with open(file, 'r') as f:
        reader = csv.DictReader(f)
        for row in reader:
            data[row["pokedex_number"]] = [row["name"], int(row["generation"]), row["type1"], row["type2"],
                                           int(row["attack"]), int(row["sp_attack"]), int(row["defense"]),
                                           int(row["sp_defense"]), int(row["hp"]), int(row["speed"]),
                                           int(row["attack"]) + int(row["sp_attack"]) + int(row["defense"]) +
                                           int(row["sp_defense"]) + int(row["hp"]) + int(row["speed"]),
                                           bool(int(row["is_legendary"]))]

            data_list.append([row["pokedex_number"], row["name"], int(row["generation"]), row["type1"], row["type2"],
                             int(row["attack"]), int(row["sp_attack"]), int(row["defense"]), int(row["sp_defense"]),
                             int(row["hp"]), int(row["speed"]), int(row["attack"]) + int(row["sp_attack"]) +
                             int(row["defense"]) + int(row["sp_defense"]) + int(row["hp"]) + int(row["speed"]),
                             bool(int(row["is_legendary"]))])
    f.close()


# sorts the data_list for given attribute from lowest to highest
def sort_to_min(attribute):
    sorted_list = data_list.copy()
    attribute_index = list_index[attribute]
    sorted_list.sort(key=itemgetter(attribute_index))
    print(str(sorted_list))
    return sorted_list


# sorts the data_list for given attribute from highest to lowest
def sort_to_max(attribute):
    sorted_list = data_list.copy()
    attribute_index = list_index[attribute]
    sorted_list.sort(key=itemgetter(attribute_index), reverse=True)
    print(str(sorted_list))
    return sorted_list


import_csv2(csv_file)
print(str(data_list))
sort_to_max("total_power")
sort_to_min("attack")


# int: attack, defense, hp, pokedex_number, sp_attack, sp_defense, speed, generation
# string: name, type1, type2 (can be empty string)
# bool: is_legendary
