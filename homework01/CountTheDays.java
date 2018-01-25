/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :   A class to count the days between two dates
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Kevin Solis Sosa
 *  Date          :  2018-01-23
 *  Description   :  Gives absolute number of days between two dates
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:      Reason for change/modification
 *           -----  ----------  ------------      -----------------------------------------------------------
 *  @version 1.0.0  2018-01-23	Kevin Solis Sosa	Creating the initial file.
 */
public class CountTheDays{
    public static void main(String[] args) {
    	long m1 = Long.parseLong(args[0]);
    	long d1 = Long.parseLong(args[1]);
    	long y1 = Long.parseLong(args[2]);
    	
    	long m2 = Long.parseLong(args[3]);
    	long d2 = Long.parseLong(args[4]);
    	long y2 = Long.parseLong(args[5]);
    	System.out.println(CalendarStuff.daysBetween(m1, d1, y1, m2, d2, y2))
    }
}