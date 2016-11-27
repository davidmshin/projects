Implementation of Hash Table for DNA sequence reading implemented in Algorithm and Data Structures class.

With provided code for reading the sequences from files, it takes a particular match length from the command line, performs the sub-string matching computation. 

My approach was to create a hash table to use the matching algorithm.

Hash table holds objects type of Records, while the hash table itself has three main functions: insert, delete, and find
In each slot of the hash table, it also stores the integer hash value of Record's key to speed up the search by avoiding a direct comparison of two strings.
The hash table also grows dynamically as records are inserted. Keeping load factor(fullness of the hash table) to 0.25, the hash table doubles if it detects that the table is quarter full.
