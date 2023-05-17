package nl.rug.oop.rts.model.gameContent.factions;

/**
 * Mordor Faction.
 * A type of army.
 */
public class Mordor extends Faction {
    private final String[] names = {"Orc Warrior", "Orc Pikeman", "Haradrim archer"};

    /**
     * Constructor for Mordor orc and evil men names.
     */
    public Mordor() {
        super("Evil");
        setRandomName(names);
    }

    @Override
    public String getRandomName() {
        return names[(int) Math.floor(Math.random() * (names.length))];
    }

    @Override
    public String getName() {
        return "Mordor";
    }
}
