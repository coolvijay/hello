package vijay.cs.properties;

import java.util.Properties;
public class TestProperies {

    public static void main(String[] args)
    {
        Properties p = new Properties();
        
        if(p == null || p.isEmpty()) {
            System.out.println("if");
            System.out.println( "properties " + p.toString());
        }else {
            System.out.println("else");
        }
    }

}
