import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


class day05_01 {


    public static void main (String[] args) {

        // get array of all integer values
        Integer[] jumps = getJumps("input.txt");

        // while in range, keep traversing the array
        int jump = 0;
        int position = 0;
        int loopCount = 0;
        while (position >= 0 && position < jumps.length) {
            loopCount++;
            jump = jumps[position];
            jumps[position] += 1;
            position += jump;
        }

        // print answer
        System.out.println("Number of Loops: " + loopCount);

    }


    public static Integer[] getJumps(String filename) {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            ArrayList<Integer> int_list = new ArrayList<Integer>();
            String currentIntString = br.readLine();
            Integer currentInt = null;
            while (currentIntString != null) {
                currentInt = Integer.parseInt(currentIntString);
                int_list.add(currentInt);
                currentIntString = br.readLine();
            }
            return int_list.toArray(new Integer[int_list.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
