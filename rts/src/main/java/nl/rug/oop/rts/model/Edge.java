package nl.rug.oop.rts.model;

import java.util.ArrayList;
import java.util.List;

import nl.rug.oop.rts.controller.observers.Observer;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.gameContent.factions.Faction;
import nl.rug.oop.rts.model.node.Node;
import nl.rug.oop.rts.util.JSONArray;
import nl.rug.oop.rts.util.JSONObject;

/**
 * Edge is a class which connects two nodes.
 */
public class Edge implements Named, ContainsArmy, ContainsEvent, JSONable {

    // Dictates how far away from the edge a click will cause it to be selected.
    private final double SELECTION_DISTANCE = 10;

    // Used to autoincrement the ids.
    private static int nextID = 1;

    private final int id;
    private String name;

    // Array of length 2 that holds the 2 nodes that the edge joins.
    private final Node[] nodes;

    private final Observer observer;

    private final List<Army> armies;
    private final List<Event> events;

    /**
     * Default Constructor.
     * 
     * @param id       ID of the edge.
     * @param name     Name of the edge.
     * @param nodes    Array of nodes that the edge joins. Length = 2.
     * @param observer Observer to notify on changes.
     */
    private Edge(int id, String name, Node[] nodes, Observer observer) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
        this.observer = observer;
        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Constructor that initialises Nodes array to an empty array.
     *
     * @param id       ID of the edge.
     * @param name     Name of the edge.
     * @param observer Observer to notify on changes.
     */
    public Edge(int id, String name, Observer observer) {
        this(id, name, new Node[2], observer);
    }

    /**
     * Constructor for edge.
     * Automatically assigns id based on next id static variable.
     * 
     * @param name     name of the edge.
     * @param node0    1st of the 2 nodes that the edge connects.
     * @param node1    2nd of the 2 nodes that the edge connects.
     * @param observer Observer to notify on changes.
     */
    public Edge(String name, Node node0, Node node1, Observer observer) {
        this(nextID++, name, observer);
        nodes[0] = node0;
        nodes[1] = node1;
    }

    /**
     * Constructor for edge.
     * 
     * @param id       Id of the edge.
     * @param name     name of the edge.
     * @param node0    1st of the 2 nodes that the edge connects.
     * @param node1    2nd of the 2 nodes that the edge connects.
     * @param observer Observer to notify on changes.
     */
    public Edge(int id, String name, Node node0, Node node1, Observer observer) {
        this(id, name, observer);
        nodes[0] = node0;
        nodes[1] = node1;
    }

    /**
     * Getter for id field.
     * 
     * @return ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for name field.
     * 
     * @return Name of node.
     */
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

    /**
     * Returns one of the nodes that the edge connects.
     * 
     * @param i Edge to be returned (0 <= i < 2)
     * @return Edge selected
     */
    public Node getNode(int i) {
        return nodes[i];
    }

    public List<Army> getArmies() {
        return armies;
    }

    @Override
    public void addArmy(Army army) {
        armies.add(army);
    }

    @Override
    public void addArmy(Faction faction) {
        armies.add(new Army(faction));
    }

    @Override
    public void removeArmy(Army army) {
        armies.remove(army);
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void removeEvent(Event event) {
        events.remove(event);
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Checks if the point (x,y) is within the bounds of the edge.
     * SELECTION_DISTANCE is used to set how far away the click can be for it to
     * still select the edge.
     * 
     * This function uses perpendicular distance, and checks if the perpendicular
     * distance from the point to the edge is less than SELECTION_DISTANCE.
     * 
     * Formula used:
     * https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line
     * Cartesian coordinate - Line defined by 2 points
     * 
     * @param x X coordinate of point.
     * @param y Y coordinate of point.
     * @return True if the the point is within the distance from the point.
     */
    public boolean isInBounds(int x, int y) {

        double x1 = nodes[0].getNodeCoordinates().getCenter().getX();
        double y1 = nodes[0].getNodeCoordinates().getCenter().getY();
        double x2 = nodes[1].getNodeCoordinates().getCenter().getX();
        double y2 = nodes[1].getNodeCoordinates().getCenter().getY();

        // Ensures that the points is within the bounds of the center points of the node
        if (((x1 > x || x2 < x) && (x2 > x || x1 < x)) || ((y1 > y || y2 < y) && (y2 > y || y1 < y))) {
            return false;
        }

        double numerator = Math.abs(
                (x2 - x1) * (y1 - y) - (x1 - x) * (y2 - y1));

        double denominator = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        return SELECTION_DISTANCE > numerator / denominator;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("ID", id);
        json.put("Name", name);
        json.put("Node0", nodes[0].getId());
        json.put("Node1", nodes[1].getId());

        JSONArray armiesJson = new JSONArray();
        for (Army army : armies) {
            armiesJson.put(army.toJson());
        }
        json.put("Armies", armiesJson);

        JSONArray eventsJson = new JSONArray();
        for (Event event : events) {
            eventsJson.put(event.toJson());
        }
        json.put("Events", eventsJson);

        return json;
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
