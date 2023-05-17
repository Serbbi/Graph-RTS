package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observer;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.panels.SplitPanel;
import nl.rug.oop.rts.view.panels.TopBar;

import javax.swing.*;
import java.awt.*;

/**
 * The frame in which is displayed the game.
 */

public class Frame extends JFrame {

    /** Width of the frame. */
    public static final int WIDTH = 1200;
    /** Height of the frame. */
    public static final int HEIGHT = 800;
    private final Graph graph;

    /**
     * Constructor for the frame.
     */
    public Frame() {
        super("RTS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());

        graph = new Graph(new Observer());

        MouseObserver mouseObserver = new MouseObserver(graph);

        add(new TopBar(graph, mouseObserver), BorderLayout.PAGE_START);
        add(new SplitPanel(graph, mouseObserver), BorderLayout.CENTER);

        // Initializing the popup. Keeps the popup out of the model.
        PopUp popUp = new PopUp(graph);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
