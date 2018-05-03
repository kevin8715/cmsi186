/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangemakerTestHarness.java
 * Purpose    :  Test harness for verification of the ChangeMaker class
 * @author    :  Kevin Solis
 * Date       :  2018-04-28
 * Description:  This program provides a test harness for running tests to verify correct operaion of the
 *                "ChangeMaker.java" class.  This class is intended to be used as part of homework 7, the
 *                coin changemaker, which is a "Dynamic Programming" algorithm.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-19  B.J. Johnson  Initial release; stolen blatently from Professor Don Murphy with his
 *                                    express permission and blessing; just added this comment block
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class DynamicChangeMaker {

  public static int[] commandLineDenominationsList;
  public static int commandLineTotal;
  public static Tuple backup = new Tuple(new int[0]);


 /**
  * constructor
  */
  public DynamicChangeMaker() {
   super();
  }

/**
  *  Method to pass in command line inputs
  *  @param  args             string[]   user inputed string          
  */
  public void handleInitialArguments( String args[] ) {
   commandLineDenominationsList = new int[args.length-1];
   for(int i = 0; i < args.length-1; i++){
      validateDigits(args[i]);
      commandLineDenominationsList[i] = Integer.parseInt(args[i]);
   }
   validateDigits(args[args.length-1]);
   commandLineTotal = Integer.parseInt(args[args.length-1]);
  }

/**
  *  Checks to see if input is actual numbers
  *  @param  value             string  
  *  @throws IllegalArgumentException if the string isn't a number
  *  @return  check             boolean  if string is actual number
  */
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

/**
  *  Main to run command line inputs
  *  @param  args              string[]  user inputed string 
  */
  public static void main( String args[] ) {
      DynamicChangeMaker d = new DynamicChangeMaker();

      d.handleInitialArguments( args );

      System.out.println(d.makeChangeWithDynamicProgramming(commandLineDenominationsList, commandLineTotal).toString());
   }
  
 /**
  *  Algorithm to solve for best way to make change
  *  @param  denomiations      int[]  coin values 
  *  @param  total             int    change value
  * @throws IllegalArgumentException if  denomoinations are duplicated or less than one and if total is less than one
  *  @return  listOfChange     Tuple  smallest count of change that could be given
  */
  public static Tuple makeChangeWithDynamicProgramming(int[] denominationsList, int total) {

   try{
      for (int j = 0; j < denominationsList.length; j++) {
        for (int k = j + 1; k < denominationsList.length; k++) {
          if (denominationsList[ j ] == denominationsList[ k ]) {
            System.out.println("Error: there cannont be multiple denominations with the same values");
            throw new IllegalArgumentException();
          }
        }
      }
      for (int i = 0; i < denominationsList.length; i++) {
        if(denominationsList[ i ] < 1) { 
          System.out.println("Error: denomination cannot be less than one, found at denomination #"+ i);
          throw new IllegalArgumentException();
        }
      }
      if (total < 1) {
        System.out.println("Error: total cannot be less than one");
        throw new IllegalArgumentException();
      }
   }
    catch(IllegalArgumentException iae) {return backup;}


   int c = 0;
   int r = 0;
   int storage;
   int storage2;
   int storage3;
   Tuple[][] changeTuple = new Tuple[denominationsList.length][total + 1];


    for (r = 0; r < denominationsList.length; r++) {
      for (c = 0; c < total + 1; c++) {
         storage = denominationsList[r];
         storage2 = denominationsList.length;

         // Special case for column zero for all rows

        if (c == 0) {

          changeTuple[r][c] = new Tuple(storage2);

          // Otherwise, this is NOT column zero
        } 
        else {

         ///Case where the current denomination cannot be taken out of the given total

            if (c < storage) {

              changeTuple[r][c] = new Tuple( new int[ 0 ] );

              if ((c - storage >= 0)){

                if (!(changeTuple[r][ c - storage ].isImpossible())){

                  changeTuple[r][c].add(changeTuple[r][c - storage]);

                }
              }
          if (r!=0){

            if (!(changeTuple[r - 1][c].isImpossible())){

               if((changeTuple[r - 1][c].total() < changeTuple[r][c].total() ) || (changeTuple[r][c].isImpossible())){

                   changeTuple[r][c] = changeTuple[r - 1][c];

                 }
                } 
              }
            }
            //  take one current denomination out
            else{

              changeTuple[r][c] = new Tuple(storage2);
              changeTuple[r][c].setElement(r, 1);

              if (c - storage >= 0 ) {

                  if (changeTuple[r][c - storage].isImpossible()){

                  changeTuple[r][c] = new Tuple(new int[ 0 ]);

                  } 
                   else{

                  changeTuple[r][c] = changeTuple[r][c].add(changeTuple[r][c - storage]);

                  }
              }
              if (!(r == 0)) {

                if (!(changeTuple[r - 1][c].isImpossible())){

                  if ((changeTuple[r][c].isImpossible()) || (changeTuple[r - 1][c].total() < changeTuple[r][c].total())){
         
                    changeTuple[r][c] = changeTuple[r - 1][c];
                  }
                }  
              }
            }     
          }
        }
      }
    System.out.println(changeTuple[r - 1][c - 1]);
    return changeTuple[r - 1][c - 1];
    }
}
