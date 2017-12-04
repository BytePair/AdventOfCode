import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day01 {

    public static void main(String[] args) {

        
        String result = "";
        int total = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            result = sb.toString();
            br.close();

        } catch(IOException e) {
            e.printStackTrace();
        } 

        if (result.length() > 1) {

            // loop through entire string looking at each neighbor
            for (int i = 1; i < result.length(); i++) {
                if (result.charAt(i) == result.charAt(i-1)) {
                    total += Character.getNumericValue(result.charAt(i));
                }
            }

            // check the first and last characters
            if (result.charAt(0) == result.charAt(result.length() - 1)) {
                total += Character.getNumericValue(result.charAt(0));
            }

        }

        System.out.println(total);

    }
    
}
