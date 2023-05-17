package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to add a node to the graph.
 */
public class AddNodeAction extends AbstractAction {
    private final Graph graph;

    /**
     * Action constructor.
     * @param name name of the button
     * @param graph the graph to which we add the node
     */
    public AddNodeAction(String name, Graph graph) {
        super(name);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = JOptionPane.showInputDialog("Enter a name: ");
        if (userInput == null) {
            return;
        }
        graph.addNode(userInput);
    }
}
