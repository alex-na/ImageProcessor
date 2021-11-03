package model;
import util.Pixel;

/**
 *
 */
public interface ImageProcessingModel {

  /**
   * Returns the image that is contained within this model.
   *
   * @return a 2D array of pixels that represents an image.
   */
  Pixel[][] getImage();

  /**
   *
   * @param increment
   */
  void brightenImage(int increment, String imageName, String desiredName);

  /**
   *
   * @param component
   * @return
   */
  void displayGreyscale(String component, String imageName, String desiredName);

  /**
   * Rearranges the pixels of an image to flip them over a given axis.
   *
   * @param axis the axis that the image will be flipped across.
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  void flipImage(String axis, String imageName, String desiredName);

  /**
   *
   * @param filename
   * @param imageName
   */
  void save(String filename, String imageName);

  /**
   *
   * @param filePath
   * @param imageName
   */
  void load(String filePath, String imageName);

  /**
   *
   * @param row
   * @param col
   * @return
   */
  Pixel getPixelAt(int row, int col);

}
