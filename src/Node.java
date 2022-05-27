import java.util.LinkedList;

public class Node {
    private String name;
    private int match;
    private LinkedList<Integer> edges;

    public Node(String name) {
        this.name = name;
        match = -1;
        edges = new LinkedList<Integer>();
    }

    public void setMatch(int node) {
        match = node;
    }

    public int getMatch() {
        return match;
    }

    public String getName() {
        return name;
    }

    public void addEdge(int node) {
        edges.add(node);
    }

    public int getNext() {
        return edges.size() == 0 ? -1 : edges.peek();
    }

    public void removeEdge(int node) {
        edges.remove((Integer) node);
    }

    public Object[] getEdges() {
        return edges.toArray();
    }
}
