package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Vertical implements ImageProcessingCommand {

  public Vertical() {}

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.flipImage("vertical");
  }
}
