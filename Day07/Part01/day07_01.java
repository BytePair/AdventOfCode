import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Map;
import java.util.HashMap;

class day07_01 {


    public static void main (String[] args) {

        System.out.println("Starting Day 07 Part 01...");

        HashMap<String, Program> programs = getPrograms("input.txt");

        System.out.println("Got programs...");

        // finding the base program by finding the program with the largest depth
        Integer biggestDepth = -1;
        Program baseProgram = null;

        for (Map.Entry<String, Program> entry : programs.entrySet()) {
            // System.out.println("New Program: " + entry.getKey());
            // entry.getValue().print();
            // System.out.println("Depth: " + getDepth(entry.getValue(), programs));
            Integer currentDepth = getDepth(entry.getValue(), programs);
            if (currentDepth > biggestDepth) {
                biggestDepth = currentDepth;
                baseProgram = entry.getValue();
            }
        };

        System.out.println("Base Program: " + baseProgram.getName());

    }


    public static HashMap<String, Program> getPrograms(String filename) {
        HashMap<String, Program> programs = new HashMap<String, Program>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String next = br.readLine();
            while (next != null) {
                String[] stringProgram = next.split("\\W+");
                Program temp;
                if (stringProgram.length == 1) {
                    temp = new Program(stringProgram[0], null, null);
                } else if (stringProgram.length == 2) {
                    temp = new Program(stringProgram[0], Integer.valueOf(stringProgram[1]), null);
                } else if (stringProgram.length > 2) {
                    temp = new Program(stringProgram[0], Integer.valueOf(stringProgram[1]), Arrays.copyOfRange(stringProgram, 2, stringProgram.length));
                } else {
                    temp = null;
                }
                programs.put(temp.getName(), temp);
                next = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (programs.size() == 0) return null;
        return programs;
    }


    public static Integer getDepth(Program p, HashMap<String, Program> programs) {
        String[] stack = p.getStackedPrograms();
        if (stack == null) {
            return 0;
        } else {
            Integer biggestDepth = 0;
            for (String s : stack) {
                Program p2 = programs.get(s);
                Integer depth = getDepth(p2, programs);
                biggestDepth = (depth > biggestDepth) ? depth : biggestDepth;
            }
            return biggestDepth + 1;
        }
    }
    
}


class Program {

    private String mName;
    private Integer mWeight;
    private String[] mStackedPrograms;

    public Program(String name, Integer weight, String[] programs) {
        this.mName = name;
        this.mWeight = weight;
        this.mStackedPrograms = programs;
    }

    public String getName() {
        return mName;
    }

    public String[] getStackedPrograms() {
        return mStackedPrograms;
    }

    public void print() {
        System.out.println("Name: " + mName);
        System.out.println("Weight: " + mWeight);
        if (mStackedPrograms != null) {
            System.out.print("Stack: [ ");
            for (String s : mStackedPrograms) {
                System.out.print(s + " ");
            }
            System.out.print("]\n\n");
        } else {
            System.out.print("\n\n");
        }
    }

}
