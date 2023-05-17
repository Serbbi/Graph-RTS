package nl.rug.oop.rts.model;

import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.controller.observers.Observer;
import nl.rug.oop.rts.model.gameContent.Simulation;
import nl.rug.oop.rts.model.node.Node;
import nl.rug.oop.rts.util.JSONArray;
import nl.rug.oop.rts.util.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph class, stands for the map of the game.
 * Has Nodes and Edges.
 */
public class Graph implements JSONable {

    private final List<Edge> edges;
    private final List<Node> nodes;
    private final Observer observer;
    private final Simulation simulation;
    private String popUpText;

    /**
     * Constructor for the graph.
     * 
     * @param observer To be notified on change.
     */
    public Graph(Observer observer) {
        this.observer = observer;
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
        simulation = new Simulation(this);
    }

    /**
     * Getter for the nodes list.
     * 
     * @return List of nodes in the graph.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Adds a new Node to the graph.
     * 
     * @param node New node to be added.
     */
    public void addNode(Node node) {
        this.nodes.add(node);
        observer.notifyObservers();
    }

    /**
     * Creates a new Node object and adds it to the nodes list.
     * Node id is automatically generated.
     * 
     * @param name Name of node.
     */
    public void addNode(String name) {
        addNode(new Node(name, observer));
    }

    /**
     * Removes a node from the graph.
     * 
     * @param node Node to be removed.
     */
    public void removeNode(Node node) {
        List<Edge> edges = node.getEdges();

        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge edge = edges.remove(0);
            removeEdge(edge.getId());
        }

        removeNode(node.getId());
        observer.notifyObservers();
    }

    /**
     * Removes a node from graph by it's ID.
     * 
     * @param id ID of node to be removed.
     */
    public void removeNode(int id) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (node.getId() == id) {
                for (int j = 0; j < node.getArmies().size(); j++) {
                    node.getArmies().remove(0);
                }
                nodes.remove(i);
                return;
            }
        }
        observer.notifyObservers();
    }

    /**
     * Checks if the mouse position hovers over a node.
     * 
     * @param x coordinate for mouse.
     * @param y coordinate for mouse.
     * 
     * @return Node selected.
     */
    public Node selectNode(int x, int y) {
        for (Node node : nodes) {
            if (node.getNodeCoordinates().isInBounds(x, y)) {
                observer.notifyObservers();
                return node;
            }
        }
        observer.notifyObservers();
        return null;
    }

    /**
     * Getter for the edges list.
     * 
     * @return List of edges in the graph.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Adds a new edge to the graph.
     * 
     * @param edge  Edge to be added.
     * @param node0 1st of the 2 nodes that the edge connects.
     * @param node1 2nd of the 2 nodes that the edge connects.
     */
    public void addEdge(Edge edge, Node node0, Node node1) {
        node0.addEdge(edge);
        node1.addEdge(edge);
        this.edges.add(edge);
        observer.notifyObservers();
    }

    /**
     * Creates a new edge object and adds it to the graph.
     * 
     * @param name  Name of the new edge.
     * @param node0 1st of the 2 nodes that the edge connects.
     * @param node1 2nd of the 2 nodes that the edge connects.
     */
    public void addEdge(String name, Node node0, Node node1) {
        Edge edge = new Edge(name, node0, node1, observer);
        addEdge(edge, node0, node1);
    }

    /**
     * Removes an edge from the graph.
     * 
     * @param edge Edge to be removed.
     */
    public void removeEdge(Edge edge) {
        removeEdge(edge.getId());
        observer.notifyObservers();
    }

    /**
     * Removes an edge by id.
     * 
     * @param id ID of edge to be removed.
     */
    public void removeEdge(int id) {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.getId() == id) {
                edge.getNode(0).removeEdge(id);
                edge.getNode(1).removeEdge(id);
                for (int j = 0; j < edge.getArmies().size(); j++) {
                    edge.getArmies().remove(0);
                }
                edges.remove(i);
                return;
            }
        }
    }

    /**
     * Returns the edge that is in bounds of the point (x,y).
     * 
     * @param x X coordinate of point.
     * @param y Y coordinate of point
     * @return The selected edge.
     */
    public Edge selectEdge(int x, int y) {
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge edge = edges.get(i);
            if (edge.isInBounds(x, y)) {
                observer.notifyObservers();
                return edge;
            }
        }
        observer.notifyObservers();
        return null;
    }

    /**
     * Getter for the simulation field.
     * 
     * @return the simulation object.
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Sets the text for a popup used during the simulation.
     * Used when an army encounters and event.
     * @param text to be displayed in the message
     */
    public void popUp(String text) {
        popUpText = text;
        observer.notifyObservers();
    }

    /**
     * Getter for the popup text.
     * 
     * @return Popup text.
     */
    public String getPopUpText() {
        return popUpText;
    }

    /**
     * Adds the observable interface.
     * 
     * @param observable observable interface.
     */
    public void addObservable(Observable observable) {
        observer.addObservable(observable);
    }

    /**
     * Triggers the update method of all observable objects in the observer.
     */
    public void notifyObservers() {
        observer.notifyObservers();
    }

    /**
     * Getter for the observer.
     * 
     * @return The observer for the graph.
     */
    public Observer getObserver() {
        return observer;
    }

    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();

        // JSONify all the nodes
        JSONArray nodesJson = new JSONArray();
        for (Node node : nodes) {
            nodesJson.put(node.toJson());
        }
        json.put("Nodes", nodesJson);

        // JSONify all the edges
        JSONArray edgesJson = new JSONArray();
        for (Edge edge : edges) {
            edgesJson.put(edge.toJson());
        }
        json.put("Edges", edgesJson);

        json.put("NodeNextID", Node.getNextID());
        json.put("EdgeNextID", Edge.getNextID());

        return json;
    }
}
