/**
 * Class that processes input file, and uses
 * the Ford-Fulkerson Algorithm using the shortest augmenting paths method
 * to determine the matches of the nodes in the graph
 * Functionality includes initializing graph[] array,
 * then using Ford-Fulkerson Algorithm, as well
 * as creating the level graphs for the algorithm
 * @author Ali Ibrahim and Omar Shaban
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BipartiteMatching {
    private Node[] graph; //array holding residual graph, looks like
    // [source, bipartite nodes..., sink]
    private int N; // number of nodes (not including source/sink)
    private int SOURCE; // index of source node in residual graph array
    private int SINK; // index of sink node in residual graph array

    /**
     * uses createLG() outputMatchings() and methods to sort then use algorithm
     * to find minimum distance in points array
     * pre: graph array is initialized
     * post: all match variables are set in nodes of graph[] array
     */
    public void maxMatches() {
        // creates Node array with size of graph[]
        Node[] levelGraph = new Node[graph.length];
        // runs createLG() to see if there is path to SINK
        boolean done = !createLG(levelGraph);
        while (!done) {
            // sets current as SOURCE
            Node curr = levelGraph[SOURCE];
            // initializes stack that stores path as algorithm progresses
            Stack<Integer> path = new Stack<>();
            path.add(SOURCE);
            // while not stuck at source
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
            done = !createLG(levelGraph);
        }
        outputMatchings();
    }

    /**
     * Outputs matching of nodes
     * pre: points inputted are valid points
     * post: returns the distance between the 2 parameter points
     */
    public void outputMatchings() {
        int numOfMatches = 0;
        // goes through graph[], and outputs matches for each nodes,
        // and has a counter of total number of matches
        for (int i = 1; i <= N / 2; i++) {
            int match = graph[i].getMatch();
            // checks if node has a match
            if (match != -1) {
                numOfMatches++;
                System.out.println(graph[i].getName() + " / "
                        + graph[match].getName());
            }
        }
        System.out.println(numOfMatches + " total matches");
    }

    /**
     * uses BFS to see if there is a path to SINK
     * pre: initializeData() is successfully run
     * post: sets levelGraph variable and
     */
    public boolean createLG(Node[] levelGraph) {
        //
        int[] level = new int[graph.length];
        //SOURCE level is set to 1 instead of 0 since java initializes
        // arrays to 0, so no need to set level[] array to -1
        level[SOURCE] = 1;
        Queue<Integer> bfs = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            levelGraph[i] = new Node("");
        }
        boolean foundSink = false;
        bfs.add(0);
        while (!bfs.isEmpty()) {
            int curr = bfs.peek();
            bfs.remove();
            if (curr == SINK) { foundSink = true; }
                for (Object currEdge : graph[curr].getEdges()) {
                    int edge = (int) currEdge;
                    if (level[edge] == 0) {
                        level[edge] = level[curr] + 1;
                        bfs.add(edge);
                    }
                    if (level[edge] > level[curr]) {
                        levelGraph[curr].addEdge(edge);
                    }
                }
        }
        return foundSink;
    }

    /**
     * Initializer for graph[] array, N, SOURCE, and SINK variables
     * pre: file is formatted correctly with valid data
     * post: variables are initialized and set with
     * information from input file
     */
    public void initializeData(String fileName) {
        File file = new File(fileName);
        try {
            // initialize Scanner object
            Scanner in = new Scanner(file);
            // reads number of nodes
            N = in.nextInt();
            in.nextLine();
            // initializes graph size as N + 2 because of SOURCE and SINK
            graph = new Node[N + 2];
            SOURCE = 0;
            SINK = N + 1;
            // initializes SOURCE and SINK Nodes in graph[]
            graph[SOURCE] = new Node("source");
            graph[SINK] = new Node("sink");
            // divides graph[] in half, and adds edges from SOURCE to first
            // half, and adds edges from second half to SINK
            int mid = N / 2;
            for (int i = 1; i <= mid; i++) {
                graph[i] = new Node(in.nextLine());
                graph[SOURCE].addEdge(i);
            }
            for (int i = mid + 1; i <= N; i++) {
                graph[i] = new Node(in.nextLine());
                graph[i].addEdge(SINK);
            }
            // reads number of edges, then sets edges
            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                graph[in.nextInt()].addEdge(in.nextInt());
            }
            in.close();
        } catch (FileNotFoundException ex) {
            // catchs and prints if file is not found
            System.out.println("File not found");
        }
    }
}
