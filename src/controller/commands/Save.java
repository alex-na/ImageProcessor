package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

public class Save implements ImageProcessingCommand {

  private String fileName;
  private String imageName;

  public Save(String fileName, String imageName) {
    if (fileName.equals(null) || imageName.equals(null)) {
      throw new IllegalArgumentException("File name and image name cannot be null.");
    }
    this.fileName = fileName;
    this.imageName = imageName;
  }

  @Override
  public void go(ImageProcessingModel model, ImageProcessingView view) {
    model.save(this.fileName, this.imageName);
  }
}
