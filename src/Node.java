import java.util.LinkedList;

public class Node {
    NodeSorter sorter;
    private String name;
    private LinkedList<Node> edges;

    public Node(String name) {
        sorter = new NodeSorter();
        this.name = name;
        edges = new LinkedList<Node>();
    }

    public String getName() {
        return name;
    }

    public void addEdge(Node node) {
        edges.add(node);
    }

    public Node getNext() {
        return edges.peek();
    }

    public void removeEdge(String node) {
        edges.remove();
    }

    public void sortEdges() {
        edges.sort(sorter);
    }
}
