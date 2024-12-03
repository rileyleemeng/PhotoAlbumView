package views;

/**
 * Interface for displaying views (graphical or web).
 */
public interface IView {

  /**
   * Displays the view.
   * @param xMax Maximum width of the view window.
   * @param yMax Maximum height of the view window.
   * @param outputfile Output file for saving view state (if applicable).
   */
  void showView(int xMax, int yMax, String outputfile);
}
