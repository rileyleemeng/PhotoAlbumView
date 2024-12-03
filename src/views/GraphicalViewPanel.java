package views;

import model.IShape;
import model.ISnapshot;
import model.ShapeType;

import javax.swing.*;
import java.awt.*;

/**
 * Displays shapes from a snapshot in a graphical panel.
 */
public class GraphicalViewPanel extends JPanel {
  private final ISnapshot snapshot;
  private final int xMax;
  private final int yMax;

  /**
   * Constructs a graphical view panel.
   * @param snapshot Snapshot to display.
   * @param xMax Maximum width of the panel.
   * @param yMax Maximum height of the panel.
   */
  public GraphicalViewPanel(ISnapshot snapshot, int xMax, int yMax) {
    this.snapshot = snapshot;
    this.xMax = xMax;
    this.yMax = yMax;

    setLayout(new BorderLayout());
    add(createSnapshotLabelPanel(), BorderLayout.NORTH); // Adds label panel at the top
    add(createDrawingPanel(), BorderLayout.CENTER); // Adds drawing area at the center
  }

  /**
   * Creates a panel displaying the snapshot label.
   * @return Panel with snapshot label.
   */
  private JPanel createSnapshotLabelPanel() {
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel label = new JLabel(formatLabel());
    label.setForeground(Color.BLACK);
    labelPanel.add(label);
    return labelPanel;
  }

  /**
   * Formats the snapshot label text.
   * @return Formatted label text.
   */
  private String formatLabel() {
    return String.format("<html>%s<br>%s</html>",
        snapshot.getSnapshotId(), snapshot.getDescription());
  }

  /**
   * Creates a panel for rendering shapes.
   * @return Panel for shapes.
   */
  private JPanel createDrawingPanel() {
    JPanel drawingPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawShapes(g); // Draws shapes on the panel
      }
    };
    drawingPanel.setPreferredSize(new Dimension(xMax, yMax));
    drawingPanel.setBackground(Color.LIGHT_GRAY);
    return drawingPanel;
  }

  /**
   * Renders shapes from the snapshot.
   * @param g Graphics context for drawing.
   */
  private void drawShapes(Graphics g) {
    for (IShape shape : snapshot.getShapes()) {
      // Set the color for the current shape
      g.setColor(new Color(
          (int) shape.getColor().getR(),
          (int) shape.getColor().getG(),
          (int) shape.getColor().getB()
      ));
      int x = (int) shape.getX();
      int y = (int) shape.getY();

      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        // Cast to Rectangle and draw
        int width = (int) ((model.Rectangle) shape).getWidth();
        int height = (int) ((model.Rectangle) shape).getHeight();
        g.fillRect(x, y, width, height);
      } else if (shape.getShapeType() == ShapeType.OVAL) {
        // Cast to Oval and draw
        int xRadius = (int) ((model.Oval) shape).getXRadius();
        int yRadius = (int) ((model.Oval) shape).getYRadius();
        g.fillOval(x, y, xRadius, yRadius);
      }
    }
  }
}
