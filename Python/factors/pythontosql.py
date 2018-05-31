import bs4 as bs
import json
import re
import urllib
import mysql.connector
from bs4 import BeautifulSoup
from mysql.connector import Error


#enter input from the user
user_name = raw_input("please enter the user name: ")

#build url
url = "https://www.instagram.com/" + user_name + "/"

#crawling data from url for followers, following, posts
sauce = urllib.urlopen(url).read()
soup = bs.BeautifulSoup(sauce, 'lxml')

#crawling data from url for private and verified
web = urllib.urlopen(url)
soup = BeautifulSoup(web.read(), 'lxml')

#processing for extraction
pattern = re.compile('window._sharedData = (.*?);')
scripts = soup.find_all('script')

#creating json objects
for script in scripts:
   if(pattern.match(str(script.string))):
       data = pattern.match(script.string)
       stock = json.loads(data.groups()[0])
       
#extracting from json
stock1 = stock['entry_data']       
stock2 = stock1['ProfilePage']
stock4 = stock2[0]
stock5 = stock4['graphql']['user']['is_private']
stock6 = stock4['graphql']['user']['is_verified']
print ("======================================")
print ("private:" + str(stock5))
print ("verified:" + str(stock6))
print ("======================================")


#username and profilename
title = soup.find("title")

#extract name and username
title1 = title.string
title_ready = ""
a = 0
for t in title1:
    if t==")":
        title_ready = title_ready + t
        a = 1 
    elif a==0:
        title_ready = title_ready + t

print title_ready


#extract followers and following
fl_ready = ""
title2 = soup.find("meta",  property="og:description")
a = 0
for t in title2["content"]:
    
    if t=='-':
        a = 1 
    elif a==0:
        fl_ready = fl_ready + t

#extract integer followers and following from string data obtained
results = [int(s) for s in fl_ready.split() if s.isdigit()]
print "no. of followers = " + str(results[0])
print "no. of following = " + str(results[1])
print "no. of total posts = " + str(results[2])

#insert into database
def insert_user(name,following, followers, posts):
    query = "INSERT INTO insta_tbl(user_name, followers,following, posts, is_private, is_verified) " \
            "VALUES(%s,%s,%s,%s,%s,%s)"
    args = (name, followers, following, posts, stock5, stock6)
 
    try:
        conn = mysql.connector.connect(host='localhost',database='mayur',user='root',password='admin')
        if conn.is_connected():
            print('connected to MySql database')
 
        cursor = conn.cursor()
        cursor.execute(query, args)
 
        if cursor.lastrowid:
            print('last insert id', cursor.lastrowid)
        else:
            print('last insert id not found')
 
        conn.commit()
    except Error as error:
        print(error)
 
    finally:
        cursor.close()
        conn.close()

def main():
   insert_user(title_ready, results[0], results[1], results[2])
 
if __name__ == '__main__':
    main()
