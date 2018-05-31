import bs4 as bs
import json
import re
import urllib
import mysql.connector
import unicodedata
from bs4 import BeautifulSoup
from mysql.connector import Error




#query = "SELECT * FROM bio_table "             
query1 = "SELECT * FROM vaibhav"
 
try:
   conn = mysql.connector.connect(host='localhost',database='mayur',user='root',password='codethecode@1')
   if conn.is_connected():
      print('connected to MySql database')
 
   cursor = conn.cursor(buffered=True)
   
   cursor.execute(query1)

   for i in cursor.fetchall():
      print i
   
   

   #dataarray = cursor.fetchall()
   #dataarray1 = cursor1.fetchall()
   #print dataarray
   #print dataarray1
   #print type(dataarray)
   #print dataarray1[5]
   #for j in dataarray:
   #   print j
   #   print'\n'
   #    for k in dataarray1:
           #if k==j:
   #            counter = counter+1
   #datareq = dataarray[0]
   #print counter
   #print type(datareq)

   

   #coded = unicodedata.normalize('NKFD',datareq)
   #string = coded.encode('ascii','ignore')
   #print string
 
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


