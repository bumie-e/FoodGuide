import pandas as pd
import sqlite3 as db
import os
#import pymysql
# import the module
#from sqlalchemy import create_engine

df = pd.read_csv('dataset.txt', delimiter=":")
print(df.head(2))
# Create your connection.
conn = db.connect('connection_name')
#df.to_sql(name='foodguide', con=conn)
query = "SELECT * FROM foodguide"

#query = "ALTER TABLE foodguide ADD Images blob"

cursor = conn.cursor()
cursor.execute(query)
c  = cursor.fetchall()
print(c)
conn.commit()
print("Image and file inserted successfully as a BLOB into a table")
cursor.close()

# Function for Convert Binary Data
# to Human Readable Format
def convertToBinaryData(filename):
	
	# Convert binary format to images
	# or files data
	with open(filename, 'rb') as file:
		blobData = file.read()
	return blobData


    
def insertBLOB(n):
	try:
		
		# Using connect method for establishing
		# a connection
		sqliteConnection = db.connect('connection_name')

		cursor = sqliteConnection.cursor()
		print("Connected to SQLite")
		
        
		# insert query
		sqlite_insert_blob_query = """ INSERT INTO foodguide
								(Images) VALUES (?)"""

		# Converting human readable file into
		# binary data
		empPhoto = convertToBinaryData(n)
		
		# Convert data into tuple format
		data_tuple = (empPhoto,)
	
		# using cursor object executing our query
        
		cursor.execute(sqlite_insert_blob_query, data_tuple)
        #cursor.execute(sqlite_insert_blob_query, data_tuple)
		sqliteConnection.commit()
		print("Image and file inserted successfully as a BLOB into a table")
		cursor.close()

	except db.Error as error:
		print("Failed to insert blob data into sqlite table", error)
	
	finally:
		if sqliteConnection:
			sqliteConnection.close()
			print("the sqlite connection is closed")

# Get the list of all files and directories
path = "C:/Users/akinr/Desktop/FoodGuide/bunmi's images/Bunmi's API/"
dir_list = os.listdir(path)

#for i in dir_list:
#   insertBLOB(path+i)

# create sqlalchemy engine
#engine = create_engine("mysql+pymysql://root:12345@localhost:8008/foodguide", pool_pre_ping=True)

# Insert whole DataFrame into MySQL
#df.to_sql('foodguide', con = engine, if_exists = 'append', chunksize = 1000)
