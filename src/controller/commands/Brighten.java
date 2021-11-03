package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Brighten implements ImageProcessingCommand {

  private int increment;
  private String imageName;
  private String desiredName;

  public Brighten(int increment, String imageName, String desiredName) throws IllegalArgumentException {
    if (imageName == null || desiredName == null) {
      throw new IllegalArgumentException("imageName and/or desired name are null");
    }
    this.increment = increment;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.brightenImage(this.increment, this.imageName, this.desiredName);
  }
}
