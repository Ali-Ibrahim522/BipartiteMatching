public class Main {
    public static void main(String[] args) {
        BipartiteMatching bm = new BipartiteMatching();
        bm.initializeData("program3data.txt");
        bm.fordFulkerson();
    }
}
