import csv

import json

  

print("Hello world")

  
  

with  open('./tcdata/num_list.csv') as f:

row = csv.reader(f, delimiter=',')

height = []

for r in row:

height.append(int(r[0]))


s = sum(height)


height_down = sorted(height, reverse=True)

# print(height_down)

  

height_down_10 = []

for i in  range(10):

height_down_10.append(height_down[i])


jsondata = json.dumps({'Q1': 'Hello world', 'Q2': s, 'Q3': height_down_10},

sort_keys=True, indent=4, separators=(',', ': '))

fjson = open('result.json', 'w')

fjson.write(jsondata)

fjson.close()