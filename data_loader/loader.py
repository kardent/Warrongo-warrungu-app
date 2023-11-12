import os
import shutil
import sqlite3
import csv


shutil.copyfile('base/warrungu.db','new.db')


connection = sqlite3.connect("new.db")
cursor = connection.cursor()

count = 1
data = csv.DictReader(open('data.csv'))
for row in data:
    print(row['english'])

    # DICT ITEMS
    cursor.execute("INSERT INTO DictionaryItem VALUES(?, ?, ?, ?, ?, '', ?, ?)",
    (count,row['english'],row['language'],row['Pronunciation'],row['Description'],row['Word type'],row['Image']))

    # CATAS
    for cat in row['cat'].split(','):
        if cat:
            cursor.execute("INSERT INTO Categories VALUES(?, ?)",
            (count,cat))

    count += 1

connection.commit()