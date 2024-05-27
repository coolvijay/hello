package vijay.string.test;

public class StTest {

    static String st = "system-vIWSF/host-viwsf-healthcheck";
    
    public static void main(String[] args)
    {
        String s[] = st.split("service-");
        for(int i =0 ;i<s.length;i++) {
            System.out.println(" s " + s[i]);    
        }
        
    }

}
