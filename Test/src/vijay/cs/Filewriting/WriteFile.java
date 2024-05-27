package vijay.cs.Filewriting;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WriteFile {

    public static void main(String[] args) 
    {
        File f = new File("C:\\Users\\vijrana\\Desktop\\aprimo\\Testing");
        
        /*
         * String str = "dfkjsdk"; Path path = Paths.get("C:\\Users\\vijrana\\Desktop\\aprimo\\Testing"); byte[]
         * strToBytes = str.getBytes();
         * 
         * Files.write(path, strToBytes);
         */
        
        Map<String, Integer> v = new HashMap<String, Integer>();
        v.put("a", 1);
        v.put("b", 2);
        v.put("c", 3);
        
        String file = "C:\\Users\\vijrana\\Desktop\\aprimo\\Testing";
        saveCounters(v, "vIWSF_Counters_" , file );

       // out.write("sMax");
        //System.out.println(sMax);//This command it works
        //out.close();
    }
    public static String saveCounters(Map<String, Integer> Values, String fileType, String file)
    {

        System.out.println("File to be saved :" + file);

        try
        {
            Path path = Paths.get(file);
            for (Entry<String, Integer> entry : Values.entrySet())
            {
                if (fileType.equals("vIWSF_Counters_"))
                {
                    String s = entry.getKey() + " " + entry.getValue() + "\\n";
                    Files.write(path,s.getBytes(),StandardOpenOption.WRITE);
                }
                else
                {
                    String s = entry.getKey() + "," + entry.getValue();
                    Files.write(path,s.getBytes(),StandardOpenOption.APPEND);
                    
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("error ");
        }
 

        return file;
    }
}
