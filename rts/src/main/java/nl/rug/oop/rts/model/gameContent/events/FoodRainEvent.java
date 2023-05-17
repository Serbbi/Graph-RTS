package nl.rug.oop.rts.model.gameContent.events;

import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.Unit;
import nl.rug.oop.rts.util.JSONObject;

import java.util.List;

/**
 * Event which empowers all the troops.
 * Gives damage and health to all units.
 */
public class FoodRainEvent implements Event{
    private static final int BONUSHEALTH = 30;
    private static final int BONUSDAMAGE = 20;

    @Override
    public String getEventType() {
        return "Food Rain Event";
    }

    @Override
    public void runEvent(Army army) {
        List<Unit> units = army.getUnits();

        for (Unit unit: units) {
            unit.setHealth(unit.getHealth() + BONUSHEALTH);
            unit.setDamage(unit.getDamage() + BONUSDAMAGE);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Type", getEventType());
        return json;
    }
}
