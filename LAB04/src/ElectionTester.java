import java.util.Scanner;

public class ElectionTester {
    public static void main(String[] args) {
        MyDate election = new MyDate(2019, 3, 24);

        Person a = new Person("Lalisa", "Manoban", 1997, 3, 27);
        printPersonElectionInfo(a, election);

        Person b = new Person("Nuda", "Inter", 2012, 1, 16);
        printPersonElectionInfo(b, election);

        // Create another Person object with your information
        // Print your information, age, and election eligibility.

        Person c = new Person("Supakarn", "Laorattanakul", 1999, 10, 9);
        printPersonElectionInfo(c, election);

        // Write a program to take 3 persons information from the user
        // (Hint: Use scanner and for loop to get input)
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
			System.out.print("Enter firstname: ");
            String fname = in.nextLine();

            System.out.print("Enter lastname: ");
            String lname = in.nextLine();

            System.out.print("Enter year of birthday: ");
            int year = in.nextInt();

            System.out.print("Enter month of birthday: ");
            int month = in.nextInt();

            System.out.print("Enter day of birthday: ");
            int day = in.nextInt();

			in.nextLine();

            Person inp = new Person(fname, lname, year, month, day);
            printPersonElectionInfo(inp, election);
        }

        // Challenge Bonus
        // Instead of taking 3 persons' information, write a program to continuously take input from the user
        // until the user types 'q' to quite the program.

        while (true) {
            System.out.print("Enter firstname or enter 'q' to exit: ");
            String fname = in.nextLine();

            if (fname.equals("q") || fname.equals("Q")) break;

            System.out.print("Enter lastname: ");
            String lname = in.nextLine();

            System.out.print("Enter year of birthday: ");
            int year = in.nextInt();

            System.out.print("Enter month of birthday: ");
            int month = in.nextInt();

            System.out.print("Enter day of birthday: ");
            int day = in.nextInt();

            in.nextLine();

            Person inp = new Person(fname, lname, year, month, day);
            printPersonElectionInfo(inp, election);

        }
        in.close();
        System.out.println("Exit the program. Thank you.");
    }

    public static void printPersonElectionInfo(Person p, MyDate election) {
        p.printPersonInfo();
        System.out.println("Age: " + p.getAge(election));
        if (p.isEligible(election))
            System.out.println("This person is eligible to vote.");
        else
            System.out.println("This person is NOT eligible to vote");

        System.out.println("-----------------------------------");
    }
}
