package nl.rug.oop.rts.model;

import java.util.List;

import nl.rug.oop.rts.model.gameContent.events.Event;

/**
 * Interface for Objects that have a list of events.
 */
public interface ContainsEvent {

    /**
     * Getter for events field.
     * 
     * @return Events list
     */
    List<Event> getEvents();

    /**
     * Adds an event to the events list.
     * 
     * @param event Event to be added.
     */
    void addEvent(Event event);

    /**
     * Removes an event from the events list.
     * 
     * @param event Event to be removed.
     */
    void removeEvent(Event event);

}
