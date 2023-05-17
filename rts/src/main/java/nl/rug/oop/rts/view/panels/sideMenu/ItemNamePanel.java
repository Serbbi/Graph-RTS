package nl.rug.oop.rts.view.panels.sideMenu;

import nl.rug.oop.rts.controller.actions.ChangeNameAction;
import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Named;
import nl.rug.oop.rts.model.node.Node;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to display either the name of a node or an edge.
 */
public class ItemNamePanel extends JPanel implements Observable {

    private final JLabel label;

    private final JTextField nameEntry;
    private final MouseObserver mouseObserver;

    /**
     * Constructor for the panel.
     * 
     * @param mouseObserver getting the selected node/edge
     * @param graph         Graph to trigger notify on name change.
     */
    public ItemNamePanel(MouseObserver mouseObserver, Graph graph) {
        this.mouseObserver = mouseObserver;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        label = new JLabel();
        nameEntry = new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        label.setText("");
        add(label, gbc);

        gbc.gridy++;
        nameEntry.setText("");
        nameEntry.addActionListener(new ChangeNameAction(mouseObserver, graph, nameEntry));
        add(nameEntry, gbc);
    }

    /**
     * Returns a named object(node/edge).
     * 
     * @return named
     */
    public Named getSelectedNamed() {
        if (mouseObserver.getSelectedNode() != null) {
            return mouseObserver.getSelectedNode();
        } else if (mouseObserver.getSelectedEdge() != null) {
            return mouseObserver.getSelectedEdge();
        } else {
            return null;
        }
    }

    @Override
    public void update() {
        Named named = getSelectedNamed();

        if (named != null) {
            nameEntry.setText(named.getName());
        }
        if (named instanceof Node) {
            label.setText("Node");
        } else if (named instanceof Edge) {
            label.setText("Edge");
        } else {
            label.setText("");
            nameEntry.setText("");
        }

        repaint();
    }
}
