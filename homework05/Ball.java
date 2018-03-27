/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the BallSolver class
 *  @author       :  Kevin Solis 
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the BallSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {
  /**
   *  Class field definitions go here
   */
   private static double userInitialXPosition;
   private static double userInitialYPosition;
   private static double userInitialXVelocity;
   private static double userInitialYVelocity;
   private static double velocity;

  /**
   *  Constructor
   */
   public Ball() {
     xPosition = userInitialXPosition;
     yPosition = userInitialYPosition;
     xVelocity = userInitialXVelocity;
     yVelocity = userInitialYVelocity;
   }

   /**
   *  Stimulating acceleration of ball
   */
   public static Acceleration(){
    xVelocity = xVelocity * 0.99;
    yVelocity = yVelocity * 0.99;
   }

   /**
   *  return programs to get values
   */

   public double getxVelocity(){
    return xVelocity;
   }

   public double getyVelocity(){
    return yVelocity;
   }

   public double returnxPosition(){
    return xPosition;
   }

   public double returnyPosition(){
    return yPosition;
   }

   public double actualVelocity(){
    velocity = Math.sqrt()
   }


 /**
  *  Method to validate the optional epsilon slice argument
  *  @param  argValue  String from the main program's args[2] input
  *  @return double-precision value of the argument or 0.1 if no epsilon is given
  *  @throws IllegalArgumentException
  */
  public double validateEpsilonArg( String argValue ) {
    //testing value before passing to arg
    double arg = Double.parseDouble( argValue );
    if ( argValue == null  || argValue == "" ) {
      return DEFAULT_EPSILON_VALUE;
    }
    else if ( arg < 0 || arg > 3.0 ) {
      throw new IllegalArgumentException();
    } 
      return arg;
  } 



  /**
   *  Method to return a String representation of this Ball
   *  @return String value of the current Ball
   *  Note: method for calculating Hours:Minutes:Seconds from https://stackoverflow.com/questions/8999638/getting-data-in-seconds-want-to-calculate-hours-minutes-seconds
   */
   public String toString() {
      return (int) timeInSeconds / 3600 + ":" + (int) (timeInSeconds / 60) % 60 + ":" + (int) timeInSeconds % 60;
    }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nBall CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new Ball: " );
      Ball Ball = new Ball();
      System.out.println("New Ball created: " + Ball.toString() );
      System.out.println("Testing basic methods tick(), toString, getMinuteHandAngle, getHourHandAngle, and getHandAngle" );
      timeSlice = 11;
      while ( Ball.getTotalSeconds() <= SECONDS_IN_TWELVE_HOURS ) {
        System.out.println("Minute hand angle is at " + Ball.getMinuteHandAngle() );
        System.out.println("Hour hand angle is at " + Ball.getHourHandAngle() );
        System.out.println("The time is: " + Ball.toString() );
        System.out.println("Angle between is" + Ball.getHandAngle() );
        Ball.tick();
        System.out.println( "\n" );
      }

      System.out.println("Tests for validateAngleArg()" );
      //extremes and particular values
      try { Ball.validateAngleArg("45"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 45 degrees"); }
      try { Ball.validateAngleArg("0"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 0 degrees"); }
      try { Ball.validateAngleArg("360"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 360 degrees"); }
      //negatives
      try { Ball.validateAngleArg("-80"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for -80 degrees"); }
      //decimals
      double random;
      for(int i = 0; i<4; i++){
        random = Math.random() * 1000.0;
        try { Ball.validateAngleArg("K2"); }
        catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for" +random+  "degrees"); }
      }
      //letters
      try { Ball.validateAngleArg("K2"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for K2 degrees"); }
      try { Ball.validateAngleArg("XYZ"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for XYZ degrees"); }
      System.out.println( "\n" );
      
      System.out.println("Tests for validateTimeSliceArg()" );
      //extremes
      System.out.println("0 seconds should be -1: " + Ball.validateTimeSliceArg( "0" ));
      System.out.println("1800 seconds should be 1800: " + Ball.validateTimeSliceArg( "1800" ));
      //regular numbers
      System.out.println("85 seconds should be 61: " + Ball.validateTimeSliceArg("85"));
      System.out.println("2 second should be 2: " + Ball.validateTimeSliceArg("2"));
      //a decinmal
      System.out.println("0.321 seconds should be 0.00001: " + Ball.validateTimeSliceArg("0.123"));
      //none at all
      System.out.println("No seconds should be 60: " + Ball.validateTimeSliceArg("60"));
      System.out.println("\n");

      System.out.println("Tests for validateEpsilonArg()");
      //specific values
      try { Ball.validateEpsilonArg("0"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 0"); }
      try { Ball.validateEpsilonArg("-1"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of -1"); }
      //some decimals
      try { Ball.validateEpsilonArg("1.213123"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 1.213123"); }
      try { Ball.validateEpsilonArg("1.82"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 1.82"); }
      //out of range
      try { Ball.validateEpsilonArg("3"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 3"); }
      try { Ball.validateEpsilonArg("3.1"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 3.1"); }
      //letters
      try { Ball.validateEpsilonArg("ABC"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of ABC"); }
      //BIG number
      try { Ball.validateEpsilonArg("1712893172B3"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 1712893172B3"); }
    }
  } 
