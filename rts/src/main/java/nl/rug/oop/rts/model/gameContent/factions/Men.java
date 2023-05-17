package nl.rug.oop.rts.model.gameContent.factions;

/**
 * Men Faction.
 * A type of army.
 */
public class Men extends Faction {

    private final String[] names = {"Gondor Soldier", "Tower Guard", "Ithilien Ranger"};

    /**
     * Constructor for man soldier names.
     */
    public Men() {
        super("Good");
        setRandomName(names);
    }

    @Override
    public String getRandomName() {
        return names[(int) Math.floor(Math.random() * (names.length))];
    }

    @Override
    public String getName() {
        return "Men";
    }
}
