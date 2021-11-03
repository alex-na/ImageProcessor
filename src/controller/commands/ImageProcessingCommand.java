package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Representing the commands that an ImageProcessingController
 * can execute.
 */
public interface ImageProcessingCommand {

  /**
   * Executes a command method based on user input.
   *
   * @param model ImageProcessingModel
   * @param view ImageProcessingView
   */
  void go(ImageProcessingModel model, ImageProcessingView view) throws IllegalArgumentException;

}
