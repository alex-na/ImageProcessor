package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Flip implements ImageProcessingCommand {

  private String type;

  public Flip(String type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    this.type = type;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    String in = this.type;
    switch (in) {
      case "horizontal" :
        model.flipImage("horizontally");
        break;
      case "vertical" :
        model.flipImage("vertically");
        break;
      default: throw new IllegalArgumentException("Invalid type entered.");
    }
  }
}
