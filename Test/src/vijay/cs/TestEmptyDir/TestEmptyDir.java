package vijay.cs.TestEmptyDir;

import java.io.File;

public class TestEmptyDir {

    public static void main(String[] args)
    {
        File f = new  File("C:\\Users\\vijrana\\Desktop\\test");
        String s [] = f.list();
        
        System.out.println("s :" + s.length);
    }

}
