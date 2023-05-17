package nl.rug.oop.rts.model.gameContent;

import nl.rug.oop.rts.model.JSONable;
import nl.rug.oop.rts.model.gameContent.factions.*;
import nl.rug.oop.rts.util.JSONArray;
import nl.rug.oop.rts.util.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Army class, belonging to a faction and having a number of units.
 */
public class Army implements JSONable{
    private static final int MAX_DEFAULT_UNITS = 50;
    private static final int MIN_DEFAULT_UNITS = 20;

    private final List<Unit> units;

    private final Faction faction;

    private final String team;

    /**
     * Constructor for army, having as input only the faction.
     * The unit number is random.
     * Based on the faction, the unit's name is chosen also randomly.
     * 
     * @param faction the input
     */
    public Army(Faction faction) {
        units = new ArrayList<>();
        this.faction = faction;
        team = faction.getTeam();

        int unitCount = (int) Math.floor(Math.random() *
                (MAX_DEFAULT_UNITS - MIN_DEFAULT_UNITS + 1) + MIN_DEFAULT_UNITS);

        for (int i = 0; i < unitCount; i++) {
            addRandomUnit();
        }
    }

    /**
     * Getter for team attribute.
     * 
     * @return Team name.
     */
    public String getTeam() {
        return team;
    }

    /**
     * Getter for the units list.
     * 
     * @return List of units
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Getter for the name field.
     * 
     * @return Name of army.
     */
    public String getName() {
        return faction.getName();
    }

    /**
     * Adds a new unit to the units list.
     * 
     * @param unit New unit
     */
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    /**
     * Adds a new unit with a random name based on the faction name.
     */
    public void addRandomUnit() {
        addUnit(new Unit(faction.getRandomName()));
    }

    public int getUnitCount() {
        return units.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("Faction", faction.getName());
        json.put("Team", team);
        
        JSONArray unitsJson = new JSONArray();

        for (Unit unit : units) {
            unitsJson.put(unit.toJson());
        }
        
        json.put("Units", unitsJson);

        return json;
    }
}
