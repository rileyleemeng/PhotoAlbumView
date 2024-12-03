package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a snapshot of shapes in the album.
 */
public class Snapshot implements ISnapshot {
  private final String snapshotId;
  private final String timestamp;
  private final String description;
  private final List<IShape> shapes;

  /**
   * Constructs a snapshot with given attributes.
   * @param snapshotId Snapshot ID.
   * @param timestamp Snapshot timestamp.
   * @param description Snapshot description.
   * @param shapes List of shapes in snapshot.
   */
  public Snapshot(String snapshotId, String timestamp, String description, List<IShape> shapes) {
    this.snapshotId = snapshotId;
    this.timestamp = timestamp;
    this.description = description;
    this.shapes = new ArrayList<>();
    deepCopy(shapes, this.shapes);
  }

  /**
   * Gets the snapshot ID.
   * @return Snapshot ID.
   */
  @Override
  public String getSnapshotId() {
    return snapshotId;
  }

  /**
   * Gets the description.
   * @return Snapshot description.
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Gets the shapes in the snapshot.
   * @return List of shapes.
   */
  @Override
  public List<IShape> getShapes() {
    return shapes;
  }

  /**
   * Creates a deep copy of the shape list.
   * @param root Source list of shapes.
   * @param list Destination list of shapes.
   */
  public static void deepCopy(List<IShape> root, List<IShape> list) {
    list.clear();
    for (IShape shape : root) {
      list.add(shape.copy());
    }
  }

  /**
   * Returns a string representation of the snapshot.
   * @return Snapshot details as a string.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Snapshot ID: ").append(snapshotId).append("\n")
        .append("Timestamp: ").append(timestamp).append("\n")
        .append("Description: ").append(description).append("\n")
        .append("Shape Information:\n");
    for (IShape shape : shapes) {
      sb.append(shape.toString()).append("\n");
    }
    return sb.toString();
  }
}
