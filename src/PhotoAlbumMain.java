import controller.IPhotoalbumController;
import controller.PhotoalbumController;
import model.IPhotoalbum;
import model.PhotoAlbumModel;
import views.GraphicalViewFrame;

import java.io.IOException;

/**
 * Entry point for the photo album application.
 */
public class PhotoAlbumMain {

  /**
   * Starts the photo album application.
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    // Default values
    String inputFile = null;
    String outputFile = null;
    String viewType = null;
    int xMax = 1000;
    int yMax = 1000;

    try {
      // Parse command-line arguments
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case "-in" -> inputFile = args[++i]; // Input file argument
          case "-out" -> outputFile = args[++i]; // Output file argument
          case "-view", "-v" -> viewType = args[++i]; // View type argument
          default -> { // Handle dimensions
            if (xMax == 1000) {
              xMax = Integer.parseInt(args[i]);
            } else if (yMax == 1000) {
              yMax = Integer.parseInt(args[i]);
            }
          }
        }
      }

      // Ensure mandatory arguments are provided
      if (inputFile == null || viewType == null) {
        System.out.println("Input file and view type are required.");
        return;
      }

      // Ensure output file is provided for web view
      if ("web".equals(viewType) && outputFile == null) {
        System.out.println("Output file is required for web view.");
        return;
      }

      // Create model and controller
      IPhotoalbum model = new PhotoAlbumModel();
      IPhotoalbumController controller = new PhotoalbumController(model);

      // Run the controller
      controller.run(inputFile, viewType, xMax, yMax, outputFile);

      // Create and display graphical view if applicable
      if ("graphical".equals(viewType)) {
        new GraphicalViewFrame(model, xMax, yMax);
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Error: Unable to run the photo album.");
      e.printStackTrace();
    }
  }
}
