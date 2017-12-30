import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class day08_02 {


    public static void main(String[] args) {


        HashMap<String, Integer> registers = new HashMap<String, Integer>();
        Integer largestOverallRegister = Integer.MIN_VALUE;
        
        try {

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();

            while (line != null) {
                String[] splitLine = line.split("\\s+");
                // System.out.println(Arrays.toString(splitLine));

                // set registers to 0 if they don't exist
                registers.putIfAbsent(splitLine[0], 0);
                registers.putIfAbsent(splitLine[4], 0);

                // if evaluation is true, inc or dec the first register value
                if (evaluate(registers.get(splitLine[4]), splitLine[5], Integer.valueOf(splitLine[6]))) {
                    // System.out.print(String.valueOf(registers.get(splitLine[0])) + " " + splitLine[1] + " " + splitLine[2] + " ");
                    if (splitLine[1].equals("inc")) {
                        registers.put(splitLine[0], registers.get(splitLine[0]) + Integer.parseInt(splitLine[2]));
                    } 
                    if (splitLine[1].equals("dec")) {
                        registers.put(splitLine[0], registers.get(splitLine[0]) - Integer.parseInt(splitLine[2]));
                    }
                    // System.out.println(" = " + String.valueOf(registers.get(splitLine[0])));
                    if (registers.get(splitLine[0]) > largestOverallRegister) largestOverallRegister = registers.get(splitLine[0]);
                };

                // get next line
                line = br.readLine();
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // find the largest register
        Integer largestCurrentRegister = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : registers.entrySet()) {
            if (entry.getValue() > largestCurrentRegister) {
                largestCurrentRegister = entry.getValue();
            }
        }

        // print results
        System.out.println("Largest Register Overall: " + String.valueOf(largestOverallRegister));
        System.out.println("Largest Register Currently: " + String.valueOf(largestCurrentRegister));

    }


    private static boolean evaluate(Integer first, String comparison, Integer second) {
        // System.out.println(String.valueOf(first) + " " + comparison + " " + String.valueOf(second));
        boolean result = false;
        switch (comparison) {
            case "<":
                if (first < second) {
                    result = true;
                }
                break;
            case ">":
                if (first > second) {
                    result = true;
                }
                break;
            case "<=":
                if (first <= second) {
                    result = true;
                }
                break;
            case ">=":
                if (first >= second) {
                    result = true;
                }
                break;
            case "==":
                if (first.equals(second)) {
                    result = true;
                }
                break;
            case "!=":
                if (!first.equals(second)) {
                    result = true;
                }
                break;
            default:
                System.out.println("Error: Could not find: '" + comparison + "'");
                break;
        }

        // System.out.println("Result: " + result);
        return result;
    }


}
