package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to be executed when a player renames a node or an edge in the text
 * box.
 */
public class ChangeNameAction extends AbstractAction {

    private final MouseObserver mouseObserver;

    private final JTextField textField;

    private final Graph graph;

    /**
     * Main constructor.
     * 
     * @param mouseObserver Mouse observer to use to get the selected node.
     * @param graph         Graph in which the node to change name is.
     * @param textField     Text field to get the new name from.
     */
    public ChangeNameAction(MouseObserver mouseObserver, Graph graph, JTextField textField) {
        this.mouseObserver = mouseObserver;
        this.textField = textField;
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = textField.getText();
        if (mouseObserver.getSelectedNode() != null) {
            mouseObserver.getSelectedNode().setName(name);
            graph.notifyObservers();
        } else if (mouseObserver.getSelectedEdge() != null) {
            mouseObserver.getSelectedEdge().setName(name);
            graph.notifyObservers();
        }

    }
}
