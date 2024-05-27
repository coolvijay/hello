package vijay.cs.secondsLeftToday;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SecLeft {

    public static void main(String[] args)
    {


        System.out.println(secleftToFinishToday());
    }
    
    private static long secleftToFinishToday()
    {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        
        Calendar secLeftToFinishTodaysDay = Calendar.getInstance((TimeZone.getTimeZone("UTC")));

        secLeftToFinishTodaysDay.set(Calendar.MINUTE, 0);
        secLeftToFinishTodaysDay.set(Calendar.HOUR, 0);
        secLeftToFinishTodaysDay.set(Calendar.SECOND, 0);     
        secLeftToFinishTodaysDay.set(Calendar.MILLISECOND, 0);  
        
        secLeftToFinishTodaysDay.add(Calendar.DAY_OF_MONTH, 1);
                
        long diff = secLeftToFinishTodaysDay.getTimeInMillis() - now.getTimeInMillis(); 
        return diff/1000;
    }

}
