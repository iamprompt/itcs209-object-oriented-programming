// Supakarn Laorattanakul Section 3 ID:6288087

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sorting {
    public static void main(String[] args) {
        List<String> words = readFile("words.txt"); // Read file

        // Print Original
        System.out.print("Original: ");
        printWords(words);

        // Sort and Print
        sortWords(words);
    }

    private static List<String> readFile(String fileName) {
        File file = new File(fileName);
        try {
            String wordsString = FileUtils.readFileToString(file, "UTF-8"); // Read Text from file
            String[] wordsArr = wordsString.split(" "); // Delimited by White Space Character
            List<String> wordsList = new ArrayList<>(); // Declare new ArrayList
            for (String word : wordsArr) {
                wordsList.add(word); // Add words in ArrayList
            }
            return wordsList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void printWords(List<String> words) {
        System.out.print("[");
        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i));
            if (i != words.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Selection Sort
    private static void sortWords(List<String> words) {
        int round = 1; // Initialize Round
        for (int i = 0; i < words.size(); i++) {
            boolean haveChange = false; // Declare state change
            for (int j = 0; j < words.size(); j++) {
                // Fixed Letter word i and compare with word index j => if have more i < j => j swap to i (for all j)
                if ((i < j && words.get(i).compareTo(words.get(j)) < 0) || (i > j && words.get(i).compareTo(words.get(j)) > 0)) {
                    // Swapping
                    String temp = words.get(i);
                    words.set(i, words.get(j));
                    words.set(j, temp);
                    // Set state changed
                    haveChange = true;
                }
            }
            if (!haveChange) break; // If no change, not print
            // Print Result
            System.out.print("Pass " + round++ + ": ");
            printWords(words);
        }
    }
}
