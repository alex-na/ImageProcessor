package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command class utilized for Quitting an Image Processor Program.
 */
public class Quit implements ImageProcessingCommand {


  @Override
  public void apply(ImageProcessingModel model, ImageProcessingView view)
      throws IllegalArgumentException {
    System.out.print("Program Quit!");
    System.exit(0);
  }
}
