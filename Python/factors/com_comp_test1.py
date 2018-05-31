import MySQLdb

# Open database connection
db = MySQLdb.connect("localhost","root","codethecode@1","mayur" )

# prepare a cursor object using cursor() method
cursor = db.cursor()
cursor1 = db.cursor()
cursor2 = db.cursor()
cursor3 = db.cursor()
cursor4 = db.cursor()

#lists
promo_list = []
bio_list = []
celeb_list = []
normal_list = []
cmn_list1 = []
cmn_list2 = []
cmn_list3 = []

# execute SQL query using execute() method.
cursor.execute("SELECT * FROM promotional")
cursor1.execute("SELECT * FROM bio_table")
cursor2.execute("SELECT * FROM celeb_table")
cursor3.execute("SELECT * FROM normal_table")

# Fetch a single row using fetchone() method.
data = cursor.fetchall()
data1 = cursor1.fetchall()
data2 = cursor2.fetchall()
data3 = cursor3.fetchall()

for i in data:
    promo_list.append(i)

for j in data1:
    bio_list.append(j)

for k in data2:
    celeb_list.append(k)

for l in data3:
    normal_list.append(l)

for element in data1:
    if element in data:
        cmn_list1.append(element)

for element1 in data1:
    if element1 in data2:
        cmn_list2.append(element1)

for element2 in data1:
    if element2 in data3:
        cmn_list3.append(element2)


print promo_list
print bio_list
print celeb_list
print normal_list
promo1 = len(cmn_list1)
celeb1 = len(cmn_list2)
normal1 = len(cmn_list3)

cursor4.execute("insert into bio_comparision_table(promo, celeb, normal) values('%d','%d','%d')" %(promo1, celeb1, normal1))
db.commit()

# disconnect from server
db.close()


