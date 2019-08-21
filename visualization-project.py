import csv
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from operator import itemgetter
import plotly.graph_objects as go


# Easy access to list indices of data_list for all attributes
# pokedex_number,name,generation,type1,type2,attack,sp_attack,defense,sp_defense,hp,speed,total_power,is_legendary
list_index = {"pokedex_number": 0, "name": 1, "generation": 2, "type1": 3, "type2": 4, "attack": 5, "sp_attack": 6,
              "defense": 7, "sp_defense": 8, "hp": 9, "speed": 10, "total_power": 11, "is_legendary": 12}

csv_file = "pokemon.csv"

# contain main data
data = {}
data_list = []
data_list_integers = []
data_pandas = pd.read_csv(csv_file)


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


# experimental, removes all non-integers from data_list
def create_data_list_integer():
    i = 0
    for pokemon in data_list:
        temp = pokemon.copy()
        for attr in pokemon:
            if type(attr) != int:
                temp.remove(attr)
        temp.pop()
        if i % 20 == 0:
            data_list_integers.append(temp)
        i += 1
    print(str(data_list_integers))


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
create_data_list_integer()

fig = go.Figure(data=go.Bar(y=[2, 3, 1]))
fig.show()




################
# int: pokedex_number,generation,attack,sp_attack,defense,sp_defense,hp,speed,total_power

# string: name, type1, type2 (can be empty string)
# bool: is_legendary
