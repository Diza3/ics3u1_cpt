ICS 3U1
CPT - part 1
TREASURE HUNT
You may work with a partner, this part of your CPT is worth 20% of your final mark.
Introduction
For this game, the user will be going on a treasure hunt. They will explore a 10 x 10 board that will
contain treasure, pirates and an escape off the island. The goal is to escape the island with the most
treasure possible.
Treasure(T) - increases as more treasure is found (ie, the first treasure is +100 gold doubloons, the
second is +200 gold doubloons and so on)
Pirates (P) - the more pirates you encounter the more gold doubloons they can take. (ie. the first
crew is -100 gold doubloons, the second crew is -200 gold doubloons and so on) BUT, if the player
has no treasure, there is nothing to be lost.
Both (B)- If there is treasure AND pirates, the player gets half the treasure available.
Escape (X) - When a ship is seen in the distance, the user can leave the island.
Level 3 (up to 80)
● A predefined 10 x 10 board, with 5 pirates, 5 treasures and 3 escapes loaded using Strings or
arrays (try and use Bs as well)
● If the user sees all three ships, they have to leave.
Level 4 (up to 90)
● Use Strings or arrays to set up a 10 x 10 game board
● Have the computer randomly generate positions of 5 treasures, 5 pirates and 3 escape
● If the user sees all three ships, they have to leave.
Level +
● Ask the user the number of treasures, and pirates to place on the board.
○ No more than 10 each.
○ The number of treasures cannot be more than the number of pirates.
○ Randomly generate their positions
● There will be ONE escape on the board. When the user sees a ship, they can either leave the
island, or stay.
○ If they stay on the island, the current spot is marked as explored and a new escape is
generated on the board in a spot that has not been explored.
○ If they have explored all possible positions on the island, they HAVE to leave
the island.
● Use a 2D array or an array of arrays!
2
ALL must …
● Begin game with a menu:
1. Play game
2. How to play
3. Legend
4. Exit
● Provide a legend for the user at any time during game play.
● Make sure that the user’s position entry is between 0 and 99, inclusive.
● Display the current board after each round.
● Unexplored spots are marked with a 0.
● Mark explored spots with a #.
● Have a counter to count the number of pirates, treasure and ships (level 2 & 3 NOT level 4) .
● Display a total treasure during each round.
● Contain a printBoard method that will print the updated board every time the user takes a
turn.
● Have an isVacant method that will return true if the position is unexplored and false
otherwise
Board:
Positions [00 - 09]: 0 0 0 0 0 0 0 0 0 0
Positions [10 - 19]: 0 0 0 0 0 0 0 0 0 0
Positions [20 - 29]: 0 0 0 0 0 0 0 0 0 0
Positions [30 - 39]: 0 0 0 0 0 0 0 0 0 0
Positions [40 - 49]: 0 0 0 0 0 0 0 0 0 0
Positions [50 - 59]: 0 0 0 0 0 0 0 0 0 0
Positions [60 - 69]: 0 0 0 0 0 0 0 0 0 0
Positions [70 - 79]: 0 0 0 0 0 0 0 0 0 0
Positions [80 - 89]: 0 0 0 0 0 0 0 0 0 0
Positions [90 - 99]: 0 0 0 0 0 0 0 0 0 0
Legend:
0 - indicates that spot has not been explored yet
T - indicates treasure, 100 gold doubloon
P - indicates pirates, lose 100 gold doubloon
B - indicates both treasure and pirates, 50 gold doubloon
X - escape the island
3
Predefined Board 1D Array (if needed)
char gameBoard [] = {0, T, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, T, 0, 0, 0,
B, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, P, 0, 0, P, 0, 0, 0, 0, 0, 0, 0, 0,
0, 0, 0, 0, P, 0, 0, X, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
0, 0, 0, 0, 0, T, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, {0, 0, 0, X, 0,
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, B, 0, 0, X}
Predefined Board 2D Array (if needed)
char gameBoard [][] = {{0, T, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, T, 0, 0,
0, B, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, P, 0, 0}, {P, 0, 0, 0, 0, 0, 0,
0, 0, 0}, {0, 0, 0, P, 0, 0, X, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0,
0}, {0, 0, 0, 0, 0, 0, 0, T, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
{0, 0, 0, X, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, B, 0, 0, X}}
My printBoard method
public static void printBoard(char[][] pBoard) {
int row = 10;
System.out.println("\nPLAYER BOARD \n");
for (int i = 0; i < pBoard.length; i++) {
if (i == 0) {
System.out.print("Position [00 - 09]\t");
} else {
System.out.print("Position [" + row + " - " + (row + 9) +
"]\t");
row += 10;
}
for (int j = 0; j < pBoard[i].length; j++) {
System.out.print(pBoard[i][j] + " ");
}
System.out.println("");
}
}
Note:
You may need two boards, one for the game board and one for the player board.
4
Planning (20 marks)
Flowchart and pseudocode for level 3 must be submitted by March 25. See previous assignments
for marking criteria.
Documenting (10 marks)
Good coding practices
● Header
● Meaningful variable names
● Variables declared and initialized in correct positions
● Proper code indentation
● Logical flow
● Chunked
● Internal Documentation:
○ Single/multi line comments - describe purpose of complicated logic
○ Single line comments - ALL UPPER CASE indicating coding concept used
○ Single line comments - indicate who’s work it is
○ Methods fully documented
Coding (50 marks)
Game Play (25 marks)
● Program does not crash
● No logic/syntax errors
● Clean appearance
● No confusion on what to do next
● Game is easy to understand
● How to play the game is described clearly
● Has a beginning and an end
● Keeps score/track of progress
● Play continues until game logically ends
● Crash proof
Programming Concepts (25 marks)
● Outline concepts used in program
o Decision Statements ( If and Case)
o Loops (For and While)
o Methods
o Arrays/Strings
● Demonstrates understanding of multiple concepts
Testing (10 marks)
A paragraph describing how the program was tested for bugs and how you overcame them.
Feedback from two outside testing sources.
5
Rubric
Criteria Comments Mark
Planning -
Flowchart & pseudocode
/20
Good Coding Practices -
Internal documentation
/10
Testing /10
Game Play /25
Programming Concepts /25
2D - Arrays
Crash-Proof
Case Sensitivity /10
Total /100
Notes:
6
