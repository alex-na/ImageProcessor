package model.image;

import model.pixel.Pixel;

/**
 * Representing an Image as a 2D array of Pixels and their supporting operations.
 */
public interface Image {

  /**
   * gets the image represented in the image object.
   *
   * @return a 2D array of pixels that represents an image.
   */
  Pixel[][] getImage();

  /**
   * Gets the height of an Image;
   *
   * @return an int that represents the height of this image.
   */
  int getHeight();

  /**
   * Gets the width of an Image;
   *
   * @return an int that represents the width of this image.
   */
  int getWidth();

  /**
   * Creates a new matrix of pixels that represents
   * the brightened version of this image and returns it.
   *
   * @param increment the value that the user wants to change the brightness by.
   *
   * @return a 2D array of pixels that represents the brightened version of this image.
   * @throws IllegalArgumentException when the given increment value is invalid.
   */
  Pixel[][] brightenImage(int increment) throws IllegalArgumentException;

  /**
   * Creates a new matrix of pixels that represents
   * the greyscale version of this image and returns it.
   *
   * @param component the method that should be employed to create a greyscale version of an image.
   *                  Valid methods include red, blue, green, value, intensity and luma.
   * @return a 2D array of pixels that represents the greyscale version of this image.
   * @throws IllegalArgumentException when the given component is invalid.
   */
  Pixel[][] displayGreyscale(String component) throws IllegalArgumentException;

  /**
   * Rearranges the pixels of an image to flip them over a given axis.
   *
   * @param axis the axis that the image will be flipped across.
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  Pixel[][] flipImage(String axis);

}
