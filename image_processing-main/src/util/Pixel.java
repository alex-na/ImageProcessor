package util;

import java.util.Objects;

import model.Model;

/**
 * Represents a singular pixel of an image that contains three integer components that equate to the
 * RBG values of a color
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * @param red   the red component of a pixel's rgb color.
   * @param green the green component of a pixel's rgb color.
   * @param blue  the blue component of a pixel's rgb color.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid RGB values");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Return a pixel's red color component.
   *
   * @return an int that represents a pixel's red color component.
   */
  public int getRed() {
    return this.getRed();
  }

  /**
   * Return a pixel's green color component.
   *
   * @return an int that represents a pixel's green color component.
   */
  public int getGreen() {
    return this.getGreen();
  }


  /**
   * Return a pixel's blue color component.
   *
   * @return an int that represents a pixel's blue color component.
   */
  public int getBlue() {
    return this.getBlue();
  }


  /**
   * @param increment the increment value that the brightness of an image will be adjusted by.
   * @throws IllegalArgumentException if the int value of a
   */
  public Pixel adjustByLuma(int increment) throws IllegalArgumentException {
    if ((this.red + increment * 0.2126) > 255 || (this.red + increment * 0.2126) < 0
            || (this.green + increment * 0.7152) > 255 || (this.green + increment * 0.7152) < 0
            || (this.blue + increment * 0.0722) > 255 || (this.blue + increment * 0.0722) < 0) {
      throw new IllegalArgumentException("The given increment value will" +
              " result in an invalid rbg value");
    }
    return new Pixel(
    this.red += increment * 0.2126, this.green += increment * 0.7152,
            this.blue += increment * 0.0722);
  }


  public Pixel adjustByIntensity(int increment) throws IllegalArgumentException {
    if (this.red + increment < 0 || this.red + increment > 255
            || this.green + increment < 0 || this.green + increment > 255
            || this.blue + increment < 0 || this.blue + increment > 255) {
      throw new IllegalArgumentException("The given increment value will" +
              " result in an invalid rbg value");
    }
    return new Pixel(this.red += increment,
    this.green += increment,
    this.blue += increment);
  }


  public Pixel adjustByValue(int increment) throws IllegalArgumentException {
    int max = Math.max(this.getRed(), this.getGreen()); //find max between red and green

    if (max > this.getGreen()) { //suppose R is max then compare R with B to find max number
      max = Math.max(this.getRed(), this.getBlue());
    } else { //if G is max then compare G with B to find max number
      max = Math.max(this.getGreen(), this.getBlue());
    }

    if (max + increment > 255 || max + increment < 0) {
      throw new IllegalArgumentException("The given increment value will" +
              " result in an invalid rbg value");
    }
    int adjustedRed = this.red;
    int adjustedGreen = this.green;
    int adjustedBlue = this.blue;

    if (max == this.getRed()) {
      adjustedRed = this.red + increment;
    }
    if (max == this.getGreen()) {
      adjustedGreen = this.green + increment;
    }
    if (max == this.getGreen()) {
      adjustedBlue = this.blue + increment;
    }

    return new Pixel(adjustedRed, adjustedGreen, adjustedBlue);
  }

  public Pixel adjustPixelGreyscale(String component) throws IllegalArgumentException {
    switch (component) {
      case "Red":
        return new Pixel(this.red, this.red, this.red);
      case "Green":
        return new Pixel(this.green, this.green, this.green);
      case "Blue":
        return new Pixel(this.blue, this.blue, this.blue);
      default:
        throw new IllegalArgumentException("The given color component is invalid.");
    }
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof Pixel)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Pixel that = (Pixel) o;

    return this.red == that.red
            && this.green == that.green
            && this.blue == that.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}

