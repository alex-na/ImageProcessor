package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command class utilized for loading an image.
 */
public class Load implements ImageProcessingCommand {

  private String fileName;
  private String imageName;

  /**
   * Constructs a Load object.
   *
   * @param fileName the name of the file to be loaded
   * @param imageName the name by which the user refers to this file
   * @throws IllegalArgumentException if inputs are null
   */
  public Load(String fileName, String imageName) throws IllegalArgumentException {
    if (fileName == null || imageName == null) {
      throw new IllegalArgumentException("File name and Image name cannot be null.");
    }
    this.fileName = fileName;
    this.imageName = imageName;
  }

  @Override
  public void apply(ImageProcessingModel model, ImageProcessingView view) {
    model.load(fileName, imageName);
  }
}
