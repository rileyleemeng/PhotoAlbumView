package model;

import java.util.List;

/**
 * Represents a snapshot in the photo album, capturing a moment with shapes and metadata.
 */
public interface ISnapshot {

  /**
   * Retrieves the unique identifier of this snapshot.
   *
   * @return the snapshot ID as a string
   */
  String getSnapshotId();

  /**
   * Provides a textual description of this snapshot.
   *
   * @return the description as a string
   */
  String getDescription();

  /**
   * Returns all shapes included in this snapshot.
   *
   * @return a list of shapes in the snapshot
   */
  List<IShape> getShapes();
}
