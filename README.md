# Maze Solver (Java)

This project is a Java-based maze solving application created for a Data Structures course. The program reads a maze from a text file, represents it as a 2D matrix, and solves the maze using both **Depth-First Search (DFS)** with a stack and **Breadth-First Search (BFS)** with a queue. The resulting paths are then analyzed, validated, and displayed to compare the behavior of each traversal algorithm.

The project also includes a random maze generator and a wall-density experiment to measure how maze complexity impacts solvability.

---

## Features

- Reads maze data from an input text file
- Solves mazes using:
  - **DFS (Stack-based traversal)**
  - **BFS (Queue-based traversal)**
- Reconstructs and prints the discovered path
- Displays directional path visualization
- Validates generated paths with multiple integrity checks
- Tracks algorithm statistics such as:
  - Cells visited
  - Final path length
  - Maximum frontier size
- Generates random mazes with customizable wall density
- Runs solvability experiments on generated mazes

---

## Maze Format

The maze is stored in a text file using the following symbols:

| Symbol | Meaning |
|---|---|
| `S` | Start position |
| `E` | End position |
| `#` | Wall / blocked cell |
| `.` | Open path |
