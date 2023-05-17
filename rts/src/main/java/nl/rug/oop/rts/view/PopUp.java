package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.Graph;

import javax.swing.*;

/**
 * PopUp message to warn the user about an event which took place.
 */
public class PopUp extends JOptionPane implements Observable {

    private Graph graph;

    /**
     * Constructor for the message.
     * @param graph the model where it takes the message text
     */
    public PopUp(Graph graph) {
        this.graph = graph;
        graph.addObservable(this);
    }

    @Override
    public void update() {
        if (graph.getPopUpText() != null) {
            showMessageDialog(null, graph.getPopUpText());
            graph.popUp(null);
        }
    }
}
