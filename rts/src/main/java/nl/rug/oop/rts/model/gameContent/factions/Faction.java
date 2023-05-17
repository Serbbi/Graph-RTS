package nl.rug.oop.rts.model.gameContent.factions;

import nl.rug.oop.rts.model.Named;

/**
 * Faction interface used for selecting the unit's name based on faction.
 * Also used for getting the team's name.
 */
public abstract class Faction implements Named {
    private String name;
    private final String team;

    public Faction(String team) {
        this.team = team;
        this.name = "";
    }

    public String getTeam() {
        return team;
    }

    public String getRandomName() {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    public void setRandomName(String[] names) {
        setName(names[(int) Math.floor(Math.random() * (names.length))]);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
