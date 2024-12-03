package controller;

import model.*;
import views.GraphicalView;
import views.IView;
import views.WebView;

import java.io.IOException;

/**
 * Controls the photo album operations, connecting the
 * model and views, containing some conditions
 * on moving logic, viewTypes.
 */
public class PhotoalbumController implements IPhotoalbumController {
  private final IPhotoalbum model;
  private final FileReader reader;

  /**
   * Creates a controller for managing photo albums.
   * @param model The photo album model.
   */
  public PhotoalbumController(IPhotoalbum model) {
    this.model = model;
    this.reader = new FileReader(this);
  }

  /**
   * Executes the photo album controller.
   * @param filename File containing commands.
   * @param viewType Type of view to display.
   * @param xMax Max x-dimension of view window.
   * @param yMax Max y-dimension of view window.
   * @param outputfile File to write output.
   * @throws IOException If file operations fail.
   */
  @Override
  public void run(String filename, String viewType, int xMax, int yMax, String outputfile)
      throws IOException {
    reader.readfile(filename);

    switch (viewType.toLowerCase()) {
      case "graphical" -> viewGraphical(xMax, yMax, outputfile);
      case "web" -> viewWeb(xMax, yMax, outputfile);
      default -> throw new IllegalArgumentException("Unknown view type: " + viewType);
    }
  }

  /**
   * Displays the graphical view.
   * @param xMax Max x-dimension of view window.
   * @param yMax Max y-dimension of view window.
   * @param outputfile File to write output.
   */
  private void viewGraphical(int xMax, int yMax, String outputfile) {
    IView view = new GraphicalView(model);
    view.showView(xMax, yMax, outputfile);
  }

  /**
   * Displays the web view.
   * @param xMax Max x-dimension of view window.
   * @param yMax Max y-dimension of view window.
   * @param outputfile File to write output.
   */
  private void viewWeb(int xMax, int yMax, String outputfile) {
    IView view = new WebView(model);
    view.showView(xMax, yMax, outputfile);
  }

  /**
   * Processes a given command.
   * @param command The command to execute.
   */
  public void readCommand(String command) {
    if (command.startsWith("#") || command.trim().isEmpty()) return; // ignore the comment line

    String[] tokens = command.split("\\s+");
    switch (tokens[0].toLowerCase()) {
      case "shape" -> handleShape(tokens);
      case "move" -> handleMove(tokens);
      case "color" -> handleColor(tokens);
      case "resize" -> handleResize(tokens);
      case "remove" -> handleRemove(tokens);
      case "snapshot" -> handleSnapshot(tokens);
      default -> System.err.println("Unknown command: " + tokens[0]);
    }
  }

  /**
   * Takes a snapshot with the provided description.
   * @param description Description of the snapshot.
   */
  public void takeSnapshot(String description) {
    model.takeSnapshot(description);
  }

  private void handleShape(String[] tokens) {
    try {
      String name = tokens[1];
      ShapeType type = ShapeType.valueOf(tokens[2].toUpperCase());
      double x = Double.parseDouble(tokens[3]);
      double y = Double.parseDouble(tokens[4]);
      double param1 = Double.parseDouble(tokens[5]);
      double param2 = Double.parseDouble(tokens[6]);
      Color color = parseColor(tokens, 7);

      if (type == ShapeType.RECTANGLE) {
        model.createRectangle(name, type, x, y, param1, param2, color);
      } else if (type == ShapeType.OVAL) {
        model.createOval(name, type, x, y, param1, param2, color);
      } else {
        throw new IllegalArgumentException("Unsupported shape type: " + type);
      }
    } catch (Exception e) {
      System.err.println("Error processing shape command: " + e.getMessage());
    }
  }

  private Color parseColor(String[] tokens, int startIndex) {
    double red = Double.parseDouble(tokens[startIndex]);
    double green = Double.parseDouble(tokens[startIndex + 1]);
    double blue = Double.parseDouble(tokens[startIndex + 2]);
    return new Color(red, green, blue);
  }

  private void handleMove(String[] tokens) {
    try {
      String name = tokens[1];
      double x = Double.parseDouble(tokens[2]);
      double y = Double.parseDouble(tokens[3]);
      model.moveShape(name, x, y);
    } catch (Exception e) {
      System.err.println("Error processing move command: " + e.getMessage());
    }
  }

  private void handleColor(String[] tokens) {
    try {
      String name = tokens[1];
      Color color = parseColor(tokens, 2);
      model.changeShapeColor(name, color.getR(), color.getG(), color.getB());
    } catch (Exception e) {
      System.err.println("Error processing color command: " + e.getMessage());
    }
  }

  private void handleResize(String[] tokens) {
    try {
      String name = tokens[1];
      double param1 = Double.parseDouble(tokens[2]);
      double param2 = Double.parseDouble(tokens[3]);
      IShape shape = model.getShape(name);

      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        model.resizeRectangle(name, param1, param2);
      } else if (shape.getShapeType() == ShapeType.OVAL) {
        model.resizeOval(name, param1, param2);
      } else {
        throw new IllegalArgumentException("Unsupported shape type for resize.");
      }
    } catch (Exception e) {
      System.err.println("Error processing resize command: " + e.getMessage());
    }
  }

  private void handleRemove(String[] tokens) {
    try {
      String name = tokens[1];
      model.removeShape(name);
    } catch (Exception e) {
      System.err.println("Error processing remove command: " + e.getMessage());
    }
  }

  private void handleSnapshot(String[] tokens) {
    String description = tokens.length > 1 ? String.join(" ", tokens)
        .substring(tokens[0].length()).trim() : "";
    model.takeSnapshot(description);
  }
}
