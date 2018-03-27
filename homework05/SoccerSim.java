/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  @see
 *  @author       :  Kevin Solis
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
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


public class SoccerSim {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private final double DEFAULT_EPSILON_VALUE      = 0.1;      // small value for double-precision comparisons

   private static final double SECONDS_PER_TWELVE_HOURS = 43200;
   private static final double DEGREES_PER_ROTATION = 360.0;

   private static double[] checkedTimeArgs;
   private static double inputedTimeSlice;
   private static double firstAngleShortcut;
   private static double secondAngleShortcut;
   private static final double HALF_WIDTH_OF_FIELD = 200;
   private static final double HALF_HEIGHT_OF_FIELD = 200;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public SoccerSim() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   Note to self: create pole object
   */
   public void handleInitialArguments( String args[] ) {
    Ball pole = new Ball()

     if( 0 == args.length ) {
        System.out.println( "Please start over and enter at least two sets of ball argument on the command line." );
        System.exit( 1 );
      }
      Time Time = new Time();
      checkedTimeArgs = new double[3];
      if ( args.length == 3 ) {
        checkedTimeArgs[ 0 ] = Time.( args[ 0 ] );
        checkedTimeArgs[ 1 ] = Time.validateTimeSliceArg( args[ 1 ] );
        checkedTimeArgs[ 2 ] = Time.validateEpsilonArg( args[ 2 ] );
      }
       if ( args.length == 2 ) {
          checkedTimeArgs[ 0 ] = Time.validateAngleArg( args[ 0 ] );
          checkedTimeArgs[ 1 ] = Time.validateTimeSliceArg( args[ 1 ] );
          checkedTimeArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
        }
          else if ( args.length == 1 ) {
            checkedTimeArgs[ 0 ] = Time.validateAngleArg( args[ 0 ] );
            checkedTimeArgs[ 1 ] = DEFAULT_TIME_SLICE_IN_SECONDS;
            checkedTimeArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
          } 

      for(int i = 0; i < numberOfBalls; i++){
        Ball ball= new Ball();
        ballStorage = new Ball(numberOfBalls);
      checkedTimeArgs = new double[4];
      if ( args.length == 4 ) {
        checkedTimeArgs[ 0 ] = Time.( args[ 0 ] );
        checkedTimeArgs[ 1 ] = Time.validateTimeSliceArg( args[ 1 ] );
        checkedTimeArgs[ 2 ] = Time.validateEpsilonArg( args[ 2 ] );
      }
       if ( args.length == 3 ) {
          checkedTimeArgs[ 0 ] = Time.validateAngleArg( args[ 0 ] );
          checkedTimeArgs[ 1 ] = Time.validateTimeSliceArg( args[ 1 ] );
          checkedTimeArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
        }
          else if ( args.length == 2 ) {
            checkedTimeArgs[ 0 ] = Time.validateAngleArg( args[ 0 ] );
            checkedTimeArgs[ 1 ] = DEFAULT_TIME_SLICE_IN_SECONDS;
            checkedTimeArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
          } 

      inputedTimeSlice = checkedTimeArgs[ 1 ];
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
      SoccerSim SoccerSim = new SoccerSim();
      SoccerSim.handleInitialArguments( args );
      Time Time = new Time();
      firstAngleShortcut = Math.abs((Time.getMinuteHandAngle() -DEGREES_PER_ROTATION  ) - Time.getHourHandAngle() ); 
      secondAngleShortcut  = Math.abs((Time.getHourHandAngle() - DEGREES_PER_ROTATION  ) - Time.getMinuteHandAngle() );
      System.out.println( "\nThe simulation is running with an angle of " + checkedTimeArgs[ 0 ] + " degrees and a time slice of " + checkedTimeArgs[ 1 ] + " seconds." );
      System.out.println("\n");
      //check for when the angle happens with simulation running
      while( Time.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ) {
         if ( Time.getHandAngle() > ( checkedTimeArgs[ 0 ] - ( checkedTimeArgs[ 0 ] * checkedTimeArgs[ 2 ] ) ) && Time.getHandAngle() < ( checkedTimeArgs[ 0 ] + ( checkedTimeArgs[ 0 ] * checkedTimeArgs[ 2 ] ) ) ) {
          System.out.print( "Sought angle of " + checkedTimeArgs[ 0 ] + " degrees at time: " + Time.toString() + "\n" ); 
        }
         if ( Time.getHandAngle() > ( firstAngleShortcut - ( firstAngleShortcut * checkedTimeArgs[ 2 ] ) ) && Time.getHandAngle() < ( firstAngleShortcut + ( firstAngleShortcut * checkedTimeArgs[ 2 ] ) ) ) {
          System.out.print( "Sought angle of " + checkedTimeArgs[ 0 ] + " degrees at time: " + Time.toString() + "\n" );
        }
         if ( Time.getHandAngle() > ( secondAngleShortcut - ( secondAngleShortcut * checkedTimeArgs[ 2 ] ) ) && Time.getHandAngle() < ( secondAngleShortcut + ( secondAngleShortcut * checkedTimeArgs[ 2 ] ) ) ) {
          System.out.print( "Sought angle of " + checkedTimeArgs[ 0 ] + " degrees at time: " + Time.toString() + "\n" );
        } Time.tick();
      }
      System.exit( 0 );
   }
}