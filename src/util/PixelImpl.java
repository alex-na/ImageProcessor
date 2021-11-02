package util;

import java.util.Objects;

/**
 * Represents a singular pixel of an image that contains three integer components that equate to the
 * RBG values of a color
 */
public class PixelImpl implements Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * @param red   the red component of a pixel's rgb color.
   * @param green the green component of a pixel's rgb color.
   * @param blue  the blue component of a pixel's rgb color.
   */
  public PixelImpl(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid RGB values");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRed() {
    return this.getRed();
  }

  @Override
  public int getGreen() {
    return this.getGreen();
  }

  @Override
  public int getBlue() {
    return this.getBlue();
  }

  // displaying greyscale components
  private Pixel showGreyscaleComponent(String type) {
    String in = type;
    switch (in) {
      case "value" :
        int max = Math.max(this.red, Math.max(this.blue, this.green));
        return new PixelImpl(max,max,max);
      case "intensity" :
        int avg = (this.red + this.green + this.blue) / 3;
        return new PixelImpl(avg,avg,avg);
      case "luma" :
        int weightedSum = (int) (Math.round(this.red * 0.2126)
            + Math.round(this.green * 0.7152)
            + Math.round(this.blue * 0.0722));
        return new PixelImpl(weightedSum,weightedSum,weightedSum);
      default : throw new IllegalArgumentException("Invalid input.");
    }
  }

  // displaying color components
  private Pixel showColorComponent(String type) {
    String in = type;
    switch (in) {
      case "red" :
        return new PixelImpl(this.red, this.red, this.red);
      case "green" :
        return new PixelImpl(this.green, this.green, this.green);
      case "blue" :
        return new PixelImpl(this.blue, this.blue, this.blue);
      default : throw new IllegalArgumentException("Invalid input.");
    }
  }

  @Override
  public Pixel displayComponent(String type) {
    String in = type;
    switch(in) {
      case "value":
        return showGreyscaleComponent("value");
      case "intensity":
        return showGreyscaleComponent("intensity");
      case "luma":
        return showGreyscaleComponent("luma");
      case "red":
        return showColorComponent("red");
      case "green":
        return showColorComponent("green");
      case "blue":
        return showColorComponent("blue");
      default : throw new IllegalArgumentException("Invalid input.");
    }
  }

  @Override
  public Pixel adjustBrightness(int increment) throws IllegalArgumentException {
    int r = this.red + increment;
    int g = this.green + increment;
    int b = this.blue + increment;

    if (r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0) {
      throw new IllegalArgumentException("Invalid increment.");
    } else {
      return new PixelImpl(r,g,b);
    }
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof PixelImpl)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    PixelImpl that = (PixelImpl) o;

    return this.red == that.red
            && this.green == that.green
            && this.blue == that.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}
