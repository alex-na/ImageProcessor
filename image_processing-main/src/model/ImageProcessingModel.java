package model;
import util.Image;
import util.Pixel;

/**
 *
 */
public interface ImageProcessingModel {

  /**
   * Returns the image that is contained within this model
   *
   * @return a 2D array of pixels that represents an image.
   */
  Image getImage();

  /**
   * Adjusts the model's image to a greyscale representation of the image.
   *
   * @param component the component of an image's pixel that will
   *                  be used to set the greyscale value of that pixel.
   * @throws IllegalArgumentException if the given rgb component is not Red, Green, or Blue.
   */
  Pixel[][] adjustGreyscale(String component) throws IllegalArgumentException;

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
  public Pixel[][] adjustBrightness(String adjustmentType, String brightnessMode, int increment)
          throws IllegalArgumentException;

  /**
   * Rearranges the pixels of an image
   *
   * @param axis the axis that the image will be flipped across.
   *
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  Pixel[][] flipImage(String axis) throws IllegalArgumentException;


//  /**
//   *
//   * @param adjType
//   * @param increment
//   */
//  void adjustImage(String adjType, int increment);
//
  /**
   *
   * @param filename
   */
  void save(String filename, String imageName);

  /**
   *
   * @param filename
   */
  void load(String filename, String imageName);
}
