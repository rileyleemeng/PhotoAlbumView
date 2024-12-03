package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manages shapes and snapshots in a photo album.
 */
public class PhotoAlbumModel implements IPhotoalbum {
  private final List<IShape> shapes = new ArrayList<>();
  private final List<ISnapshot> snapshots = new ArrayList<>();
  private final List<String> snapshotIDs = new ArrayList<>();
  // improve the efficiency of checking if the shape's name is unique
  private final Set<String> shapeNames = new HashSet<>();

  /**
   * Adds a rectangle to the album.
   * @param name Rectangle name.
   * @param type Shape type.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   * @param width Rectangle width.
   * @param height Rectangle height.
   * @param color Rectangle color.
   */
  public void createRectangle(String name, ShapeType type, double x, double y,
                              double width, double height, Color color) {
    validateShapeName(name);
    shapes.add(new Rectangle(name, type, x, y, width, height, color));
    shapeNames.add(name);
  }

  /**
   * Adds an oval to the album.
   * @param name Oval name.
   * @param type Shape type.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   * @param xRadius X-axis radius.
   * @param yRadius Y-axis radius.
   * @param color Oval color.
   */
  public void createOval(String name, ShapeType type, double x, double y,
                         double xRadius, double yRadius, Color color) {
    validateShapeName(name);
    shapes.add(new Oval(name, type, x, y, xRadius, yRadius, color));
    shapeNames.add(name);
  }

  /**
   * Removes a shape by name.
   * @param shapeName Shape name to remove.
   */
  @Override
  public void removeShape(String shapeName) {
    shapes.removeIf(shape -> shape.getName().equals(shapeName));
    shapeNames.remove(shapeName);
  }

  /**
   * Clears all shapes from the album.
   */
  @Override
  public void clearShapes() {
    shapes.clear();
    shapeNames.clear();
  }

  /**
   * Clears all snapshots.
   */
  @Override
  public void clearSnapshots() {
    snapshots.clear();
    snapshotIDs.clear();
  }

  /**
   * Moves a shape to new coordinates.
   * @param shapeName Shape name.
   * @param newX New X-coordinate.
   * @param newY New Y-coordinate.
   */
  @Override
  public void moveShape(String shapeName, double newX, double newY) {
    IShape shape = getShape(shapeName);
    if (shape != null) {
      shape.move(newX, newY);
    }
  }

  /**
   * Changes a shape's color.
   * @param shapeName Shape name.
   * @param newR Red value.
   * @param newG Green value.
   * @param newB Blue value.
   */
  @Override
  public void changeShapeColor(String shapeName, double newR, double newG, double newB) {
    IShape shape = getShape(shapeName);
    if (shape != null) {
      shape.changeColor(newR, newG, newB);
    }
  }

  /**
   * Resizes a rectangle.
   * @param shapeName Rectangle name.
   * @param newWidth New width.
   * @param newHeight New height.
   */
  @Override
  public void resizeRectangle(String shapeName, double newWidth, double newHeight) {
    IShape shape = getShape(shapeName);
    if (shape instanceof Rectangle rectangle) {
      rectangle.resizeWidth(newWidth);
      rectangle.resizeHeight(newHeight);
    }
  }

  /**
   * Resizes an oval.
   * @param shapeName Oval name.
   * @param newXRadius New x-radius.
   * @param newYRadius New y-radius.
   */
  @Override
  public void resizeOval(String shapeName, double newXRadius, double newYRadius) {
    IShape shape = getShape(shapeName);
    if (shape instanceof Oval oval) {
      oval.resizeXRadius(newXRadius);
      oval.resizeYRadius(newYRadius);
    }
  }

  /**
   * Takes a snapshot of the album.
   * @param description Snapshot description.
   */
  @Override
  public void takeSnapshot(String description) {
    List<IShape> snapshotShapes = new ArrayList<>(shapes);
    String newSnapshotId = LocalDateTime.now().toString();
    String formatTime = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    ISnapshot newSnapshot = new Snapshot(newSnapshotId,
        formatTime, description, snapshotShapes);

    snapshots.add(newSnapshot);
    snapshotIDs.add(newSnapshotId);
  }

  /**
   * Retrieves snapshot IDs.
   * @return List of snapshot IDs.
   */
  @Override
  public List<String> getSnapshotIDs() {
    return snapshotIDs;
  }

  /**
   * Retrieves all snapshots.
   * @return List of snapshots.
   */
  @Override
  public List<ISnapshot> getSnapshots() {
    return snapshots;
  }

  /**
   * Retrieves all shapes.
   * @return List of shapes.
   */
  @Override
  public List<IShape> getShapes() {
    return shapes;
  }

  /**
   * Retrieves a shape by name.
   * @param name Shape name.
   * @return Shape or null if not found.
   */
  @Override
  public IShape getShape(String name) {
    return shapes.stream().filter(shape -> shape.getName()
        .equals(name)).findFirst().orElse(null);
  }

  /**
   * Validates that a shape name is unique.
   * @param name Shape name to validate.
   * @throws IllegalArgumentException If the name already exists.
   */
  private void validateShapeName(String name) {
    if (shapeNames.contains(name)) {
      throw new IllegalArgumentException("Shape with name '" + name + "' already exists.");
    }
  }
}
