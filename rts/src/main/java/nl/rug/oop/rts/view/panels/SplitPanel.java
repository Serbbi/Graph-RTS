package nl.rug.oop.rts.view.panels;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.panels.sideMenu.SideBar;

import javax.swing.*;

/**
 * SplitPanel for the menu on the left and the graph on the right.
 */
public class SplitPanel extends JSplitPane implements Observable {

    /**
     * Constructor for the split panel.
     * 
     * @param graph         to be passed to the other panels.
     * @param mouseObserver To get the selections on the graph panel.
     */
    public SplitPanel(Graph graph, MouseObserver mouseObserver) {
        super(JSplitPane.HORIZONTAL_SPLIT, new SideBar(graph, mouseObserver), new GraphPanel(graph, mouseObserver));
        graph.addObservable(this);
        setDividerLocation(150);
    }

    @Override
    public void update() {
        repaint();
    }
}
