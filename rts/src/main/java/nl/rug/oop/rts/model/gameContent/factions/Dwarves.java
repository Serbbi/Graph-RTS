package nl.rug.oop.rts.model.gameContent.factions;

/**
 * Dwarf faction.
 * Is a type of army.
 */
public class Dwarves extends Faction {
    private final String[] names = {"Guardian", "Phalanx", "Axe Thrower"};

    /**
     * Constructor for dwarf names.
     */
    public Dwarves() {
        super("Good");
        setRandomName(names);
    }

    @Override
    public String getRandomName() {
        return names[(int) Math.floor(Math.random() * (names.length))];
    }

    @Override
    public String getName() {
        return "Dwarves";
    }
}
