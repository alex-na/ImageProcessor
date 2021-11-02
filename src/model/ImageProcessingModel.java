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
   * @param filepath
   * @param imageName
   */
  void save(String filepath, String imageName)throws IllegalArgumentException;

  /**
   *
   * @param filename
   * @param imageName
   * @throws IllegalArgumentException
   */
  void load(String filename, String imageName) throws IllegalArgumentException;

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
