package nl.rug.oop.rts.view.panels;

import nl.rug.oop.rts.controller.actions.*;
import nl.rug.oop.rts.controller.observers.*;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;

/**
 * JPanel for the top menu.
 */
public class TopBar extends JPanel implements Observable {

    private final MouseObserver mouseObserver;
    private final JButton removeSelectedLocationBtn;
    private final JButton removeSelectedRouteBtn;
    private final JButton drawRouteBtn;

    /**
     * Constructor for the panel.
     * 
     * @param graph         To be changed by the buttons.
     * @param mouseObserver To get the selections on the graph panel.
     */
    public TopBar(Graph graph, MouseObserver mouseObserver) {
        this.mouseObserver = mouseObserver;
        graph.addObservable(this);

        JButton addLocationBtn = new JButton(new AddNodeAction("Add Location", graph));
        add(addLocationBtn);

        removeSelectedLocationBtn = new JButton(new RemoveNodeAction("Remove Location", graph, mouseObserver));
        add(removeSelectedLocationBtn);

        drawRouteBtn = new JButton(new AddEdgeAction("Draw Route", mouseObserver));
        add(drawRouteBtn);

        removeSelectedRouteBtn = new JButton(
                new RemoveEdgeAction("Remove Selected Route", graph, mouseObserver));
        add(removeSelectedRouteBtn);

        JButton simulateTimeStepBtn = new JButton(new SimulateStep("Simulate Time Step", graph));
        add(simulateTimeStepBtn);

        JButton toJsonBtn = new JButton(new WriteJsonAction("To JSON", graph, this));
        add(toJsonBtn);
    }

    /**
     * Method for updating the buttons, when the player can or cannot press them.
     */
    public void updateBtns() {
        if (mouseObserver.getSelectedNode() == null) {
            removeSelectedLocationBtn.setEnabled(false);
            drawRouteBtn.setEnabled(false);
        } else {
            removeSelectedLocationBtn.setEnabled(true);
            drawRouteBtn.setEnabled(true);
        }
        removeSelectedRouteBtn.setEnabled(mouseObserver.getSelectedEdge() != null);
    }

    @Override
    public void update() {
        updateBtns();
        repaint();
    }
}