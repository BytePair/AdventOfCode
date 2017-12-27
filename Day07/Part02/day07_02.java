import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Map;
import java.util.HashMap;


class day07_02 {
    

    public static void main (String[] args) {

        System.out.println("Starting Day 07 Part 02...");

        HashMap<String, Program> programs = getPrograms("input.txt");

        System.out.println("Got programs...");

        // finding the base program by finding the program with the largest depth
        Integer biggestDepth = -1;
        Program baseProgram = null;

        for (Map.Entry<String, Program> entry : programs.entrySet()) {
            Integer currentDepth = entry.getValue().getDepth(programs);
            if (currentDepth > biggestDepth) {
                biggestDepth = currentDepth;
                baseProgram = entry.getValue();
            }
        };

        System.out.println("Base Program: " + baseProgram.getName());

        // next we need to start at the base and find all nodes that are unbalanced
        ArrayList<Program> unbalancedPrograms = new ArrayList<Program>();
        unbalancedPrograms.add(baseProgram);
        Program nextUnbalancedProgram = baseProgram.getNextUnbalanced(programs);
        while (nextUnbalancedProgram != null) {
            unbalancedPrograms.add(nextUnbalancedProgram);
            nextUnbalancedProgram = nextUnbalancedProgram.getNextUnbalanced(programs);
        }

        // get bad weight
        Integer badWeight = unbalancedPrograms.get(unbalancedPrograms.size() - 1).getTotalWeight(programs);
        System.out.println("Bad Weight: " + String.valueOf(badWeight));

        // get good weight
        Integer goodWeight = null;
        for (String s : unbalancedPrograms.get(unbalancedPrograms.size() - 2).getStackedPrograms()) {
            goodWeight = programs.get(s).getTotalWeight(programs);
            if (goodWeight != badWeight) {
                break;
            }
        }
        System.out.println("Target Weight: " + String.valueOf(goodWeight));

        // find difference between expected and actual weights, and add to the base weight
        Integer difference = goodWeight - badWeight;
        Integer baseWeight = unbalancedPrograms.get(unbalancedPrograms.size() - 1).getSpecificWeight();
        System.out.println("Expected Base Value: " + String.valueOf(baseWeight + difference));

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

    public Integer getDepth(HashMap<String, Program> programs) {
        if (mStackedPrograms == null) {
            return 0;
        } else {
            Integer biggestDepth = 0;
            for (String s : mStackedPrograms) {
                Program p2 = programs.get(s);
                Integer depth = p2.getDepth(programs);
                biggestDepth = (depth > biggestDepth) ? depth : biggestDepth;
            }
            return biggestDepth + 1;
        }
    }

    public Integer getSpecificWeight() {
        return mWeight;
    }

    public Integer getTotalWeight(HashMap<String, Program> programs) {
        if (mStackedPrograms == null) {
            return this.getSpecificWeight();
        } else {
            Integer total = this.getSpecificWeight();
            for (String s : mStackedPrograms) {
                total += programs.get(s).getTotalWeight(programs);
            }
            return total;
        }
    }

    public Boolean isBalanced(HashMap<String, Program> programs) {
        if (mStackedPrograms == null) {
            return true;
        } else {
            Integer weight = null;
            Integer weightCompare = null;
            for (String s : mStackedPrograms) {
                if (weight == null) {
                    weight = programs.get(s).getTotalWeight(programs);
                } else {
                    weightCompare = programs.get(s).getTotalWeight(programs);
                    if (weight != weightCompare) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public Program getNextUnbalanced(HashMap<String, Program> programs) {
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        Integer[] weights = new Integer[mStackedPrograms.length];
        for (int i = 0; i < mStackedPrograms.length; i++) {
            Integer weight = programs.get(mStackedPrograms[i]).getTotalWeight(programs);
            weights[i] = weight;
            if (counts.containsKey(weight)) {
                counts.put(weight, counts.get(weight)+1);
            } else {
                counts.put(weight, 1);
            }
        }
        for (int i = 0; i < weights.length; i++) {
            if (counts.get(weights[i]) == 1) {
                return programs.get(mStackedPrograms[i]);
            }
        }
        return null;
    }

}
