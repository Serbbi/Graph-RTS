package nl.rug.oop.rts.view.panels.sideMenu;

import nl.rug.oop.rts.controller.actions.eventActions.AddEventAction;
import nl.rug.oop.rts.controller.actions.eventActions.RemoveEventAction;
import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.*;

/**
 * Panel containing two buttons to add or remove an event from a node.
 */
public class ModifyEventsPanel extends JPanel implements Observable {

    private final JButton addEvent;
    private final JButton removeEvent;
    private final MouseObserver mouseObserver;
    private final InteractionMenuData interactionMenuData;

    /**
     * Constructor for the panel.
     * 
     * @param mouseObserver       getting the selected node
     * @param interactionMenuData getting the selected army
     */
    public ModifyEventsPanel(MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        this.mouseObserver = mouseObserver;
        this.interactionMenuData = interactionMenuData;
        setLayout(new GridBagLayout());
        setVisible(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label = new JLabel("Events:");
        add(label, gbc);

        gbc.gridx++;
        addEvent = new JButton(new AddEventAction("+", mouseObserver, interactionMenuData));
        add(addEvent, gbc);

        gbc.gridx++;
        removeEvent = new JButton(new RemoveEventAction("-", mouseObserver, interactionMenuData));
        removeEvent.setEnabled(false);
        add(removeEvent, gbc);
    }

    @Override
    public void update() {
        Event event = interactionMenuData.getSelectedEvent();

        if (mouseObserver.getSelectedContainsEvent() != null) {
            this.setVisible(true);
            removeEvent.setEnabled(event != null);
        } else {
            this.setVisible(false);
        }
    }
}
