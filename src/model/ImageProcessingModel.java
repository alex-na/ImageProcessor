package model;

import java.util.Map;

import model.image.Image;

/**
 * Representing the model of an ImageProcessor, utilized for performing operations on an image
 * and storing said operations for future use.
 */
public interface ImageProcessingModel {

  /**
   * Retrieves the images with associated names.
   */
  Map<String, Image> getLoadMap();

  /**
   * Brighten an image based on an increment.
   *
   * @param increment increment to brighten/darken an image by
   * @param imageName name of the image
   * @param desiredName desired name of the image
   */
  void brightenImage(int increment, String imageName, String desiredName) throws IllegalArgumentException;

  /**
   * Display the component of an image based on user input.
   *
   * @param component the name of the component
   * @param imageName name of the image
   * @param desiredName desired name of the image
   */
  void displayGreyscale(String component, String imageName, String desiredName) throws IllegalArgumentException;

  /**
   * Rearranges the pixels of an image to flip them over a given axis.
   *
   * @param axis the axis that the image will be flipped across.
   * @param imageName the name of the image
   * @param desiredName the desired name of the image
   * @throws IllegalArgumentException when the given String is not
   *    *                             a valid axis to flip the image across.
   */
  void flipImage(String axis, String imageName, String desiredName) throws IllegalArgumentException;

  /**
   * Saves an image within the desired file location.
   *
   * @param filename the desired location of the image
   * @param imageName the name of the image that is to be saved
   */
  void save(String filename, String imageName) throws IllegalArgumentException;

  /**
   * Load an image from a file path to be used internally within the model.
   *
   * @param filePath the location of the image
   * @param imageName the name by which this image will be referred to within the program
   */
  void load(String filePath, String imageName) throws IllegalArgumentException;

}
