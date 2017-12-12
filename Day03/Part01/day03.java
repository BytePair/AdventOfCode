import java.io.BufferedReader;
import java.io.FileReader;


public class day03 {

    public static void main(String[] args) {

        // get target from input file
        int target = getTarget("input.txt");
        System.out.println("Target: " + String.valueOf(target));

        // find required size
        int size = getSize(target);
        System.out.println("Size: " + String.valueOf(size));

        // get exit location
        int[] exit_location = getExit(size);
        System.out.println("Exit Location: [" + String.valueOf(exit_location[0]) + ", " + String.valueOf(exit_location[0]) + "]");

        // get target location
        int[] target_location = getTarget(size, target);
        System.out.println("Target Location: [" + String.valueOf(target_location[0]) + ", " + String.valueOf(target_location[1]) + "]");

        // get distance to exit
        int exit_distance = getDistance(target_location, exit_location);
        System.out.println("Manhattan Distance: " + String.valueOf(exit_distance));

    }


    public static int getTarget(String filename) {
        int target = -1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            target = Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }


    public static int getSize(int target) {
        int t = 1;
        while (t*t < target) {
            t += 2;
        }
        return t;
    }


    public static int[] getExit(int size) {
        int[] loc = new int[2];
        loc[0] = loc[1] = size / 2;
        return loc;
    }


    public static int[] getTarget(int size, int target) {
        int current_node = size * size;
        int[] loc = {0, 0};
        // check bottom row
        for (int i = size - 1; i > 0; i--) {
            if (current_node == target) {
                loc[0] = size - 1;
                loc[1] = i;
            }
            current_node--;
        }
        // check left row
        for (int i = size - 1; i > 0; i--) {
            if (current_node == target) {
                loc[0] = i;
                loc[1] = 0;
            }
            current_node--;
        }
        // check top row
        for (int i = 0; i < size - 1; i++) {
            if (current_node == target) {
                loc[0] = 0;
                loc[1] = i;
            }
            current_node--;
        }
        // check right row
        for (int i = 0; i < size - 1; i++) {
            if (current_node == target) {
                loc[0] = i;
                loc[1] = size - 1;
            }
            current_node--;
        }
        return loc;
    }


    public static int getDistance(int[] target_loc, int[] exit_loc) {
        return Math.abs(target_loc[0] - exit_loc[0]) + Math.abs(target_loc[1] - exit_loc[1]);
    }

} 
