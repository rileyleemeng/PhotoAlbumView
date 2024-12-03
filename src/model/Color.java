package model;

/**
 * Represents a color with RGB values.
 */
public class Color {
  private double r;
  private double g;
  private double b;

  /**
   * Constructs a color with RGB values.
   * @param r Red value (0-255).
   * @param g Green value (0-255).
   * @param b Blue value (0-255).
   * @throws IllegalArgumentException If values are out of range.
   */
  public Color(double r, double g, double b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Color values must be in range [0, 255].");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets the red value.
   * @return Red value.
   */
  public double getR() {
    return r;
  }

  /**
   * Gets the green value.
   * @return Green value.
   */
  public double getG() {
    return g;
  }

  /**
   * Gets the blue value.
   * @return Blue value.
   */
  public double getB() {
    return b;
  }

  /**
   * Sets the red value.
   * @param r Red value (0-255).
   */
  public void setR(double r) {
    this.r = r;
  }

  /**
   * Sets the green value.
   * @param g Green value (0-255).
   */
  public void setG(double g) {
    this.g = g;
  }

  /**
   * Sets the blue value.
   * @param b Blue value (0-255).
   */
  public void setB(double b) {
    this.b = b;
  }

  /**
   * Creates a copy of the color.
   * @return Cloned Color object.
   */
  public Color copy() {
    return new Color(r, g, b);
  }
}
