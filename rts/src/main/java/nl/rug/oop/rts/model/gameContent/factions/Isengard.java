package nl.rug.oop.rts.model.gameContent.factions;

/**
 * Isengard Faction.
 * A type of army.
 */
public class Isengard extends Faction {
    private final String[] names = {"Uruk-hai", "Uruk Crossbowman", "Warg Rider"};

    /**
     * Constructor for Isendgard troops.
     */
    public Isengard() {
        super("Evil");
        setRandomName(names);
    }

    @Override
    public String getRandomName() {
        return names[(int) Math.floor(Math.random() * (names.length))];
    }

    @Override
    public String getName() {
        return "Isengard";
    }
}
