/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Kevin Solis
 *  Date          :  2018-02-20
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet{

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
    if(count < 1){
      throw new IllegalArgumentException();
    }
      ds = new Die[ count ];
    for (int i = 0; i < count; i ++){
      ds[i] = new Die(sides);
    }

   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
    int sum = 0;
    for(Die d: ds){
      sum += d.getValue();
    }

      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
    for(Die d : ds){
      d.roll();
    }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      return ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
    String r = "";
    for(int i = 0; i < ds.length; i++){
      r+= (i + " " + getIndividual(i));
      i++;
    }
    return r; 
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
    ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
    if(this.ds.length != ds.length){
      return false;
    }
    for(int i = 0; i < ds.length; i++){
      if(this.getIndividual(i) != getIndividual(i)){
        return false;
      }
    }
return true;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println( "Hello world from the DieSet class..." );
      DiceSet ds = null;
      int count = 0;
      int test;
      int test2; 
      while (count < 10){
        test = (int)Math.floor(Math.random()*100 + 1);
        test2 = (int)Math.floor(Math.random()*20 + 1);
        try{ds = new DiceSet(test, test2);}
        catch ( IllegalArgumentException iae ) { System.out.println( "Given die side values is too small or  # of dies is too small" ); }
        System.out.println("\nTesting for proper return values for methods with sides being:" + test + " and # of die being" + test2 );
        for(int i = 0; i < 3; i++){
          ds.roll();
          ds.toString();  
        }
        System.out.println("sum of rolls is: " + ds.roll());
        System.out.println("output for static toString method:" + toString(ds));
        System.out.println("testing rollIndividual and getIndividual: ");
        for(int i = 0; i<ds.length; i++)
          System.out.println(i + " roll:" + ds.rollIndividual(i) );
        count++;
      }
      try { ds = new DiceSet(0, 2);}
      catch ( IllegalArgumentException iae ) { System.out.println( "Given die side values is too small or  # of dies is too small" ); }
      System.out.println(ds.toString());
   }

}
