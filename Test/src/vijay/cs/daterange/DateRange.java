package vijay.cs.daterange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateRange {

    public static void main(String[] args) throws ParseException
    {

        System.out.println(isMigrationInValidDateRange("1", "2023-07-01T09:50:25.511+0000", 1, 1));
        System.out.println(isMigrationInValidDateRangeForExcludedFiles("1", "2023-07-01T09:50:25.511+0000", 1, 1));
    }
    
    private static Boolean isMigrationInValidDateRange(String migrationId, String migrationCompletedDate, int rbPeriod,
            int runIndays) throws ParseException
    {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        Calendar tillDate = Calendar.getInstance(timeZone);
        SimpleDateFormat dateFormatter = null;
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        tillDate.add(Calendar.DAY_OF_MONTH, -(runIndays));
        
        Date tillDateCompare = dateFormatter.parse(dateFormatter.format(tillDate.getTime()));

        Calendar startCalDate = Calendar.getInstance(timeZone);
        Date date = new Date();
        startCalDate.setTime(date);
        startCalDate.add(Calendar.DAY_OF_MONTH, -(rbPeriod + 1));

        Date startDateCompare = dateFormatter.parse(dateFormatter.format(startCalDate.getTime()));

        String sDate1 = migrationCompletedDate.substring(0, 10);
        Date date1 = dateFormatter.parse(sDate1);

        Calendar migrationCompleteDate = Calendar.getInstance(timeZone);
        migrationCompleteDate.setTime(date1);

        Calendar forLoggerOnly = Calendar.getInstance(timeZone);
        forLoggerOnly.setTime(tillDateCompare);
        forLoggerOnly.add(Calendar.SECOND, - 1);
        Date dateLogger = forLoggerOnly.getTime(); 
        
        SimpleDateFormat formatLogger = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");          
        String tillDateLogger = null;

        tillDateLogger = formatLogger.format(dateLogger);
        
        if (((startDateCompare.compareTo(migrationCompleteDate.getTime()) <= 0))
                && ((tillDateCompare.compareTo(migrationCompleteDate.getTime()) > 0)))
        {

           System.out.println("Migration id " + migrationId + " with migration completion date " + migrationCompletedDate
                    + " ran after date and time " + dateFormatter.format(startDateCompare.getTime()) + ":00 and before date and  time "
                    + tillDateLogger + " so Migration completion date is VALID");
            return true;
        }
        else
        {
            System.out.println("Migration id " + migrationId + " with migration completion date " + migrationCompletedDate
                    + " did not run after date and time " + dateFormatter.format(startDateCompare.getTime()) + ":00 and before date and time "
                    + tillDateLogger + " so Migration completion date is INVALID");
            return false;
        }
    }
    
    
    
    private static Boolean isMigrationInValidDateRangeForExcludedFiles(String migrationId, String migrationCompletedDate, int rbPeriod,
            int runIndays) throws ParseException
    {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        Calendar tillDate = Calendar.getInstance(timeZone);
        SimpleDateFormat dateFormatter = null;

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        tillDate.add(Calendar.DAY_OF_MONTH, -(runIndays));

        Date tillDateCompare = dateFormatter.parse(dateFormatter.format(tillDate.getTime()));

        Calendar startCalDate = Calendar.getInstance(timeZone);
        Date date = new Date();
        startCalDate.setTime(date);
        startCalDate.add(Calendar.DAY_OF_MONTH, -(rbPeriod + 1));

        Date startDateCompare = dateFormatter.parse(dateFormatter.format(startCalDate.getTime()));

        String sDate1 = migrationCompletedDate.substring(0, 10);
        Date date1 = dateFormatter.parse(sDate1);

        Calendar migrationCompleteDate = Calendar.getInstance(timeZone);
        migrationCompleteDate.setTime(date1);
        /*
         * LOGGER.debug("startDateCompare " +startDateCompare); LOGGER.debug("migrationCompleteDate " +date1);
         * LOGGER.debug("tillDateCompare " +tillDateCompare);
         */

        Calendar forLoggerOnly = Calendar.getInstance(timeZone);
        forLoggerOnly.setTime(tillDateCompare);
        forLoggerOnly.add(Calendar.SECOND, - 1);
        Date dateLogger = forLoggerOnly.getTime(); 
        
        SimpleDateFormat formatLogger = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String tillDateLogger = null;

        tillDateLogger = formatLogger.format(dateLogger);
        
        if (((startDateCompare.compareTo(migrationCompleteDate.getTime()) <= 0))
                && ((tillDateCompare.compareTo(migrationCompleteDate.getTime()) > 0)))
        {
         System.out.println(migrationCompletedDate + " ran after date and time  " + dateFormatter.format(startDateCompare.getTime())
                    + ":00 and before date and time " + tillDateLogger
                    + " so Migration completion date is OK");

            return true;
        }
        else
        {
           System.out.println(migrationCompletedDate + " did not run after date time "
                    + dateFormatter.format(startDateCompare.getTime()) + ":00 and before date time "
                    + tillDateLogger + " so completion date is NOT OK");
            return false;
        }
    }
}
