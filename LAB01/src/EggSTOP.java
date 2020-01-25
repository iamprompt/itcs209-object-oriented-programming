public class EggSTOP {
    public static void main(String[] args) {
        EGG(1);
        BLANK();

        EGG(1);
        DIV();
        BLANK();

        EGG(2);
        DIV();
    }

    public static void EGG(int n) {
        System.out.println("  ______");
        System.out.println(" /      \\");
        System.out.println("/        \\");
        if(n != 1) System.out.println("|  STOP  |");
        System.out.println("\\        /");
        System.out.println(" \\______/");
    }

    public static void BLANK() {
        System.out.println("");
    }

    public static void DIV() {
        System.out.println("+--------+");
    }
}
