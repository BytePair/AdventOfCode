import java.io.BufferedReader;
import java.io.FileReader;


public class day03_02 {

    public static void main (String[] args) {

        int target = -1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            target = Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Find the biggest matrix we will need
        int maxWidth = findMaxWidth(target);
        System.out.println("Target: " + target);
        System.out.println("Max Width: " + maxWidth);

        // Build the starting matrix
        int[][] matrix = new int[maxWidth][maxWidth];

        // Keep track of current number, position, and current loop size
        int currentNumber = 1;
        int xPos = maxWidth / 2;
        int yPos = maxWidth / 2;
        int loopSize = 1;
        matrix[xPos][yPos] = currentNumber;

        // Keep adding numbers to matrix until we reach the target
        while (currentNumber < target) {
            loopSize += 2;
            // move cursor right to start
            yPos += 1;
            // right column
            for (int i = 0; i < loopSize - 1; i++) {
                matrix[xPos][yPos] = currentNumber = addNeighbors(xPos, yPos, matrix);
                if (currentNumber > target) { 
                    break; 
                }
                if (i < loopSize - 2) { 
                    xPos -= 1; 
                };
            };
            if (currentNumber > target) { 
                break; 
            };
            // top row
            yPos -= 1;
            for (int i = 0; i < loopSize - 1; i++) {
                matrix[xPos][yPos] = currentNumber = addNeighbors(xPos, yPos, matrix);
                if (currentNumber > target) { 
                    break; 
                }
                if (i < loopSize - 2) { 
                    yPos -= 1; 
                };
            };
            if (currentNumber > target) { 
                break; 
            };
            // left column
            xPos += 1;
            for (int i = 0; i < loopSize - 1; i++) {
                matrix[xPos][yPos] = currentNumber = addNeighbors(xPos, yPos, matrix);
                if (currentNumber > target) { 
                    break; 
                }
                if (i < loopSize - 2) { 
                    xPos += 1; 
                };
            };
            if (currentNumber > target) { 
                break; 
            };
            // bottom row
            yPos += 1; 
            for (int i = 0; i < loopSize - 1; i++) {
                matrix[xPos][yPos] = currentNumber = addNeighbors(xPos, yPos, matrix);
                if (currentNumber > target) { 
                    break; 
                }
                if (i < loopSize - 2) { 
                    yPos += 1; 
                };
            };
        };

        // Print the answer
        System.out.println("Answer: " + currentNumber);
            
    };

    private static int findMaxWidth(int t) {
        int size = 1;
        while (t > size*size) {
            size += 2;
        }
        return size;
    }

    private static int addNeighbors(int x, int y, int[][] matrix) {
        int runningTotal = matrix[x][y];
        runningTotal += matrix[x][y-1];
        runningTotal += matrix[x][y+1];
        runningTotal += matrix[x-1][y-1];
        runningTotal += matrix[x-1][y];
        runningTotal += matrix[x-1][y+1];
        runningTotal += matrix[x+1][y-1];
        runningTotal += matrix[x+1][y];
        runningTotal += matrix[x+1][y+1];
        return runningTotal;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[ ");
            for (int k = 0; k < matrix[i].length; k++) {
                System.out.print(" " + matrix[i][k] + ", ");
            }
            System.out.print(" ]\n");
        }
    }

}
