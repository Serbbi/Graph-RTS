package nl.rug.oop.rts.model.sideBar;

import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.controller.observers.Observer;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.node.Node;

/**
 * Holds all of the data for the left side bar.
 */
public class InteractionMenuData {
    private Node selectedNode;
    private Army selectedArmy;
    private Event selectedEvent;
    private final Observer observer;

    /**
     * Constructor for the class.
     * 
     * @param observer to observe the radioButton class and modifyArmy class
     */
    public InteractionMenuData(Observer observer) {
        this.observer = observer;
    }

    /**
     * Getter for the selected node field.
     * 
     * @return Selected node.
     */
    public Node getSelectedNode() {
        return this.selectedNode;
    }

    /**
     * Sets the new selected node.
     * Clears selectedArmy as it will be corresponding to a different node.
     * 
     * @param selectedNode New selected node.
     */
    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
        this.selectedArmy = null;
        this.selectedEvent = null;
    }

    public Army getSelectedArmy() {
        return this.selectedArmy;
    }

    public void setSelectedArmy(Army selectedArmy) {
        this.selectedArmy = selectedArmy;
    }

    public void setSelectedEvent(Event event) {
        this.selectedEvent = event;
    }

    public Event getSelectedEvent() {
        return this.selectedEvent;
    }

    /**
     * Notifies all observers of the interaction menu data.
     */
    public void notifyObservers() {
        observer.notifyObservers();
    }

    /**
     * Adds the observable object.
     * 
     * @param observable observable interface.
     */
    public void addObservable(Observable observable) {
        observer.addObservable(observable);
    }

}
