
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

   private double userInitialXPosition;

   private double userInitialYPosition;

   private double userInitialXVelocity;

   private double userInitialYVelocity;

   private double velocity;

   private static final double HALF_WIDTH_OF_FIELD = 1000;

   private static final double HALF_HEIGHT_OF_FIELD = 1000;

   private double xPosition = 0;

   private double yPosition = 0;

   private double xVelocity = 0;

   private double yVelocity = 0;



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

   public void accelerationMovement(){
    xPosition = xPosition + xVelocity;
    yPosition = yPosition + yVelocity;

    xVelocity = xVelocity * 0.99;
    yVelocity = yVelocity * 0.99;
    if(xVelocity < (0.08333333))
      xVelocity = 0;
    if(yVelocity < (0.08333333))
      yVelocity = 0;


   }



   /**

   *  return programs to get values

   */



   public double getxVelocity(){

    return xVelocity;

   }

  public void setxVelocity(){
    xVelocity = userInitialXVelocity;
   }



   public double getyVelocity(){

    return yVelocity;

   }


   public void setyVelocity(){
    yVelocity = userInitialYVelocity;
   }



   public double getxPosition(){

    return xPosition;

   }

   public void setxPosition(){
    xPosition = userInitialXPosition;
   }



   public double getyPosition(){

    return yPosition;
  }

   public void setyPosition(){
    yPosition = userInitialYPosition;
   }




    /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   *  Note: method for calculating Hours:Minutes:Seconds from https://stackoverflow.com/questions/8999638/getting-data-in-seconds-want-to-calculate-hours-minutes-seconds
   */
   public String toString() {
      return "Location is "+xPosition+" at x and "+yPosition+" at y. The velocity in the x direction is "+xVelocity+" inches per sec and in the y direction is "+yVelocity+" inches per second.";
    }

   public double validateXPosition( String argValue ) {

    //testing value before passing to arg

    double arg = Double.parseDouble( argValue );

    if ( argValue == null  || argValue == "" ){

      return 0;

    }

    else if ( Math.abs(arg) > HALF_WIDTH_OF_FIELD) {

      throw new IllegalArgumentException();

    } 
    userInitialXPosition = arg;

      return arg;

}

   public double validateYPosition( String argValue ) {

    //testing value before passing to arg

    double arg = Double.parseDouble( argValue );

    if ( argValue == null  || argValue == ""  ){

      return 0;

    }

    else if ( Math.abs(arg) > HALF_HEIGHT_OF_FIELD) {

      throw new IllegalArgumentException();

    } 
    userInitialYPosition = arg;

      return arg;

}

   public double validateXVelocity( String argValue ) {

    //testing value before passing to arg

    double arg = Double.parseDouble( argValue );

    if ( argValue == null  || argValue == ""  ){

      return 0;

  } 
  userInitialXVelocity = arg;

      return arg;

}

   public double validateYVelocity( String argValue ) {

    //testing value before passing to arg

    double arg = Double.parseDouble( argValue );

    if ( argValue == null  || argValue == ""  ){

      return 0;

  } 
  userInitialYVelocity = arg;

      return arg;

}



  /**

   *  The main program starts here

   *  remember the constraints from the project description

   *  @see  http://bjohnson.lmu.build/cmsi186web/homework05.html

   *  be sure to make LOTS of tests!!

   *  remember you are trying to BREAK your code, not just prove it works!

   */

   public static void main( String args[] ) {



      System.out.println( "\nBall CLASS TESTER PROGRAM\n" +

                          "--------------------------\n" );

      System.out.println( "  Creating a new Ball: " );


      Ball ball = new Ball();

      System.out.println("User inputed numbers to test basic methods, remember the numbers.");
      ball.validateXPosition(args[0]);
      ball.validateYPosition(args[1]);
      ball.validateXVelocity(args[2]);
      ball.validateYVelocity(args[3]);

      ball.setxPosition();
      ball.setyPosition();
      ball.setxVelocity();
      ball.setyVelocity();

      System.out.println("Testing get methods in setting the values and output.");
      System.out.println("Ball x position is "+ball.getxPosition());
      System.out.println("Ball y position is "+ball.getyPosition());
      System.out.println("Ball x velocity is "+ball.getxVelocity());
      System.out.println("Ball y velocity is "+ball.getyVelocity()+ "\n");




      System.out.println("Test for toString Method: " + ball.toString()+"\n" );

      System.out.println("Testing for accelerationMovement method in 10 sec time frame: " );

      int t = 0;
      while ( t < 10 ) {
        ball.accelerationMovement();
        System.out.println(ball.toString()+ "\n");
        t++;
      }



      System.out.println("Tests for validateXPosition" );

      //extremes and particular values

      try { ball.validateXPosition("-205"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for -205"); }

      try { ball.validateXPosition("205"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 205"); }

      try { ball.validateXPosition("20"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 20 "); }

      //negatives

      try { ball.validateXPosition("-20"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for -20 "); }

      //decimals

      double random;

      for(int i = 0; i<4; i++){

        random = Math.random() * 1000.0;

        try { ball.validateXPosition("K2"); }

        catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for " +random ); }

      }

      //letters

      try { ball.validateXPosition("K2"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for K2 "); }

      try { ball.validateXPosition("XYZ"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for XYZ "); }

      System.out.println( "\n" );



      System.out.println("Tests for validateYPosition" );

      //extremes and particular values

      try { ball.validateYPosition("-205"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for -205"); }

      try { ball.validateYPosition("205"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 205"); }

      try { ball.validateYPosition("20"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for 20 "); }

      //negatives

      try { ball.validateYPosition("-20"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for -20 "); }

      //decimals


      for(int i = 0; i<4; i++){

        random = Math.random() * 1000.0;

        try { ball.validateYPosition("K2"); }

        catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for " +random ); }

      }

      //letters

      try { ball.validateYPosition("K2"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for K2 "); }

      try { ball.validateYPosition("XYZ"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for XYZ "); }

      System.out.println( "\n" );


      System.out.println("Test for validateXVelocity");
      //decimals


      for(int i = 0; i<4; i++){

        random = Math.random() * 1000.0;

        try { ball.validateXVelocity("K2"); }

        catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for " +random ); }

      }

      //letters

      try { ball.validateXVelocity("K2"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for K2 "); }

      try { ball.validateXVelocity("XYZ"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for XYZ "); }

      System.out.println( "\n" );


      System.out.println("Test for validateYVelocity");
      //decimals


      for(int i = 0; i<4; i++){

        random = Math.random() * 1000.0;

        try { ball.validateYVelocity("K2"); }

        catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for " +random ); }

      }

      //letters

      try { ball.validateYVelocity("K2"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for K2 "); }

      try { ball.validateYVelocity("XYZ"); }

      catch( IllegalArgumentException iae ) { System.out.println("Exception thrown for XYZ "); }

      System.out.println( "\n" );


  } 
}
