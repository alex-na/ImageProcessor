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


package model;

/**
 * Represents a singular pixel of an image that contains three integer components that equate to the
 * RBG values of a color
 */
public class Pixel {
  int row;
  int col;
  private int r;
  private int g;
  private int b;

  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getR() {
    return this.getR();
  }

  public int getG() {
    return this.getG();
  }

  public int getB() {
    return this.getB();
  }


  // adjust by luma
  public void adjustByLuma(int increment) {
    this.r += increment * 0.2126;
    this.g += increment * 0.7152;
    this.b += increment * 0.0722;
  }


  public void adjustByIntensity(int increment) {
    this.r += increment;
    this.g += increment;
    this.b += increment;
  }


  public void adjustByValue(int increment) {
    int max = Math.max(this.getR(), this.getG());

    if (max > this.getG()) { //suppose R is max then compare R with B to find max number
      max = Math.max(this.getR(), this.getB());
    } else { //if G is max then compare G with B to find max number
      max = Math.max(this.getG(), this.getB());
    }

    if (max + increment > 255) {
      throw new IllegalArgumentException("The given adjustment value is too large.");
    }

    if (max == this.getR()) {
      this.r += increment;
    }
    if (max == this.getG()) {
      this.g += increment;
    }
    if (max == this.getG()) {
      this.b += increment;
    }
  }
}

