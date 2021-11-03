package model.image;

import model.image.Image;
import model.pixel.Pixel;

/**
 * Represents an image in the form of a 2D array of Pixels
 */
public class PixelImage implements Image {
  private Pixel[][] image;
  private int height;
  private int width;

  public PixelImage(Pixel[][] image) throws IllegalArgumentException{
    if (image == null) {
      throw new IllegalArgumentException("The given image is null.");
    }
    this.image = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  public Pixel[][] getImage() {
    return this.image;
  }

  public Pixel[][] brightenImage(int increment) {
    return null;
  }

  public Pixel[][] displayGreyscale(String component) {
    return null;
  }

  public Pixel[][] flipImage(String axis) {
    return null;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return null;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }
}
