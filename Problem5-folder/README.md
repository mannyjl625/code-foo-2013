Problem 5 README
==============

To compile program for running:

````
javac Problem5.java
```

To run:

````
java Problem5
````

Input
------

To test this program with difference score text files, they should be
formated like the following;

`````
100 IGN
200 Code Foo
300 Mr. Awesome
````


Efficiency Analysis
------------------

For this problem, I chose to implement a merge sort algorithm to sort a list of scores and names read from a text file.
I chose this sorting algorithm because of its near-unbeatable average and worst-case runing times, which are both O(nlogn) where
n is the number of elements being sorted. For space time complexity, merge sort is O(n).

I chose this sorting algorithm over the other popular choice of quicksort due to its better performance in the worst case, being that
quick sort suffers from an undesirable O(n^2) worst case running time. Merge sort also has the added benifit of being a stable sort, while 
quick sort is an unstable sort.

As far as how this implemation scales when dealing with millions of elements in an online environment, merge sort has some advantages as well
as some drawbacks. Because the nature of merge sort has it passing subarrays as peices at any given time, rather then one giant set, it lends
itself well to an online enviroment where bandwidth may have to be used sparingly. But it has the drawback of becoming expensive when large numbers
of subarrays much smaller then the original set  need to be passed in.

One way this implemtation can have its runtime and spacetime complexity reduced for large sets being sorted is to limit writes to disk, which 
are very slow, as much as posible by storing and sorting any subarrays that become small enough to be stored in the machines cache.
