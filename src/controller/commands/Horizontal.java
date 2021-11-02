package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Horizontal implements ImageProcessingCommand {

  public Horizontal() {}

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.flipImage("horizontal");
  }
}