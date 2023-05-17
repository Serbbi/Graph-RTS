package nl.rug.oop.rts.controller.observers;

import nl.rug.oop.rts.model.ContainsArmy;
import nl.rug.oop.rts.model.ContainsEvent;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.node.Node;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse listener for selecting, dragging.
 */
public class MouseObserver extends MouseAdapter {

    private final Graph graph;

    private Node selectedNode = null;

    private Node firstSelected = null;

    private Edge selectedEdge = null;

    private int x_offset = 0;
    private int y_offset = 0;

    private boolean addEdgeFlag = false;

    /**
     * Constructor for the mouse observer.
     * 
     * @param graph select, drag things from the graph
     */
    public MouseObserver(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int x = e.getX();
        int y = e.getY();

        selectedNode = graph.selectNode(x, y);

        if (addEdgeFlag && selectedNode != null) {
            checkToAddEdge();
        }

        if (selectedNode == null) {
            addEdgeFlag = false;
            selectedEdge = graph.selectEdge(x, y);
        } else {
            this.x_offset = e.getX() - selectedNode.getNodeCoordinates().getX();
            this.y_offset = e.getY() - selectedNode.getNodeCoordinates().getY();

            selectedEdge = null;
        }
        graph.notifyObservers();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        if (selectedNode != null) {
            selectedNode.moveNode(e.getX() - this.x_offset, e.getY() - this.y_offset);
        }
        graph.notifyObservers();
    }

    public Node getSelectedNode() {
        return selectedNode;
    }

    public Edge getSelectedEdge() {
        return selectedEdge;
    }

    public void setAddEdgeFlag(boolean addEdgeFlag) {
        this.addEdgeFlag = addEdgeFlag;
    }

    public void setFirstSelected(Node firstSelected) {
        this.firstSelected = firstSelected;
    }

    /**
     * Based on the flag being true and the two nodes selected being different,
     * the method will add an edge.
     */
    public void checkToAddEdge() {
        if (firstSelected != selectedNode) {
            String userInput = JOptionPane.showInputDialog("Enter a name: ");
            if (userInput == null) {
                return;
            }
            graph.addEdge(userInput, firstSelected, selectedNode);
            firstSelected = null;
            addEdgeFlag = false;
        }
    }

    /**
     * Returns the current selected ContainsArmy.
     * 
     * @return the current selected ContainsArmy.
     */
    public ContainsArmy getSelectedContainsArmy() {
        if (getSelectedNode() != null) {
            return (ContainsArmy) getSelectedNode();
        } else if (getSelectedEdge() != null) {
            return (ContainsArmy) getSelectedEdge();
        }
        return null;
    }

    /**
     * Returns the current selected ContainsEvent.
     * 
     * @return the current selected ContainsEvent.
     */
    public ContainsEvent getSelectedContainsEvent() {
        if (getSelectedNode() != null) {
            return (ContainsEvent) getSelectedNode();
        } else if (getSelectedEdge() != null) {
            return (ContainsEvent) getSelectedEdge();
        }
        return null;
    }
}
