package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action for the button to simulate a step.
 */
public class SimulateStep extends AbstractAction {

    private final Graph graph;

    /**
     * Constructor for the action.
     * @param name  of the button
     * @param graph in which the simulation takes place
     */
    public SimulateStep(String name, Graph graph) {
        super(name);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getSimulation().fullStep();
    }
}
