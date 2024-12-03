package views;

import controller.GraphicalViewController;
import model.IPhotoalbum;
import model.ISnapshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Frame for displaying graphical photo album snapshots.
 */
public class GraphicalViewFrame extends JFrame {
  private final GraphicalViewController controller;
  private final JPanel snapshotPanel;
  private final JPanel buttonsPanel;
  private final int xMax;
  private final int yMax;

  /**
   * Constructs the graphical view frame.
   * @param model Photo album model.
   * @param xMax Maximum width of the frame.
   * @param yMax Maximum height of the frame.
   */
  public GraphicalViewFrame(IPhotoalbum model, int xMax, int yMax) {
    super("CS5004 Shapes Photo Album Viewer");
    this.controller = new GraphicalViewController(model);
    this.xMax = xMax;
    this.yMax = yMax;

    // Initialize frame settings
    setSize(xMax, yMax);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    snapshotPanel = new JPanel(new BorderLayout());
    buttonsPanel = new JPanel(new FlowLayout());

    add(snapshotPanel, BorderLayout.CENTER);
    add(buttonsPanel, BorderLayout.SOUTH);

    createButtonsPanel(); // Create and add navigation buttons
    displaySnapshot(controller.getCurrentSnapshot());
    setVisible(true);
  }

  /**
   * Creates and adds navigation buttons to the panel.
   */
  private void createButtonsPanel() {
    JButton prev = new JButton("<< Prev ");
    JButton next = new JButton(" Next >>");
    JButton select = new JButton(" Select ");
    JButton quit = new JButton(" Quit ");

    prev.addActionListener(e -> startSnapshot(-1)); // Navigate to previous snapshot
    next.addActionListener(e -> startSnapshot(1));  // Navigate to next snapshot
    select.addActionListener(this::selectSnapshot);    // Select a specific snapshot
    quit.addActionListener(e -> System.exit(0));       // Exit the application

    buttonsPanel.add(prev);
    buttonsPanel.add(next);
    buttonsPanel.add(select);
    buttonsPanel.add(quit);
  }

  /**
   * Navigates through snapshots based on direction.
   * @param direction Navigation direction (-1 for previous, 1 for next).
   */
  private void startSnapshot(int direction) {
    if (controller.start(direction)) {
      displaySnapshot(controller.getCurrentSnapshot());
    } else {
      JOptionPane.showMessageDialog(this, "No more snapshots in this direction.",
          "Warning", JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * Opens a dialog to select a snapshot by ID.
   * @param e The triggered action event.
   */
  private void selectSnapshot(ActionEvent e) {
    String[] ids = controller.getSnapshotIDs().toArray(new String[0]);
    String selectedId = (String) JOptionPane.showInputDialog(
        this, "Select a Snapshot ID:", "Select Snapshot",
        JOptionPane.QUESTION_MESSAGE, null, ids, ids[0]);
    if (selectedId != null) {
      ISnapshot snapshot = controller.getSnapshotById(selectedId);
      if (snapshot != null) {
        displaySnapshot(snapshot);
      }
    }
  }

  /**
   * Displays a snapshot in the snapshot panel.
   * @param snapshot The snapshot to display.
   */
  private void displaySnapshot(ISnapshot snapshot) {
    snapshotPanel.removeAll(); // Clear existing content
    if (snapshot == null) {
      snapshotPanel.add(new JLabel("No snapshots available.", SwingConstants.CENTER),
          BorderLayout.CENTER);
    } else {
      snapshotPanel.add(new GraphicalViewPanel(snapshot, xMax, yMax), BorderLayout.CENTER);
    }
    snapshotPanel.revalidate();
    snapshotPanel.repaint();
  }
}
