import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BipartiteMatching {
    private Node[] graph; //array holding residual graph, looks like [source, bipartite nodes..., sink]
    private int N; // number of nodes (not including source/sink)
    private int SOURCE; // index of source node in residual graph array
    private int SINK; // index of sink node in residual graph array

    public void maxMatches() {
        while (true) {
            Node[] levelGraph = new Node[graph.length];
            if (!createLG(levelGraph)) { break; }
            Node curr = levelGraph[SOURCE];
            Stack<Integer> path = new Stack<>();
            path.add(SOURCE);
            while (curr != levelGraph[SOURCE] || curr.getNext() != -1) {
                if (curr == levelGraph[SINK]) {
                    int to;
                    int sizeOfPath = path.size() - 1;
                    for (int i = 0; i < sizeOfPath; i++) {
                        to = path.pop();
                        graph[path.peek()].setMatch(to);
                        levelGraph[path.peek()].removeEdge(to);
                        graph[path.peek()].removeEdge(to);
                        graph[to].addEdge(path.peek());
                    }
                    curr = levelGraph[SOURCE];
                } else if (curr.getNext() == -1) {
                    int removedNode = path.pop();
                    curr = levelGraph[path.peek()];
                    for (Node node : levelGraph) {
                        node.removeEdge(removedNode);
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
        int numOfMatches = 0;
        for (int i = 1; i <= N / 2; i++) {
            int match = graph[i].getMatch();
            if (match != -1) {
                numOfMatches++;
                System.out.println(graph[i].getName() + " / " + graph[match].getName());
            }
        }
        System.out.println(numOfMatches + " total matches");
    }

    public boolean createLG(Node[] levelGraph) {
        HashSet<Integer> visited = new HashSet<Integer>();
        int[] level = new int[graph.length];
        Arrays.fill(level, -1);
        level[SOURCE] = 0;
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
                for (Object currEdge : graph[curr].getEdges()) {
                    int edge = (int) currEdge;
                    if (level[edge] == -1) {
                        level[edge] = level[curr] + 1;
                    }
                    if (level[edge] > level[curr]) {
                        levelGraph[curr].addEdge(edge);
                    }
                    if (!visited.contains(edge)) {
                        visited.add(edge);
                        bfs.add(edge);
                    }
                }
        }
        return foundSink;
    }

    public void initializeData(String fileName) {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            N = in.nextInt();
            in.nextLine();
            graph = new Node[N + 2];
            SOURCE = 0;
            SINK = N + 1;
            graph[SOURCE] = new Node("source");
            graph[SINK] = new Node("sink");
            int mid = N / 2;
            for (int i = 1; i <= mid; i++) {
                graph[i] = new Node(in.nextLine());
                graph[SOURCE].addEdge(i);
            }
            for (int i = mid + 1; i <= N; i++) {
                graph[i] = new Node(in.nextLine());
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
