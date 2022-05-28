/**
 * Class to represent a Node
 * Stores name, match, and LinkedList of indices all outgoing edges.
 * Functionality includes setting and getting name, match,
 * and the contents of the "edges" LinkedList
 * @author Ali Ibrahim and Omar Shaban
 */

import java.util.LinkedList;

public class Node {
    private String name; //name of node
    private int match; // index of matching node
    private LinkedList<Integer> edges; // indices of outgoing edges

    /**
     * constructor
     * pre: none
     * post: instance variables are initialized
     */
    public Node(String name) {
        this.name = name;
        match = -1;
        edges = new LinkedList<Integer>();
    }

    /**
     * mutator to set match variable
     * pre: none
     * post: sets match variable to parameter
     */
    public void setMatch(int node) {
        match = node;
    }

    /**
     * accessor for match
     * pre: none
     * post: returns Node's match
     */
    public int getMatch() {
        return match;
    }

    /**
     * accessor for name
     * pre: none
     * post: returns Node's name
     */
    public String getName() {
        return name;
    }

    /**
     * mutator to add into "edges" LinkedList
     * pre: none
     * post: adds parameter into "edges" LinkedList
     */
    public void addEdge(int node) {
        edges.add(node);
    }

    /**
     * accessor for next index in "edges" LinkedList
     * pre: none
     * post: returns next index in "edges" LinkedList
     */
    public int getNext() {
        //if list size is more than 0, return top element
        return edges.size() == 0 ? -1 : edges.peek();
    }

    /**
     * mutator to remove nodes from "edges" LinkedList
     * pre: none
     * post: removes parameter from "edges" LinkedList
     */
    public void removeEdge(int node) {
        edges.remove((Integer) node);
    }

    /**
     * accessor for "edges" LinkedList
     * pre: none
     * post: returns all indexes in "edges" LinkedList in the
     * form of an array
     */
    public Object[] getEdges() {
        return edges.toArray();
    }
}
