/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Kevin Solis Sosa
 *  Date          :  2018-01-23
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {

  /**
   * Maxes
   */
  private static final int maxMonth = 12;
  private static final int yearLength = 4;

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY    = SUNDAY    + 2;
   private static final int WEDNESDAY    = SUNDAY    + 3;
   private static final int THURSDAY    = SUNDAY    + 4;
   private static final int FRIDAY    = SUNDAY    + 5;
   private static final int SATURDAY    = SUNDAY    + 6;

  
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = JANUARY   + 2;
   private static final int APRIL      = JANUARY   + 3;
   private static final int MAY        = JANUARY   + 4;
   private static final int JUNE       = JANUARY   + 5;
   private static final int JULY       = JANUARY   + 6;
   private static final int AUGUST     = JANUARY   + 7;
   private static final int SEPTEMBER  = JANUARY   + 8;
   private static final int OCTOBER    = JANUARY   + 9;
   private static final int NOVEMBER   = JANUARY   + 10;
   private static final int DECEMBER   = JANUARY   + 11;
  // you can finish these on your own, too
  
  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[] daysForEachMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   public long year;
   public int month;
   public int day;

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      year = 0;
      month = 0;
      day = 0;
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
    //Only once case that can result in a leap year
    if (year%400 == 0 || year%4 == 0 && year%100 != 0){
        return true;
      }
    else
      return false; 
 
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      if(isLeapYear(year) && (month - 1) ==1)
        return 29;
      else
        return (long)daysForEachMonth[(int)month - 1];
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if(month1 == month2 && day1 == day2 && year1 == year2)
      return true;
    else
      return false;
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if(dateEquals( month1, day1, year1, month2, day2, year2 ))
      return 0; 
    else if(year2>year1)
      return -1;
    else if(month2>month1 && year2 == year1)
      return -1;
    else if(day2>day1 && month1 == month2)
      return -1;
    else
      return 1;
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
    int yearLength = String.valueOf((int)year).length();
    if(yearLength != 4)
      return false;
    if(isLeapYear(year) && month-1 == 1){
      if(day == 29)
        return true;
      else
        return false;
      }
  
      else if(month>=1 && month<=maxMonth && day <= daysForEachMonth[(int)month-1] && day>0)
          return true;
       else
        return false;
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
    String monthString = "";
      switch( month - 1 ) {
            case 0:  monthString = "January";
                     break;
            case 1:  monthString = "February";
                     break;
            case 2:  monthString = "March";
                     break;
            case 3:  monthString = "April";
                     break;
            case 4:  monthString = "May";
                     break;
            case 5:  monthString = "June";
                     break;
            case 6:  monthString = "July";
                     break;
            case 7:  monthString = "August";
                     break;
            case 8:  monthString = "September";
                     break;
            case 9: monthString = "October";
                     break;
            case 10: monthString = "November";
                     break;
            case 11: monthString = "December";
                     break;
            default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
        }
        return monthString;
    }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
    String dayString = "";
      switch( day - 1 ) {
            case 0:  dayString = "Sunday";
                     break;
            case 1:  dayString = "Monday";
                     break;
            case 2:  dayString = "Tuesday";
                     break;
            case 3:  dayString = "Wednesday";
                     break;
            case 4:  dayString = "Thursday";
                     break;
            case 5:  dayString = "Friday";
                     break;
            case 6:  dayString = "Saturday";
                     break;
            default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
      return dayString;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
 public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     long dayCount = 0;
     long differenceYears = Math.abs( year2 - year1-1);
     long dayFirst; 
     long monthFirst; 
     long yearFirst; 
     long dayLast;
     long monthLast;
     long yearLast;
     if (dateEquals( month1, day1, year1, month2, day2, year2 ) ) {
      return dayCount;
     } 
     if (month1 == month2 && year1 == year2 ) {
      return dayCount+(Math.abs( day1 - day2));
     } 
     if (compareDate( month1, day1, year1, month2, day2, year2 ) == -1  ) {
        dayFirst = day1; 
        monthFirst = month1; 
        yearFirst = year1; 
        dayLast = day2;
        monthLast = month2;
        yearLast = year2;
     } 
     else {
        dayFirst = day2;
        monthFirst = month2; 
        yearFirst = year2; 
        dayLast = day1;
        monthLast = month1;
        yearLast = year1;
     }
     if ( monthLast -1 == monthFirst && yearLast == yearFirst + 1 || yearFirst == yearLast ) {
      dayCount += daysInMonth( monthFirst, yearFirst ) - dayFirst;
      dayCount += dayLast;
      return dayCount;
     }
     dayCount += daysInMonth( monthFirst, yearFirst ) - dayFirst;
     dayCount += dayLast;
     long differenceMonths = ( ( maxMonth- monthFirst ) + ( maxMonth * differenceYears ) + ( monthLast ) - 1 );
     for ( long count = 0; count < differenceMonths; count++ ) {
      dayCount += daysInMonth( monthFirst, yearFirst );
      if ( monthFirst > maxMonth - 1) {
        monthFirst = 0;
        yearFirst++;
      } 
     } 
    System.out.println(dayCount); 
     return dayCount;
  }
    }
}
