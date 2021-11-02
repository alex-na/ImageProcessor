package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Load implements ImageProcessingCommand {

  private String fileName;
  private String imageName;

  public Load(String fileName, String imageName) throws IllegalArgumentException {
    if (fileName == null || imageName == null) {
      throw new IllegalArgumentException("File name and Image name cannot be null.");
    }
    this.fileName = fileName;
    this.imageName = imageName;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.load(fileName, imageName);
  }
}
