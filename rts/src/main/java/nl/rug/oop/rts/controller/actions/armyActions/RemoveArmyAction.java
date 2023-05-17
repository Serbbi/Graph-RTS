package nl.rug.oop.rts.controller.actions.armyActions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action for removing an army on button press.
 */
public class RemoveArmyAction extends AbstractAction {

    private final InteractionMenuData interactionMenuData;
    private final MouseObserver mouseObserver;

    /**
     * Constructor for action.
     * @param name                name of the button
     * @param interactionMenuData where the selected army is stored
     * @param mouseObserver       from which node to remove the army
     */
    public RemoveArmyAction(String name, InteractionMenuData interactionMenuData, MouseObserver mouseObserver) {
        super(name);
        this.interactionMenuData = interactionMenuData;
        this.mouseObserver = mouseObserver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mouseObserver.getSelectedNode() != null) {
            Army army = interactionMenuData.getSelectedArmy();
            interactionMenuData.setSelectedArmy(null);
            mouseObserver.getSelectedNode().removeArmy(army);
        }
    }
}
