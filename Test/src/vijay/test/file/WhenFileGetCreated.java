package vijay.test.file;

import java.io.File;

public class WhenFileGetCreated {

    public static void main(String[] args)
    {
        String s = "C:\\Users\\vijrana\\Desktop\\2289\\helo";
        File f = new File(s);
        
        
        if( f.exists())
        {
            System.out.println("yes");
        }
        else
        {
            System.out.println("no");
        }
    }

}
