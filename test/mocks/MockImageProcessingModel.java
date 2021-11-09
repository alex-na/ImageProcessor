package mocks;

import java.awt.*;
import java.util.Map;

import model.ImageProcessingModel;
import model.image.Image;

/**
 * Mock model class utilized for testing.
 */
public class MockImageProcessingModel implements ImageProcessingModel {

  private StringBuilder log;

  /**
   * Constructing a MockImageProcessingModel object.
   * @param log StringBuilder
   */
  public MockImageProcessingModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void brightenImage(int increment, String imageName, String desiredName)
      throws IllegalArgumentException {
    log.append(String.format("brightenImage method called with parameters: %d, %s, %s",
        increment, imageName, desiredName));
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName)
      throws IllegalArgumentException {
    log.append(String.format("displayGreyscale method called with parameters: %s, %s, %s",
        component, imageName, desiredName));
  }

  @Override
  public void flipImage(String axis, String imageName, String desiredName)
      throws IllegalArgumentException {
    log.append(String.format("flipImage method called with parameters: %s, %s, %s",
        axis, imageName, desiredName));
  }

  /**
   * Filters an image based on user input.
   *
   * @param filterType  the type of filter to be applied to the image
   * @param imageName   the name of the image
   * @param desiredName the desired name of the image
   * @throws IllegalArgumentException if an invalid filterType is entered
   */
  @Override
  public void filterImage(String filterType, String imageName, String desiredName) throws IllegalArgumentException {

  }

  /**
   * Transforms an image based on user input.
   *
   * @param transformType the type of transformation to be applied to the image
   * @param imageName     the name of the image
   * @param desiredName   the desired name of the image
   * @throws IllegalArgumentException if an invalid transformType is entered
   */
  @Override
  public void transformImage(String transformType, String imageName, String desiredName) throws IllegalArgumentException {

  }

  @Override
  public void save(String filePath, String imageName) throws IllegalArgumentException {
    log.append(String.format("save method called with parameters: %s, %s",
        filePath, imageName));
  }

  @Override
  public void load(String filePath, String imageName) throws IllegalArgumentException {
    log.append(String.format("load method called with parameters: %s, %s",
        filePath, imageName));

  }

  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Color getPixelAt(String imageName, int row, int col) throws IllegalArgumentException {
    return null;
  }

  /**
   * Gets the height of an image associated with the given imageName.
   *
   * @param imageName
   * @return an int that represents the height of an image.
   * @throws IllegalArgumentException when the given imageName is not associated with an image.
   */
  @Override
  public int getImageHeight(String imageName) throws IllegalArgumentException {
    return 0;
  }

  /**
   * Gets the width of an image associated with the given imageName.
   *
   * @param imageName
   * @return an int that represents the width of an image.
   * @throws IllegalArgumentException when the given imageName is not associated with an image.
   */
  @Override
  public int getImageWidth(String imageName) throws IllegalArgumentException {
    return 0;
  }
}
