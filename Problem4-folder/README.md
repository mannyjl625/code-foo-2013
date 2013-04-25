Problem 4 README
=================

To complile program for running:

javac Problem4.java

To run:

java Problem4

Summary
-------

For this problem, I implemented my own family tree graph, modeled 
after my own semi-immediate family with Person obects, only using 
fields neccesary to the search algorigtm, though
more fields such as sex and parents could be easily added. To traverse
the graph and locate family members with the same name and/or 
generation, I used a depth first search on the family tree graph.

Efficiency Analysis
-------

The implementation of depth first search that I used, with a Stack,
takes an O(E) runtime, where E is the number of edges, which is the
number of parent/child relationships. For space-time complexity, depth
first search uses O(V) space where V is the number of vertices. As far
as how my implemtation scales, in the case of a large tree with 
hundreds of thousands of family members, dfs can suffer from longer
then desired runtimes when the depth of the tree it's traversing 
approaches infinity. But for the purposes of family trees with finite
depths, the search should maintain its O(E) runtime since the graph 
is explicit and revisited person objects is avoided
