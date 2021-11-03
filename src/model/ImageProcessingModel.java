package model;
import model.pixel.Pixel;

/**
 *
 */
public interface ImageProcessingModel {

  /**
   * Returns the image that is contained within this model.
   *
   * @return a 2D array of pixels that represents an image.
   */
  Pixel[][] getImage(String imageName);

  /**
   *
   * @param increment
   */
  Pixel[][] brightenImage(int increment);

  /**
   *
   * @param component
   * @return
   */
  Pixel[][] displayGreyscale(String component);

  /**
   * Rearranges the pixels of an image to flip them over a given axis.
   *
   * @param axis the axis that the image will be flipped across.
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  Pixel[][] flipImage(String axis);

  /**
   *
   * @param filePath
   * @param imageName
   */
  void save(String filePath, String imageName);

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

  /**
   *
   * @return
   */
  int getHeight();

  /**
   *
   * @return
   */
  int getWidth();

}
