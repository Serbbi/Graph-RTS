package nl.rug.oop.rts.model.gameContent.events;

import java.util.List;
import java.util.Random;

import nl.rug.oop.rts.util.JSONObject;

import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.Unit;

/**
 * When event is run, it increases the damage of each unit in the army.
 * Each unit receives between 0 and 50 extra damage.
 */
public class HiddenWeaponryEvent implements Event {

    @Override
    public void runEvent(Army army) {
        Random r = new Random();
        List<Unit> units = army.getUnits();

        for (Unit unit : units) {
            int result = r.nextInt(50);

            unit.increaseDamage(result);
        }

    }

    @Override
    public String getEventType() {
        return "Hidden Weaponry Event";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Type", getEventType());
        return json;
    }
}
