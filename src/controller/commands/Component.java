package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Component implements ImageProcessingCommand {

  private String type;

  public Component(String type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    this.type = type;
  }


  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    String in = this.type;
    switch (in) {
      case "value":
          model.adjustGreyscale("value");
          break;
      case "intensity":
        model.adjustGreyscale("intensity");
        break;
      case "luma":
        model.adjustGreyscale("luma");
        break;
      case "red":
        model.adjustGreyscale("red");
        break;
      case "green":
        model.adjustGreyscale("green");
        break;
      case "blue":
        model.adjustGreyscale("blue");
        break;
      default : throw new IllegalArgumentException("Invalid input.");
    }
  }
}
