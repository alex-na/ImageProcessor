package model.pixel;

/**
 * Representing a single Pixel in an Image and its supporting operations.
 */
public interface Pixel {

  /**
   * Return a pixel's red color component.
   *
   * @return an int that represents a pixel's red color component.
   */
  int getRed();

  /**
   * Return a pixel's green color component.
   *
   * @return an int that represents a pixel's green color component.
   */
  int getGreen();


  /**
   * Return a pixel's blue color component.
   *
   * @return an int that represents a pixel's blue color component.
   */
  int getBlue();

  /**
   * Displaying the RBG component corresponding to the input. Supports
   * various component types.
   *
   * @param type one of: value, intensity, luma, red, blue, green commands.
   * @return Pixel with adjusted RBG values based on input.
   */
  Pixel displayComponent(String type);

  /**
   * Adjusting the brightness of a Pixel based on the input value.
   *
   * @param increment number by which we increment Pixel brightness
   * @return Pixel with updated RGB values
   */
  Pixel adjustBrightness(int increment);
}
