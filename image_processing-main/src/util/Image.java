package util;

/**
 * Represents an Image and the functionality that should be available to all images
 */
public interface Image {


  /**
   * Returns the 2D array of pixels associated with this image.
   *
   * @return a 2D array of pixels that represents an image.
   */
  public Pixel[][] getImage();

  /**
   * Returns the height of this Image.
   *
   * @return an int that represents the height of an image in pixels.
   */
  int getHeight();

  /**
   * Returns the width of this Image.
   *
   * @return an int that represents the width of an image in pixels.
   */
  int getWidth();

  /**
   * Adjusts the model's image to a greyscale representation of the image.
   *
   * @param component the component of an image's pixel that will
   *                  be used to set the greyscale value of that pixel.
   * @throws IllegalArgumentException if the given rgb component is not Red, Green, or Blue.
   */
  Pixel[][] adjustGreyscale(String component) throws IllegalArgumentException;

  /**
   * Rearranges the pixels of an image
   *
   * @param axis the axis that the image will be flipped across.
   *
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  Pixel[][] flipImage(String axis) throws IllegalArgumentException;

  /**
   * Returns the pixel of an image at a particular row,column grid coordinate.
   *
   * @param row the row of a row,col grid coordinate.
   * @param col the column of a row,col grid coordinate.
   * @return the Pixel at the row and column of an image's 2D array of pixels.
   *
   * @throws IllegalArgumentException when the given row and/or column is out
   *                                  of the range of an image's width and height.
   */

  /**
   * Given an increment value, adjusts the brightness of each pixel of this model's image
   * using the given desired brightening method
   *
   * @param adjustmentType whether the image will be brightened or darkened.
   * @param brightnessMode the brightening method that will be used (intensity, value, or luma.)
   * @param increment      the increment value that will be used to adjust the image's brightness.
   * @throws IllegalArgumentException when the given brightness adjustment mode is not
   *                                  Value, Intensity, or Luma
   */
  Pixel[][] adjustBrightness(String adjustmentType, String brightnessMode, int increment)
          throws IllegalArgumentException;

  /**
   * Returns the pixel of an image at a particular row,column grid coordinate.
   *
   * @param row the row of a row,col grid coordinate.
   * @param col the column of a row,col grid coordinate.
   * @return the Pixel at the row and column of an image's 2D array of pixels.
   *
   * @throws IllegalArgumentException when the given row and/or column is out
   *                                  of the range of an image's width and height.
   */
  Pixel getPixelAt(int row, int col) throws IllegalArgumentException;
}
