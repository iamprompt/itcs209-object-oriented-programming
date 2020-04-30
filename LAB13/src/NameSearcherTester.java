
public class NameSearcherTester {

    public static void main(String[] args) {
        /*
         * ***************** NORMAL****************
         * [Linear-Case1] Found: 'Zebra' AT_INDEX(0) >>> Number of Comparisons (Linear):1
         * [Linear-Case2] Found: 'ant' AT_INDEX(3) >>> Number of Comparisons (Linear):4
         * [Linear-Case3] Not Found Name: 'tiger' >>> Number of Comparisons (Linear):6
         * [Linear-Case4] Found: 'Monkey D. Luffy' AT_INDEX(500) >>> Number of Comparisons (Linear):501
         * [Linear-Case5] Not Found Name: 'Monkey' >>> Number of Comparisons (Linear):1177
         * [Linear-Case6] Found: 'trafalgar d. water law' AT_INDEX(1079) >>> Number of Comparisons (Linear):1080
         * [Linear-Case7] Not Found Name: 'Yonta Maria Grand Fleet' >>> Number of Comparisons (Linear):1177
         */

        System.out.println("***************** NORMAL****************");
        NameSearcher linearA = new LinearNameSearcher("test.txt");
        System.out.print("[Linear-Case1] " + linearA.find("Zebra"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearA.getNumComparisons() + "\n");

        linearA.resetCompareCounter();
        System.out.print("[Linear-Case2] " + linearA.find("ant"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearA.getNumComparisons() + "\n");

        linearA.resetCompareCounter();
        System.out.print("[Linear-Case3] " + linearA.find("tiger"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearA.getNumComparisons() + "\n");


        NameSearcher linearB = new LinearNameSearcher("unsortedName.txt");
        System.out.print("[Linear-Case4] " + linearB.find("Monkey D. Luffy"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearB.getNumComparisons() + "\n");

        linearB.resetCompareCounter();
        System.out.print("[Linear-Case5] " + linearB.find("Monkey"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearB.getNumComparisons() + "\n");

        linearB.resetCompareCounter();
        System.out.print("[Linear-Case6] " + linearB.find("trafalgar d. water law"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearB.getNumComparisons() + "\n");

        linearB.resetCompareCounter();
        System.out.print("[Linear-Case7] " + linearB.find("Yonta Maria Grand Fleet"));
        System.out.println(" >>> Number of Comparisons (Linear):" + linearB.getNumComparisons() + "\n");

        //BONUS
        System.out.println("********* BONUS for CRAZY PEOPLE************");

        NameSearcher binaryC = new BinaryNameSearcher("test.txt");

        binaryC.sortWord();
        System.out.print("[Binary-Case1] " + binaryC.find("Zebra"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryC.getNumComparisons() + "\n");

        binaryC.resetCompareCounter();
        System.out.print("[Binary-Case2] " + binaryC.find("ant"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryC.getNumComparisons() + "\n");

        binaryC.resetCompareCounter();
        System.out.print("[Binary-Case3] " + binaryC.find("tiger"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryC.getNumComparisons() + "\n");

        NameSearcher binaryD = new BinaryNameSearcher("unsortedName.txt");
        binaryD.sortWord();
        System.out.print("[Binary-Case4] " + binaryD.find("Monkey D. Luffy"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryD.getNumComparisons() + "\n");

        binaryD.resetCompareCounter();
        System.out.print("[Binary-Case5] " + binaryD.find("Monkey"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryD.getNumComparisons() + "\n");

        binaryD.resetCompareCounter();
        System.out.print("[Binary-Case6] " + binaryD.find("trafalgar d. water law"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryD.getNumComparisons() + "\n");

        binaryD.resetCompareCounter();
        System.out.print("[Binary-Case7] " + binaryD.find("Yonta Maria Grand Fleet"));
        System.out.println(" >>> Number of Comparisons (Binary):" + binaryD.getNumComparisons() + "\n");
    }
}
