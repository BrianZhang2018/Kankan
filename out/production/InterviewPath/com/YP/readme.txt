Welcome to your coding test.  Please use Java to solve these questions, based on the data files in this directory. 
You can use any java library you wish, although none should be required to produce a working solution (beyond java.lang/java.io/java.util).

The files in this directory are:
users.tsv
	This file is a tab-delimited file with USERID<tab>LATITUDE<tab>LONGITUDE<\n>
LatLon.java
	This is a java source file, provided for your convenience to calculate the distance between 2 lat/lon pairs in kilometers
	
	
Write a java program to read users.tsv from disk.  
You can store/model the data in anyway you wish -- and you should create methods that are able to answer the following questions:


A) Question:How many unique USERIDs are there?
	Method: public int numberOfUniqueUserIDs();

B) Question:How many unique lat/lon pairs are there?
	Method: public int numberOfUniqueLatLon();
	
C) Question:For every UserID that has more than one location, what is the UserID of the person who has the furthest distance between any two of their locations?
	Method: public String findFurthestDistanceUser();

D) Question:Build a method that takes in a lat/lon, and returns the top K closest locations based on distance to the input lat/lon
	Method: public double[][] getClosestUserIDs(double lat, double lon, int how_many_to_return)
