package nl.rug.oop.rts.model.gameContent;

import nl.rug.oop.rts.model.ContainsArmy;

import java.util.ArrayList;
import java.util.List;

/**
 * The Battle class resolves the battles between the armies.
 */
public class Battle {

    private final List<Army> goodArmies = new ArrayList<>();
    private final List<Army> badArmies = new ArrayList<>();

    /**
     * Constructor for the Battle class.
     */
    public Battle() {
    }

    /**
     * Place armies in the two teams.
     * 
     * @param location the location of the armies
     */
    public void initArmies(ContainsArmy location) {
        for (Army army : location.getArmies()) {
            if (army.getTeam().equals("Good")) {
                goodArmies.add(army);
            } else {
                badArmies.add(army);
            }
        }
    }

    /**
     * Does the fighting between the armies.
     * 
     * @param location where the fight takes place
     */
    public void fight(ContainsArmy location) {
        initArmies(location);

        if (badArmies.isEmpty() || goodArmies.isEmpty()) {
            goodArmies.clear();
            badArmies.clear();
            return;
        }

        while (!goodArmies.isEmpty() && !badArmies.isEmpty()) {
            int damageGood = getArmiesDamage(goodArmies);
            int damageBad = getArmiesDamage(badArmies);

            damageGood /= badArmies.size();
            damageBad /= goodArmies.size();

            dealDamage(goodArmies, damageBad);
            dealDamage(badArmies, damageGood);

            removeDefeated(goodArmies, location);
            removeDefeated(badArmies, location);
        }

        goodArmies.clear();
        badArmies.clear();
    }

    /**
     * Computes the damage from the first index unit in the army from every army.
     * 
     * @param armies the armies from which takes the damage
     * @return the total damage
     */
    public int getArmiesDamage(List<Army> armies) {
        int damage = 0;
        for (Army army : armies) {
            damage += army.getUnits().get(0).getDamage();
        }
        return damage;
    }

    /**
     * Deals damage to the first index unit in the armies.
     * The damage is divided to equally to the number of armies.
     * 
     * @param armies to which the damage is applied
     * @param damage which is dealt
     */
    public void dealDamage(List<Army> armies, int damage) {
        for (Army army : armies) {
            army.getUnits().get(0).receiveDamage(damage);
            if (army.getUnits().get(0).getHealth() <= 0) {
                army.getUnits().remove(0);
            }
        }
    }

    /**
     * Removes the defeated armies from the location.
     * 
     * @param armies   the list of armies in which we look for defeated
     * @param location where the armies are located
     */
    public void removeDefeated(List<Army> armies, ContainsArmy location) {
        for (int i = armies.size() - 1; i >= 0; i--) {
            Army army = armies.get(i);
            if (army.getUnits().isEmpty()) {
                armies.remove(army);
                location.removeArmy(army);
            }
        }
    }
}
