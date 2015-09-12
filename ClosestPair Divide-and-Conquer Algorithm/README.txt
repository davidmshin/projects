This project takes a number of points and returns the closest pair of points.
By comparing both the brute-force algorithm and divide-and-conquer algorithm,

Divide-and-Conquer algorithm, which bases on recursion, breaks the problem into
one or more subproblems, which are similar to the original yet smaller in size. It solves
problems recursively, then combines these broken down solutions to create
the solution to the original problem.

The returning print output shows the difference in running time between the
brute-force and d-c algorithm. 

As per the efficiency of the dc-algorithm used in finding the closest pair of points,
it is better than the brute-force because of these reasons:

The runtime of the brute-forcec is O(n^2), since you need to look at all possible pair of points.
Given, take something that grows quadratically, divide in two, each with half of size as before, 
it takes one quarter to solve the problem in each half, roughly requiring half the time the brute-force
solution. Four subproblems would take one fourth, and so on.

The dc algorithm ends up being faster here because we avoid dealing with too many pair of points that 
otherwise we would actually have to check.
