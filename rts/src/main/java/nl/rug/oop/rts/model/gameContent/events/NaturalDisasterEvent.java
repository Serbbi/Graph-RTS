package nl.rug.oop.rts.model.gameContent.events;

import java.util.List;
import java.util.Random;

import nl.rug.oop.rts.util.JSONObject;

import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.Unit;

/**
 * When this event is run, it kills off units from the army.
 * Up to 50% of the units can be killed.
 */
public class NaturalDisasterEvent implements Event {

    @Override
    public void runEvent(Army army) {
        Random r = new Random();
        List<Unit> units = army.getUnits();

        // Kills up to half of the units
        int result = r.nextInt(units.size() / 2);

        for (int i = 0; i < result; i++) {
            units.remove(0);
        }

    }

    @Override
    public String getEventType() {
        return "Natural Disaster Event";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Type", getEventType());
        return json;
    }
}
