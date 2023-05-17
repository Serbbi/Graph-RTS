package nl.rug.oop.rts.controller.actions.armyActions;

import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to select an army.
 */
public class SelectArmyAction extends AbstractAction {

    private final InteractionMenuData interactionMenuData;
    private final Army army;

    /**
     * Constructor for the action.
     * @param interactionMenuData where to store the selected army
     * @param army                army to be selected
     */
    public SelectArmyAction(InteractionMenuData interactionMenuData, Army army) {
        this.interactionMenuData = interactionMenuData;
        this.army = army;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interactionMenuData.setSelectedArmy(army);
        interactionMenuData.notifyObservers();
    }
}
