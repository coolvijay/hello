package aprimo.vijay.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AprimoTest2 {
    
    static int startdays;
    
    static int enddays;
    
    public static void main(String[] args) throws ParseException
    {
        TestCase1();
        System.out.println("======================================================");
        TestCase2();
        System.out.println("======================================================");
        TestCase3();
    }

    public static void TestCase1() throws ParseException
    {

        String lastAprimoSuccesDataGenerationDate = "";
        String aprimoFirstRunDate = "2023-07-05";
        int rollbackPeriod = 3;
        int runPeriod = 2;



        System.out.println(
                "Value of hidden.aprimo.lastsuccessdatagenerationdate is NULL or EMPTY in DB. Aprimo has not generated the files since it started, generate the files from current date "
                        + aprimoFirstRunDate );
         
        setStartdays(rollbackPeriod+runPeriod);
        setEnddays(rollbackPeriod);
        
        System.out.println("Set START value to " + getStartdays() + " OR from date " + getDateRunPeriodNoOfDays(getStartdays()));
        System.out.println("Set END value to "  + getEnddays() + " OR till date " + getDateRunPeriodNoOfDays(getEnddays()));

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(isMigrationInValidDateRange("1", "2023-07-01T09:50:25.511+0000", getStartdays(), getEnddays()));
        System.out.println(isMigrationInValidDateRangeForExcludedFiles("1", "2023-07-01T09:50:25.511+0000",  getStartdays(), getEnddays()));

    
    }
    
    public static void TestCase2() throws ParseException
    {
        String lastAprimoSuccesDataGenerationDate = "2023-07-03";
        int rollbackPeriod = 3;
        int runPeriod = 2;
        
        
        System.out.println(
                "Value of hidden.aprimo.lastsuccessdatagenerationdate is NOT NULL and NOT EMPTY in DB. And last Aprimo run generated the data successully on date "
                        + lastAprimoSuccesDataGenerationDate);

        setStartdays(rollbackPeriod+runPeriod);
        setEnddays(rollbackPeriod);
        System.out.println("Set START value to " + getStartdays() + " OR from date " + getDateRunPeriodNoOfDays(getStartdays()));
        System.out.println("Set END value to "  + getEnddays() + " OR till date " + getDateRunPeriodNoOfDays(getEnddays()));

        
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        System.out.println(isMigrationInValidDateRange("1", "2023-07-01T09:50:25.511+0000", getStartdays(), getEnddays()));
        System.out.println(isMigrationInValidDateRangeForExcludedFiles("1", "2023-07-01T09:50:25.511+0000",  getStartdays(), getEnddays()));

    
    }
    public static void TestCase3() throws ParseException
    {
        
        String lastAprimoSuccesDataGenerationDate = "2023-06-29";
        int rollbackPeriod = 3;
        int runPeriod = 2;
        
        System.out.println(
                "Value of hidden.aprimo.lastsuccescbxbsdatagenerationdate is NOT NULL and NOT EMPTY in DB. BUT Last Aprimo run DID NOT generated the data successfully, Last date on which Aprimo successfully on 'hidden.aprimo.lastsuccessdatagenerationdays' " 
                        + lastAprimoSuccesDataGenerationDate);

        System.out.println("Last date on which Aprimo successfully and generated files on 'hidden.aprimo.lastsuccessdatagenerationdays' "
                + lastAprimoSuccesDataGenerationDate);
        System.out.println("Last date on which Aprimo ran but could not generate files on " 
                + getLastAprimoRunDate(runPeriod));
        try
        {
        int c =getNoOfDaysBetweenTwoDays(lastAprimoSuccesDataGenerationDate, getLastAprimoRunDate(runPeriod));
        System.out.println("No of Days Between lastAprimoSuccesDataGenerationDate " + lastAprimoSuccesDataGenerationDate + " and last Aprimo run " + getLastAprimoRunDate(runPeriod) + " is : " + c);
        System.out.println("Add this No of Days " + c + " to actual rollback period " +rollbackPeriod + " to generate the data on which aprimo failed to generate the files ");

        int r = c + rollbackPeriod+runPeriod;
        
        setStartdays(r);
        setEnddays(rollbackPeriod);
        
        System.out.println("Set START value to " + getStartdays() + " OR from date " + getDateRunPeriodNoOfDays(getStartdays()));
        System.out.println("Set END value to "  + getEnddays() + " OR till date " + getDateRunPeriodNoOfDays(getEnddays()));
        }
        catch (Exception e)
        {
            System.err.println("ex");
        }
        
        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        System.out.println(isMigrationInValidDateRange("1", "2023-07-01T09:50:25.511+0000", getStartdays(), getEnddays()));
        System.out.println(isMigrationInValidDateRangeForExcludedFiles("1", "2023-07-01T09:50:25.511+0000",  getStartdays(), getEnddays()));

    }
    
    private static int getNoOfDaysBetweenTwoDays(String start , String end) throws ParseException
    {
        Date datestart=new SimpleDateFormat("yyyy-MM-dd").parse(start);  
        
        Date dateend=new SimpleDateFormat("yyyy-MM-dd").parse(end);  

        long time = dateend.getTime() - datestart.getTime();
        long daysDifference = (time / (1000 * 60 * 60 * 24))% 365;
                
        System.out.println("getNoOfDaysBetweenTwoDays() start date " + start + " end date " +end + " is :"+(int) daysDifference);
        return (int) daysDifference;
                
    }
    
    private static String getDateRunPeriodNoOfDays(int period)
    {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.DAY_OF_MONTH, -(period));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        //LOGGER.info("getDateBeforePeriodNoOfDays() period " + period + format1.format(c.getTime()));
        return format1.format(c.getTime());
    }
    
    private static String getLastAprimoRunDate(int runPeriod)
    {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
        c.add(Calendar.DAY_OF_MONTH, -(runPeriod));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println("getLastAprimoRunDate()  " + format1.format(c.getTime()));
        return format1.format(c.getTime());
    }
    
    
    private static Boolean isMigrationInValidDateRange(String migrationId, String migrationCompletedDate, int startDay,
            int endDay) throws ParseException
    {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        Calendar tillDate = Calendar.getInstance(timeZone);
        SimpleDateFormat dateFormatter = null;
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        tillDate.add(Calendar.DAY_OF_MONTH, -(endDay));
        
        Date tillDateCompare = dateFormatter.parse(dateFormatter.format(tillDate.getTime()));

        Calendar startCalDate = Calendar.getInstance(timeZone);
        Date date = new Date();
        startCalDate.setTime(date);
        startCalDate.add(Calendar.DAY_OF_MONTH, -(startDay));

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
    
    private static Boolean isMigrationInValidDateRangeForExcludedFiles(String migrationId, String migrationCompletedDate, int startDay,
            int endDay) throws ParseException
    {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        Calendar tillDate = Calendar.getInstance(timeZone);
        SimpleDateFormat dateFormatter = null;

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        tillDate.add(Calendar.DAY_OF_MONTH, -(endDay));

        Date tillDateCompare = dateFormatter.parse(dateFormatter.format(tillDate.getTime()));

        Calendar startCalDate = Calendar.getInstance(timeZone);
        Date date = new Date();
        startCalDate.setTime(date);
        startCalDate.add(Calendar.DAY_OF_MONTH, -(startDay));

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

    public static int getStartdays()
    {
        return AprimoTest2.startdays;
    }

    public static void setStartdays(int startdays)
    {
        AprimoTest2.startdays = startdays;
    }

    public static int getEnddays()
    {
        return AprimoTest2.enddays;
    }

    public static void setEnddays(int enddays)
    {
        AprimoTest2.enddays = enddays;
    }
    

}
