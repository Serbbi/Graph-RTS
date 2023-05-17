package nl.rug.oop.rts.model.gameContent;

import nl.rug.oop.rts.model.JSONable;
import nl.rug.oop.rts.model.Named;
import nl.rug.oop.rts.util.JSONObject;

/**
 * Unit class are the troops in the armies.
 */
public class Unit implements Named, JSONable {

    private String name;
    private int health;
    private int damage;

    /**
     * Constructor for unit, takes as input only the name.
     * The damage and health are chosen randomly.
     * 
     * @param name of the unit
     */
    public Unit(String name) {
        this.name = name;
        health = (int) Math.floor(Math.random() * (100 - 50 + 1) + 50);
        damage = (int) Math.floor(Math.random() * (100 - 0 + 1) + 0);
    }

    /**
     * Getter for name field.
     * 
     * @return Name of unit
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name field.
     * 
     * @param name Name of unit
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for health field.
     * 
     * @return Health of unit.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter for damage field.
     * 
     * @param damage Damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Increases the unit's damage.
     * 
     * @param damageIncrease The amount that the damage will be increase.
     */
    public void increaseDamage(int damageIncrease) {
        this.damage += damageIncrease;
    }

    /**
     * Getter for damage field.
     * 
     * @return Damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Setter for health field.
     * 
     * @param health Health of unit.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Decreases health when the unit is damaged.
     * 
     * @param damageReceived The amount of health to remove from the unit.
     */
    public void receiveDamage(int damageReceived) {
        health -= damageReceived;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Damage", damage);
        json.put("Health", health);
        return json;
    }
}
