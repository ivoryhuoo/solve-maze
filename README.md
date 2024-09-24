# solve-maze
Maze Solving Using Graphs

# Overview
This project implements a maze-solving program that uses an undirected graph to represent a maze. The maze consists of rooms connected by corridors, some of which are closed by doors requiring coins to open. The program takes an input file representing the maze and produces a path from the entrance to the exit using a modified depth-first search (DFS) algorithm.

# Features
Graph Representation: The maze is represented as a graph, where rooms are nodes and corridors or doors are edges.
DFS Pathfinding: Uses a modified depth-first search to find the optimal path, considering available coins for opening doors.
Door Mechanics: Each door requires a specific number of coins, which can only be used once.

# Classes
GraphNode: Represents a node (room) in the graph, with methods for marking and retrieving node details.
GraphEdge: Represents an edge (corridor or door) between two nodes, with methods for managing edge type and label.
Graph: Implements the graph structure using an adjacency list, with methods for adding edges and retrieving node information.
Maze: Reads the input file, constructs the graph, and finds the path from entrance to exit.

# Technologies Used
Java

# Author
Ivory Huo - University of Western Ontario
