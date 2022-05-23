import java.util.LinkedList;

public class Node {
    private String name;
    private LinkedList<Integer> edges;

    public Node(String name) {
        this.name = name;
        edges = new LinkedList<Integer>();
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

    public void clearEdges() {
        edges.clear();
    }
}
