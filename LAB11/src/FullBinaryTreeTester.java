import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FullBinaryTreeTester {

    public static void inOrderTraverse(Node root) {
        if (root == null) return;
        else {
            inOrderTraverse(root.left);
            System.out.print(root.id + " ");
            inOrderTraverse(root.right);
        }
    }

    public static boolean isFullBinTree(Node root) {
        if (root == null) return true;
        else {
            if (root.left == null && root.right == null) return true;
            else if (root.left == null || root.right == null) return false;
            else return isFullBinTree(root.left) && isFullBinTree(root.right);
        }
    }

    public static void normalTester() {
        Node[] ts = new Node[7];
        int count = 0;
        ts[count++] = null;
        ts[count++] = new Node(16, null, null);

        ts[count++] = new Node(16, new Node(14, null, null), null);

        ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)),
                new Node(4, new Node(8, null, null), new Node(10, null, null)));

        ts[count++] = new Node(1, new Node(3, null, null),
                new Node(4, new Node(8, null, null), new Node(10, null, null)));

        ts[count++] = new Node(1, new Node(3, new Node(6, null, null), null),
                new Node(4, new Node(8, null, null), new Node(10, null, null)));

        ts[count++] = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)),
                null);

        for (int i = 0; i < ts.length; i++) {
            System.out.print("[T" + i + "] in-order: ");
            inOrderTraverse(ts[i]);
            System.out.println("\n[T" + i + "] is" + (isFullBinTree(ts[i]) ? " " : " NOT ") + "a full binary tree.\n");
        }

    }

    /**************BONUS STARTS***************/
    public static void printBinTree(Node root) {
        //YOUR BONUS CODE GOES HERE
        if (root == null) return;
        else {
            for (int i = 1; i <= getLevel(root); i++) {
                printLevel(root, i);
                System.out.println();
            }
        }
    }

    public static int getLevel(Node root) {
        if (root == null) return 0;
        else {
            int leftC = getLevel(root.left);
            int rightC = getLevel(root.right);
            return leftC > rightC ? leftC + 1 : rightC + 1;
        }
    }

    public static void printLevel(Node n, int level) {
        if (n == null) return;
        else if (level == 1) {
            System.out.print(n.id + " ");
            return;
        } else {
            // Enter to that level
            printLevel(n.left, level - 1);
            printLevel(n.right, level - 1);
        }
    }

    public static Node getBinSearchTree(Node root) {    //YOUR BONUS CODE GOES HERE
        List<Integer> btArrayList = BTtoArrayList(root, new ArrayList<Integer>());
        return ArrayListtoBST(btArrayList);
    }

    public static Node ArrayListtoBST(List<Integer> btArray) {
        if (btArray.size() == 0) return null;
        else {
            Collections.sort(btArray);
            int arrSize = btArray.size();
            int mid = arrSize / 2;
            return new Node(btArray.get(mid), ArrayListtoBST(btArray.subList(0, mid)), ArrayListtoBST(btArray.subList(mid + 1, arrSize)));
        }
    }

    public static ArrayList<Integer> BTtoArrayList(Node n, ArrayList<Integer> btArray) {
        if (n != null) {
            btArray.add(n.id);
            BTtoArrayList(n.left, btArray);
            BTtoArrayList(n.right, btArray);
        }
        return btArray;
    }

    public static void bonusTester() {
        Node t = new Node(1, new Node(3, new Node(6, null, null), new Node(7, null, null)),
                new Node(4, new Node(8, null, null), new Node(10, null, null)));
        System.out.println("Before Transforming: ");
        printBinTree(t);
        System.out.println("After Transforming: ");
        printBinTree(getBinSearchTree(t));

    }

    /**************BONUS ENDS***************/


    public static void main(String[] args) {
        normalTester();

        //Uncomment for bonus
        bonusTester();
    }
}
