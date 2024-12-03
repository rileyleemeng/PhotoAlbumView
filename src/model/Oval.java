package model;

/**
 * Represents an oval shape with radii and color.
 */
public class Oval extends Shape {
  private double xRadius; // Semi-major axis
  private double yRadius; // Semi-minor axis

  /**
   * Constructs an oval with attributes.
   * @param name Oval name.
   * @param type Shape type (OVAL).
   * @param x Center x-coordinate.
   * @param y Center y-coordinate.
   * @param xRadius Radius along x-axis.
   * @param yRadius Radius along y-axis.
   * @param color Oval color.
   * @throws IllegalArgumentException If any radius is non-positive.
   */
  public Oval(String name, ShapeType type, double x, double y,
              double xRadius, double yRadius, Color color) {
    super(name, ShapeType.OVAL, x, y, color);
    validateRadius(xRadius, "X-radius");
    validateRadius(yRadius, "Y-radius");
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Gets x-axis radius.
   * @return X-radius.
   */
  public double getXRadius() {
    return xRadius;
  }

  /**
   * Gets y-axis radius.
   * @return Y-radius.
   */
  public double getYRadius() {
    return yRadius;
  }

  /**
   * Updates x-axis radius.
   * @param newXRadius New x-radius.
   * @throws IllegalArgumentException If the radius is non-positive.
   */
  public void resizeXRadius(double newXRadius) {
    validateRadius(newXRadius, "X-radius");
    this.xRadius = newXRadius;
  }

  /**
   * Updates y-axis radius.
   * @param newYRadius New y-radius.
   * @throws IllegalArgumentException If the radius is non-positive.
   */
  public void resizeYRadius(double newYRadius) {
    validateRadius(newYRadius, "Y-radius");
    this.yRadius = newYRadius;
  }

  /**
   * Returns a string representation of the oval.
   * @return String description.
   */
  @Override
  public String toString() {
    return String.format(
        "Name: %s\nType: oval\nCenter: (%.1f, %.1f), X radius: %.1f, Y radius: %.1f, "
            + "Color: (%.1f, %.1f, %.1f)",
        name, x, y, xRadius, yRadius, color.getR(), color.getG(), color.getB()
    );
  }

  /**
   * Creates a copy of the oval.
   * @return A new Oval instance.
   */
  @Override
  public IShape copy() {
    return new Oval(name, ShapeType.OVAL, x, y, xRadius, yRadius, color.copy());
  }

  /**
   * Validates that a radius is positive.
   * @param radius Radius to validate.
   * @param radiusName Radius name for error messages.
   * @throws IllegalArgumentException If radius is non-positive.
   */
  private void validateRadius(double radius, String radiusName) {
    if (radius <= 0) {
      throw new IllegalArgumentException(radiusName + " must be positive.");
    }
  }
}
