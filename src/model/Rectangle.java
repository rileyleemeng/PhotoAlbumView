package model;

/**
 * Represents a rectangle with dimensions and color.
 */
public class Rectangle extends Shape {
  private double width;
  private double height;

  /**
   * Constructs a rectangle with specified attributes.
   * @param name Rectangle name.
   * @param type Shape type (RECTANGLE).
   * @param x X-coordinate of bottom-left corner.
   * @param y Y-coordinate of bottom-left corner.
   * @param width Rectangle width.
   * @param height Rectangle height.
   * @param color Rectangle color.
   */
  public Rectangle(String name, ShapeType type, double x, double y,
                   double width, double height, Color color) {
    super(name, type, x, y, color);
    validateDimension(width, "Width");
    validateDimension(height, "Height");
    this.width = width;
    this.height = height;
  }

  /**
   * Gets the rectangle's width.
   * @return Width.
   */
  public double getWidth() {
    return width;
  }

  /**
   * Gets the rectangle's height.
   * @return Height.
   */
  public double getHeight() {
    return height;
  }

  /**
   * Updates the rectangle's width.
   * @param newWidth New width.
   * @throws IllegalArgumentException If new width is non-positive.
   */
  public void resizeWidth(double newWidth) {
    validateDimension(newWidth, "Width");
    this.width = newWidth;
  }

  /**
   * Updates the rectangle's height.
   * @param newHeight New height.
   * @throws IllegalArgumentException If new height is non-positive.
   */
  public void resizeHeight(double newHeight) {
    validateDimension(newHeight, "Height");
    this.height = newHeight;
  }

  /**
   * Returns a string representation of the rectangle.
   * @return Formatted rectangle details.
   */
  @Override
  public String toString() {
    return String.format(
        "Name: %s\nType: rectangle\nCorner: (%.1f, %.1f), Width: %.1f, Height: %.1f, "
            + "Color: (%.1f, %.1f, %.1f)",
        name, x, y, width, height, color.getR(), color.getG(), color.getB()
    );
  }

  /**
   * Creates a copy of this rectangle.
   * @return A new Rectangle instance.
   */
  @Override
  public IShape copy() {
    return new Rectangle(name, ShapeType.RECTANGLE, x, y, width, height, color.copy());
  }

  /**
   * Validates if a dimension is positive.
   * @param value Dimension value.
   * @param dimensionName Dimension name for error messages.
   * @throws IllegalArgumentException If dimension is non-positive.
   */
  private void validateDimension(double value, String dimensionName) {
    if (value <= 0) {
      throw new IllegalArgumentException(dimensionName + " must be positive.");
    }
  }
}
