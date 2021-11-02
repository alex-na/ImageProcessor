package model;
import util.Image;
import util.Pixel;

/**
 *
 */
public interface ImageProcessingModel {

  /**
   *
   * @return
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
   *
   * @param flipType
   */
  Pixel[][] flipImage(String flipType);

  /**
   *
   * @param filename
   */
  void save(String filename);

  /**
   *
   */
  void load();

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
