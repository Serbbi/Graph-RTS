package nl.rug.oop.rts.controller.actions.eventActions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.ContainsEvent;
import nl.rug.oop.rts.model.gameContent.events.*;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Adds an event to the selected ContainsEvent.
 */
public class AddEventAction extends AbstractAction {
    private final MouseObserver mouseObserver;
    private final InteractionMenuData interactionMenuData;

    /**
     * Constructor for action.
     *
     * @param name                name of the button.
     * @param mouseObserver       for selecting the ContainsEvent where to add the
     *                            event.
     * @param interactionMenuData interaction menu data
     */
    public AddEventAction(String name, MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        super(name);
        this.mouseObserver = mouseObserver;
        this.interactionMenuData = interactionMenuData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] possibilities = {"Hidden Weaponry Event", "Natural Disaster Event", "Reinforcement Event",
                                  "Food Rain Event"};
        String userInput = (String) JOptionPane.showInputDialog(null, "Select a type of event.",
                "Add an event", JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);

        if (userInput == null) {
            return;
        }

        ContainsEvent containsEvent = mouseObserver.getSelectedContainsEvent();
        if (containsEvent != null) {
            switch (userInput) {
                case "Hidden Weaponry Event" -> containsEvent.addEvent(new HiddenWeaponryEvent());
                case "Natural Disaster Event" -> containsEvent.addEvent(new NaturalDisasterEvent());
                case "Reinforcement Event" -> containsEvent.addEvent(new ReinforcementEvent());
                case "Food Rain Event" -> containsEvent.addEvent(new FoodRainEvent());
            }
        }
        interactionMenuData.notifyObservers();
    }
}
