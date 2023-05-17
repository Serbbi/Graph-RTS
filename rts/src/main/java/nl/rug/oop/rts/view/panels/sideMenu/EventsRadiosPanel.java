package nl.rug.oop.rts.view.panels.sideMenu;

import java.util.ArrayList;
import java.util.List;

import nl.rug.oop.rts.controller.actions.eventActions.SelectEventAction;
import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.ContainsEvent;
import nl.rug.oop.rts.model.gameContent.events.Event;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.*;

/**
 * EventsRadiosPanel displays all the events on that node/edge in a radio button
 * panel.
 */
public class EventsRadiosPanel extends RadiosPanel {
    private List<Event> events;
    private GridBagConstraints gbc;

    /**
     * Constructor for the panel.
     * 
     * @param mouseObserver       from which we take the selected node/edge
     * @param interactionMenuData where we store the selected army
     */
    public EventsRadiosPanel(MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        super(mouseObserver, interactionMenuData);
        gbc = initGBC();

        events = new ArrayList<>();
    }

    /**
     * Sets the radio buttons.
     */
    public void setRadioButtons() {
        for (JRadioButton btn : getButtons()) {
            getButtonGroup().remove(btn);
            remove(btn);
            gbc.gridy--;
        }
        getButtons().clear();

        for (Event event : events) {
            JRadioButton btn = new JRadioButton(event.getEventType());

            btn.addActionListener(new SelectEventAction(getInteractionMenuData(), event));

            getButtons().add(btn);
            getButtonGroup().add(btn);
            add(btn, gbc);
            gbc.gridy++;
        }
        updateUI();
    }

    @Override
    public void update() {
        ContainsEvent containsEvent = getMouseObserver().getSelectedContainsEvent();

        if (containsEvent != null) {
            if (getInteractionMenuData().getSelectedEvent() == null) {
                events = containsEvent.getEvents();
                setRadioButtons();
                this.setVisible(true);
            }
        } else {
            this.setVisible(false);
        }
    }
}