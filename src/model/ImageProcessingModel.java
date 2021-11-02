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
  Image getImage();

  /**
   *
   * @param adjType
   * @param increment
   */
  Image adjustImage(String adjType, int increment);

  /**
   *
   */
  Pixel[][] adjustGreyscale(String type);


  /**
   *
   * @param flipType
   */
  Pixel[][] flipImage(String flipType);

  /**
   *
   * @param filepath
   * @param imageName
   */
  void save(String filepath, String imageName)throws IllegalArgumentException;

  /**
   *
   */
  void load(String filename, String imageName);

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
