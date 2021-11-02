package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 *
 */
public interface ImageProcessingCommand {

  /**
   *
   * @param model
   * @param view
   */
  void go(ImageProcessingModel model, ImageProcessingView view);

}
