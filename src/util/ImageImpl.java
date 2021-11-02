package util;

import java.util.Objects;

/**
 * Implementation of an image that is represented as a 2D array of pixels with a height and width.
 */
public class ImageImpl implements Image {
  private Pixel[][] image;
  private int height;
  private int width;




  /**
   * Given a the file path of an image, creates the model of the image processor where that image
   * can be altered.
   *
   * @param filename a string that represents the file path of an image.
   * @throws NullPointerException if this file name is null.
   * @throws IllegalArgumentException if a file with the given file name doesn't exist.
   */
  public ImageImpl(String filename) throws NullPointerException {
    if (filename.equals(null)) {
      throw new NullPointerException("given filename is null.");
    }
    this.image = ImageUtil.readPPM(filename);
    this.height = this.image.length;
    this.width = this.image[0].length;

  }

  public Pixel[][] getImage() {
    return this.image;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }
}
