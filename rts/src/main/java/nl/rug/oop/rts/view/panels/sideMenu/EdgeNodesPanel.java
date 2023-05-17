package nl.rug.oop.rts.view.panels.sideMenu;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to display the two nodes an edge connects.
 */
public class EdgeNodesPanel extends JPanel implements Observable {

    private final JTextField infoNode0;
    private final JTextField infoNode1;
    private final MouseObserver mouseObserver;

    /**
     * Constructor for the panel.
     * @param mouseObserver getting the selected edge
     */
    public EdgeNodesPanel(MouseObserver mouseObserver) {
        this.mouseObserver = mouseObserver;
        infoNode0 = new JTextField();
        infoNode1 = new JTextField();
        setLayout(new GridBagLayout());
        setVisible(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel nodesLabel = new JLabel("Nodes:");
        add(nodesLabel, gbc);

        gbc.gridy++;
        add(infoNode0, gbc);

        gbc.gridx++;
        add(infoNode1, gbc);
    }

    @Override
    public void update() {
        if (mouseObserver.getSelectedEdge() != null) {
            this.setVisible(true);
            infoNode0.setText(mouseObserver.getSelectedEdge().getNode(0).getName());
            infoNode1.setText(mouseObserver.getSelectedEdge().getNode(1).getName());
        } else {
            this.setVisible(false);
        }
    }
}
