import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word or phrase to check if it is a palindrome: ");
            String word = in.nextLine();
            String CVlower = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); // Remove Special Char & Change String to Array
            char[] letter = CVlower.toCharArray(); // Convert String to Array
            int counter = 0; // Match
            int Wlength = letter.length; // Length of String

            for (int i = 0; i < Wlength / 2; i++) if (letter[i] == letter[Wlength - 1 - i]) counter++;

            // Print Result
            System.out.print("The input word \"" + word + "\" is");
            if (counter != Wlength / 2) System.out.print(" not");
            System.out.println(" a palindrome\n");
        }
    }
}
