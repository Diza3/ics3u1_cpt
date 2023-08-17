// post hiding all characters that are not 0s or #s
// post creating a new array for the internalBoard (does not hide most characters when displayed)

/*  errors: create function that prevents user from getting trapped and unable to move if the spaces around it are already explored; make them
    be able to escape that trap and go to the nearest '0' that also does not have explored spaces all around it so it is able to keep moving
    (already kinda made (check method "stuck()"), need to clean it up a little); check new error png for error
    make user leave the island immediately after all positions have been explored
        as in the words of the genius vivian: 
            "but yea check for end-of-game conditions by checking if the board is full as well as if youre stuck
            then if thats true then end the game"
    code formatting is very off and some of the logic is v shitty
    while(true) is not a valid condition- FIX!
    ADD COMMENTS TO THE FUCKING CODE
    */

/* Checklist: 

    ● Ask the user the number of treasures, and pirates to place on the board. ✓
        ○ No more than 10 each. ✓
        ○ The number of treasures cannot be more than the number of pirates. ✓
        ○ Randomly generate their positions ✓
    ● There will be ONE escape on the board. When the user sees a ship, they can either leave the
        island, or stay. ✓
        ○ If they stay on the island, the current spot is marked as explored and a new escape is generated on the board in a spot 
            that has not been explored. ✓
        ○ If they have explored all possible positions on the island, they HAVE to leave the island. X
    ● Use a 2D array or an array of arrays! ✓

    ● Begin game with a menu: ✓
        1. Play game
        2. How to play
        3. Legend
        4. Exit
    ● Provide a legend for the user at any time during game play. ✓ (sort of? provide when user prompts, or provide all the time on each turn?)
    ● Make sure that the user’s position entry is between 0 and 99, inclusive. ✓ (sort of? only lets user go W, A, S, or D; error handling correctly?)
    ● Display the current board after each round. ✓
    ● Unexplored spots are marked with a 0. ✓
    ● Mark explored spots with a #. ✓
    ● Display a total treasure during each round. ✓ (should I re-format this?)
    ● Contain a printBoard method that will print the updated board every time the user takes a
      turn. ✓
    ● Have an isVacant method that will return true if the position is unexplored and false
     otherwise ✓
*/



// Diza Sidana  ICS3U8Ge  July 26, 2023   Ms. Kaminski

/* This game takes the user on an island to participate in a treasure hunt,
 * with the goal of finding the most treasure possible.
 * The user has 4 options on the main menu:
 * 1. Play Game
  * begins the game and asks the user the number of  
 */

// Importing the Random class
import java.util.Random;

// Importing the Scanner class
import java.util.Scanner;

// Catches all exceptions
import java.util.InputMismatchException;

// 
class TreasureHunt {
    int treasureCount = 0;
    int piratesCount = 0;
    int escapeCount = 0;

    int treasureTotal = 0;
  
    static int playerRow = 0;
    static int playerCol = 0;

    static char escape = ' ';

    static char[][] gameBoard = new char[10][10];
    static char[][] internalBoard = new char[10][10];

    // boolean end = false;

    // Instantiating the Random and Scanner objects
    Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

  /* This method is used in initializing the board after the user has inputted
   * the number of pirates and treasures they want on the board.
   *
   * Randomly generates the positions of/places the treasures (T), pirates (P),
   * both (B), and escape (X) symbols onto the board.
   * Randomly places a character/characters into the game board array (for 
   * this purpose, any character that is not a '0'), depending on either user 
   * input or a predetermined amount 
   */

  public void placeItems(char symbol, int count){ 
    // Parameters specify which character is being randomly placed into the 
    // array and the amount of said character

    // Will check how many of said symbol has already been placed into the 
    // board; set to 0 since none have been placed yet
    int placed = 0;

    // While the number of symbols placed is less than the amount we need to
    // place, the loop runs
    while (placed < count) {

      int i = rand.nextInt(10);
      int j = rand.nextInt(10);

      // '0' is the default value for each element in the array; if it is '0'
      // (unexplored) the method can place the symbol in that spot

      if (internalBoard[i][j] == '0' && gameBoard[i][j] == '0') {
        internalBoard[i][j] = symbol;
        placed++;
      // Creates the "Both" position. Allows Pirates (P) to fall on the same square as Treasures (T). If this occurs, it makes that position in the array hold the character 'B'
      } else if (symbol == 'P' && internalBoard[i][j] == 'T') {
        internalBoard[i][j] = 'B';
        placed++;
      }
    }
  }

  // Initializing the default board
  public void initializeBoard(int countT, int countP){
    
    for(int i = 0; i < internalBoard.length; i++){
      for(int j = 0; j < internalBoard.length; j++){
        internalBoard[i][j] = '0'; 
      }
    }

    for(int i = 0; i < gameBoard.length; i++){
      for(int j = 0; j < gameBoard.length; j++){
        gameBoard[i][j] = '0'; 
      }
    }

    placeItems('T', countT);
    placeItems('P', countP);
    placeItems('X', 1);
  }
    

  // ?????????????
  public void playGame(int countT, int countP){
    initializeBoard(countT, countP);
    playerRow = 0;
    playerCol = 0;

    if(playerRow == 0 || playerCol == 0) { // Does this need to be an "if" statement?
      // add # to first pos
      gameBoard[0][0] = '#';
      internalBoard[0][0] = '#';
    }  
  }

  // Displays board on the screen
  public void printBoard(){ //char[][] pBoard?
    
    int row = 10;
    System.out.println("\nPLAYER BOARD \n");
  
    for (int i = 0; i < gameBoard.length; i++) {
      if (i == 0) {
        System.out.print("Position [00 - 09]\t");
      } else {
        System.out.print("Position [" + row + " - " + (row + 9) + "]\t");
        row += 10;
      }
      for (int j = 0; j < gameBoard[i].length; j++) {
        System.out.print(gameBoard[i][j] + " ");
      }
      System.out.println(" ");
    }

      // Delete after

      System.out.println("\n\ninternal board");
        for (int k = 0; k < internalBoard.length; k++) {
            for (int l = 0; l < internalBoard[k].length; l++) {
                if (internalBoard[k][l] == '0') {
                    System.out.print(".");
                } else {
                    System.out.print(internalBoard[k][l]);
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }

      
        
  //   row = 10;
  //   System.out.println("\nINTERNAL BOARD \n");

  //   for (int k = 0; k < internalBoard.length; k++) {
  //     if (k == 0) {
  //       System.out.print("Position [00 - 09]\t");
  //     } else {
  //       System.out.print("Position [" + row + " - " + (row + 9) + "]\t");
  //       row += 10;
  //     }
  //     for (int l = 0; l < internalBoard[k].length; l++) {
  //       System.out.print(internalBoard[k][l] + " ");
  //     }
  //     System.out.println(" ");
  //   }
  

}

  // Determines if a spot on the map is vacant (0)
  public boolean isVacant(int i, int j){
    
    return internalBoard[i][j] == '0'; 

  }
  
  public boolean isTaken(int i, int j){
    
    return internalBoard[i][j] == 'P' || internalBoard[i][j] == 'T' || internalBoard[i][j] == 'B' || internalBoard[i][j] == 'X';
    
  }
  
  public boolean isExplored(int i, int j){

    // Using gameBoard instead of internalBoard as internalBoard does not recognize "isTaken" elements as explored until 1 move after they have been explored
    return gameBoard[i][j] == '#';

  }
  
  //
  public void explorePosition(int i, int j){

    int tVal = 0;
    int pVal = 0;
    
    // 
    if(internalBoard[i][j] == 'T'){
      tVal = 100 * (treasureCount + 1);
      treasureCount++;
      treasureTotal += tVal;
      System.out.println("You have found treasure worth " + tVal + " gold doubloons.");
      System.out.println("Your treasure count is " + treasureTotal + " gold doubloons.");
    }
    else if(internalBoard[i][j] == 'P'){
      if(treasureTotal > 0){
        pVal = 100 * (piratesCount + 1);
        piratesCount++;
        // ensure treasureTotal is over 0 and does not go into the negatives
        if(treasureTotal > pVal){
          treasureTotal -= pVal;
          System.out.println("You have found a gang of pirates. They have stolen " + pVal + " of your gold doubloons.");
        }
        else{
          System.out.println("You have found a gang of pirates. They have stolen " + treasureTotal + " of your gold doubloons. They would have stolen more but you were too poor for that.");
          treasureTotal -= treasureTotal;
        }
      System.out.println("Your treasure count is " + treasureTotal + " gold doubloons.");
       }
      else{
        System.out.println("You have encountered pirates but they found out you were too poor to lose any gold."); 
        System.out.println("Your treasure count is " + treasureTotal + " gold doubloons.");
      } 
    }
    else if(internalBoard[i][j] == 'B'){
      tVal = 50 * (treasureCount + 1);
      treasureCount++;
      piratesCount++;
      treasureTotal += tVal;
      System.out.println("You have found a gang of pirates plotting to steal some treasure. You manage to sneak half of the treasure while they weren't looking. You have gained " + tVal + " gold doubloons.");
      System.out.println("Your treasure count is " + treasureTotal + " gold doubloons.");
      System.out.println(" ");
    }
    else if((internalBoard[i][j] == 'X')){
      escapeCount++;

      // Forces user to escape if they have explored all spots
      
      /* if(gameBoard[10][10].equals('X')){
        
      } */
      
      System.out.println("You found a ship. Would you like to leave the island or stay? \n(Remember: the goal of the game is to escape the island with the most treasure possible.)");
      System.out.print("Type 'S' to stay and 'L' to leave: ");
        

      // The method "toUpperCase()" ensures that the input of X and Y in both uppercase and lowercase are accepted

      do{
        escape = scan.next().toUpperCase().charAt(0);
        if(escape == ('S')){
            placeItems('X', 1);
          }
          else if(escape == 'L'){
            System.out.println("Congratulations! You have escaped the island.");
            System.out.println("You have found " + treasureTotal + " gold doubloons in total.");
            System.exit(0);
          }
          else{
            System.out.println("Invalid input. Enter S or L.");
          }
      }
      while(escape != 'S' && escape != 'L');
        }
      
  }

  public void move(){

    int newRow = playerRow;
    int newCol = playerCol;

    System.out.println("Enter where you want to move.");
      char move = scan.next().toUpperCase().charAt(0); 
    System.out.println(" ");

    // 
    switch(move){
      case 'W':
        newRow = playerRow - 1;
        newCol = playerCol;
        break;
      case 'A':
        newRow = playerRow;
        newCol = playerCol - 1;
        break;
      case 'S':
        newRow = playerRow + 1;
        newCol = playerCol;
        break;
      case 'D':
        newRow = playerRow;
        newCol = playerCol + 1;
        break;
      default:
        System.out.println("Invalid input. Enter either W, A, S, or D.");
        move();
    }

    // if(isExplored(newRow, newCol) == false)
    if(newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10 && isExplored(newRow, newCol) == false){ // Or !isExplored(newRow, newCol);
      if(isVacant(newRow, newCol)){
        gameBoard[newRow][newCol] = '#';
        internalBoard[newRow][newCol] = '#'; // Location symbol
        playerRow = newRow;
        playerCol = newCol;
      }
      else if(isTaken(newRow, newCol)){
        gameBoard[newRow][newCol] = '#';
        printBoard();
        legend();
        explorePosition(newRow, newCol);
        internalBoard[newRow][newCol] = '#';
        playerRow = newRow;
        playerCol = newCol;
        move();
      }
      else{ // Idk what this is for
        gameBoard[newRow][newCol] = '#'; // Location symbol
        internalBoard[newRow][newCol] = '#';
        playerRow = newRow;
        playerCol = newCol;
      }
    }
    else{
      if(newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10 && isExplored(newRow, newCol)){
        System.out.println("You cannot move backwards to a spot that has already been explored. Choose another place to move.");
      }
      else{
        System.out.println("You cannot move this way. Choose another place to move.");
      }
      move();
    }

  }

  public static boolean stuck(int i, int j){

    /*for(int x = 0; x <= gameBoard[10][10].length; x++){
      
    }*/

      
      for (int y = i - 1; y < i + 1; y++) {
          for (int x = j - 1; x < j + 1; x++) {
                if (i == y && j == x) {
                    continue;
                }    
                // revise
                if (i < 0 || i > 9 || j < 0 || j > 9) {
                    continue;
                }
                if (gameBoard[y][x] != '#') {
                    return false;
                }
          }
      }
      return true;

    
  }
  public void legend(){

   //String legendReq = null;

    // System.out.format("%20s", "Legend");
    // System.out.println(" ");
    System.out.println(" ");
    
    //legendReq = scan.next();

    // LEGEND
    //if(legendReq.equalsIgnoreCase("Legend")){
      System.out.println("Legend");
    System.out.println("0 - indicates that spot has not been explored yet");
    System.out.println("T - indicates treasure, 100 gold doubloons");
    System.out.println("P - indicates pirates, lose 100 gold doubloon");
    System.out.println("B - indicates both treasure and pirates, 50 gold doubloons");
    System.out.println("X - escape the island");
    System.out.println(" ");
  }


public static int scanInt() { // revise while true (ask teacher)
  while (true) {
    try {
      return scan.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Your input is invalid. Enter a number to continue.\n");  
      scan.nextLine();
      System.out.println(" ");
    }
  }
}
/* public static char scanChar() { // Eliminates the need for comments
    return scan.next().toUpperCase().charAt(0);
}
*/

public static void main(String args[]) {
  TreasureHunt game = new TreasureHunt();

  int treasure = 0;
  int pirates = 0;
  
  int choice = 0;
  boolean end = false;

    while (true) { // Cannot use "while(true)"
      System.out.println("1. Play Game");
      System.out.println("2. How to Play");
      System.out.println("3. Legend");
      System.out.println("4. Exit");
      System.out.println(" ");

      // Checking for errors (non-Integer values) and gathering input at the same time
      choice = scanInt();

      // New line for readability
      System.out.println(" ");

      if (choice == 1) {

        System.out.println("How many pirates would you like to place on the board?");
        pirates = scanInt();

        // Space for readability
        System.out.println(" ");

        /* Asks the user to re-enter the number of pirates if
         * the user inputs a number that is greater than 10
         * or less than 1. Repeats until the user inputs a valid number
         */
        
        while (pirates > 10 || pirates < 1) {  

          // Gathers the new value for pirates
          System.out.println("Your input is invalid. Please enter a value between 1-10.");
          pirates = scanInt(); 
        }

        // Asks user for the number of treasures they want to place on the board
        System.out.println("How many treasures would you like to place on the board?");
       treasure = scanInt();
          
        // Asks the user to re-enter the number of treasures if the user inputs a number that
                // is greater than 10 or less than 1
                // Repeats until the user inputs a valid number
                while (treasure > 10 || treasure < 1 || treasure > pirates) {
                  if (treasure > 10 || treasure < 1){
                    System.out.println("Your input is invalid. Please enter a value between 1-10.");
                      treasure = scanInt(); // Gathers the new value for treasure
                  }
                  else if (treasure > pirates) {
                    System.out.println("The number of treasures cannot be more than the number of pirates. Please re-enter the number of treasures.");
                    treasure = scanInt();
                      
                    }
                }

                  while (escape != 'L'){
                    // first move
                    if(playerRow == 0 && playerCol == 0){
                      game.playGame(treasure, pirates);
                      game.printBoard();
                      game.legend();
                      //game.explorePosition(playerRow, playerCol);
                      game.move();
                    }
                    else{
                      // game.explorePosition(playerRow, playerCol);
                      game.printBoard();
                      game.legend();
                      game.move();
                   }
                  }

            }
                

            else if (choice == 2) {
                System.out.println("How to Play:");
                System.out.println(
                        "You are going on a treasure hunt. You are exploring a 10 x 10 board that will contain treasure, pirates, and an escape off the island.");
                System.out.println("The goal is to escape the island with the most treasure possible.");
                System.out.println(" ");
                System.out.println("To move your character, enter either W, A, S, or D when prompted.");
                System.out.println("\n" + "\t" + "W" + "\n" + " A  " + "\u2022" + "  S" + "\n" + "\t" + "D" + "\n");
                System.out.println("W: move up" + "\n" + "A: move left" + "\n" + "S: move down" + "\n" + "D: move right");
                System.out.println(" ");
                System.out.println(" enter more instructions here");

            } else if (choice == 3) {
                game.legend();
            } else if (choice == 4) {
                System.out.println("Game ended.");
                System.exit(0);
            } else {
                System.out.println("Your input is invalid. Please enter a number between 1-4 to continue.");
              // have to fix so that it does not end the program when more than one word/ a number is entered
              // can change 'choice' to a String?
                System.out.println(" ");
            }
        }
    }
}
