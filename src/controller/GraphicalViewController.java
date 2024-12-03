package controller;

import model.IPhotoalbum;
import model.ISnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages snapshots navigation and retrieval.
 */
public class GraphicalViewController {
  private final IPhotoalbum model;
  private final List<ISnapshot> snapshots;
  private final List<String> snapshotIDs;
  private final Map<String, ISnapshot> snapshotMap;
  private int curSnapshotIndex;

  /**
   * Initializes the controller with a photo album model.
   * @param model Photo album model to manage.
   */
  public GraphicalViewController(IPhotoalbum model) {
    this.model = model;
    this.snapshots = model.getSnapshots();
    this.snapshotIDs = model.getSnapshotIDs();
    this.snapshotMap = new HashMap<>();
    initializeSnapshotMap();
    this.curSnapshotIndex = 0;
  }

  /**
   * Populates the snapshot map for quick retrieval.
   */
  private void initializeSnapshotMap() {
    for (ISnapshot snapshot : snapshots) {
      snapshotMap.put(snapshot.getSnapshotId(), snapshot);
    }
  }

  /**
   * Gets the currently selected snapshot.
   * @return Current snapshot, or null if none exist.
   */
  public ISnapshot getCurrentSnapshot() {
    return snapshots.isEmpty() ? null : snapshots.get(curSnapshotIndex);
  }

  /**
   * Navigates to a snapshot in a specific direction.
   * @param direction Offset to navigate (positive or negative).
   * @return True if navigation succeeds; false otherwise.
   */
  public boolean start(int direction) {
    int newIndex = curSnapshotIndex + direction;
    if (newIndex >= 0 && newIndex < snapshots.size()) {
      curSnapshotIndex = newIndex;
      return true;
    }
    return false;
  }

  /**
   * Retrieves a snapshot by its unique ID.
   * @param id Unique identifier for the snapshot.
   * @return Snapshot object, or null if not found.
   */
  public ISnapshot getSnapshotById(String id) {
    return snapshotMap.get(id);
  }

  /**
   * Gets all snapshot IDs.
   * @return List of snapshot IDs.
   */
  public List<String> getSnapshotIDs() {
    return snapshotIDs;
  }
}
