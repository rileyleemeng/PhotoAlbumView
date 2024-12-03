package model;

import java.util.List;

/**
 * Interface for managing shapes and snapshots in a photo album.
 */
public interface IPhotoalbum {

  /**
   * Adds a rectangle to the album.
   * @param name Name of the rectangle.
   * @param type Shape type (RECTANGLE).
   * @param x X-coordinate.
   * @param y Y-coordinate.
   * @param width Rectangle width.
   * @param height Rectangle height.
   * @param color Rectangle color.
   */
  void createRectangle(String name, ShapeType type, double x, double y,
                       double width, double height, Color color);

  /**
   * Adds an oval to the album.
   * @param name Name of the oval.
   * @param type Shape type (OVAL).
   * @param x X-coordinate of center.
   * @param y Y-coordinate of center.
   * @param xRadius X-axis radius.
   * @param yRadius Y-axis radius.
   * @param color Oval color.
   */
  void createOval(String name, ShapeType type, double x, double y,
                  double xRadius, double yRadius, Color color);

  /**
   * Removes a shape by name.
   * @param shapeName Name of the shape to remove.
   */
  void removeShape(String shapeName);

  /**
   * Clears all shapes in the album.
   */
  void clearShapes();

  /**
   * Clears all snapshots in the album.
   */
  void clearSnapshots();

  /**
   * Moves a shape to new coordinates.
   * @param name Name of the shape.
   * @param newX New X-coordinate.
   * @param newY New Y-coordinate.
   */
  void moveShape(String name, double newX, double newY);

  /**
   * Resizes a rectangle.
   * @param name Name of the rectangle.
   * @param newWidth New width.
   * @param newHeight New height.
   */
  void resizeRectangle(String name, double newWidth, double newHeight);

  /**
   * Resizes an oval.
   * @param name Name of the oval.
   * @param xRadius New X-axis radius.
   * @param yRadius New Y-axis radius.
   */
  void resizeOval(String name, double xRadius, double yRadius);

  /**
   * Changes a shape's color.
   * @param name Shape name.
   * @param newR New red value (0-255).
   * @param newG New green value (0-255).
   * @param newB New blue value (0-255).
   */
  void changeShapeColor(String name, double newR, double newG, double newB);

  /**
   * Captures a snapshot with a description.
   * @param description Snapshot description.
   */
  void takeSnapshot(String description);

  /**
   * Gets all snapshot IDs.
   * @return List of snapshot IDs.
   */
  List<String> getSnapshotIDs();

  /**
   * Gets all snapshots.
   * @return List of snapshots.
   */
  List<ISnapshot> getSnapshots();

  /**
   * Gets all shapes.
   * @return List of shapes.
   */
  List<IShape> getShapes();

  /**
   * Retrieves a shape by name.
   * @param name Shape name.
   * @return The matching shape.
   */
  IShape getShape(String name);
}
