package vijay.cs.hhmm;

import java.util.Calendar;
import java.util.TimeZone;

public class HourMin {

    public static void main(String[] args)
    {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String now = c.toInstant().toString();
        String h = now.substring(11, 13);
        String m = now.substring(14, 16);
                
        System.out.println(c.toInstant().toString());
        System.out.println("hour :" + h+m);
        System.out.println("min :" + m);
        
        
        String s = "";
        
        if(s.isEmpty())
        {
            System.out.println("empty");
        }else
        {
            System.out.println("Not empty");
        }
        
        
        Long l = null;
        
        System.out.println( "Test :" +  l/60);
    }

}