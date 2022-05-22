import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BipartiteMatching {
    //array holding of nodes
    //looks like [source, bipartite nodes..., sink]
    private Node[] graph;
    private int SOURCE;
    private int SINK;

    public void fordFulkerson() {
        while (true) {
            Node[] levelGraph = new Node[graph.length];
            if (!createLG(levelGraph)) { break; }
            Node curr = levelGraph[levelGraph[SOURCE].getNext()];
            Stack<Integer> path = new Stack<>();
            while (curr != levelGraph[SOURCE] && curr.getNext() != -1) {
                if (curr == graph[SINK]) {
//                    Augment flow with path
//                    Update Gf
//                    Delete edges in path from LG
                    int to = path.pop();
                    for (int i = 0; i < path.size(); i++) {
                        levelGraph[path.peek()].removeEdge(to);
                        to = path.pop();
                    }
//                    Set location to source
                    curr = levelGraph[SOURCE];
//                    Clear path
                    path.clear();
                } else if (curr.getNext() != -1) {
//                    Delete current node and incoming edges from LG
//                    Delete last edge from path
                    int removedNode = path.pop();
                    curr = levelGraph[path.peek()];
                    for (int i = 1; i < levelGraph.length - 1; i++) {
                        levelGraph[i].removeEdge(removedNode);
                    }
                } else {
//                    Advance along some edge in LG that leaves current location
//                    Update current path
                    path.push(curr.getNext());
                    curr = levelGraph[curr.getNext()];
                }
            }
        }
    }

    public boolean createLG(Node[] levelGraph) {
//        Construct level graph LG from Gf using breadth-first search (delete back and cross edges).
//        If no path exists from source to sink (i.e., sink not found during BFS), output matching, done.
//        Initialize location to source node, path to empty.
        System.out.println("lovable");
        return false;
    }

    public void initializeData(String fileName) {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            int n = in.nextInt();
            graph = new Node[n + 2];
            SOURCE = 0;
            SINK = n + 1;
            graph[SOURCE] = new Node("source");
            graph[SINK] = new Node("sink");
            for (int i = 1; i <= n; i++) {
                graph[i] = new Node(in.nextLine());
                graph[SOURCE].addEdge(i);
                graph[i].addEdge(SINK);
            }
            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                graph[in.nextInt()].addEdge(in.nextInt());
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
}
