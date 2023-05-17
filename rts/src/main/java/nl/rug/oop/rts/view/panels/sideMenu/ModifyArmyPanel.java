package nl.rug.oop.rts.view.panels.sideMenu;

import nl.rug.oop.rts.controller.actions.armyActions.AddArmyAction;
import nl.rug.oop.rts.controller.actions.armyActions.RemoveArmyAction;
import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.controller.observers.Observable;
import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.sideBar.InteractionMenuData;

import javax.swing.*;
import java.awt.*;

/**
 * Panel containing two buttons to add or remove an army from a node.
 */
public class ModifyArmyPanel extends JPanel implements Observable {

    private final JButton addArmy;
    private final JButton removeArmy;
    private final MouseObserver mouseObserver;
    private final InteractionMenuData interactionMenuData;

    /**
     * Constructor for the panel.
     * 
     * @param mouseObserver       getting the selected node
     * @param interactionMenuData getting the selected army
     */
    public ModifyArmyPanel(MouseObserver mouseObserver, InteractionMenuData interactionMenuData) {
        this.mouseObserver = mouseObserver;
        this.interactionMenuData = interactionMenuData;
        setLayout(new GridBagLayout());
        setVisible(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label = new JLabel("Army:");
        add(label, gbc);

        gbc.gridx++;
        addArmy = new JButton(new AddArmyAction("+", mouseObserver));
        add(addArmy, gbc);

        gbc.gridx++;
        removeArmy = new JButton(new RemoveArmyAction("-", interactionMenuData, mouseObserver));
        removeArmy.setEnabled(false);
        add(removeArmy, gbc);
    }

    @Override
    public void update() {
        Army selectedArmy = interactionMenuData.getSelectedArmy();

        if (mouseObserver.getSelectedNode() != null) {
            this.setVisible(true);
            removeArmy.setEnabled(selectedArmy != null);
        } else {
            this.setVisible(false);
        }
    }
}
