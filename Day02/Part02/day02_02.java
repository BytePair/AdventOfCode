import java.io.*;
import java.util.*; // Required for Arrays.stream()


public class day02_02 {

    public static void main(String[] args) {

        Integer ans = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            String line = br.readLine();

            while (line != null) {

                // map array of strings to integers
                Integer[] nums = Arrays.stream(line.split("\\s+")).map(Integer::valueOf).toArray(Integer[]::new);

                for (Integer i : nums) {
                    for (Integer j : nums) {
                        if (i != j && i % j == 0) {
                            // assuming only one answer per line
                            ans += (i / j);
                        }
                    }
                }
                
                // get next line
                line = br.readLine();
            }

            br.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Answer: " + String.valueOf(ans));

    }

}
