package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Component implements ImageProcessingCommand {

  private String type;
  private String imageName;
  private String desiredName;

  public Component(String type, String imageName, String desiredName) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    if (imageName == null) {
      throw new IllegalArgumentException("Image name cannot be null.");
    }
    if (desiredName == null) {
      throw new IllegalArgumentException("Desired name cannot be null.");
    }

    this.type = type;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }


  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {

    switch (this.type) {
      case "value":
          model.displayGreyscale("value", imageName, desiredName);
          break;
      case "intensity":
        model.displayGreyscale("intensity", imageName, desiredName);
        break;
      case "luma":
        model.displayGreyscale("luma", imageName, desiredName);
        break;
      case "red":
        model.displayGreyscale("red", imageName, desiredName);
        break;
      case "green":
        model.displayGreyscale("green", imageName, desiredName);
        break;
      case "blue":
        model.displayGreyscale("blue", imageName, desiredName);
        break;
      default : throw new IllegalArgumentException("Invalid input.");
    }
  }
}
