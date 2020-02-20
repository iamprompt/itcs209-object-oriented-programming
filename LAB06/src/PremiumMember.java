import java.util.ArrayList;

public class PremiumMember extends Member {
    private double fee;
    private static double Family_FEE = 80.00;
    private ArrayList<String> family;

    public PremiumMember(String email, String password, double fee) {
        super(email, password);
        this.fee = fee;
        family = new ArrayList<>();
    }

    @Override
    public void printMemberInfo() {
        System.out.println("---- PREMIUM MEMBER ----");
        System.out.println("Member fee: " + fee);
        super.printMemberInfo();
        if (family.size() > 0) {
            System.out.println("---------------------");
            System.out.println("List of Family");
            for (int i = 0; i < family.size(); i++) {
                if (i != 0) System.out.print(", ");
                if (i != family.size() - 1) System.out.print(family.get(i));
                else System.out.println(family.get(i));
            }

        }
    }

    public boolean addFamily(String username) {
        if (username != null) {
            if (family.size() < 2) {
                family.add(username);
                System.out.println(username + " is added successfully.");
                return true;
            } else {
                System.out.println("user: " + username + " cannot be added, the Family user is reached the limit");
                return false;
            }
        } else return false;
    }

    public boolean removeFamily(String username) {
        int iUser = 0;
        boolean userExist = false;
        for (; iUser < family.size(); iUser++) {
            if (username.equals(family.get(iUser))) {
                userExist = true;
                break;
            }
        }
        if (userExist == true) {
            family.remove(username);
            System.out.println(username + " is removed successfully.");
            return true;
        } else {
            System.out.println("user: " + username + " does not exits and cannot be removed.");
            return false;
        }

    }

    public double getMonthlyBill() {
        return fee + Family_FEE;
    }
}
