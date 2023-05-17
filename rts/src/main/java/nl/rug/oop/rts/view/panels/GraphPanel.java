package nl.rug.oop.rts.view.panels;

import javax.swing.*;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.node.Node;
import nl.rug.oop.rts.model.node.NodeCoordinates;
import nl.rug.oop.rts.util.ImageLoader;

import java.awt.*;
import java.util.List;

/**
 * JPanel for displaying the graph.
 */
public class GraphPanel extends JPanel implements Observable {
    private ImageLoader imageLoader;

    private final Graph graph;

    private final MouseObserver mouseObserver;

    /**
     * Constructor for the panel.
     * 
     * @param graph         Which is constructed.
     * @param mouseObserver To get selections.
     */
    public GraphPanel(Graph graph, MouseObserver mouseObserver) {
        imageLoader = new ImageLoader();
        this.graph = graph;
        graph.addObservable(this);
        this.mouseObserver = mouseObserver;
        this.addMouseListener(mouseObserver);
        this.addMouseMotionListener(mouseObserver);
    }

    /**
     * Displays the graph.
     * 
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBackgroundImage(),
                0, 0, getWidth() - 50, getHeight() - 50, this);

        drawEdges(g);
        drawNodes(g);
    }

    /**
     * Draws the nodes held in the graph.
     * 
     * If the node is selected, it is a magenta color.
     * 
     * @param g Graphics object to be used to draw the nodes.
     */
    public void drawNodes(Graphics g) {
        List<Node> nodes = graph.getNodes();

        Node selectedNode = mouseObserver.getSelectedNode();

        for (Node node : nodes) {
            NodeCoordinates coord = node.getNodeCoordinates();
            if (selectedNode != null && selectedNode == node) {
                g.drawImage(imageLoader.getNodeSelectedImage(), coord.getX(), coord.getY(), this);
            } else {
                g.drawImage(imageLoader.getNodeImage(), coord.getX(), coord.getY(), this);
            }

            // Draw the name onto the node.
            String nodeName = node.getName();
            g.setColor(Color.BLACK);
            g.setFont(new Font("Dialog", Font.BOLD, 16));

            int nameWidth = g.getFontMetrics().stringWidth(nodeName);

            g.drawString(nodeName, coord.getX() + (coord.getWidth() - nameWidth) / 2,
                    coord.getY() + coord.getHeight() / 2);

            g.setFont(new Font("Dialog", Font.BOLD, 12));
            if (!node.getArmies().isEmpty()) {
                int offset = 0;
                for (Army army : node.getArmies()) {
                    g.drawImage(imageLoader.getArmyImage(army), coord.getX() + offset, coord.getY() - 10, this);
                    offset += 20;
                    // Draw the number of troops above the armies icons.
                    g.drawString(Integer.toString(army.getUnitCount()), coord.getX() + offset - 10,
                            coord.getY() - 12);
                }
            }
        }
    }

    /**
     * Draws the edges held in the graph.
     * Connects the centers of the nodes that the edge has with a line.
     * 
     * If the edge is selected, it is a magenta color.
     * 
     * @param g Graphics object to be used to draw the edges.
     */
    public void drawEdges(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[] { 9 }, 0);
        g2d.setStroke(dashed);

        List<Edge> edges = graph.getEdges();

        Edge selectedEdge = mouseObserver.getSelectedEdge();

        for (Edge edge : edges) {
            Point node0Center = edge.getNode(0).getNodeCoordinates().getCenter();
            Point node1Center = edge.getNode(1).getNodeCoordinates().getCenter();

            if (edge == selectedEdge) {
                g2d.setColor(Color.lightGray);
            } else {
                g2d.setColor(Color.darkGray);
            }

            g2d.drawLine((int) node0Center.getX(), (int) node0Center.getY(),
                    (int) node1Center.getX(), (int) node1Center.getY());

            if (!edge.getArmies().isEmpty()) {
                int offset = 0;
                for (Army army : edge.getArmies()) {
                    g.drawImage(imageLoader.getArmyImage(army),
                            (int) (((node0Center.getX() + node1Center.getX()) / 2) + offset),
                            (int) ((node0Center.getY() + node1Center.getY()) / 2), this);
                    offset += 20;
                }
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }
}
