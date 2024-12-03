package views;

import model.*;
import model.Color;

import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * Displays the photo album in a web view.
 */
public class WebView implements IView {
  private IPhotoalbum model;
  private String htmlContent;

  /**
   * Constructs a web view.
   * @param model Photo album model.
   */
  public WebView(IPhotoalbum model) {
    this.model = model;
  }

  /**
   * Displays the photo album in a browser.
   * @param xMax Maximum X dimension (optional, unused).
   * @param yMax Maximum Y dimension (optional, unused).
   * @param outputfile File to save the HTML content.
   */
  @Override
  public void showView(int xMax, int yMax, String outputfile) {
    List<ISnapshot> snapshots = model.getSnapshots();
    generateHtml(snapshots);
    try {
      File file = new File(outputfile);
      writeToFile(file);
      openInBrowser(file);
    } catch (IOException e) {
      System.err.println("Error displaying HTML content: " + e.getMessage());
    }
  }

  /**
   * Generates HTML content for all snapshots.
   * @param snapshots List of snapshots.
   */
  private void generateHtml(List<ISnapshot> snapshots) {
    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<!DOCTYPE html>")
        .append("<html><head><title>Shapes Photo Album</title></head><body>");
    for (ISnapshot snapshot : snapshots) {
      htmlBuilder.append("<div style=\"background-color:lightgrey;\">")
          .append("<h2>").append(snapshot.getSnapshotId()).append("</h2>")
          .append("<p>Description: ").append(snapshot.getDescription()).append("</p>")
          .append("<svg width=\"800\" height=\"800\">")
          .append(generateSvg(snapshot))
          .append("</svg></div>");
    }
    htmlBuilder.append("</body></html>");
    htmlContent = htmlBuilder.toString();
  }

  /**
   * Writes HTML content to a file.
   * @param file File to write to.
   * @throws IOException If file writing fails.
   */
  private void writeToFile(File file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(htmlContent);
    }
  }

  /**
   * Opens the HTML file in the default browser.
   * @param file HTML file to open.
   * @throws IOException If browser opening fails.
   */
  private void openInBrowser(File file) throws IOException {
    Desktop.getDesktop().browse(file.toURI());
  }

  /**
   * Generates SVG content for a snapshot.
   * @param snapshot Snapshot to process.
   * @return SVG content as a string.
   */
  private String generateSvg(ISnapshot snapshot) {
    StringBuilder svgBuilder = new StringBuilder();
    for (IShape shape : snapshot.getShapes()) {
      svgBuilder.append(generateShapeSvg(shape));
    }
    return svgBuilder.toString();
  }

  /**
   * Generates SVG content for a single shape.
   * @param shape Shape to process.
   * @return SVG representation of the shape.
   */
  private String generateShapeSvg(IShape shape) {
    String color = formatColor(shape.getColor());
    return switch (shape.getShapeType()) {
      case RECTANGLE -> generateRectangleSvg((model.Rectangle) shape, color);
      case OVAL -> generateOvalSvg((model.Oval) shape, color);
      default -> "";
    };
  }

  /**
   * Generates SVG for a rectangle.
   * @param rectangle Rectangle shape.
   * @param color Shape color in SVG format.
   * @return SVG string for the rectangle.
   */
  private String generateRectangleSvg(model.Rectangle rectangle, String color) {
    return String.format("<rect x=\"%.1f\" y=\"%.1f\" "
           + "width=\"%.1f\" height=\"%.1f\" style=\"fill:%s\" />",
        rectangle.getX(), rectangle.getY(),
        rectangle.getWidth(), rectangle.getHeight(),
        color);
  }

  /**
   * Generates SVG for an oval.
   * @param oval Oval shape.
   * @param color Shape color in SVG format.
   * @return SVG string for the oval.
   */
  private String generateOvalSvg(model.Oval oval, String color) {
    return String.format("<ellipse cx=\"%.1f\" cy=\"%.1f\" "
            + "rx=\"%.1f\" ry=\"%.1f\" style=\"fill:%s\" />",
        oval.getX() + oval.getXRadius(),
        oval.getY() + oval.getYRadius(),
        oval.getXRadius(), oval.getYRadius(),
        color);
  }

  /**
   * Formats a color for SVG.
   * @param color Color object.
   * @return Color in "rgb(r,g,b)" format.
   */
  private String formatColor(Color color) {
    return String.format("rgb(%d,%d,%d)",
        (int) color.getR(), (int) color.getG(), (int) color.getB());
  }

  /**
   * Retrieves the generated HTML content.
   * @return HTML content as a string.
   */
  public String getHtmlContent() {
    return htmlContent;
  }
}
