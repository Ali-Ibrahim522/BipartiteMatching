import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
            path.add(SOURCE);
            while (curr != levelGraph[SOURCE] || curr.getNext() != -1) {
                if (curr == levelGraph[SINK]) {
                    int to = path.pop();
                    for (int i = 0; i < path.size(); i++) {
                        levelGraph[path.peek()].removeEdge(to);
                        graph[path.peek()].removeEdge(to);
                        graph[to].addEdge(path.peek());
                        to = path.pop();
                    }
                    curr = levelGraph[SOURCE];
                    path.clear();
                    path.add(0);
                } else if (curr.getNext() == -1) {
                    int removedNode = path.pop();
                    curr = levelGraph[path.peek()];
                    for (int i = 1; i < levelGraph.length - 1; i++) {
                        levelGraph[i].removeEdge(removedNode);
                    }
                } else {
                    path.push(curr.getNext());
                    curr = levelGraph[curr.getNext()];
                }
            }
        }
        outputMatchings();
    }

    public void outputMatchings() {
        for (int i = 1; i < SINK - 1; i++) {
            int match = graph[i].getNext();
            if (match != -1) {
                System.out.println(graph[i].getName() + " / " + graph[match].getName());
            }
        }
    }

    public boolean createLG(Node[] levelGraph) {
//        Construct level graph LG from Gf using breadth-first search (delete back and cross edges).
//        If no path exists from source to sink (i.e., sink not found during BFS), output matching, done.
//        Initialize location to source node, path to empty.
        HashSet<Integer> visited = new HashSet<Integer>();
        Queue<Integer> bfs = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            levelGraph[i] = new Node(graph[i].getName());
        }
        boolean foundSink = false;
        bfs.add(0);
        while (!bfs.isEmpty()) {
            int curr = bfs.peek();
            bfs.remove();
            if (curr == SINK) { foundSink = true; }
            if (!visited.contains(curr)) {
                visited.add(curr);
                Object[] currEdges = graph[curr].getEdges();
                for (Object currEdge : currEdges) {
                    levelGraph[curr].addEdge((int) currEdge);
                    bfs.add((int) currEdge);
                }
            }
        }
        return foundSink;
    }

    public void initializeData(String fileName) {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            int n = in.nextInt();
            in.nextLine();
            System.out.println(n);
            graph = new Node[n + 2];
            SOURCE = 0;
            SINK = n + 1;
            graph[SOURCE] = new Node("source");
            graph[SINK] = new Node("sink");
            int mid = n / 2;
            for (int i = 1; i <= mid; i++) {
                graph[i] = new Node(in.nextLine());
                System.out.println(graph[i].getName());
                graph[SOURCE].addEdge(i);
            }
            for (int i = mid + 1; i <= n; i++) {
                graph[i] = new Node(in.nextLine());
                System.out.println(graph[i].getName());
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
