package views;

import model.IPhotoalbum;

/**
 * Represents the graphical view for the photo album.
 */
public class GraphicalView implements IView {
  private IPhotoalbum model;

  /**
   * Initializes the graphical view.
   * @param model Photo album model instance.
   */
  public GraphicalView(IPhotoalbum model) {
    this.model = model;
  }

  /**
   * Displays the graphical view in a window, rendering model data to
   * visual graphics.
   * @param xMax Maximum X dimension of the window.
   * @param yMax Maximum Y dimension of the window.
   * @param outputfile Output file for saving view state (unused here).
   */
  @Override
  public void showView(int xMax, int yMax, String outputfile) {
    javax.swing.SwingUtilities.invokeLater(() -> {
      GraphicalViewFrame frame = new GraphicalViewFrame(this.model, xMax, yMax);
      frame.setVisible(true);
    });
  }
}
