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
          model.displayGreyscale("value");
          break;
      case "intensity":
        model.displayGreyscale("intensity");
        break;
      case "luma":
        model.displayGreyscale("luma");
        break;
      case "red":
        model.displayGreyscale("red");
        break;
      case "green":
        model.displayGreyscale("green");
        break;
      case "blue":
        model.displayGreyscale("blue");
        break;
      default : throw new IllegalArgumentException("Invalid input.");
    }
  }
}
