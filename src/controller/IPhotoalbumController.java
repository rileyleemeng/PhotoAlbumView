package controller;

import java.io.IOException;

/**
 * Interface for the photo album controller.
 */
public interface IPhotoalbumController {
  /**
   * Executes the controller logic.
   * @param filename File with commands.
   * @param viewType Display type of the view.
   * @param xMax Maximum x-dimension for the view window.
   * @param yMax Maximum y-dimension for the view window.
   * @param outputfile Output file for results.
   * @throws IOException If file operations fail.
   */
  void run(String filename, String viewType, int xMax, int yMax, String outputfile)
      throws IOException;
}
