Problem 6 README
==============

To compile program for running

`````
javac Proble6.java
`````

To run:

`````
java Problem6
`````

Summary
---------

For this problem, I implemented a grah where all words were connected
other words that were one 'move' away, which differed by only one 
letter. To establish a shortest path between two words, if one existed,
I used a breadth first search graph traversal too find the end word
starting from the beginning word. If nothing was found, then there
was no valid path between the two words. If the end word was found,
then the number of moves are given along with path of words taken to 
each the end word from the start word.
