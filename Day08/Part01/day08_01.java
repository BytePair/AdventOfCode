import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class day08_01 {


    public static void main(String[] args) {


        HashMap<String, Integer> registers = new HashMap<String, Integer>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] splitLine = line.split("\\s+");
                System.out.println(Arrays.toString(splitLine));
                // set registers to 0 if they don't exist
                registers.putIfAbsent(splitLine[0], 0);
                registers.putIfAbsent(splitLine[4], 0);
                // if evaluation is true, inc or dec the first register value
                if (evaluate(registers.get(splitLine[4]), splitLine[5], Integer.valueOf(splitLine[6]))) {
                    // System.out.print(String.valueOf(registers.get(splitLine[0])) + " " + splitLine[1] + " " + splitLine[2] + " ");
                    if (splitLine[1].equals("inc")) {
                        registers.put(splitLine[0], registers.get(splitLine[0]) + Integer.parseInt(splitLine[2]));
                        
                    } else {
                        registers.put(splitLine[0], registers.get(splitLine[0]) - Integer.parseInt(splitLine[2]));
                    }
                    // System.out.println(" = " + String.valueOf(registers.get(splitLine[0])));
                };
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // find the largest register
        Integer largestRegister = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : registers.entrySet()) {
            if (entry.getValue() > largestRegister) {
                largestRegister = entry.getValue();
            }
        }

        // print result
        System.out.println("Largest Register: " + String.valueOf(largestRegister));

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
                if (first == second) {
                    result = true;
                }
                break;
            case "!=":
                if (first != second) {
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
