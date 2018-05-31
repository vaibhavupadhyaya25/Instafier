import json
import MySQLdb
import unicodedata
from pprint import pprint

# Open database connection
db = MySQLdb.connect("localhost","root","codethecode@1","mayur")

# prepare a cursor object using cursor() method
cursor = db.cursor()

testFile = open("F:/College_Semesters_study_Material/minor 6sem/post_dates/instagram-profilecrawl-master/profiles/i.think.2.json")

data = json.load(testFile)
userBio = data["bio"]


req_str = unicodedata.normalize('NFD', userBio).encode('ascii','ignore')


for word in req_str.split():
    print (word)
    cursor.execute("insert into bio_table(bio) values('%s')" %(word))
    db.commit()

db.close()


