/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Kevin Solis
 *  Date          :  2018-02-20
 *  Description   :  Program to
 *  Notes         :  MainProgLoopDemo.java was used as a template for this program
 *  Warnings      :  None
 *  Exceptions    :  Exception when user input is not in range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {
  
    public static void optionsDisplay() {
      System.out.println( "\n   Welcome to a Die Simulator. Please pick one of the following options by returning an options corresponding number.\n" );
      System.out.println( "1. ROLL ALL THE DICE");
      System.out.println( "2. ROLL A SINGLE DIE");
      System.out.println( "3. CALCULATE THE SCORE FOR THIS SET");
      System.out.println( "4. SAVE THIS SCORE AS HIGH SCORE");
      System.out.println( "5. DISPLAY THE HIGH SCORE");
      System.out.println( "6. ENTER 6 TO QUIT THE PROGRAM");
    }


    public static void main( String args[] ) {
      int diceTotal = 0;
      int highScore = 0;
      DiceSet diceSet = new DiceSet( Integer.parseInt(args[0]), Integer.parseInt(args[1]) );
      System.out.println( "\n   Welcome to HighRoll.\n" );
      HighRoll.optionsDisplay();
  
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();
            if( 0 == inputLine.length() ) {
               System.out.println("No text here. Please enter some below:");
            }
            System.out.println( "You typed: " + inputLine );
            if( '1' == inputLine.charAt(0) ) {
              diceSet.roll();
              System.out.println( "You rolled " + diceSet.toString() );
            }
            if( '2' == inputLine.charAt(0) ) {
               System.out.println( "Please type the location number of the die you would like to roll: " );
               inputLine = input.readLine();
               int dieIndex = Integer.parseInt( inputLine ) - 1;
               diceSet.rollIndividual( dieIndex );
               System.out.println( "You rolled a " + diceSet.getIndividual( dieIndex ));
            }
            if( '3' == inputLine.charAt(0) ) {
               diceTotal = diceSet.sum();
               System.out.println( "Your total score is " + diceTotal );
            }
            if( '4' == inputLine.charAt(0) ) {
               highScore = diceTotal;
               System.out.println( "Your high score has been saved." );
            }
            if( '5' == inputLine.charAt(0) ) {
               System.out.println( "The current high score is " + highScore );
            }
            if( '6' == inputLine.charAt(0) ) {
               System.out.println( "Thanks for playing" );
               break;
            }
            HighRoll.optionsDisplay();
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
        }
    }
}
