package model.image;

import model.pixel.Pixel;

/**
 * Represents an image in the form of a 2D array of Pixels.
 */
public class PixelImage implements Image {
  private Pixel[][] image;
  private int height;
  private int width;

  /**
   * Given a 2D array of pixels, constructs a PixelImage object that contains the Pixel matrix,
   * along with the height and width of passed in matrix.
   *
   * @param image the image that will be stored in this PixelImage object.
   *
   * @throws IllegalArgumentException when the given Pixel matrix is null.
   */
  public PixelImage(Pixel[][] image) throws IllegalArgumentException{
    if (image == null) {
      throw new IllegalArgumentException("The given image is null.");
    }
    this.image = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  /**
   * Gets the image stored in this PixelImage object.
   *
   * @return the Pixel matrix stored in this PixelImage object.
   */
  public Pixel[][] getImage() {
    return this.image;
  }

  /**
   * Gets the height of the image stored in this PixelImage object.
   *
   * @return an int that represents the PixelImage's height.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the width of the image stored in this PixelImage object.
   *
   * @return an int that represents the PixelImage's width.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Creates a new matrix of pixels that represents
   * the brightened version of an image and returns it.
   *
   * @param increment the value that the user wants to change the brightness by.
   *
   * @return a Pixel[][] that represents the brightened version of this image.
   *
   * @throws IllegalArgumentException when this image cannot be brightened by the given value.
   */
  public Pixel[][] brightenImage(int increment) throws IllegalArgumentException{
    Pixel[][] brightened = new Pixel[this.height][this.width];
    for (int row = 0; row < brightened.length; row++) {
      for (int col = 0; col < brightened[0].length; col++) {
        brightened[row][col] = image[row][col].adjustBrightness(increment);
      }
    }
    return brightened;
  }


  /**
   * Creates a new matrix of pixels that represents
   * the greyscale version of this image and returns it.
   *
   * @param component the method that should be employed to create a greyscale version of an image.
   *                  Valid methods include red, blue, green, value, intensity and luma.
   * @return a 2D array of pixels that represents the greyscale version of this image.
   * @throws IllegalArgumentException when the given component is invalid.
   */
  public Pixel[][] displayGreyscale(String component) {
    Pixel[][] greyscale = new Pixel[this.height][this.width];
    for (int row = 0; row < greyscale.length; row++) {
      for (int col = 0; col < greyscale[0].length; col++) {
        greyscale[row][col] = image[row][col].displayComponent(component);
      }
    }
    return greyscale;
  }

  public Pixel[][] flipImage(String axis) {
    Pixel[][] flippedImage = new Pixel[this.height][this.width];
    switch (axis) {
      case "vertical":
        for (int row = 0; row < image.length; row++) {
          for (int col = 0; col < image[0].length; col++) {
            flippedImage[row][col] = image[row][image[0].length - 1 - col];
          }
        }
        break;
      case "horizontal":
        for (int row = 0; row < image.length; row++) {
          for (int col = 0; col < image[0].length; col++) {
            flippedImage[row][col] = image[image.length - 1 - row][col];
          }
        }
        break;
      default:
        throw new IllegalArgumentException("The given axis is not valid");
    }
    return flippedImage;
  }
}
