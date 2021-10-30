package util;

/**
 *
 */
public class Pixel {

  private int red;
  private int green;
  private int blue;

  /**
   *
   * @param red
   * @param green
   * @param blue
   */
  Pixel(int red, int green, int blue) throws IllegalArgumentException {

    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid RGB values");
    }

    this.red=red;
    this.green=green;
    this.blue=blue;
  }

}
