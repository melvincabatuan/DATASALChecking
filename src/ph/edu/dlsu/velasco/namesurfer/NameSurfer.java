package ph.edu.dlsu.velasco.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import ph.edu.dlsu.velasco.myqueue.*;

public class NameSurfer extends Program implements NameSurferConstants {

    /* Method: init() */
    /**
     * This method has the responsibility for reading in the data base and
     * initializing the interactors at the top of the window.
     */
    private NameSurferDataBase database = new NameSurferDataBase(NAMES_DATA_FILE);
    private NameSurferGraph canvas = new NameSurferGraph();

    private final JButton graph = new JButton("GRAPH");
    private final JButton clear = new JButton("CLEAR");

    private final JLabel label = new JLabel("NAME: ");
    private JTextField enterName = new JTextField(50);
    private int counter = -1;

    private MyQueue<NameSurferEntry> queue = new MyQueue<>();

    public void init() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        add(canvas);
        add(label, NORTH);
        add(enterName, NORTH);
        enterName.addActionListener(this);
        add(graph, NORTH);
        graph.addActionListener(this);
        add(clear, NORTH);
        clear.addActionListener(this);
    }

    /* Method: actionPerformed(e) */
    /**
     * This class is responsible for detecting when the buttons are clicked, so
     * you will have to define a method to respond to button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String nameInput = enterName.getText();
        if (e.getSource() == graph) {
            if (database.NameListCheck(nameInput)) {
                System.out.println("" + database.findEntry(nameInput).toString());
                if (!queue.isFull()) {
                    queue.enqueue(database.findEntry(nameInput));
                } else {
                    NameSurferEntry entry = queue.dequeue();
                    canvas.removeEntry(entry);
                    queue.enqueue(database.findEntry(nameInput));
                }
                counter++;
                if (counter % 5 == 0) {
                    counter = 1;
                }
                canvas.addEntry(database.findEntry(nameInput), counter);
            } else {
                System.out.println("Name not Found");
            }
        } else if (e.getSource() == clear) {
            canvas.clear();
        } else if (e.getSource() == enterName) {
            if (database.NameListCheck(nameInput)) {
                System.out.println("" + database.findEntry(nameInput).toString());
                if (!queue.isFull()) {
                    queue.enqueue(database.findEntry(nameInput));
                } else {
                    NameSurferEntry entry = queue.dequeue();
                    canvas.removeEntry(entry);
                    queue.enqueue(database.findEntry(nameInput));
                }
                counter++;
                if (counter % 5 == 0) {
                    counter = 1;
                }
                canvas.addEntry(database.findEntry(nameInput), counter);
            } else {
                System.out.println("Name not Found");
            }
        }
    }

    public static void main(String[] args) {
        new NameSurfer().start(args);
    }
}
