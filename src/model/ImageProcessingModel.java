package model;

import util.image.Image;

/**
 * Representing the model of an ImageProcessor, utilized for performing operations on an image and
 * storing said operations for future use.
 */
public interface ImageProcessingModel extends ImageProcessingModelState {

  /**
   * Load an image from a file path to be used internally within the model.
   *
   * @param imageName the name of the image
   * @param image     the image will be stored in the model.
   * @throws IllegalArgumentException if the given parameters are null
   */
  void load(String imageName, Image image) throws IllegalArgumentException;


  /**
   * Brighten an image based on an increment.
   *
   * @param increment   increment to brighten/darken an image by
   * @param imageName   name of the image
   * @param desiredName desired name of the image
   */
  void brightenImage(int increment, String imageName, String desiredName)
          throws IllegalArgumentException;

  /**
   * Display the component of an image based on user input.
   *
   * @param component   the name of the component
   * @param imageName   name of the image
   * @param desiredName desired name of the image
   */
  void displayGreyscale(String component, String imageName, String desiredName)
          throws IllegalArgumentException;

  /**
   * Rearranges the pixels of an image to flip them over a given axis.
   *
   * @param axis        the axis that the image will be flipped across.
   * @param imageName   the name of the image
   * @param desiredName the desired name of the image
   * @throws IllegalArgumentException when the given String is not a valid axis to flip the image
   *                                  across.
   */
  void flipImage(String axis, String imageName, String desiredName)
          throws IllegalArgumentException;

  /**
   * Filters an image based on user input.
   *
   * @param filterType  the type of filter to be applied to the image
   * @param imageName   the name of the image
   * @param desiredName the desired name of the image
   * @throws IllegalArgumentException if an invalid filterType is entered
   */
  void filterImage(String filterType, String imageName, String desiredName)
          throws IllegalArgumentException;

  /**
   * Transforms an image based on user input.
   *
   * @param transformType the type of transformation to be applied to the image
   * @param imageName     the name of the image
   * @param desiredName   the desired name of the image
   * @throws IllegalArgumentException if an invalid transformType is entered
   */
  void transformImage(String transformType, String imageName, String desiredName)
          throws IllegalArgumentException;


}
