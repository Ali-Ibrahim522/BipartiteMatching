import java.util.Collections;
import java.util.LinkedList;

public class Node {
    private LinkedList<String> edges;

    public Node(String name) {
        edges = new LinkedList<String>();
    }

    public void addEdge(String node) {
        edges.add(node);
    }

    public String getNext() {
        return edges.peek();
    }

    public void removeEdge(String node) {
        edges.remove();
    }

    public void sortEdges() {
        Collections.sort(edges);
    }
}
