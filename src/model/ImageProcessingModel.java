package model;
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
   * @param adjType
   * @param increment
   */
  void adjustImage(String adjType, int increment);

  /**
   *
   */
  void adjustGreyscale();


  /**
   *
   * @param flipType
   */
  void flipImage(String flipType);

  /**
   *
   * @param filename
   */
  void save(String filename);

  /**
   *
   * @param filename
   */
  void load(String filename);

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
