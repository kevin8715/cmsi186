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

 *  Revision History

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

   private static final double INVALID_ARGUMENT_VALUE = -1.0;



   private static double timeSlice;

   private static double inputedTimeSlice;

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

   *  @see  http://bjohnson.lmu.build/cmsi186web/homework05.html

   *  be sure to make LOTS of tests!!

   *  remember you are trying to BREAK your code, not just prove it works!

   */

   public static void main( String args[] ) {



      System.out.println( "\nTime CLASS TESTER PROGRAM\n" +

                          "--------------------------\n" );

      System.out.println( "  Creating a new Time: " );

      Timer Time = new Timer();

      System.out.println("New Time created: " + Time.toString() );

      System.out.println("Testing basic methods tick(), toString, getMinuteHandAngle, getHourHandAngle, and getHandAngle" );

      timeSlice = 11;

      while ( Time.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS) {

        System.out.println(Time.toString());
        System.out.println(Time.getTotalSeconds());

        Time.tick();

        System.out.println( "\n" );

      }


      

      System.out.println("Tests for validateTimeSliceArg()" );

      //extremes

      System.out.println("0 seconds should be -1: " + Time.validateTimeSliceArg( "0" ));

      System.out.println("1800 seconds should be 1800: " + Time.validateTimeSliceArg( "1800" ));

      //regular numbers

      System.out.println("85 seconds should be 61: " + Time.validateTimeSliceArg("85"));

      System.out.println("2 second should be 2: " + Time.validateTimeSliceArg("2"));

      //a decinmal

      System.out.println("0.321 seconds should be 0.00001: " + Time.validateTimeSliceArg("0.123"));

      //none at all

      System.out.println("No seconds should be 60: " + Time.validateTimeSliceArg("60"));

      System.out.println("\n");


    }

  } 
