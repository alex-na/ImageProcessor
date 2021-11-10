package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;
import util.ImageUtil;
import view.ImageProcessingView;

/**
 * Command class utilized for saving an image.
 */
public class Save implements ImageProcessingCommand {

  private String fileName;
  private String imageName;

  /**
   * Constructing a Save object.
   *
   * @param fileName  the location where the file is to be saved
   * @param imageName the name of the file to be saved.
   */
  public Save(String fileName, String imageName) {
    if (fileName == null || imageName == null) {
      throw new IllegalArgumentException("File name and image name cannot be null.");
    }
    this.fileName = fileName;
    this.imageName = imageName;
  }

  @Override
  public void apply(ImageProcessingModel model, ImageProcessingView view) {

    if (!(fileName.contains(imageName))) {
      throw new IllegalArgumentException("Specified path does not include imageName");
    }

    String[] splitAtPeriods = fileName.split("\\.");
    if (splitAtPeriods.length <= 1) {
      throw new IllegalArgumentException("Filepath does not contain a file type.");
    }
    int indexOfType = splitAtPeriods.length - 1;

    if (splitAtPeriods[indexOfType].equals("ppm")) {
      ImageUtil.writePPM(fileName, model.getImage(imageName));
    } else {
      int i = 0;
      int j = 0;
      try {
        int height = model.getImageHeight(imageName);
        int width = model.getImageWidth(imageName);
        BufferedImage savedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (i = 0; i < width; i++) {
          for (j = 0; j < height; j++) {
            int pixel = (model.getPixelAt(imageName, j, i).getRed() << 16)
                    | (model.getPixelAt(imageName, j, i).getGreen() << 8)
                    | (model.getPixelAt(imageName, j, i).getBlue());
            savedImage.setRGB(i, j, pixel);
          }
        }

        File newFile = new File(fileName);
        ImageIO.write(savedImage, splitAtPeriods[indexOfType], newFile);
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      } catch (NullPointerException e) {
        System.out.println(i + "  " + j);

      }
    }
  }
}
