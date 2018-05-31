import MySQLdb

db = MySQLdb.connect("localhost","root","codethecode@1","mayur")
cursor = db.cursor()

file = open('F:/College_Semesters_study_Material/minor 6sem/post_dates/instagram-profilecrawl-master/profiles/i.think.2_commenters.txt', 'r')
file_content = file.read()
file.close()

#print file_content

#query = "INSERT INTO table VALUES (%s)"

#cursor.execute(query, (file_content,))

for word in file_content.split():
    print (word)
    cursor.execute("insert into commentators3(names) values('%s')" %(word))
    db.commit()


db.close()
