package nl.rug.oop.rts.model.gameContent;

import nl.rug.oop.rts.model.ContainsEvent;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.node.Node;

import java.util.List;

/**
 * The simulation class does the movement of the armies on the graph, the
 * battles and the events.
 */
public class Simulation {

    private final Graph graph;
    private final Battle battle;

    /**
     * Constructor for the simulation.
     * 
     * @param graph The simulation uses the graph.
     */
    public Simulation(Graph graph) {
        this.graph = graph;
        battle = new Battle();
    }

    /**
     * The oneStep method simulates the movement of an army through the graph.
     * For each node in the graph we take each army and select a random edge to go
     * to.
     * Then for each edge in the graph we take each army and select a random node to
     * go to.
     * The army battles before going to an edge, when on the edge and on arrival on
     * the final node.
     */
    public void fullStep() {
        for (Node nodeStart : graph.getNodes()) {
            battle.fight(nodeStart);
            if (nodeStart.getEdges().isEmpty()) {
                continue;
            }
            for (Army army : nodeStart.getArmies()) {
                int idx = (int) Math.floor(Math.random() * 33) % nodeStart.getEdges().size();
                Edge route = nodeStart.getEdges().get(idx);
                route.addArmy(army);
            }
            nodeStart.getArmies().clear();
        }

        for (Edge edge : graph.getEdges()) {
            battle.fight(edge);
            playEvent(edge, edge.getArmies());
            for (Army army : edge.getArmies()) {
                int idx = (int) Math.floor(Math.random() * 33) % 2;
                edge.getNode(idx).addArmy(army);
            }
            edge.getArmies().clear();
        }

        for (Node node : graph.getNodes()) {
            battle.fight(node);
            playEvent(node, node.getArmies());
        }
        graph.notifyObservers();
    }

    /**
     * Potentially plays an event at a given location.
     * 
     * @param location the location where the event takes place
     * @param armies   the armies which are affected by the event
     */
    public void playEvent(ContainsEvent location, List<Army> armies) {
        if (location.getEvents().isEmpty() || armies.isEmpty()) {
            return;
        }

        int possibility = (int) Math.floor(Math.random() * 333) % 100;
        if (possibility >= 50) {
            int idx = (int) Math.floor(Math.random() * (location.getEvents().size()));
            Event randomEvent = location.getEvents().get(idx);
            for (Army army : armies) {
                randomEvent.runEvent(army);
            }
            popUpMessage(randomEvent, armies);
            location.removeEvent(randomEvent);
        }
    }

    /**
     * Sets the text for the PopUp message.
     * 
     * @param event  which type of event takes place
     * @param armies which armies are affected
     */
    public void popUpMessage(Event event, List<Army> armies) {
        String text = event.getEventType();
        text += "\nArmies: ";
        for (Army army : armies) {
            text += army.getName() + " ";
        }
        graph.popUp(text);
    }
}
