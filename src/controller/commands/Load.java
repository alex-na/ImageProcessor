package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;
import util.image.Image;
import util.image.PixelImage;
import view.ImageProcessingView;


import util.ImageUtil;

/**
 * Command class utilized for loading an image.
 */
public class Load implements ImageProcessingCommand {

  private String imageName;
  private Image image;

  /**
   * Constructs a Load object.
   *
   * @param fileName  the name of the file to be loaded
   * @param imageName the name by which the user refers to this file
   * @throws IllegalArgumentException if inputs are null
   */
  public Load(String fileName, String imageName) throws IllegalArgumentException {
    if (fileName == null || imageName == null) {
      throw new IllegalArgumentException("File name and Image name cannot be null.");
    }
    this.imageName = imageName;

    if (fileName.endsWith(".ppm")) {
      this.image = ImageUtil.readPPM(fileName);
    } else {
      try {
        File file = new File(fileName);

        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        Color[][] pixelMatrix = new Color[height][width];

        for (int row = 0; row < height; row++) {
          for (int col = 0; col < width; col++) {
            int pixel = image.getRGB(row, col); //why does java switch the coordinate order?
            pixelMatrix[row][col] = new Color((pixel & 0xff0000) >> 16, (pixel & 0xff00) >> 8,
                    pixel & 0xff);
          }
        }
        this.image = new PixelImage(pixelMatrix);
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  @Override
  public void apply(ImageProcessingModel model, ImageProcessingView view) {
    model.load(imageName, image);
  }
}
//create a new command create a load command in your test and apply to the model
// not doing IO on files to get images for testing - construct an image based on an array of pixel

//save export the file and reimport it to see if they have the smae height and width