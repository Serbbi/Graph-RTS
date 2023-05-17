package nl.rug.oop.rts.model.gameContent.events;

import nl.rug.oop.rts.util.JSONObject;

import nl.rug.oop.rts.model.gameContent.Army;

/**
 * Event interface to dictate the methods in an event.
 */
public interface Event {

    String getEventType();

    void runEvent(Army army);

    JSONObject toJson();
}
