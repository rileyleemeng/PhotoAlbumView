package model;

/**
 * Represents a general interface for geometric shapes.
 */
public interface IShape {

  /**
   * Retrieves the identifier of this shape.
   *
   * @return the name of this shape
   */
  String getName();

  /**
   * Retrieves the classification or category of this shape.
   *
   * @return the type of the shape
   */
  ShapeType getShapeType();

  /**
   * Provides the x-coordinate of the shape's position.
   *
   * @return the x-coordinate
   */
  double getX();

  /**
   * Provides the y-coordinate of the shape's position.
   *
   * @return the y-coordinate
   */
  double getY();

  /**
   * Repositions the shape to a specified location.
   *
   * @param newX the target x-coordinate
   * @param newY the target y-coordinate
   */
  void move(double newX, double newY);

  /**
   * Retrieves the current color of this shape.
   *
   * @return the color as a {@link Color} object
   */
  Color getColor();

  /**
   * Updates the color of the shape with new RGB values.
   *
   * @param r the red component of the new color
   * @param g the green component of the new color
   * @param b the blue component of the new color
   */
  void changeColor(double r, double g, double b);

  /**
   * Creates a duplicate of this shape instance.
   *
   * @return a new instance with the same properties
   */
  IShape copy();
}
