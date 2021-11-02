package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Load implements ImageProcessingCommand {

  private String fileName;

  public Load(String fileName) throws IllegalArgumentException {
    if (fileName == null) {
      throw new IllegalArgumentException("File name cannot be null.");
    }
    this.fileName = fileName;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.load(this.fileName);
  }
}
