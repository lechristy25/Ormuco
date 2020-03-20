# Ormuco
Source files for each program can be found in OrmucoTest/src

Question A) 
--
-- Your goal for this question is to write a program that accepts two lines (x1,x2) and (x3,x4) on the x-axis and returns whether they overlap. As an example, (1,5) and (2,6) overlaps but not (1,5) and (6,8).

-- My implementation of this program involved the use of the StringTokenizer class to separate the user's coordinate inputs into two separate arrays, with each array holding the integer coordinates of each line. These arrays are then formatted so that the smaller coordinate is assigned to the 0th index, which then allows for an easy comparison between the coordinates of each line to see if they overlap or not. Some functionalities that I didn't implement include: A format checker for the user input. 



Question B)
--
-- The goal of this question is to write a software library that accepts 2 version string as input and returns whether one is greater than, equal, or less than the other. As an example: “1.2” is greater than “1.1”. Please provide all test cases you could think of.

-- My implementation of this program once again involved the use of the StringTokenizer class to parse through and represent each version input's individual numbers in an integer array. For example, 1.1.19 would be stored in the array as {1, 1, 19}. Once both versions have been formatted as such, the program compares the lengths of the two arrays so as to correctly iterate through and compare the values at each index of the array. Missing functionalities include: A format checker for the version input. 



Question C)
--
-- At Ormuco, we want to optimize every bits of software we write. Your goal is to write a new library that can be integrated to the Ormuco stack. Dealing with network issues everyday, latency is our biggest problem. Thus, your challenge is to write a new Geo Distributed LRU (Least Recently Used) cache with time expiration.

-- My implementation of the LRU Cache with time expiration involved the use of a hashmap and doubly linked list for fast lookup and fast get/delete operations respectively. The time expiry was implemented using a separate thread that concurrently scans the cache for any elements that exist past the expiration time given by the user. A concurrent hashmap was therefore used to allow the expiry thread to iterate concurrently over the elements. Missing functionalities include: A method to get all unexpired elements of the cache and a method to delete all currently stored elements. 
