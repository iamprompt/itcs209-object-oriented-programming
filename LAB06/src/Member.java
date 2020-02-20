import java.util.ArrayList;

public class Member {
    private String email;
    private String password;
    private ArrayList<Video> vdoList;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
        vdoList = new ArrayList<>();
    }

    public boolean addVideo(Video vdo) {
        if (vdo != null) {
            vdoList.add(vdo);
            return true;
        } else return false;
    }

    public boolean removeVideo(Video vdo) {
        int iVDO = 0;
        boolean VDOexist = false;
        for (; iVDO < vdoList.size(); iVDO++) {
            if (vdo.isEqual(vdoList.get(iVDO))) {
                VDOexist = true;
                break;
            }
        }
        if (VDOexist == true) {
            vdoList.remove(iVDO);
            System.out.println(vdo.toString() + " is successfully removed.");
            return true;
        } else return false;
    }

    public void printMemberInfo() {
        System.out.println("Email: " + email + " (pwd: " + password + ")");
        System.out.println("List of Videos");
        for (int i = 0; i < vdoList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + vdoList.get(i).toString());
        }
    }
}
