package mocks;

import model.ImageProcessingModel;

public class MockImageProcessingModel implements ImageProcessingModel {

  private StringBuilder log;

  public MockImageProcessingModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void brightenImage(int increment, String imageName, String desiredName) throws IllegalArgumentException {
    log.append(String.format("brightenImage method called with parameters: %d, %s, %s",
        increment, imageName, desiredName));
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName) throws IllegalArgumentException {
    log.append(String.format("displayGreyscale method called with parameters: %s, %s, %s",
        component, imageName, desiredName));
  }

  @Override
  public void flipImage(String axis, String imageName, String desiredName) throws IllegalArgumentException {
    log.append(String.format("flipImage method called with parameters: %s, %s, %s",
        axis, imageName, desiredName));
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
}
