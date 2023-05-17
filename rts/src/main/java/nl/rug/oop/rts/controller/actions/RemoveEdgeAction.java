package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Triggers the removal of an edge from the model iff an edge is selected.
 */
public class RemoveEdgeAction extends AbstractAction {
    private final Graph graph;

    private final MouseObserver mouseObserver;

    /**
     * Main constructor.
     * 
     * @param name          Name of the Action.
     * @param graph         The main graph.
     * @param mouseObserver The main mouseObserver.
     */
    public RemoveEdgeAction(String name, Graph graph, MouseObserver mouseObserver) {
        super(name);
        this.graph = graph;
        this.mouseObserver = mouseObserver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mouseObserver.getSelectedEdge() != null) {
            graph.removeEdge(mouseObserver.getSelectedEdge());
        }
    }
}
