package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Save implements ImageProcessingCommand {

  private String fileName;

  public Save(String fileName) {
    if (fileName == null) {
      throw new IllegalArgumentException("File name cannot be null.");
    }
    this.fileName = fileName;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.save(this.fileName);
  }
}
