import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Quote  {
    ArrayList<String> List1 = new ArrayList<String>();
    Scanner Scan;
    public String Quote;




    public void write() throws IOException {



                FileWriter FW = new FileWriter("C:/Users/Public/test.txt", true);
                FW.write("\n" + Quote);
                FW.flush();
                FW.close();


        List1.add(Quote);
    }








    public String getQuote() {




        {
            try {
                Scan = new Scanner(new File("C:/Users/Public/test.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        {
            while (Scan.hasNextLine()) {
                List1.add(Scan.nextLine());
            }
            String[] array = List1.toArray(new String[0]);

            double z = Math.random() * List1.size();
            int x = (int) Math.round(z);
            System.out.println(z);

            return (List1.get(x));
        }
    }




    public void Quote(String quote) {
        Quote = quote;
    }



//    public void Add() {
//        List1.add(Quote);
//    }



}
