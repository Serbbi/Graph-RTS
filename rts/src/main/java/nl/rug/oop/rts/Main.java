package nl.rug.oop.rts;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarculaLaf;

import nl.rug.oop.rts.view.Frame;

/**
 * Main class. It's like the Big Bang, but for a program.
 */
public class Main {

    /**
     * main thing.
     * @param args magic
     */
    public static void main(String[] args) {
        FlatDarculaLaf.setup(); // Dark mode
        SwingUtilities.invokeLater(Frame::new);
    }
}