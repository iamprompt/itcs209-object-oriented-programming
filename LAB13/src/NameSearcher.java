import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.io.FileUtils;

public abstract class NameSearcher {

    protected static ArrayList<String> readNames = null;
    protected int number_of_compared = 0;

    NameSearcher(String filename) {
        try {
            readNames = (ArrayList<String>) FileUtils.readLines(new File(filename), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //clean word
        readNames.replaceAll(name -> name.toLowerCase());
    }

    public int getNumComparisons() {
        return number_of_compared;
    }

    public void resetCompareCounter() {
        number_of_compared = 0;
    }

    public void sortWord() {
        Collections.sort(readNames);
    }

    public abstract String find(String query);


}

class LinearNameSearcher extends NameSearcher {

    LinearNameSearcher(String filename) {
        super(filename);
    }

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

    @Override
    public String find(String query) {
        for (int index = 0; index < readNames.size(); index++) {
            number_of_compared++;
            if (query.compareToIgnoreCase(readNames.get(index)) == 0)
                return "Found: '" + query + "' AT_INDEX(" + index + ")";
        }
        return "Not Found Name: '" + query + "'";
    }
}

class BinaryNameSearcher extends NameSearcher {

    BinaryNameSearcher(String filename) {
        super(filename);
        this.sortWord();
    }

    @Override
    public String find(String query) {
        int min = 0, mid = 0, max = readNames.size() - 1;
        int compare;
        do {
            number_of_compared++;
            mid = (min + max) / 2;
            compare = query.compareToIgnoreCase(readNames.get(mid));
            if (compare == 0)
                return "Found: '" + query + "' AT_INDEX(" + mid + ")";
            else if (compare < 0)
                max = mid - 1;
            else min = mid + 1;
        } while (max >= min);
        return "Not Found Name: '" + query + "'";
    }
}