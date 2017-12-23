import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


class day06_02 {


    public static void main(String[] args) {

        // get stacking blocks 
        Integer[] blocks = getBlocks("input.txt");
        System.out.print("Original Blocks: ");
        for (Integer i : blocks) {
            System.out.print(i + " ");
        }

        // create arraylist to hold previous block patterns
        ArrayList<String> blockPatterns = new ArrayList<String>();

        // keep rearranging while the current block pattern has not been seen
        Integer loopCount = 0;
        while (!blockPatterns.contains(Arrays.toString(blocks))) {
            // add current blocks pattern to the arraylist
            blockPatterns.add(Arrays.toString(blocks));
            // cycle the blocks
            blocks = cycleBlocks(blocks);
            // increment loop counter
            loopCount++;
        }
        
        System.out.println("\nLoop Count: " + loopCount);

        // reset arraylist for part 2
        blockPatterns = new ArrayList<String>();

        Integer cycleCount = 0;

        while (!blockPatterns.contains(Arrays.toString(blocks))) {
            // add current blocks pattern to the arraylist
            blockPatterns.add(Arrays.toString(blocks));
            // cycle the blocks
            blocks = cycleBlocks(blocks);
            // increment loop counter
            cycleCount++;
        }

        System.out.println("Cycle Count: " + cycleCount);

    }


    public static Integer[] getBlocks(String filename) {

        Integer[] blocks = null;

        try {  

            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] stringBlocks = br.readLine().split("\\s+");
            blocks = new Integer[stringBlocks.length];
            for (int i = 0; i < stringBlocks.length; i++) {
                blocks[i] = Integer.parseInt(stringBlocks[i]);
            }
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blocks;

    }


    public static Integer[] cycleBlocks(Integer[] blocks) {

        Integer largestBlock = 0;
        Integer toRedistribute = null;

        // find largest block
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] > blocks[largestBlock]) {
                largestBlock = i;
            }
        }

        // redistribute to other blocks
        toRedistribute = blocks[largestBlock];
        blocks[largestBlock] = 0;
        while (toRedistribute > 0) {
            largestBlock++;
            if (largestBlock >= blocks.length) {
                largestBlock = 0;
            }
            blocks[largestBlock]++;
            toRedistribute--;
        }

        return blocks;
    }

}
