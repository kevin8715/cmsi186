/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 *  File name     :  SoccerSim.java

 *  Purpose       :  The main program for the SoccerSim class

 *  @see

 *  @author       :  Kevin Solis

 *  Date written  :  2017-03-26

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
   *
   *  Class field definintions go here
   * 
   */

   private final double MAX_Timer_SLICE_IN_SECONDS  = 1800.00;

   private final double DEFAULT_Timer_SLICE_IN_SECONDS = 60.0;



   private static final double HALF_WIDTH_OF_FIELD = 200;

   private static final double HALF_HEIGHT_OF_FIELD = 200;


   private static final Ball[] ballStorage;


   private static double timeSlice;

   private static boolean collusion = false;

   private static boolean allBallsStopped = false;



  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */

   public SoccerSim() {

      super();

   }



  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulatiom
   */

   public void handleInitialArguments( String args[] ) {

     if( 9 <= args.length ) {

        System.out.println( "Please start over and enter at least two sets of ball arguments and a Timer slice on the command line." );

        System.exit( 1 );

      }
     if(1 == args.length%4){
      System.out.println("Illegal number of arguments");

      System.exit( 1 );
     }

      Timer time = new Timer();
      timeSlice = time.validateTimerSliceArg( args[ args.length-1 ] );
      int numberOfBalls =  args.length/4;
      ballStorage = new Ball[numberOfBalls+1];


    for(int i = 0; i < numberOfBalls-1; i++){

      Ball ball = new Ball();

      ball.validateXPosition(args[0+4*i]);
      ball.validateYPosition(args[1+4*i]);
      ball.validateXVelocity(args[2+4*i]);
      ball.validateYVelocity(args[3+4*i]);

      ballStorage[i] = ball;
   }
      Ball pole = new ball();
      ball.validateXPosition(0);
      ball.validateYPosition(0);
      ball.validateXVelocity(0);
      ball.validateYVelocity(0);
      ballStorage[ballStorage.length-1]= pole;
 }



  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the Timer slice; this is optional and defaults to 60 seconds
   *                args[2] is the epsilon value; this is optional and defaults to 0.1        
   */       

   public static void main( String args[] ) {

      SoccerSim SoccerSim = new SoccerSim();

      SoccerSim.handleInitialArguments( args );

      Timer time = new Timer();

      int collusionItemOne;
      int collusionItemTwo;

      while( time.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ){
      allBallsStopped = true;
      for(int i = 0; i < numberOfBalls; i++){
        System.out.println(ballStorage[i]);
        ballStorage[i].accelerationMovement();
        if(ballStorage[i].getxVelocity() != 0 || ballStorage[i].getyVelocity()) != 0){
          allBallsStopped = false;
          }
      }
      for(int i = 0; i < numberOfBalls; i++){
        for(int j = i; j < numberOfBalls; j++)]
          if(Math.abs(Math.abs(ballStorage[i].getxPosition()) - Math.abs(ballStorage[j].getxPosition()) < 8.9) && Math.abs(Math.abs(ballStorage[i].getyPosition()) - Math.abs(ballStorage[j].getyPosition()) < 8.9)
            collusionItemTwo = j;
            collusion = true;
          }
      }

        clock.tick();
      }
      if(collusion){
        System.out.println("Collusion at location: "+ballStorage[collusionLocation].getxPosition()+ " for x and for y at "+ballStorage[collusionLocation].getxPosition());
        System.exit(0);
      }
      if(allBallsStopped){
        System.out.println("All balls are stopped."+time.toString());
        System.exit(0);
      }


     

      System.exit( 0 );

   }

}
