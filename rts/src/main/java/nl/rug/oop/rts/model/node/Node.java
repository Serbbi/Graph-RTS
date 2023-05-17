package nl.rug.oop.rts.model.node;

import nl.rug.oop.rts.controller.observers.Observer;
import nl.rug.oop.rts.model.ContainsArmy;
import nl.rug.oop.rts.model.ContainsEvent;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.JSONable;
import nl.rug.oop.rts.model.Named;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.gameContent.factions.Faction;

import java.util.List;

import nl.rug.oop.rts.util.JSONArray;
import nl.rug.oop.rts.util.JSONObject;

import java.util.ArrayList;

/**
 * Node for the map graph.
 */
public class Node implements Named, ContainsArmy, ContainsEvent, JSONable {
    /** Used to automatically set the ids of the nodes. */
    private static int nextID = 1;
    private final int id;
    private String name;
    private List<Edge> edges;
    private final Observer observer;
    private final NodeCoordinates nodeCoordinates = new NodeCoordinates();
    private final List<Army> armies;
    private final List<Event> events;

    /**
     * Constructor with edges passed as parameter.
     * 
     * @param id       Id of the graph node.
     * @param name     Name of the node.
     * @param edges    Edges list for all adjacent edges.
     * @param x        X coordinate of the node.
     * @param y        Y coordinate of the node.
     * @param observer The observer for this component.
     */
    public Node(int id, String name, List<Edge> edges, int x, int y, Observer observer) {
        this.id = id;
        this.name = name;
        this.edges = edges;
        this.observer = observer;

        nodeCoordinates.setX(x);
        nodeCoordinates.setY(y);

        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Constructor that initialises edges as a new ArrayList.
     * 
     * @param id       ID of the node.
     * @param name     Name of the node.
     * @param observer The observer for this component.
     */
    public Node(int id, String name, Observer observer) {
        this(id, name, new ArrayList<Edge>(), (int) Math.floor(Math.random() * (450 - 0 + 1) + 0),
                (int) Math.floor(Math.random() * (450 - 0 + 1) + 0), observer);
    }

    /**
     * Constructor that initialises edges as a new ArrayList.
     * Automatically assigns the id for the node based on the static nextID value.
     * 
     * @param name     Name of the node.
     * @param observer The observer for this component.
     */
    public Node(String name, Observer observer) {
        this(nextID++, name, observer);
    }

    /**
     * Getter for id field.
     * 
     * @return ID
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name field.
     * 
     * @param name Name of the node.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Army> getArmies() {
        return armies;
    }

    @Override
    public void addArmy(Faction faction) {
        armies.add(new Army(faction));
        observer.notifyObservers();
    }

    @Override
    public void addArmy(Army army) {
        armies.add(army);
    }

    @Override
    public void removeArmy(Army army) {
        armies.remove(army);
        observer.notifyObservers();
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Getter for the edges list field.
     * 
     * @return Edges list.
     */
    public List<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Adds an edge to the edges list.
     * 
     * @param edge New edge.
     */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    /**
     * Removes an edge based on its id.
     * 
     * @param id of the edge.
     */
    public void removeEdge(int id) {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.getId() == id) {
                edges.remove(i);
                return;
            }
        }
    }

    /**
     * Getter for nodeCoordinates field.
     * 
     * @return NodeCoordinates of the node.
     */
    public NodeCoordinates getNodeCoordinates() {
        return nodeCoordinates;
    }

    /**
     * Moves the node based on mouse drag.
     * Triggers the update of the edge positions.
     *
     * @param xOffset X distance to move the node.
     * @param yOffset Y distance to move the node.
     */
    public void moveNode(int xOffset, int yOffset) {
        this.getNodeCoordinates().setX(xOffset);
        this.getNodeCoordinates().setY(yOffset);

        observer.notifyObservers();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("ID", id);
        jsonObj.put("Name", name);

        JSONArray armiesJson = new JSONArray();
        for (Army army : armies) {
            armiesJson.put(army.toJson());
        }
        jsonObj.put("Armies", armiesJson);

        JSONArray eventsJson = new JSONArray();
        for (Event event : events) {
            eventsJson.put(event.toJson());
        }
        jsonObj.put("Events", eventsJson);

        return jsonObj;
    }

    /**
     * Returns the value of the static nextID field.
     * Only used for writing to json to avoid id clashes when reloaded.
     * 
     * @return nextID.
     */
    public static int getNextID() {
        return nextID;
    }
}
