import MySQLdb

# Open database connection
db = MySQLdb.connect("localhost","root","codethecode@1","mayur")

# prepare a cursor object using cursor() method
cursor = db.cursor()
cursor1 = db.cursor()
cursor2 = db.cursor()
cursor3 = db.cursor()


#lists
first_list = []
second_list = []
third_list = []
cmn_list1 = []
cmn_list2 = []

# execute SQL query using execute() method.
cursor.execute("SELECT * FROM commentators1")
cursor1.execute("SELECT * FROM commentators2")
cursor2.execute("SELECT * FROM commentators3")

# Fetch a single row using fetchone() method.
data1 = cursor.fetchall()
data2 = cursor1.fetchall()
data3 = cursor2.fetchall()

for i in data1:
    first_list.append(i)

for j in data2:
    second_list.append(j)

for k in data3:
    third_list.append(k)


for element in data1:
    if element in data2:
        cmn_list1.append(element)

for element1 in cmn_list1:
    if element1 in data3:
        cmn_list2.append(element1)

print first_list
print second_list
print third_list


req_param1 = len(first_list)
req_param2 = len(second_list)
req_param3 = len(third_list)
req_param4 = len(cmn_list2)


cursor3.execute("insert into cmn_com(twelve, twentyfour, thirtysix, common) values('%d','%d','%d','%d')" %(req_param1, req_param2, req_param3, req_param4))
db.commit()

# disconnect from server
db.close()


