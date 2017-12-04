import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.io.*;


public class day02_01 {

    public static void main(String[] args) {

        // keeps track of the running checksum
        Integer checksum = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            while (line != null) {

                Integer min = null;
                Integer max = null;

                // get min and max integer from each line
                for (String s : line.split("\\s+")) { 
                    
                    Integer stringAsInt = Integer.valueOf(s);
                    if (min == null || max == null) {
                        min = stringAsInt;
                        max = stringAsInt;
                    } else {
                        if (stringAsInt < min) min = stringAsInt;
                        if (stringAsInt > max) max = stringAsInt;
                    }

                }

                // add difference to checksum
                checksum += (max - min);

                // get next line
                line = br.readLine();

            }

            br.close();

        } catch(IOException e) {
            e.printStackTrace();
        } 

        // print result 
        System.out.println("Checksum: " + String.valueOf(checksum));

    }

}
