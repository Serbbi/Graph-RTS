package nl.rug.oop.rts.view.panels.sideMenu;

import java.util.ArrayList;
import java.util.List;

import nl.rug.oop.rts.controller.actions.armyActions.SelectArmyAction;
import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.ContainsArmy;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.*;

/**
 * ArmyRadiosPanel displays all the armies on that node/edge in a radio button
 * panel.
 */
public class ArmyRadiosPanel extends RadiosPanel {
    private List<Army> armies;
    private GridBagConstraints gbc;

    /**
     * Constructor for the panel.
     * 
     * @param mouseObserver       from which we take the selected node/edge
     * @param interactionMenuData where we store the selected army
     */
    public ArmyRadiosPanel(MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        super(mouseObserver, interactionMenuData);
        gbc = initGBC();

        armies = new ArrayList<>();
    }

    /**
     * Sets the radio buttons.
     */
    public void setRadioButtons() {
        for (JRadioButton btn : getButtons()) {
            getButtonGroup().remove(btn);
            remove(btn);
            gbc.gridy--;
        }
        getButtons().clear();

        for (Army army : armies) {
            JRadioButton btn = new JRadioButton(army.getName());

            btn.addActionListener(new SelectArmyAction(getInteractionMenuData(), army));

            getButtons().add(btn);
            getButtonGroup().add(btn);
            add(btn, gbc);
            gbc.gridy++;
        }
        updateUI();
    }

    @Override
    public void update() {
        ContainsArmy containsArmy = getMouseObserver().getSelectedContainsArmy();

        if (containsArmy != null) {
            if (getInteractionMenuData().getSelectedArmy() == null) {
                armies = containsArmy.getArmies();
                setRadioButtons();
                this.setVisible(true);
            }
        } else {
            this.setVisible(false);
        }
    }
}