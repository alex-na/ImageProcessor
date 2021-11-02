package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Brighten implements ImageProcessingCommand {

  private int increment;

  public Brighten(int increment) throws IllegalArgumentException {
    this.increment = increment;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.adjustImage("brighten", this.increment);
  }
}
