import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;


public class day04_02 {

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
                    // if the string is already added, then the passphrase is no good
                    if (!usedWords.add(s)) {
                        validPassphrase = false;
                    }
                    // add all possible combinations for next loop
                    for (String st: getCombinations(s)) {
                        usedWords.add(st);
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

    public static String[] getCombinations(String word) {
        ArrayList<String> ans = new ArrayList<String>();
        if (word.length() <= 1) {
            ans.add(word);
        }
        else if (word.length() == 2) {
            ans.add(word);
            ans.add("" + word.charAt(1) + word.charAt(0));
        }
        else {
            for (int i = 0; i < word.length(); i++) {
                for (String s: getCombinations(word.substring(0, i) + word.substring(i+1))) {
                    ans.add(word.charAt(i) + s);
                }
            }
        }
        return ans.toArray(new String[ans.size()]);
    }

}
