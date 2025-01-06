# Task Assignment Problem Using Branch and Bound in Java

This program addresses the classic task assignment problem, where n tasks need to be assigned to n agents with the goal of minimizing the total cost. The algorithm leverages branch and bound to efficiently explore and prune the solution space, ensuring optimal results.

## Key Features:
**Initial Bound:**
- The initial pessimistic bound is calculated as the sum of the diagonal costs of the cost matrix. This serves as a starting point for pruning non-promising branches in the solution tree.
Input and Configuration:

- The program expects the dimensions of the cost matrix and the matrix content to be provided through a file passed via standard input.
The user can specify parameters to control the output destination and behavior.
Output:

- The resulting optimal task-to-agent assignment is returned either via a file or standard output, depending on the parameters specified by the user.

**Trace Option:**

- By using the -t parameter, the program provides a detailed trace of the algorithm's execution. This includes insights into branching decisions, bounds calculations, and pruning steps.
Help Option:

- The -h parameter displays a help menu that explains the usage, input format, and available options, making the program user-friendly.

## Usage Example:
Input:
A file containing a cost matrix of size n x n, where each element represents the cost of assigning a specific task to a specific agent.
```txt
3 3
20 34 17
10 35 42
15 27 19
```
Output:
An optimal assignment that minimizes the total cost, along with the total cost value.
```txt
1 3
2 1
3 2
```
