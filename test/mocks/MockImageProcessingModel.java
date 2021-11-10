package mocks;

import java.awt.*;

import model.ImageProcessingModel;
import util.image.Image;

/**
 * Mock model class utilized for testing.
 */
public class MockImageProcessingModel implements ImageProcessingModel {

  private StringBuilder log;

  /**
   * Constructing a MockImageProcessingModel object.
   *
   * @param log StringBuilder
   */
  public MockImageProcessingModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Load an image from a file path to be used internally within the model.
   *
   * @param imageName the name of the image
   * @param image     the image will be stored in the model.
   * @throws IllegalArgumentException if the given parameters are null.
   */
  @Override
  public void load(String imageName, Image image) throws IllegalArgumentException {
    log.append(String.format("The image was processed in the controller, " +
                    "and the following imageName and image were passed to the model: %s, %s",
            imageName, image));
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

  @Override
  public void filterImage(String filterType, String imageName, String desiredName) throws IllegalArgumentException {

  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName) throws IllegalArgumentException {

  }

//  @Override
//  public void save(String filePath, String imageName) throws IllegalArgumentException {
//    log.append(String.format("save method called with parameters: %s, %s",
//        filePath, imageName));
//  }
//
//  @Override
//  public void load(String filePath, String imageName) throws IllegalArgumentException {
//    log.append(String.format("load method called with parameters: %s, %s",
//        filePath, imageName));
//  }

  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Color getPixelAt(String imageName, int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getImageHeight(String imageName) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public int getImageWidth(String imageName) throws IllegalArgumentException {
    return 0;
  }
}
