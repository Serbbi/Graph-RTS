package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.controller.observers.MouseObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Adds a new edge to the model.
 */
public class AddEdgeAction extends AbstractAction {

    private final MouseObserver mouseObserver;

    /**
     * Main Constructor.
     * 
     * @param name          Name of the Action.
     * @param mouseObserver The main mouseObserver.
     */
    public AddEdgeAction(String name, MouseObserver mouseObserver) {
        super(name);
        this.mouseObserver = mouseObserver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mouseObserver.getSelectedNode() == null) {
            JOptionPane.showMessageDialog(null, "Please select a node for which to add an edge.", "Select a node",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        mouseObserver.setAddEdgeFlag(true);
        mouseObserver.setFirstSelected(mouseObserver.getSelectedNode());
    }
}
