/**
 * Moving chess pieces (Part 2)
 * Project for CS 214 students. Fall 2022.
 *
 * @author Macharla Jeevan
 */
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MacharlaJeevan_ChessProject_Part2 {

    final static double MIN_X = -4, MIN_Y = -14;
    
    final static double MAX = 80 - MIN_X;
    final static double CENTER = (MIN_X + MAX) / 2;
    final static double NUM_OF_STEPS_IN_A_MOVE = 10;
    final static int START = 770, PICKED = 771, MOVING = 772;
    //Don't change constants!

    //               !!!!!!!!!!!!!!!!!!
    //BEFORE YOU START, make sure to COPY AND PASTE HERE the methods that you implemented for Project 2: 
    //    drawImagesOnBoard(...), drawMovingPiece(...), resetBoard(...), getFigureChar(...), printBoard(...),
    //    getBoardFromCharArray(...), getPieceNumber(...)
    //               !!!!!!!!!!!!!!!!!!
    private static void drawImagesOnBoard(int[][] b) {
        //Check each b[i][j] and determine which image (if any)
        //needs to be drawn. For that, you would also need to convert i and j 
        //to (x,y) coordinates. The size of the image should be set to width 10 and height 11.
        // i.e., your drawing command should be something like that:
        // StdDraw.picture(x, y, someImageFileName, 10, 11);
        //...
        for( int i = 0; i < b.length; i++)
        {
            for(int j = 0; j < b[i].length; j++)
            {
                if(b[i][j]>0)
                {
                    StdDraw.picture(5+j*10, 75-i*10, "pics/"+b[i][j]+".png", 10, 11);
                }
            }
        }
    }
 
  
  
    /**
    * TO BE FINISHED BY YOU
    * 
    * This method is drawing a "moving" figure at a location along the moving path. Note this methods only
    * draws the image one time in one location. The animation is handled in main which calls this method 
    * when needed. 
    * The  movingImage is given to you (you don't have to determine which file to use here).
    * rowS and colS are the row and column of the starting cell of the move (not x and y coordinates, but a row and col of the board array!!!).
    * rowF and colF are the row and column of the finishing cell of the move.
    * currentMovingStep input tells you which step of the animated move you are currently in. You will have to use it
    * to calculate moving figures current location and draw the image in that location.
    * 
    * When some figure starts "moving" it's currentMovingStep is set to 0, and every time this method is called,
    * currentMovingStep is incremented by one and returned to the animation loop. Each "move" should be divided by a fixed
    * number of steps -- this number is given by constant NUM_OF_STEPS_IN_A_MOVE. 
    * 
    * As you can see, this method checks if that number of steps has been reached, and returns -2 if it was reached (don't change that part).
    *
    */   
    private static int drawMovingPiece(String movingImage, int rowS, int colS, int rowF, int colF, int currentMovingStep) {
        if (currentMovingStep < 0) {
            return -1; //That means nothing's moving currently.
        }            
        double newX=-1.0, newY=-1.0;//These are just "dummy" initial values.
        //You would need to compute the actual values below.
        //DON'T CHANGE THE STATEMENTS ABOVE
        
        //Do the math to COMPUTE newX and newY (coordinates of a new position of a movingImage).
        // These should be computed by
        // 1. Figuring out initial coordinates (center of the starting cell (cell before the move)), based on rowS and colS.
        // 2. Figuring out finishing coordinates (center of the finishing cell (cell after the move)), based on rowF and colF.
        // 3. Use NUM_OF_STEPS_IN_A_MOVE constant and info from 1 and 2 to figure out the step size in x direction and step size in y direction.
        // 4. Use currentMovingStep and step sizes from 3 to compute newX and newY.
        // (See more in the project description. We'll also discuss it in class.).
        
        //...WRITE YOUR CODE HERE...
        //...
        int xS, yS, xF, yF;
        double dx, dy;
        xS = colS*10 + 5;
        yS = (7-rowS)*10 + 5;
        xF = colF*10 + 5;
        yF = (7-rowF)*10 + 5;
        dx = (xF - xS)/NUM_OF_STEPS_IN_A_MOVE;
        dy = (yF - yS)/NUM_OF_STEPS_IN_A_MOVE;
        newX = xS + currentMovingStep*dx;
        newY = yS + currentMovingStep*dy;
        
        
        //DON'T CHANGE THE STATEMENTS BELOW
        StdDraw.picture(newX, newY, movingImage, 10, 11); //Draw the image of a figure in (newX, newY) location.      
        if (currentMovingStep >= NUM_OF_STEPS_IN_A_MOVE) {
            return -2; //That means we are done moving.
        }    
        return currentMovingStep + 1;
    } 
    
    
    
    /**
    * TO BE IMPLEMENTED BY YOU
    * 
    * This method resets the board array
    *
    * Input: the board array. 
    * Reset all the elements of the board
    * to the standard initial position of a chess game.
    *
    * This method SHOULD use my initBoard() method.  It's a REQUIREMENT!
    *
    */
    private static void resetBoard(int[][] board) {
        // you can get an array with initial positions using
        // my initBoard() method, and then copy each element of that 
        // array into corresponding positions of the board array.
        int [][]reset = initBoard();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0;j < board.length;j++)
            board[i][j] = reset[i][j];
            
        }
    }   
    /**
    * TO BE IMPLEMENTED BY YOU
    * 
    * This method takes an int representation of a chess figure
    * and returns a corresponding char character:
    * 11--> 'P' (black pawn), 21--> 'p' (white pawn), 14-->'N', 24-->'n',  and so on
    * Refer to the Project description for details on these.
    *
    * You HAVE to use SWITCH statement here! It's a REQUIREMENT!
    *
    */     
    private static char getFigureChar(int figureInt) {
    
        char figname;
        
        switch(figureInt){
            
            case 11: figname = 'P';
                     break;
            case 14: figname = 'N';
                     break;
            case 15: figname = 'B';
                     break;
            case 16: figname = 'R';
                     break;
            case 17: figname = 'Q';
                     break;
            case 18: figname = 'K';
                     break;
            case 21: figname = 'p';
                     break;
            case 24: figname = 'n';
                     break;
            case 25: figname = 'b';
                     break;
            case 26: figname = 'r';
                     break;
            case 27: figname = 'q';
                     break;
            case 28: figname = 'k';
                     break;
            default: figname = '.';
                     break;
        }                     

            
        
        
    
        return figname;//would need to change of course
    }

   
    /**
    * TO BE IMPLEMENTED BY YOU
    * 
    * This method prints the board array using characters:
    * . - if empty
    * p - for a pawn, n - for a knight and so on
    *
    * This method SHOULD use your own getFigureChar() method!  It's a REQUIREMENT!
    *
    */    
    private static void printBoard(int[][] b) {
    
    for (int i=0; i<b.length; i++)
        {
            System.out.println("\t");
            for (int j=0;j<b.length;j++)
            {
                char a = getFigureChar(b[i][j]);
                System.out.print("\t"+a+" ");
            }
        }
    }
    /**
    * TO BE IMPLEMENTED BY YOU
    * 
    * This method takes two arrays as inputs. The first one
    * is a 2D array of chars and serves here as a source, the second one
    * is a 2D array of ints and serves here as a target.
    * Your task is to "translate" from chars to ints according
    * to our preset rules (this should actually be done by your getPieceNumber() method).
    *
    * This method SHOULD use your own getPieceNumber().  It's a REQUIREMENT!
    *
    */     
    private static void getBoardFromCharArray(char[][] c, int[][] b){
    
        for(int i=0;i<c.length;i++){
            
            for(int j=0;j<c[i].length;j++){
                b[i][j] =getPieceNumber(c[i][j]);
            }
        }
    }
    /**
    * TO BE IMPLEMENTED BY YOU
    * 
    * This method takes a char as an input and 
    * "translates" this char to an int according
    * to our preset rules ('.'-->0, 'P'--> 11, 'p'-->21, etc.)
    * It then returns that int. 
    *
    * You HAVE to use SWITCH statement here! It's a REQUIREMENT!
    *
    */      
    private static int getPieceNumber(char c){
    
        int number;
    
        switch(c){
            
            case 'P': number = 11;
                     break;
            case 'N': number = 14;
                     break;
            case 'B': number = 15;
                     break;
            case 'R': number = 16;
                     break;
            case 'Q': number = 17;
                     break;
            case 'K': number = 18;
                     break;
            case 'p': number = 21;
                     break;
            case 'n': number = 24;
                     break;
            case 'b': number = 25;
                     break;
            case 'r': number = 26;
                     break;
            case 'q': number = 27;
                     break;
            case 'k': number = 28;
                     break;
            default: number = 0;
                     break;
        }           
        return number;//would need to change, most likely
        
    }



 
    /**
    * ALREADY IMPLEMENTED FOR YOU
    *
    * This method checks if a "proposed" move is allowed by the rules.
    * Inputs: board array, row and column of a stating square (rS and cS), 
    * and row and column of a finishing square (rF, cF).
    * 
    * The method simply returns True if this move is allowed, and False otherwise.
    * To accomplish that the method simply redirects the "question" to other methods
    * depending on which figure is in the starting square.
    * You will have to implement those methods.
    */ 
    static boolean isMoveLegal(int[][] b, int rS, int cS, int rF, int cF){
        boolean kingLegal;
        if ((b[rS][cS]%10)==8){
            kingLegal = isLegalKing(b, rS, cS, rF, cF);
            if (!kingLegal)
                kingLegal = checkCastle(b, rS, cS, rF, cF);
            return kingLegal;
        }
        if ((b[rS][cS]%10)==7){
            return isLegalQueen(b, rS, cS, rF, cF);
        }if ((b[rS][cS]%10)==6){
            return isLegalRook(b, rS, cS, rF, cF);
        }
        if ((b[rS][cS]%10)==5){
            return isLegalBishop(b, rS, cS, rF, cF);
        }
        if ((b[rS][cS]%10)==4){
            return isLegalKnight(b, rS, cS, rF, cF);
        }
        if ((b[rS][cS])==11){
            return isLegalBlackPawn(b, rS, cS, rF, cF);
        }
        if ((b[rS][cS])==21){
            return isLegalWhitePawn(b, rS, cS, rF, cF);
        }
        return true;
    }

    /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one specifically checking the proposed move of a black pawn.
    * Pawns can only move forward and only capture diagonally forward.
    * Note that "forward" is different for black and white pawns.Hence different methods.
    *
    * An important rule to remember about the very first move that a pawn makes:
    * Every pawn for its first move has a choice to move two or one squares forward.
    * For all other moves the pawn is only allowed to move one square forward.
    * A pawn can capture an opponent's piece only diagonally forward (on a square touching its own by a corner), 
    * but cannot capture directly forward. It also cannot simply move diagonally, if it's not a capture.
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */
    static boolean isLegalBlackPawn(int[][] b, int rS, int cS, int rF, int cF) {
        
        if((cS==cF)&&(rF==rS+2)&&(rS==1))
        return true;
        if((cF==cS+1)||(cF==cS-1)){
            if(b[rS+1][cF]!=0)
            return true;
        }
        if((cS==cF)&&(rF==rS+1)){
            if(b[rF][cF]!=0)
            return true;
        }
 
        return false;
}

    /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one specifically checking the proposed move of a white pawn.
    * Pawns can only move forward and only capture diagonally forward.
    * Note that "forward" is different for black and white pawns. Hence different methods.
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */
    static boolean isLegalWhitePawn(int[][] b, int rS, int cS, int rF, int cF) {
        
        if((cS==cF)&&(rF==rS-2)&&(rS==6))
        return true;
        if((cF==cS+1)||(cF==cS-1)){
            if(b[rS-1][cF]!=0)
            return true;
        }
        if((cS==cF)&&(rF==rS-1)){
            if(b[rF][cF]!=0){
                return false;
            }
            return true;
        }
        return false;
    }
  
    
    /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one checks if the proposed move for a King is legal.
    * A king is allowed to move in any direction (including diagonally) but only one square.
    *
    * There are more complicated rules about "checks", but we are not implementing them yet.
    * There are also ways to "castle", but we are not implementing those here.
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */ 
    static boolean isLegalKing(int[][] b, int rS, int cS, int rF, int cF) {
        if((cF==cS)||(cF==cS-1)||(cF==cS+1))
        {
            if((rF==rS||rF==rS-1||rF==rS+1))
            {
                return true;
            }
        }
        return false;
    }


    /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one checks if the proposed move for a Queen is legal.
    * A Queen is the most powerfull piece in chess. It is allowed to move 
    * in any direction (including diagonally) as long as the path is not "barred" by another piece.
    * In other words it is not allowed to "jump" over other pieces.
    *
    * If an opponents piece is at the very end of the queen's move it simply captures it, so that is a legal move.
    *
    * Implement this one after the rook and the bishop methods and realize that this one simply combines the two together
    * i.e. the queen moves as both a rook and a bishop.
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */          
    static boolean isLegalQueen(int[][] b, int rS, int cS, int rF, int cF) {
        int d=0;
        if(rS == rF)
        {
            if(cS<cF)
            {
                for(int i = cS+1; i < cF; i++)
                {
                    if(b[rF][i] != 0)
                    {
                    d++;
                    }
                }
            }
            if(cS>cF)
            {
                for(int i = cF+1; i < cS; i++)
                {
                    if(b[rF][i] != 0)
                    { 
                    d++;
                    }
                }
            }
                if(d!=0)
                return false;
                
                else
                return true;
                

        }
        if(cS == cF)
        {
            if(rS<rF)
            {
                for(int i = rS+1; i < rF; i++)
                {
                    if(b[i][cF] != 0)
                    {
                    d++;
                    }
                }
            }                
            if(rS>rF)
            {
                for(int i = rF+1; i < rS; i++)
                {
                    if(b[i][cF] != 0)
                    {
                    d++;
                    }
                }
            }
                if(d!=0)
                return false;
                
                else
                return true;
                

        }
        
        if(Math.abs(rS-rF) == Math.abs(cS-cF))
        {
            for(int i = rS+1; i < rF; i++)
            {
                    for(int j = cS-1; j > cF; j--)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }
            for(int i = rS-1; i >rF; i--)
            {
                    for(int j = cS-1; j > cF; j--)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }
            for(int i = rS+1; i < rF; i++)
            {
                    for(int j = cS+1; j < cF; j++)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }
            for(int i = rS-1; i > rF; i--)
            {
                    for(int j = cS+1; j < cF; j++)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }

            return true;
        }            


        return false;
    }
    
 
     /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one checks if the proposed move for a Rook is legal.
    * A Rook is the second most powerfull piece in chess. 
    * It is allowed to move either vertically or horizontally as long as the path is not "barred" by another piece.
    * In other words it is not allowed to "jump" over other pieces.
    *
    * Because of that, after checking that the proposed move is indeed a horizontal or vertical, you need to check if that
    * row or column along which the rook is moving is clear (i.e. there are no other pieces along the way -- except for the actual finishing square). 
    * To that purpose, notice that there are two "types of lines" to check: row if the move is horizontal, and column if the move is vertical.
    * These should be checked differently.
    *
    * If an opponents piece is at the very end of the rook's move it simply captures it, so that is a legal move.
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */     
    static boolean isLegalRook(int[][] b, int rS, int cS, int rF, int cF) {
        
        int d=0;
        if(rS == rF)
        {
            if(cS<cF)
            {
                for(int i = cS+1; i < cF; i++)
                {
                    if(b[rF][i] != 0)
                    {
                    d++;
                    }
                }
            }
            if(cS>cF)
            {
                for(int i = cF+1; i < cS; i++)
                {
                    if(b[rF][i] != 0)
                    { 
                    d++;
                    }
                }
            }
                if(d!=0)
                return false;
                
                else
                return true;
                

        }
        if(cS == cF)
        {
            if(rS<rF)
            {
                for(int i = rS+1; i < rF; i++)
                {
                    if(b[i][cF] != 0)
                    {
                    d++;
                    }
                }
            }                
            if(rS>rF)
            {
                for(int i = rF+1; i < rS; i++)
                {
                    if(b[i][cF] != 0)
                    {
                    d++;
                    }
                }
            }
                if(d!=0)
                return false;
                
                else
                return true;
                

        }
    return false;
     
    }


    /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one checks if the proposed move for a Bishop is legal.
    * A Bishop is allowed to move only diagonally, as long as the path is not "barred" by another piece.
    * In other words it is not allowed to "jump" over other pieces.
    *
    * Because of that, after checking that the proposed move is indeed a diagonal, you need to check if that
    * diagonal is clear (except for the actual finishing square). To that purpose, notice that there are two "types" of
    * diagonals: ones that are parallel to the diagonal that goes from upper-left corner to the lower-right corner (call it 00_77 diagonal),
    * and others parallel to the diagonal from 70 corner (lower-left) to 07 corner (upper-right). These should be checked differently.
    *
    * If an opponents piece is at the very end of the bishop's move it simply captures it, so that is a legal move.
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */      
    static boolean isLegalBishop(int[][] b, int rS, int cS, int rF, int cF) {
    
        if(Math.abs(rS-rF) == Math.abs(cS-cF))
        {
            for(int i = rS+1; i < rF; i++)
            {
                    for(int j = cS-1; j > cF; j--)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }
            for(int i = rS-1; i >rF; i--)
            {
                    for(int j = cS-1; j > cF; j--)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }
            for(int i = rS+1; i < rF; i++)
            {
                    for(int j = cS+1; j < cF; j++)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }
            for(int i = rS-1; i > rF; i--)
            {
                    for(int j = cS+1; j < cF; j++)
                    {
                        if(b[i][j] != 0)
                        
                        return false;
                        
                    }
            }

            return true;
        }            
        return false;
    }
    /**
    * TO BE IMPLEMENTED BY YOU
    *
    * This one checks if the proposed move for a Rook is legal.
    * A Knight is the only chess piece that is allowed to "jump" over other pieces.
    * It moves in "L" shapes only: two squares in one direction and one square to the side. 
    *
    * For more details see Wikipedia article I linked to from the project description. 
    */  
    static boolean isLegalKnight(int[][] b, int rS, int cS, int rF, int cF) {
        if(((rF==rS-1)&&(cF==cS-2))||((rF==rS-2)&&(cF==cS-1))){
            return true;
        }
        if(((rF==rS+1)&&(cF==cS+2))||((rF==rS+2)&&(cF==cS+1))){
            return true;
        }
        if(((rF==rS-1)&&(cF==cS+2))||((rF==rS-2)&&(cF==cS+1))){
            return true;
        }
        if(((rF==rS+1)&&(cF==cS-2))||((rF==rS+2)&&(cF==cS-1))){
            return true;
        }
        return false;



        
    }



  //=======================================================
 
     
    /**
    * ALREADY IMPLEMENTED FOR YOU
    *
    * Alright, we are checking the "short castle", sort of.
    * Don't worry about it, just leave it as is.
    *
    */    
    static boolean checkCastle(int[][] b, int rS, int cS, int rF, int cF) {
        boolean ans = false;
        //Check for Black Short Castle:
        if (b[rS][cS]==18 && rS==0 && cS==4 && rF==0 && cF==6 && b[0][7]==16 && b[0][5]==0 && b[0][6]==0) {
            return true;
        }
        //Check for White Short Castle:
        if (b[rS][cS]==28 && rS==7 && cS==4 && rF==7 && cF==6 && b[7][7]==26 && b[7][5]==0 && b[7][6]==0) {
            return true;
        }
        
        return ans;
    }
       
    /**
    * ALREADY IMPLEMENTED FOR YOU
    *
    * Alright, we are checking the "short castle", sort of.
    * Don't worry about it, just leave it as is.
    *
    */    
    static int checkAndSetCastle(int[][] b, int rS, int cS, int rF, int cF) {
        
        //Check for Black Short Castle:
        if (b[rS][cS]==18 && rS==0 && cS==4 && rF==0 && cF==6 && b[0][7]==16 && b[0][5]==0 && b[0][6]==0) {
            return 1;
        }
        //Check for White Short Castle:
        if (b[rS][cS]==28 && rS==7 && cS==4 && rF==7 && cF==6 && b[7][7]==26 && b[7][5]==0 && b[7][6]==0) {
            //System.out.println("short castle");
            return 2;
        }
        return 0;
    }   
    
    //===============================================================================
    // DO NOT CHANGE CODE BELOW THIS LINE
    //===============================================================================     

    
    /**
    * This method creates and returns the array
    * with initial position for a chess game
    *
    * DON'T CHANGE IT!
    */
    private static int[][] initBoard() {
        int[][] b = {
            {16, 14, 15, 17, 18, 15, 14, 16},
            {11, 11, 11, 11, 11, 11, 11, 11},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {21, 21, 21, 21, 21, 21, 21, 21},
            {26, 24, 25, 27, 28, 25, 24, 26},};
        return b;
    }
    

    /**
    * This method draws a "spot" in a specified location
    *
    * DON'T CHANGE IT!
    */     
    private static void drawBoard(int[][] b) {
        StdDraw.picture(CENTER, CENTER, "pics/board2.png", 80, 80, 0);
        drawAllButtons();
        drawNumbers();
        drawImagesOnBoard(b);
    }
   
   
    /**
    * This method draws a "spot" in a specified location
    *
    * DON'T CHANGE IT!
    */
    private static void drawSpot(double x, double y) {
        float r = 1.0f;
        float g = 0.15f;
        float b = 0.0f;
        StdDraw.setPenColor(new Color(r, g, b, 0.08f));
        StdDraw.filledCircle(x, y, 4.5);
        StdDraw.setPenColor(new Color(r, g, b, 0.08f));
        StdDraw.filledCircle(x, y, 6.0);
        StdDraw.setPenColor(new Color(r, g, b, 0.05f));
        StdDraw.filledCircle(x, y, 6.5);
        StdDraw.setPenColor(new Color(r, g, b, 0.05f));
        StdDraw.filledCircle(x, y, 7);
        StdDraw.setPenColor(new Color(r, g, b, 0.05f));
        StdDraw.filledCircle(x, y, 7.5);
    }
    



    /**
    * This method picks a position for the "spot"
    * in the middle of the clicked cell and draws it.
    *
    * DON'T CHANGE IT!
    */
    private static void highlight(double mx, double my) {
        int xx = (int) (mx / 10);
        int yy = (int) (my / 10);
        drawSpot(5 + xx * 10, 5 + yy * 10);
    }
    
    /**
    * Gets i and j of the board array, given
    * x and y of the mouse click
    *
    * DON'T CHANGE IT!
    */
    private static int[] getIJ(double x, double y) {
        int xx = (int) (x / 10);
        int yy = (int) (y / 10);
        int[] ij = {7 - yy, xx};
        return ij;
    }




    /**
    * Calls all drawButtons commands
    *
    * DON'T CHANGE IT!
    */ 
    static void drawAllButtons() {
        drawButton(1, false);
        drawButton(2, false);
        drawButton(3, false);
        drawButton(4, false);
        drawButton(5, false);
    }

    /**
    * Draws a preset button in one of the preset locations
    * depending on bNumber input.
    * if pushed, it will draw a button in a preset "pushed" way.
    *
    * DON'T CHANGE IT!
    */ 
    static void drawButton(int bNumber, boolean pushed) {
        double x = -100.0, y = -100.0, w = 0.0, h = 0.0;
        String fileName = "pics/b1.png";
        String pushedFileName = "pics/b11.png";
        String name = "";
        if (bNumber == 1) {
            x = 15.0;
            y = -8;
            w = 10.0;
            h = 6.0;
            name = "Reset";
        } else if (bNumber == 2) {
            x = 30.0;
            y = -8;
            w = 10.0;
            h = 6.0;
            name = "Load 1";
        } else if (bNumber == 3) {
            x = 45.0;
            y = -8;
            w = 10.0;
            h = 6.0;
            name = "Load 2";
        } else if (bNumber == 4) {
            x = 60.0;
            y = -8;
            w = 10.0;
            h = 6.0;
            name = "print B";
        } else if (bNumber == 5) {
            x = 75.0;
            y = -8;
            w = 14.0;
            h = 6.0;
            name = "PLAY";
            fileName = "pics/b10.png";
            pushedFileName = "pics/b15.png";
        }
        if (!pushed) {
            StdDraw.picture(x, y, fileName, w, h);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(x, y, name);
        } else {
            StdDraw.picture(x, y, pushedFileName, w, h);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(x, y, name);
        }

    }


    /**
    * Detects if given click coordinates, result in a button pushed
    * (a set of preset buttons is checked), and if yes, the corresponding
    * method is called, usually on a provided board array.
    *
    * DON'T CHANGE IT!
    */ 
    static boolean detectButtonPush(double mx, double my, int[][] board) {
        if (my > -11.5 && my < -5.5) {
            if (mx <= 20 && mx >= 10) {
                drawButton(1, true);
                resetBoard(board);
            } else if (mx <= 35 && mx >= 25) {
                drawButton(2, true);
                loadPositionFromFile("pics/position1.txt", board);
            } else if (mx <= 50 && mx >= 40) {
                drawButton(3, true);
                loadPositionFromFile("pics/position2.txt", board);
            } else if (mx <= 65 && mx >= 55) {
                drawButton(4, true);
                printBoard(board);
            } else if (mx <= 80 && mx >= 70) {
                drawButton(5, true);
            } else {
                return false;
            }
            return true;
        }
        return false;
    }
    
    
    /**
    * This method reads in the position file (determined by the provided filename)
    * that has an encoding of a current position on the chess board
    * and loads it in the 2D integer array board (also provided as an input).
    * this method used method getBoardFromCharArray().
    *
    * DON'T CHANGE IT!
    */     
    private static void loadPositionFromFile(String filename, int[][] board){
        URL url = StdDraw.class.getResource(filename);
        File file = new File(url.getPath());
        Scanner sc = null;
        
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File "+filename+" not found!");
            return;
        }
        
        String line;
        char[][] chArr = new char[8][8];
        int row=0;
        try{
            while(sc.hasNext()){
                line=sc.nextLine();
                if (row>=8){
                    System.out.println("ERROR!!! File "+filename+" has too many lines!");
                    return;
                }
                //System.out.println(line);
                //System.out.print(line.length()+":");
                if (line.length()!=8){
                    System.out.println("ERROR!!! Line "+row+" in file "+filename+" has wrong length!");
                    return;
                }
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    //System.out.print(c+"_");
                    chArr[row][col]=c;
                }
                //System.out.println("");
                row++;
            }
        }catch (ArrayIndexOutOfBoundsException e){        
            System.out.println("File "+filename+" has incorrect format! Something's wrong with it!");
            return;
        }
        
        getBoardFromCharArray(chArr, board);
        
    }

    /**
    * This method draws letters and numbers on the board
    *
    * DON'T CHANGE IT!
    */  
    static void drawNumbers() {
        for (int i = 0; i < 8; i++) {
            StdDraw.text(-2, 5 + 10 * i, "" + (i + 1));
        }
        String[] a = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int i = 0; i < 8; i++) {
            StdDraw.text(5 + 10 * i, -2, a[i]);
        }
    }

    /**
    * This method draws current mode on the board
    *
    * DON'T CHANGE IT!
    */
    static void printMode(int mode) {
        String m = "";
        switch (mode) {
            case START:
                m = "START";
                break;
            case PICKED:
                m = "PICKED";
                break;
            case MOVING:
                m = "MOVING";
                break;
        }
        StdDraw.text(0.5, -7, "m:" + m);

    }

    /**
    * Prints whose turn it is.
    *
    * DON'T CHANGE IT!
    */     
    static void printTurn(int turn) {
        String m = "";
        switch (turn) {
            case 1:
                m = "BLACK MOVE";
                break;
            case 2:
                m = "WHITE MOVE";
                break;
        }
        StdDraw.text(40, 82, "(" + m + ")");

    }

    /**
    * This method checks some consequences after the move.
    * Like a pawn promotion.
    *
    * DON'T CHANGE IT!
    */    
    static void checkPostMove(int[][] b, int rF, int cF){
        if (b[rF][cF]==21 && rF==0) b[rF][cF]=27;
        if (b[rF][cF]==11 && rF==7) b[rF][cF]=17;
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);//set the "physical" size of the window in pixels (place this command on top!)
        //set "window":
        StdDraw.setXscale(MIN_X, MAX);
        StdDraw.setYscale(MIN_Y, MAX);

        StdDraw.enableDoubleBuffering();
        double mx = -100, my = -100, mx1 = -100, my1 = -100;
        int mode = START;
        int[][] board = initBoard();
        int turn = 2;
        boolean wasItBlackShortCastle=false;
        boolean wasItWhiteShortCastle=false;
        String movingImage = "";
        int movingStep = -1;
        int rowS = -1, colS = -1;
        int rowF = -1, colF = -1;

        while (true) {
            StdDraw.clear();
            //StdDraw.setPenColor(255, 255, 255);
            drawBoard(board);
            movingStep = drawMovingPiece(movingImage, rowS, colS, rowF, colF, movingStep);
            if (movingStep == -2) {
                movingStep = -1;
                board[rowF][colF] = board[rowS][colS] * -1;
                board[rowS][colS] = 0;
                checkPostMove(board, rowF, colF);
                if (wasItBlackShortCastle) { board[0][5]=board[0][7]; board[0][7]=0; wasItBlackShortCastle=false;}
                else if (wasItWhiteShortCastle) { board[7][5]=board[7][7]; board[7][7]=0; wasItWhiteShortCastle=false;}
                turn=(turn%2)+1;
                mode = START;
            }
            StdDraw.setPenColor(StdDraw.BLACK);
            printMode(mode);
            printTurn(turn);

            if (StdDraw.isMousePressed()) {
                printMode(mode);
                if (mode == START) {  //if there is a mouse click, process it and follow the main game logic

                    mx = StdDraw.mouseX(); //x-coordinate of the mouse
                    my = StdDraw.mouseY(); //y-coordinate of the mose
                    if (detectButtonPush(mx, my, board)) {
                        //System.out.println("pushed");
                        StdDraw.show();
                        StdDraw.pause(100);
                        continue;
                    }
                    if (mx <= 0 || my <= 0 || mx >= 80 || my >= 80) {
                        continue;
                    }
                    //System.out.println(mx+" "+my);
                    int[] rowCol = getIJ(mx, my);
                    rowS = rowCol[0];
                    colS = rowCol[1];
                    if (!(rowS >= 0 && rowS < 8 && colS >= 0 && colS < 8)) {
                        continue;
                    }
                    //System.out.println(board[rowS][colS]);
                    if (board[rowS][colS] == 0) {
                        continue;
                    }
                    //Check for correct turn:
                    if (board[rowS][colS]/10 != turn) {
                        continue;
                    }
                    mode = PICKED;
                    movingImage = "pics/" + board[rowS][colS] + ".png";
                    highlight(mx, my);
                    StdDraw.show();
                    StdDraw.pause(100); //Pauses for a bit after a mouse click, to avoid multiple mouse events. DO NOT REMOVING!mx = StdDraw.mouseX();
                } else if (mode == PICKED) {
                    mx1 = StdDraw.mouseX(); //x-coordinate of the mouse
                    my1 = StdDraw.mouseY(); //y-coordinate of the mose
                    if (mx1 <= 0 || my1 <= 0 || mx1 >= 80 || my1 >= 80) {
                        continue;
                    }
                    //System.out.println(mx+" "+my);
                    int[] rowCol = getIJ(mx1, my1);
                    rowF = rowCol[0];
                    colF = rowCol[1];
                    if (!(rowF >= 0 && rowF < 8 && colF >= 0 && colF < 8)) {
                        continue;
                    }
                    //System.out.println(board[rowF][colF]);
                    
                    //If the target has the figure of the same side, cancel it (go back to START mode):
                    if (board[rowF][colF] / 10 == board[rowS][colS] / 10) {
                        StdDraw.pause(200);
                        mode = START;
                        continue;
                    }
                    
                    if (!isMoveLegal(board, rowS, colS, rowF, colF)){
                        StdDraw.pause(200);
                        mode = START;
                        continue;
                    }
                    
                    if (board[rowS][colS]%10 == 8) {
                        if (checkAndSetCastle(board, rowS, colS, rowF, colF)==1){
                            wasItBlackShortCastle=true;
                        }else if (checkAndSetCastle(board, rowS, colS, rowF, colF)==2){
                            wasItWhiteShortCastle=true;
                        }
                    }
                    board[rowS][colS] *= -1;
                    mode = MOVING;
                    movingStep = 0;
                    StdDraw.pause(200);
                } else if (mode == MOVING) {               
                    mx1 = StdDraw.mouseX(); //x-coordinate of the mouse
                    my1 = StdDraw.mouseY(); //y-coordinate of the mose
                    StdDraw.pause(200);
                }
            }
            if (mode == PICKED) {
                highlight(mx, my);
            }
            StdDraw.show();
            StdDraw.pause(30);
        }

    }

}