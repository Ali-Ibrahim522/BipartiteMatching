public class Main {
    public static void main(String[] args) {
        BipartiteMatching bm = new BipartiteMatching();
        TestMaker.createTest(100);
        bm.initializeData("program3data.txt");
        bm.maxMatches();
    }
}
