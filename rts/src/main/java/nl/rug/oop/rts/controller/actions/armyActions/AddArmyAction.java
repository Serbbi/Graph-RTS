package nl.rug.oop.rts.controller.actions.armyActions;

import nl.rug.oop.rts.controller.observers.MouseObserver;
import nl.rug.oop.rts.model.node.Node;
import nl.rug.oop.rts.model.gameContent.factions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to add an army on button click.
 */
public class AddArmyAction extends AbstractAction {
    private final MouseObserver mouseObserver;

    /**
     * Constructor for action.
     * 
     * @param name          name of the button.
     * @param mouseObserver for selecting the node where to add the army.
     */
    public AddArmyAction(String name, MouseObserver mouseObserver) {
        super(name);
        this.mouseObserver = mouseObserver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] possibilities = { "Men", "Elves", "Dwarves", "Mordor", "Isengard" };
        String userInput = (String) JOptionPane.showInputDialog(null, "Select a Faction",
                "Add an army", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Men");
        if (userInput == null) {
            return;
        }
        Node node = mouseObserver.getSelectedNode();
        if (node != null) {
            switch (userInput) {
                case "Men" -> node.addArmy(new Men());
                case "Elves" -> node.addArmy(new Elves());
                case "Dwarves" -> node.addArmy(new Dwarves());
                case "Mordor" -> node.addArmy(new Mordor());
                case "Isengard" -> node.addArmy(new Isengard());
            }
        }
    }
}
