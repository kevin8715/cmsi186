/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string
   private byte[] remainder;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
    internalValue = value;
    reversed = new StringBuffer( internalValue ).reverse().toString();
    validateDigits(internalValue);
    if(internalValue.equals(null) || internalValue.equals("")){
      throw new IllegalArgumentException("No string was found");
    }
    if(internalValue.substring(0,1).equals("-")){
      internalValue = stringCharacterRemover(internalValue, 0);
      sign = 1;
    }
    else if(internalValue.substring(0,1).equals("+")){
      internalValue = stringCharacterRemover(internalValue, 0);
      sign = 0;
    }
    else{
      sign = 0;
    }
    byteVersion = new byte[internalValue.length()];
     for(int i = 0; i < internalValue.length(); i++){
      byteVersion[i] = Byte.parseByte(reversed.substring(i, i+1));
    }
   }

  /**
   *  Removes a character from a string given  a string and the location of that charcter
   *   
   *  @param  inputed string without the character at location
   */
  public String stringCharacterRemover(String subject, int location){
    if(location>0){
    return subject.substring(0, location)+subject.substring(location+1);
    }
    else{
    return subject.substring(1);
    }
   }

    /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  Byte[] value to make into a BrobInt
   */
   public BrobInt( byte[] value, byte v) {
    sign = v;
    byteVersion = value;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits(String value) {
    boolean check = false;
    String digits = "+-0123456789";
    for(int i = 0; i < value.length(); i++){
      check = false;
      for(int j = 0; j < digits.length(); j++){
        if(value.substring(i, i+1).equals(digits.substring(j, j+1))){
          check = true;
          break;
          } 
        }
    if (!(check)){
      throw new IllegalArgumentException("The string contains non-numbers."); 
         }
      }
    return check;
   }

 /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to remove zeros from a byte array in a complicated way
   *  @return  byteVersion
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public byte[] removeZeros(byte[] input) {
    byte[] storage;
    int count = 0;
    for(int i = 0; i < input.length; i++){
      if(input[i] != 0){
        count++;
      }
    }
    storage = new byte[count];
    count = 0;
    for(int i = 0; i < input.length; i++){
      if(input[i] != 0){
        storage[count] = input[i];
        count++;
      }
    }
    return storage;
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the byter array, ByteVersion, from a BrobInt
   *  @return  byteVersion
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public byte[] getByteVersion() {
    return byteVersion;
   }
   //might not need this method


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      byte[] reverse = new byte[byteVersion.length];

      for(int i = 0; i <byteVersion.length; i++){
        reverse[i] = byteVersion[byteVersion.length-i];
      }
      return new BrobInt(reverse, sign);
   }
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to flip sign of a brobint
   *  @return nothing
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt flipSign(){
    if(this.sign == (byte)0){
      this.sign = (byte)1;
    }
    else{
      this.sign = 0;
    }
    return this;
  }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser( BrobInt gint ) {
      return gint.reverser();
   }

   public byte[] extendByte(byte[] input , int length){
      byte[] extended = new byte[input.length+length];
      for(int i = 0; i < input.length; i++){
        extended[i] = input[i];
      }
      return extended;
   }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addByte( BrobInt gint ) {
    int comparision = this.compareTo(gint);
    if(gint.equals(ZERO)){
      return this;
    }
    if(this.equals(ZERO)){
      return gint;
    }
      int storage = 0;
      byte carryOn = 0;
      byte s = 0;
      byte[] sum;
    if(comparision == 1 && this.sign == 0 && gint.sign == 0){
      if(gint.byteVersion.length == this.byteVersion.length && gint.byteVersion[gint.byteVersion.length-1] + this.byteVersion[this.byteVersion.length-1] > 9){
        sum = extendByte(this.byteVersion, 1);
     }
      sum = this.byteVersion;
      System.out.println("before everything: "+new BrobInt(sum, s).toString());
      for(int i = 0; i < gint.byteVersion.length;  i++){
        storage = sum[i] + gint.byteVersion[i];
        if(storage > 9){
          sum[i+1] +=1;
          sum[i] = (byte)(storage%10);
          System.out.println("carry one: " + new BrobInt(sum, s).toString());

        }
        else{
          sum[i] = (byte)storage;
          System.out.println("no carry on: "+ new BrobInt(sum, s).toString());
        }
        storage = 0;
       }
       return new BrobInt(sum, s);
    }
    else if(comparision == -1 && this.sign == 0 && gint.sign == 0){
      BrobInt b = gint.addByte(this);
      System.out.println("recursive 1 gint: "+gint.toString());
      System.out.println("recursive 1 this: "+this.toString());
      System.out.println("after addByte call: "+b.toString());
      return b;
    }
    else if(this.sign == 1 && gint.sign == 0){
      return gint.subtractByte(this.flipSign());
    }
    else if(this.sign == 0 && gint.sign == 1){
      return this.subtractByte(gint.flipSign());
    }
    else if(comparision == -1 && this.sign == -1 && gint.sign == -1){
      return (gint.flipSign().addByte(this.flipSign())).flipSign();
    }
    else if(comparision == 0){
      return this.multiply(TWO);
    }
    else{
    return (this.flipSign().addByte(gint.flipSign())).flipSign();
    }

  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addInt( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobIntTesterbInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt gint ) {
    int comparision = this.compareTo(gint);
   if(gint.internalValue.equals("") || gint.internalValue.equals(null) || gint.equals(ZERO))
      return this;
      int storage = 0;
      byte carryOn = 0;
      byte s = -1;
      byte[] difference;
    if(comparision == -1 && this.sign == 0 && gint.sign == 0){
      difference = gint.byteVersion;
      for(int i = 0; i < this.byteVersion.length;  i++){
        storage = difference[i] - this.byteVersion[i];
        if(storage < 0){
          difference[i+1] = (byte)(difference[i+1] - 1);
          difference[i] = (byte)(10 + storage);
          System.out.println("carry on: "+ new BrobInt(difference, s).toString());
         }
        else{
          difference[i] = (byte)storage;
          System.out.println("no carry on: "+ new BrobInt(difference, s).toString());
       }
       storage = 0;
    }
    return new BrobInt(removeZeros(difference), s);
    }
    else if(comparision == 0){
      return ZERO;
    }
    else if(comparision == 1 && this.sign == 0 && gint.sign == 0){
      return gint.subtractByte(this).flipSign();
    }
    else if(this.sign == 1 && gint.sign == 0){
      return (gint.addByte(this.flipSign()));
    }
    else if(this.sign == 0 && gint.sign == 1){
      return this.addByte(gint.flipSign());
    }
    else if(comparision == -1 && this.sign == -1 && gint.sign == -1){
      return (gint.flipSign().subtractByte(this.flipSign()));
    }
    else{
      return (this.flipSign().subtractByte(gint.flipSign()));
    }

  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractInt( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
    int storage = 0;
    byte carryOn = 0;
    int extraStorage= 0;
    byte r = 0;
    byte s;
    byte[] product = new byte[gint.byteVersion.length + this.byteVersion.length];
    if (this.equals(ONE)) {
         return gint;
      } 
    else if (gint.equals(ONE)) {
         return this;
      }
    else if (gint.byteVersion.length >= this.byteVersion.length) {
      if(this.sign == gint.sign){
      s = 0;
        }
     else{
       s = 1;
        }
      for (int i = 0; i < gint.byteVersion.length; i++) {
         for (int j = 0; j < this.byteVersion.length; j++) {
            storage = (gint.byteVersion[i] * this.byteVersion[j]);
            if(storage > 10){
              product[i+j] += (byte)(storage%10);
              product[i+j+1] += (byte)(storage/10);
            }
            System.out.println("carry one: " + new BrobInt(product, s).toString());
            storage = 0;
         }
         
         for (int k = 0; k < product.length; k++) {
            extraStorage = (product[k]);
            if(product[k] < 10){}
            else{
            product[k] = 0;
            while(extraStorage > 10){
               product[k + r] += product[k] % 10;
               extraStorage = (extraStorage/10);
               r++;
            }
          }
         }
      }
      return new BrobInt(removeZeros(product), s);  
    }
    else{
     return gint.multiply(this);
      }
    
 }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
    /*
    byte quotientLength;
    byte dividerLength = (byte)gint.byteVersion.length;
    int storage = 0;
    byte carryOn = 0;
    byte extraStorage= 0;
    byte s;
    BrobInt dividend = this;
    quotientLength = ((byte)(this.byteVersion.length-gint.byteVersion.length+1));
    byte[] quotient = new byte[quotientLength];
    if (this.equals(ONE)) {
         return ZERO;
      } 
    else if (gint.equals(ONE)) {
         return this;
      }
    else if (gint.equals(ZERO)){
      throw new IllegalArgumentException("Zero is not a valid input");
    }
    else if (this.byteVersion.length < gint.byteVersion.length) {
      return ZERO;
    }
    else{
      if(gint.sign == this.sign){
      s = 0;
      } 
      else{
      s = 1;
      }
    System.out.println("Still working after assigning sign");
    for(int i = 0; i < quotient.length; i++){
    storage = (dividend.byteVersion[dividend.byteVersion.length-r-1]/gint.byteVersion[gint.byteVersion.length-r-1]);
     System.out.println("Still working after for loop i: " + i);
    while( !( storage > 0 && dividend.byteVersion[r]%storage >= 1)){
      r++;
      storage = dividend.byteVersion[dividend.byteVersion.length-r]/gint.byteVersion[gint.byteVersion.length-i];
       System.out.println("Still working, while loop");
    }
    r = (byte)(r+dividerLength);
    byte[] bigStorage = new byte[quotientLength];
    for(int j = 0; j < gint.byteVersion.length; j++){ 
      bigStorage[j] = (byte)(gint.byteVersion[j]*r);
       System.out.println("Still working, for loop j: "+ j);
    }
    quotient[quotient.length-i] = r;
    dividend = dividend.subtractByte( new BrobInt(bigStorage, (byte)0));
   }
 }
 remainder = quotient;
 return  new BrobInt(quotient, s);
 */
 throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );

}

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      byte s;
      if(gint.sign == this.sign){
      s = 0;
      } 
      else{
      s = 1;
      }
      this.divide(gint);
      return new BrobInt(remainder, s );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt gint ) {
   if( internalValue.length() > gint.internalValue.length() ) {
      return 1;
   } else if( internalValue.length() < gint.internalValue.length() ) {
      return (-1);
   } else {
      for( int i = 0; i < internalValue.length(); i++ ) {
         Character a = new Character( internalValue.charAt(i) );
         Character b = new Character( gint.internalValue.charAt(i) );
         if( new Character(a).compareTo( new Character(b) ) > 0 ) {
            return 1;
         } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
            return (-1);
         }
      }
   }
   return 0;
}

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (this.compareTo(gint) == 0);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String byteVersionOutput = "";
      for( int i = 0; i < byteVersion.length; i++ ) {
         byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
      }
      if(sign == 0)
        byteVersionOutput = byteVersionOutput + "+";
      else 
        byteVersionOutput = byteVersionOutput + "-";
      byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
      return byteVersionOutput;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
