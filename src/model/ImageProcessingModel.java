package model;
import util.Image;
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
  Pixel[][] brightenImage(int increment);

  /**
   *
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
   * @param filename
   */
  void save(String filename, String imageName);

  /**
   *
   */
  void load(String imageName);

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
