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
       
stock1 = stock['entry_data']
stock2 = stock1['ProfilePage']
stock3 = stock2[0]
stock4 = stock3['graphql']['user']['biography']
print stock4

print type(stock4)
enc = stock4.encode('utf-8')


query = "INSERT INTO bio_table(bio) " \
            "VALUES(%s)"

 
try:
   conn = mysql.connector.connect(host='localhost',database='mayur',user='root',password='codethecode@1')
   if conn.is_connected():
      print('connected to MySql database')
 
   cursor = conn.cursor()
   cursor.execute(query,(enc,))
 
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


