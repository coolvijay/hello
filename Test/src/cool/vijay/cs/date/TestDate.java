package cool.vijay.cs.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {

    public static void main(String[] args) throws ParseException
    {

        //System.out.println();
        //getNoOfDaysBetweenTwoDays("2023-05-14","2023-05-15");
        
        System.out.println("sleep time" + calculateSleepTime()/60);
        
    }
    private static long calculateSleepTime()
    {
       // String time = getCmap().get("connectivity.aprimo.start").trim();

        String time = "2359";
                
        ZonedDateTime utcinstallTime = ZonedDateTime.now(ZoneOffset.UTC);
        String installTime = utcinstallTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:ss.SZ"));

        String sub = installTime.substring(0, 11);
        String runat = sub + time.substring(0, 2) + ":" + time.substring(2, 4) + ":00.0Z";
        

        ZonedDateTime dateTime1 = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime dateTime2 = ZonedDateTime.parse(runat);

        Duration duration = Duration.between(dateTime1, dateTime2);

        //long days = Integer.parseInt(getCmap().get("connectivity.aprimo.run").trim());
        long days = 1;
        long sleepTime = 0L;

        if (duration.getSeconds() < 0L)
        {
            sleepTime = duration.getSeconds() + days * 86400L;
            //LOGGER.info(" time already gone wait till secs, scheduled time :" + sleepTime);
        }
        else
        {
            if (days == 1)
            {
                sleepTime = duration.getSeconds();
              //  LOGGER.info(" time upcoming wait till secs, scheduled time :" + sleepTime);

            }
            else
            {
                sleepTime = duration.getSeconds() + days * 86400L;
                //LOGGER.info(" time upcoming days wait till secs, scheduled time :" + sleepTime);
            }
        }

        return sleepTime;

    }

    private static void testDaysDiff(int rbPeriod , int runIndays) throws ParseException
    {
        
        Calendar tillDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        // Date date = dateFormatter.parse("2022-04-05");
        //tillDate.add(Calendar.DAY_OF_MONTH, -(-runIndays+rbPeriod +1));

        tillDate.add(Calendar.DAY_OF_MONTH, -(runIndays));
        
        System.out.println("Till date :" + tillDate.getTime() );
        
        Calendar startCalDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        startCalDate.setTime(date);
        startCalDate.add(Calendar.DAY_OF_MONTH, -(rbPeriod + 1));
        
        System.out.println("startCalDate date :" + startCalDate.getTime() );

        
    }

    
    private static long getPeriod(String start , String end) throws ParseException
    {
        Date datestart=new SimpleDateFormat("yyyy-MM-dd").parse(start);  
        
        Date dateend=new SimpleDateFormat("yyyy-MM-dd").parse(end);  

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        
        
        long time = dateend.getTime() - datestart.getTime();
        long daysDifference = (time / (1000 * 60 * 60 * 24))% 365;
        return daysDifference;
        
        
    }
    
    private static String getTheDate(int period)
    {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.DAY_OF_MONTH, -(period +1));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(c.getTime());
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
}
