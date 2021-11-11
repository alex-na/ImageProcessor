package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Quit implements ImageProcessingCommand {


  @Override
  public void apply(ImageProcessingModel model, ImageProcessingView view)
      throws IllegalArgumentException {
    System.out.print("Program Quit!");
    System.exit(0);
  }
}
