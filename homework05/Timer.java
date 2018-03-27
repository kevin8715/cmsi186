/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a class defining methods for the TimerSolver class
 *  @author       :  Kevin Solis 
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the TimerSolver class
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

public class Timer {
  /**
   *  Class field definitions go here
   */
   private static final double SECONDS_PER_TWELVE_HOURS = 43200;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double DEFAULT_EPSILON_VALUE = 0.1;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00833334;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   private static double timeSlice;
   private static double inputedTimeSlice;
   private static double minuteHandAngle = 0;
   private static double hourHandAngle = 0;
   double timeInSeconds = 0;

  /**
   *  Constructor
   */
   public Timer() {
     timeSlice = inputedTimeSlice;
     timeInSeconds = 0;
   }

 /**
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current Timer tick
   *  @throws IllegalArgumentException
   */
   public double tick() {
     if ( !(timeSlice > 0) ) {
      timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
     }
     timeInSeconds += timeSlice;
     return timeInSeconds;
   }


/**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main program's args[0] input
   *  @return  double-precision value of the argument
   *  @throws  IllegalArgumentException
   */
   public double validateAngleArg( String argValue ) {
      double arg = Double.parseDouble( argValue );
      if ( arg <= 0 || arg >= MAXIMUM_DEGREE_VALUE ) {
        throw new IllegalArgumentException();
      }
      return arg;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main program's args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      if ( argValue == "" || argValue == null ) {
        return DEFAULT_TIME_SLICE_IN_SECONDS;
      }
      double arg = Double.parseDouble( argValue );
      if ( arg <= 0 || arg > 1800 ) {
        return INVALID_ARGUMENT_VALUE;
      } else {
        inputedTimeSlice = arg;
        return inputedTimeSlice;
      }
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
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
     hourHandAngle = (timeInSeconds * HOUR_HAND_DEGREES_PER_SECOND) % 360;
     return hourHandAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
     minuteHandAngle = (timeInSeconds * MINUTE_HAND_DEGREES_PER_SECOND) % 360;
     return minuteHandAngle;
   }

   public void handAngles(){
    getMinuteHandAngle();
    getHourHandAngle();
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
     handAngles();
     return Math.abs( hourHandAngle - minuteHandAngle  );
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return timeInSeconds;
   }

  /**
   *  Method to return a String representation of this Timer
   *  @return String value of the current Timer
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

      System.out.println( "\nTimer CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new Timer: " );
      Timer Timer = new Timer();
      System.out.println("New Timer created: " + Timer.toString() );
      System.out.println("Testing basic methods tick(), toString, getMinuteHandAngle, getHourHandAngle, and getHandAngle" );
      timeSlice = 11;
      while ( Timer.getTotalSeconds() <= SECONDS_IN_TWELVE_HOURS ) {
        System.out.println("Minute hand angle is at " + Timer.getMinuteHandAngle() );
        System.out.println("Hour hand angle is at " + Timer.getHourHandAngle() );
        System.out.println("The time is: " + Timer.toString() );
        System.out.println("Angle between is" + Timer.getHandAngle() );
        Timer.tick();
        System.out.println( "\n" );
      }

      System.out.println("Tests for validateAngleArg()" );
      //extremes and particular values
      try { Timer.validateAngleArg("45"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 45 degrees"); }
      try { Timer.validateAngleArg("0"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 0 degrees"); }
      try { Timer.validateAngleArg("360"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 360 degrees"); }
      //negatives
      try { Timer.validateAngleArg("-80"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for -80 degrees"); }
      //decimals
      double random;
      for(int i = 0; i<4; i++){
        random = Math.random() * 1000.0;
        try { Timer.validateAngleArg("K2"); }
        catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for" +random+  "degrees"); }
      }
      //letters
      try { Timer.validateAngleArg("K2"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for K2 degrees"); }
      try { Timer.validateAngleArg("XYZ"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for XYZ degrees"); }
      System.out.println( "\n" );
      
      System.out.println("Tests for validateTimeSliceArg()" );
      //extremes
      System.out.println("0 seconds should be -1: " + Timer.validateTimeSliceArg( "0" ));
      System.out.println("1800 seconds should be 1800: " + Timer.validateTimeSliceArg( "1800" ));
      //regular numbers
      System.out.println("85 seconds should be 61: " + Timer.validateTimeSliceArg("85"));
      System.out.println("2 second should be 2: " + Timer.validateTimeSliceArg("2"));
      //a decinmal
      System.out.println("0.321 seconds should be 0.00001: " + Timer.validateTimeSliceArg("0.123"));
      //none at all
      System.out.println("No seconds should be 60: " + Timer.validateTimeSliceArg("60"));
      System.out.println("\n");

      System.out.println("Tests for validateEpsilonArg()");
      //specific values
      try { Timer.validateEpsilonArg("0"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 0"); }
      try { Timer.validateEpsilonArg("-1"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of -1"); }
      //some decimals
      try { Timer.validateEpsilonArg("1.213123"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 1.213123"); }
      try { Timer.validateEpsilonArg("1.82"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 1.82"); }
      //out of range
      try { Timer.validateEpsilonArg("3"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 3"); }
      try { Timer.validateEpsilonArg("3.1"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 3.1"); }
      //letters
      try { Timer.validateEpsilonArg("ABC"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of ABC"); }
      //BIG number
      try { Timer.validateEpsilonArg("1712893172B3"); }
      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for an epsilon of 1712893172B3"); }
    }
  } 