package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Flip implements ImageProcessingCommand {

  private String type;
  private String imageName;
  private String desiredName;

  public Flip(String type, String imageName, String desiredName) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    this.type = type;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    String in = this.type;
    switch (in) {
      case "horizontal" :
        model.flipImage("horizontal", this.imageName, this.desiredName);
        break;
      case "vertical" :
        model.flipImage("vertical", this.imageName, this.desiredName);
        break;
      default: throw new IllegalArgumentException("Invalid type entered.");
    }
  }
}
