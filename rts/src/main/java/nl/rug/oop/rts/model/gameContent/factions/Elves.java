package nl.rug.oop.rts.model.gameContent.factions;

/**
 * Elf faction.
 * A type of army.
 */
public class Elves extends Faction {
    private final String[] names = {"Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer"};

    /**
     * Constructor for elf names.
     */
    public Elves() {
        super("Good");
        setRandomName(names);

    }

    @Override
    public String getRandomName() {
        return names[(int) Math.floor(Math.random() * (names.length))];
    }

    @Override
    public String getName() {
        return "Elves";
    }
}
