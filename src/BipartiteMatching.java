import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BipartiteMatching {
    private Node start;
    private Node end;

    public BipartiteMatching() {
        start = new Node("Start");
        end = new Node("end");
    }

    public void fordFulkerson() {

    }

    public void initializeData(String fileName) {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            int n = in.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(in.nextLine());
                start.addEdge(nodes[i]);
                nodes[i].addEdge(end);
            }
            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                nodes[in.nextInt() - 1].addEdge(nodes[in.nextInt() - 1]);
            }
            for (int i = 0; i < n; i++) {
                nodes[i].sortEdges();
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
}
