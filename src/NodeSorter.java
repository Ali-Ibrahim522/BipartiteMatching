import java.util.Comparator;

public class NodeSorter implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
