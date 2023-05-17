package nl.rug.oop.rts.view.panels.sideMenu;

import javax.swing.JPanel;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

/**
 * Base radios panel to provide basic functionality for Radios panels.
 */
public class RadiosPanel extends JPanel implements Observable {
    private final MouseObserver mouseObserver;
    private final InteractionMenuData interactionMenuData;

    private final ButtonGroup buttonGroup;
    private final List<JRadioButton> buttons;

    /**
     * Constructor for the panel.
     * 
     * @param mouseObserver       from which we take the selected node/edge
     * @param interactionMenuData where we store the selected army
     */
    public RadiosPanel(MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        this.mouseObserver = mouseObserver;
        this.interactionMenuData = interactionMenuData;
        initGBC();

        buttonGroup = new ButtonGroup();
        buttons = new ArrayList<>();

        setLayout(new GridBagLayout());
        setVisible(false);
    }

    /**
     * Initializer for the GridBagConstraints.
     * 
     * @return GridBagConstraints.
     */
    protected GridBagConstraints initGBC() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        return gbc;
    }

    protected MouseObserver getMouseObserver() {
        return mouseObserver;
    }

    /**
     * Getter for interaction menu.
     * 
     * @return interaction menu data
     */
    protected InteractionMenuData getInteractionMenuData() {
        return interactionMenuData;
    }

    /**
     * Getter for buttonGroup.
     * 
     * @return buttonGroup
     */
    protected ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    /**
     * Getter for buttons list.
     * 
     * @return Buttons list.
     */
    protected List<JRadioButton> getButtons() {
        return buttons;
    }

    @Override
    public void update() {
    }
}
