/**
 * Main class used to test BipartiteMatching Class
 * Initializes file object using test file, creates
 * BipartiteMatching object, then tests initializeData() &
 * maxMatches()
 * @author Ali Ibrahim and Omar Shaban
 */

public class Main {
    public static void main(String[] args) {
        BipartiteMatching bm = new BipartiteMatching();
        //TestMaker.createTest(100);
        bm.initializeData("program3basicdata.txt");
        bm.maxMatches();
    }
}
