import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;


public class day04_01 {

    public static void main(String[] args) {

        try {

            int validPassphraseCount = 0;

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));

            String nextLine = br.readLine();

            while (nextLine != null) {
                Boolean validPassphrase = true;
                String[] splitLine = nextLine.split("\\W+");
                HashSet<String> usedWords = new HashSet<String>();
                for (String s: splitLine) {
                    if (!usedWords.add(s)) {
                        validPassphrase = false;
                    }
                }
                if (validPassphrase) {
                    validPassphraseCount += 1;
                }
                nextLine = br.readLine();
            }

            System.out.println("Answer: " + validPassphraseCount);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
