public class FreeMember extends Member {
    private final int FREE_LIMITED_VDOs = 3;
    private static int numDownloadedVDO = 0;

    public FreeMember(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean addVideo(Video vdo) {
        if (numDownloadedVDO < FREE_LIMITED_VDOs) {
            super.addVideo(vdo);
            numDownloadedVDO++;
            return true;
        } else {
            System.out.println(vdo.toString() + " cannot be downloaded because the number of video is reaching the limit.");
            return false;
        }
    }

    @Override
    public boolean removeVideo(Video vdo) {
        boolean REMOVE = super.removeVideo(vdo);
        if (REMOVE == true) {
            numDownloadedVDO--;
            return true;
        } else  return false;
    }

    @Override
    public void printMemberInfo() {
        System.out.println("---- FREE MEMBER ----");
        super.printMemberInfo();
    }

    public int getNumVideo() {
        return numDownloadedVDO;
    }
}
