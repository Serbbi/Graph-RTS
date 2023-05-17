package nl.rug.oop.rts.controller.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import nl.rug.oop.rts.util.JSONObject;

import nl.rug.oop.rts.model.Graph;

/** Writes the whole graph to a json file. */
public class WriteJsonAction extends AbstractAction {
    private final Graph graph;
    private final JComponent parent;

    /**
     * Constructor for the action.
     * 
     * @param name   of the button.
     * @param graph  in which the simulation takes place.
     * @param parent parent jComponent.
     */
    public WriteJsonAction(String name, Graph graph, JComponent parent) {
        super(name);
        this.graph = graph;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Choose the save location....");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String fileNameInput = JOptionPane.showInputDialog("What would you like to name the save file?");

        if (fileNameInput == null || fileNameInput.equals("")) {
            JOptionPane.showMessageDialog(parent, "JSON writing cancelled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File file = new File(fc.getSelectedFile().getAbsolutePath() + "\\" + fileNameInput + ".json");
        System.out.println(file.getAbsolutePath());

        PrintWriter pr;
        try {
            pr = new PrintWriter(file, "UTF-8");

            JSONObject json = graph.toJson();

            pr.println(json.toString(0));

            pr.flush();
            pr.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e1) {
            JOptionPane.showMessageDialog(parent, "Unable to write json.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(parent, "Unable to write json.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
