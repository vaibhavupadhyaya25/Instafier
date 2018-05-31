import MySQLdb

# Open database connection
db = MySQLdb.connect("localhost","root","codethecode@1","mayur")

# prepare a cursor object using cursor() method
cursor = db.cursor()
cursor1 = db.cursor()
cursor2 = db.cursor()

# execute SQL query using execute() method.
cursor.execute("SELECT COUNT(distinct post_date) FROM post_info")
cursor1.execute("SELECT COUNT(post_date) FROM post_info")

# Fetch a single row using fetchone() method.
data1 = cursor.fetchall()
data2 = cursor1.fetchall()

a = int(data1[0][0])
b = int(data2[0][0])

cursor2.execute("insert into postdate_rto(postsno, dateno) values('%d','%d')" %(a,b))
db.commit()

# disconnect from server
db.close()


