package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Removes a node from the model iff a node is selected.
 */
public class RemoveNodeAction extends AbstractAction {

    private final Graph graph;
    private final MouseObserver mouseObserver;

    /**
     * Action constructor.
     * 
     * @param name          name of the button
     * @param graph         the graph to which we add the node
     * @param mouseObserver Used to get the selected node for deletion.
     */
    public RemoveNodeAction(String name, Graph graph, MouseObserver mouseObserver) {
        super(name);
        this.graph = graph;
        this.mouseObserver = mouseObserver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mouseObserver.getSelectedNode() != null) {
            graph.removeNode(mouseObserver.getSelectedNode());
        }
    }
}
