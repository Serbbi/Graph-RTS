package nl.rug.oop.rts.util;

import nl.rug.oop.rts.model.gameContent.Army;

import java.awt.*;

/**
 * Class to load all the necessary images.
 */
public class ImageLoader {

    /** Size in pixels for node width. */
    public static final int NODEWIDTH = 70;
    /** Size in pixels for node height. */
    public static final int NODEHEIGHT = 70;
    /** Size in pixels for the army icon. */
    public static final int ARMYICON = 30;

    private Image backgroundImage;
    private Image nodeImage;
    private Image nodeSelectedImage;
    private Image men;
    private Image elves;
    private Image dwarves;
    private Image mordor;
    private Image isengard;

    /**
     * Constructor to initialize all the images.
     */
    public ImageLoader(){
        backgroundImage = TextureLoader.getInstance().getTexture("mapTexture", 1000, 500);
        nodeImage = TextureLoader.getInstance().getTexture("node4", NODEWIDTH, NODEHEIGHT);
        nodeSelectedImage = TextureLoader.getInstance().getTexture("node3", NODEWIDTH, NODEHEIGHT);
        men = TextureLoader.getInstance().getTexture("factionMen", ARMYICON, ARMYICON);
        elves = TextureLoader.getInstance().getTexture("factionElves", ARMYICON, ARMYICON);
        dwarves = TextureLoader.getInstance().getTexture("factionDwarves", ARMYICON, ARMYICON);
        mordor = TextureLoader.getInstance().getTexture("factionMordor", ARMYICON, ARMYICON);
        isengard = TextureLoader.getInstance().getTexture("factionIsengard", ARMYICON, ARMYICON);
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public Image getNodeImage() {
        return nodeImage;
    }

    public Image getNodeSelectedImage() {
        return nodeSelectedImage;
    }

    /**
     * Getter for Army image.
     * @param army the army which needs an image
     * @return     an image based on the army
     */
    public Image getArmyImage(Army army) {
        return switch (army.getName()) {
            case "Men" -> men;
            case "Elves" -> elves;
            case "Dwarves" -> dwarves;
            case "Mordor" -> mordor;
            case "Isengard" -> isengard;
            default -> null;
        };
    }
}
