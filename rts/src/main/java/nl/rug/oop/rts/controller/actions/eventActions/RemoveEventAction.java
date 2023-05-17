package nl.rug.oop.rts.controller.actions.eventActions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action for removing an army on button press.
 */
public class RemoveEventAction extends AbstractAction {

    private final InteractionMenuData interactionMenuData;
    private final MouseObserver mouseObserver;

    /**
     * Constructor for action.
     * 
     * @param name                name of the button
     * @param interactionMenuData where the selected army is stored
     * @param mouseObserver       from which node to remove the army
     */
    public RemoveEventAction(String name, MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        super(name);
        this.interactionMenuData = interactionMenuData;
        this.mouseObserver = mouseObserver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mouseObserver.getSelectedContainsEvent() != null && interactionMenuData.getSelectedEvent() != null) {
            Event event = interactionMenuData.getSelectedEvent();
            interactionMenuData.setSelectedEvent(null);
            mouseObserver.getSelectedContainsEvent().removeEvent(event);
            interactionMenuData.notifyObservers();
        }
    }
}
