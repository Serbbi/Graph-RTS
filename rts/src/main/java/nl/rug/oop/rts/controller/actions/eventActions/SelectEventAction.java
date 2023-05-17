package nl.rug.oop.rts.controller.actions.eventActions;

import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to select an army.
 */
public class SelectEventAction extends AbstractAction {

    private final InteractionMenuData interactionMenuData;
    private final Event event;

    /**
     * Constructor for the action.
     * 
     * @param interactionMenuData where to store the selected army
     * @param event               event to be selected
     */
    public SelectEventAction(InteractionMenuData interactionMenuData, Event event) {
        this.interactionMenuData = interactionMenuData;
        this.event = event;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interactionMenuData.setSelectedEvent(event);
        interactionMenuData.notifyObservers();
    }
}
