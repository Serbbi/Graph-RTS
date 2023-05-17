package nl.rug.oop.rts.model.gameContent.events;

import java.util.List;
import java.util.Random;

import nl.rug.oop.rts.util.JSONObject;

import nl.rug.oop.rts.model.gameContent.Army;
import nl.rug.oop.rts.model.gameContent.Unit;

/**
 * When the event is run, it adds up to 20% more units to the army.
 */
public class ReinforcementEvent implements Event {

    @Override
    public void runEvent(Army army) {
        Random r = new Random();
        List<Unit> units = army.getUnits();

        int result = r.nextInt((units.size() / 10) * 2);

        for (int i = 0; i < result; i++) {
            army.addRandomUnit();
        }
    }

    @Override
    public String getEventType() {
        return "Reinforcement Event";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Type", getEventType());
        return json;
    }
}
