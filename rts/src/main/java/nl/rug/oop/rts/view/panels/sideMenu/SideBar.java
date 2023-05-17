package nl.rug.oop.rts.view.panels.sideMenu;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.controller.observers.Observer;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.node.Node;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel for the left-side menu.
 */
public class SideBar extends JPanel implements Observable {
    private final MouseObserver mouseObserver;
    private final InteractionMenuData interactionMenuData;

    /**
     * Constructor for the panel.
     * 
     * @param graph         To be changed by the buttons.
     * @param mouseObserver To get the selections on the graph panel.
     */
    public SideBar(Graph graph, MouseObserver mouseObserver) {
        this.interactionMenuData = new InteractionMenuData(new Observer());

        this.mouseObserver = mouseObserver;
        graph.addObservable(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        ItemNamePanel itemNamePanel = new ItemNamePanel(mouseObserver, graph);
        graph.addObservable(itemNamePanel);
        add(itemNamePanel, c);

        c.gridy++;
        EdgeNodesPanel edgeNodesPanel = new EdgeNodesPanel(mouseObserver);
        graph.addObservable(edgeNodesPanel);
        add(edgeNodesPanel, c);

        addArmyPanels(graph, c);

        addEventsPanels(graph, c);
    }

    /**
     * Adds all of the panels that work with the armies.
     * 
     * @param graph The graph.
     * @param c     GridBagConstraints for panel.
     */
    public void addArmyPanels(Graph graph, GridBagConstraints c) {
        c.gridy++;
        ModifyArmyPanel modifyArmyPanel = new ModifyArmyPanel(mouseObserver, interactionMenuData);
        interactionMenuData.addObservable(modifyArmyPanel);
        graph.addObservable(modifyArmyPanel);
        add(modifyArmyPanel, c);

        c.gridy++;
        ArmyRadiosPanel armyRadiosPanel = new ArmyRadiosPanel(mouseObserver, interactionMenuData);
        interactionMenuData.addObservable(armyRadiosPanel);
        graph.addObservable(armyRadiosPanel);
        add(armyRadiosPanel, c);
    }

    /**
     * Adds all of the panels that work with the event.
     * 
     * @param graph The graph.
     * @param c     GridBagConstraints for panel.
     */
    public void addEventsPanels(Graph graph, GridBagConstraints c) {
        c.gridy++;
        ModifyEventsPanel modifyEventsPanel = new ModifyEventsPanel(mouseObserver, interactionMenuData);
        interactionMenuData.addObservable(modifyEventsPanel);
        graph.addObservable(modifyEventsPanel);
        add(modifyEventsPanel, c);

        c.gridy++;
        EventsRadiosPanel eventsRadiosPanel = new EventsRadiosPanel(mouseObserver, interactionMenuData);
        interactionMenuData.addObservable(eventsRadiosPanel);
        graph.addObservable(eventsRadiosPanel);
        add(eventsRadiosPanel, c);
    }

    /**
     * Gets the selected Node item from the mouseObserver.
     * 
     * @return Selected item which should be displayed.
     */
    public Node getSelectedNode() {
        if (mouseObserver.getSelectedNode() != null) {
            return mouseObserver.getSelectedNode();
        } else {
            return null;
        }
    }

    @Override
    public void update() {
        if (interactionMenuData.getSelectedNode() != getSelectedNode()) {
            interactionMenuData.setSelectedNode(getSelectedNode());
        }
        repaint();
    }
}
