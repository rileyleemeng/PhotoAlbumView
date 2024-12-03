package model;

/**
 * Abstract base class representing a generic shape.
 */
public abstract class Shape implements IShape {
  protected String name;
  private ShapeType type;
  protected double x;
  protected double y;
  protected Color color;

  /**
   * Initializes a shape with specified attributes.
   *
   * @param name  the name of the shape
   * @param type  the type of the shape
   * @param x     the x-coordinate of the shape's position
   * @param y     the y-coordinate of the shape's position
   * @param color the color of the shape
   */
  public Shape(String name, ShapeType type, double x, double y, Color color) {
    validateName(name);
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.color = color;
  }

  /**
   * Returns the name of this shape.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the type of this shape.
   *
   * @return the shape type
   */
  @Override
  public ShapeType getShapeType() {
    return type;
  }

  /**
   * Returns the x-coordinate of this shape.
   *
   * @return the x-coordinate
   */
  @Override
  public double getX() {
    return x;
  }

  /**
   * Returns the y-coordinate of this shape.
   *
   * @return the y-coordinate
   */
  @Override
  public double getY() {
    return y;
  }

  /**
   * Updates the position of this shape.
   *
   * @param newX the new x-coordinate
   * @param newY the new y-coordinate
   */
  @Override
  public void move(double newX, double newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Returns the color of this shape.
   *
   * @return the color object
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Updates the color of this shape with new RGB values.
   *
   * @param newR the red component (0-255)
   * @param newG the green component (0-255)
   * @param newB the blue component (0-255)
   */
  @Override
  public void changeColor(double newR, double newG, double newB) {
    validateColor(newR, "Red");
    validateColor(newG, "Green");
    validateColor(newB, "Blue");
    color.setR(newR);
    color.setG(newG);
    color.setB(newB);
  }

  /**
   * Creates a duplicate of this shape.
   * This method must be implemented by subclasses.
   *
   * @return a copy of the shape
   */
  @Override
  public abstract IShape copy();

  /**
   * Validates the name of the shape.
   *
   * @param name the name to validate
   * @throws IllegalArgumentException if the name is null or empty
   */
  private void validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }
  }

  /**
   * Validates a color component value.
   *
   * @param value the color value to validate
   * @param componentName the name of the color component (for error messages)
   * @throws IllegalArgumentException if the value is out of range [0, 255]
   */
  private void validateColor(double value, String componentName) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException(componentName
          + " value must be in the range [0, 255].");
    }
  }
}
