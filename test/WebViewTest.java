import static org.junit.Assert.*;
import org.junit.Test;
import model.*;
import controller.PhotoalbumController;
import views.WebView;

/**
 * Tests for WebView integration with Controller and Model.
 */
public class WebViewTest {

  /**
   * Tests WebView and Controller with valid inputs.
   */
  @Test
  public void testGenerateHtmlWithController() {
    IPhotoalbum model = new PhotoAlbumModel();
    PhotoalbumController controller = new PhotoalbumController(model);
    WebView view = new WebView(model);

    // Create shapes and take a snapshot using the controller
    model.createRectangle("Rectangle1", ShapeType.RECTANGLE, 10, 10,
        50, 50, new Color(255, 0, 0));
    model.createOval("Oval1", ShapeType.OVAL, 30, 30,
        20, 20, new Color(0, 255, 0));
    controller.takeSnapshot("First Snapshot");

    // Verify generated HTML contains expected elements
    view.showView(800, 800, "output.html");
    String htmlContent = view.getHtmlContent();
    assertTrue(htmlContent.contains("First Snapshot"));
    assertTrue(htmlContent.contains("<rect"));
    assertTrue(htmlContent.contains("<ellipse"));

    // Compare HTML content with expected output
    String expected = "<!DOCTYPE html>"
        + "<html>"
        + "<head>"
        + "<title>Shapes Photo Album</title>"
        + "</head>"
        + "<body>"
        + "<div style=\"background-color:lightgrey;\">"
        + "<h2>" + model.getSnapshots().get(0).getSnapshotId() + "</h2>"
        + "<p>Description: First Snapshot</p>"
        + "<svg width=\"800\" height=\"800\">"
        + "<rect x=\"10.0\" y=\"10.0\" width=\"50.0\" height=\"50.0\" style=\"fill:rgb(255,0,0)\" />"
        + "<ellipse cx=\"50.0\" cy=\"50.0\" rx=\"20.0\" ry=\"20.0\" style=\"fill:rgb(0,255,0)\" />"
        + "</svg>"
        + "</div>"
        + "</body>"
        + "</html>";
    assertEquals(expected, htmlContent);
  }

  /**
   * Tests behavior when all snapshots are cleared.
   */
  @Test
  public void testClearSnapshots() {
    IPhotoalbum model = new PhotoAlbumModel();
    WebView view = new WebView(model);

    // Create shape and take a snapshot, then clear all snapshots
    model.createRectangle("Rect1", ShapeType.RECTANGLE, 10, 10,
        30, 30, new Color(255, 255, 0));
    model.takeSnapshot("Snapshot before clear");
    model.clearSnapshots();

    // Verify HTML output for an empty album
    view.showView(800, 800, "output_empty.html");
    String htmlContent = view.getHtmlContent();

    String expected = "<!DOCTYPE html>"
        + "<html>"
        + "<head>"
        + "<title>Shapes Photo Album</title>"
        + "</head>"
        + "<body>"
        + "</body>"
        + "</html>";
    assertEquals(expected, htmlContent);
  }

  /**
   * Tests taking snapshots with no shapes added.
   */
  @Test
  public void testSnapshotWithoutShapes() {
    IPhotoalbum model = new PhotoAlbumModel();
    WebView view = new WebView(model);

    // Take a snapshot without adding any shapes
    model.takeSnapshot("Empty Snapshot");

    // Verify HTML output contains snapshot metadata but no shapes
    view.showView(800, 800, "output_noShape.html");
    String htmlContent = view.getHtmlContent();
    assertTrue(htmlContent.contains("Empty Snapshot"));
    assertTrue(htmlContent.contains("<svg width=\"800\" height=\"800\"></svg>"));

    String expected = "<!DOCTYPE html>"
        + "<html>"
        + "<head>"
        + "<title>Shapes Photo Album</title>"
        + "</head>"
        + "<body>"
        + "<div style=\"background-color:lightgrey;\">"
        + "<h2>" + model.getSnapshots().get(0).getSnapshotId() + "</h2>"
        + "<p>Description: Empty Snapshot</p>"
        + "<svg width=\"800\" height=\"800\"></svg>"
        + "</div>"
        + "</body>"
        + "</html>";
    assertEquals(expected, htmlContent);
  }

  /**
   * Tests invalid shape creation scenarios.
   */
  @Test
  public void testInvalidShapeCreation() {
    IPhotoalbum model = new PhotoAlbumModel();

    // Test invalid rectangle parameters
    assertThrows(IllegalArgumentException.class, () -> {
      model.createRectangle("InvalidRect", ShapeType.RECTANGLE, -10, -10, -20, -20, new Color(255, 0, 0));
    });

    // Test invalid color parameters
    assertThrows(IllegalArgumentException.class, () -> {
      model.createOval("InvalidOval", ShapeType.OVAL, 30, 30, 20, 20, new Color(-1, 256, 300));
    });
  }

  /**
   * Tests generating multiple snapshots with different shapes.
   */
  @Test
  public void testMultipleSnapshots() {
    IPhotoalbum model = new PhotoAlbumModel();
    WebView view = new WebView(model);

    // Create shapes and take multiple snapshots
    model.createRectangle("Rect1", ShapeType.RECTANGLE, 10, 10, 30, 30, new Color(255, 255, 0));
    model.takeSnapshot("Snapshot1");
    model.createOval("Oval1", ShapeType.OVAL, 20, 20, 10, 10, new Color(0, 0, 255));
    model.takeSnapshot("Snapshot2");

    // Verify HTML contains metadata and elements for all snapshots
    view.showView(800, 800, "output_multiple.html");
    String htmlContent = view.getHtmlContent();
    assertTrue(htmlContent.contains("Snapshot1"));
    assertTrue(htmlContent.contains("Snapshot2"));
    assertTrue(htmlContent.contains("<rect"));
    assertTrue(htmlContent.contains("<ellipse"));
  }
}
