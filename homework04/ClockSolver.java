/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Kevin Solis
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
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


public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private final double DEFAULT_EPSILON_VALUE      = 0.1;      // small value for double-precision comparisons

   private static final double SECONDS_PER_TWELVE_HOURS = 43200;
   private static final double DEGREES_PER_ROTATION = 360.0;

   private static double[] checkedClockArgs;
   private static double inputedTimeSlice;
   private static double firstAngleShortcut;
   private static double secondAngleShortcut;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     if( 0 == args.length ) {
        System.out.println( "Please start over and enter at least one argument on the command line." );
        System.exit( 1 );
      }
      Clock clock = new Clock();
      checkedClockArgs = new double[3];
      if ( args.length == 3 ) {
        checkedClockArgs[ 0 ] = clock.validateAngleArg( args[ 0 ] );
        checkedClockArgs[ 1 ] = clock.validateTimeSliceArg( args[ 1 ] );
        checkedClockArgs[ 2 ] = clock.validateEpsilonArg( args[ 2 ] );
      }
       if ( args.length == 2 ) {
          checkedClockArgs[ 0 ] = clock.validateAngleArg( args[ 0 ] );
          checkedClockArgs[ 1 ] = clock.validateTimeSliceArg( args[ 1 ] );
          checkedClockArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
        }
          else if ( args.length == 1 ) {
            checkedClockArgs[ 0 ] = clock.validateAngleArg( args[ 0 ] );
            checkedClockArgs[ 1 ] = DEFAULT_TIME_SLICE_IN_SECONDS;
            checkedClockArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
          } 
      inputedTimeSlice = checkedClockArgs[ 1 ];
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   *                args[2] is the epsilon value; this is optional and defaults to 0.1        
   */       
   public static void main( String args[] ) {
      ClockSolver clockSolver = new ClockSolver();
      clockSolver.handleInitialArguments( args );
      Clock clock = new Clock();
      firstAngleShortcut = Math.abs((clock.getMinuteHandAngle() -DEGREES_PER_ROTATION  ) - clock.getHourHandAngle() ); 
      secondAngleShortcut  = Math.abs((clock.getHourHandAngle() - DEGREES_PER_ROTATION  ) - clock.getMinuteHandAngle() );
      System.out.println( "\nThe simulation is running with an angle of " + checkedClockArgs[ 0 ] + " degrees and a time slice of " + checkedClockArgs[ 1 ] + " seconds." );
      System.out.println("\n");
      //check for when the angle happens with simulation running
      while( clock.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ) {
         if ( clock.getHandAngle() > ( checkedClockArgs[ 0 ] - ( checkedClockArgs[ 0 ] * checkedClockArgs[ 2 ] ) ) && clock.getHandAngle() < ( checkedClockArgs[ 0 ] + ( checkedClockArgs[ 0 ] * checkedClockArgs[ 2 ] ) ) ) {
          System.out.print( "Sought angle of " + checkedClockArgs[ 0 ] + " degrees at time: " + clock.toString() + "\n" ); 
        }
         if ( clock.getHandAngle() > ( firstAngleShortcut - ( firstAngleShortcut * checkedClockArgs[ 2 ] ) ) && clock.getHandAngle() < ( firstAngleShortcut + ( firstAngleShortcut * checkedClockArgs[ 2 ] ) ) ) {
          System.out.print( "Sought angle of " + checkedClockArgs[ 0 ] + " degrees at time: " + clock.toString() + "\n" );
        }
         if ( clock.getHandAngle() > ( secondAngleShortcut - ( secondAngleShortcut * checkedClockArgs[ 2 ] ) ) && clock.getHandAngle() < ( secondAngleShortcut + ( secondAngleShortcut * checkedClockArgs[ 2 ] ) ) ) {
          System.out.print( "Sought angle of " + checkedClockArgs[ 0 ] + " degrees at time: " + clock.toString() + "\n" );
        } clock.tick();
      }
      System.exit( 0 );
   }
}
