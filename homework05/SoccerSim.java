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
   private static final double SECONDS_PER_TWELVE_HOURS = 43200;

   private final double MAX_Timer_SLICE_IN_SECONDS  = 1800.00;

   private final double DEFAULT_Timer_SLICE_IN_SECONDS = 60.0;



   private static final double HALF_WIDTH_OF_FIELD = 1000;

   private static final double HALF_HEIGHT_OF_FIELD = 1000;


   private static Ball[] ballStorage;

   private static int numberOfBalls;


   private static double timeSlice;

   private static boolean collusion = false;

   private static boolean allBallsStopped = false;
   private static Timer time;



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

     if( 5 > args.length ) {

        System.out.println( "Please start over and enter at least two sets of ball arguments and a Timer slice on the command line." );

        System.exit( 1 );

      }
     if(1 != args.length%4){
      System.out.println("Illegal number of arguments");

      System.exit( 1 );
     }

      time = new Timer();
      timeSlice = time.validateTimeSliceArg( args[ args.length - 1 ] );
      numberOfBalls =  args.length/4;
      ballStorage = new Ball[numberOfBalls+1];


    for(int i = 0; i < ballStorage.length-1; i++){

      Ball ball = new Ball();

      ball.validateXPosition(args[0+4*i]);
      System.out.println("validateXPosition at "+i + " " + ball.getxPosition());
      ball.validateYPosition(args[1+4*i]);
      System.out.println("validateYPosition at "+i + " "+ ball.getyPosition());
      ball.validateXVelocity(args[2+4*i]);
      System.out.println("validateXVelocity at "+i + " "+ ball.getxVelocity());
      ball.validateYVelocity(args[3+4*i]);
      System.out.println("validateYVelocity at "+i + " "+ ball.getyVelocity());

      ball.setxPosition();
      System.out.println("setxPosition at "+i+" "+ball.getxPosition());
      ball.setyPosition();
      System.out.println("setyPosition at "+i+" "+ball.getyPosition());
      ball.setxVelocity();
      System.out.println("setxVelocity at "+i+" "+ball.getxVelocity());
      ball.setyVelocity();
      System.out.println("setyVelocity at "+i+" "+ball.getyVelocity());

      ballStorage[i] = ball;
   }
      Ball pole = new Ball();
      pole.validateXPosition("0");
      pole.validateYPosition("0");
      pole.validateXVelocity("0");
      pole.validateYVelocity("0");
      pole.setxPosition();
      pole.setyPosition();
      pole.setxVelocity();
      pole.setyVelocity();
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

      SoccerSim soccerSim = new SoccerSim();

      soccerSim.handleInitialArguments( args );

      /*
      int collusionItemOne = -1;
      int collusionItemTwo = -1;
      System.out.println("\n Ball number "+0+": \n"+ballStorage[0].toString());
      System.out.println("\n Ball number "+1+": \n"+ballStorage[1].toString());
      System.out.println("\n Ball number "+2+": \n"+ballStorage[2].toString());

      while( time.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ){
      for(int r = 0; r < timeSlice; r++){
      allBallsStopped = true;
       for(int i = 0; i < numberOfBalls; i++){
          ballStorage[i].accelerationMovement();
         if(ballStorage[i].getxVelocity() != 0 || ballStorage[i].getyVelocity() != 0){
           allBallsStopped = false;
           }
       }
      for(int p = 0; p < numberOfBalls; p++){
        for(int j = p; j < numberOfBalls; j++){
          if(Math.abs(ballStorage[p].getxPosition()) - Math.abs(ballStorage[j].getxPosition()) < 8.9 && Math.abs(ballStorage[p].getyPosition()) - Math.abs(ballStorage[j].getyPosition()) < 8.9){
            collusionItemOne = p;
            collusionItemTwo = j;
            collusion = true;
          }
        }
      }

      time.tick();
      if(collusion){
        System.out.println("Collusion with ball: "+ collusionItemOne+ "at location "+ballStorage[collusionItemOne].getxPosition()+ " for x and for y at "+ballStorage[collusionItemOne].getyPosition()+"with the ball: "+ collusionItemOne+ "at location "+ballStorage[collusionItemTwo].getxPosition()+ " for x and for y at "+ballStorage[collusionItemTwo].getxPosition());
        System.exit(0);
      }
      if(allBallsStopped){
        System.out.println("NO COLLISION IS POSSIBLE"+time.toString());
        System.exit(0);
      }

     }
   }
     

      System.exit( 0 );
*/
   }

}
