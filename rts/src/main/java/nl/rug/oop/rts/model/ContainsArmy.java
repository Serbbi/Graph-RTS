package nl.rug.oop.rts.model;

import java.util.List;

import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.factions.Faction;

/**
 * ContainsArmy interface to group the nodes and edges together in terms of
 * having armies.
 */
public interface ContainsArmy {

    /**
     * Getter for armies list field.
     * 
     * @return List of armies
     */
    List<Army> getArmies();

    /**
     * Adds a new army to the armies list.
     * 
     * @param faction faction that the new army belongs to.
     */
    void addArmy(Faction faction);

    /**
     * Adds an army to armies list.
     * 
     * @param army Army to be added.
     */
    void addArmy(Army army);

    /**
     * Removes an army from the armies list.
     * Takes advantage of the remove function of List class.
     * 
     * @param army Army to be removed.
     */
    void removeArmy(Army army);
}