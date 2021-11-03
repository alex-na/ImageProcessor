package model;

import util.ImageUtil;
import model.pixel.Pixel;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an ImageProcessor for an image, along with its height and width.
 */
public class Model implements ImageProcessingModel {

  private static Map<String, Pixel[][]> loadMap;

  /**
   * Given the file path of an image, creates the model of the image processor where that image
   * can be altered.
   */
  public Model() {
    loadMap = new HashMap<>();
  }

  @Override
  public Pixel[][] getImage(String imageName) {
    if (!(loadMap.containsKey(imageName))) {
      throw new IllegalArgumentException("Image name does not exist.");
    }
    return loadMap.get(imageName);
  }

  @Override
  public void load(String filePath, String imageName) {
    this.loadMap.put(imageName, ImageUtil.readPPM(filePath));
  }

  @Override
  public void save(String filePath, String imageName)throws IllegalArgumentException {
    if(!(filePath.contains(imageName))) {
      throw new IllegalArgumentException("specified path does not include imageName");
    }
    if(filePath == null || imageName == null) {
      throw new IllegalArgumentException("filepath and/or imageName is invalid");
    }
    ImageUtil.writePPM(filePath, this.loadMap.get(imageName));
  }

  @Override
  public Pixel[][] brightenImage(int increment) {
    Pixel[][] brightened = new Pixel[image.length][image[0].length];
    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[0].length; col++) {
        brightened[row][col] = image[row][col].adjustBrightness(increment);
      }
    }
    //loadMap.put(imageName, brightened);
    return brightened;
  }

  @Override
  public Pixel[][] displayGreyscale(String component) {
    Pixel[][] greyscale = new Pixel[image.length][image[0].length];
    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[0].length; col++) {
        greyscale[row][col] = image[row][col].displayComponent(component);
      }
    }
    return greyscale;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    if (row > image.length || row < 0 || col > image[0].length || col < 0) {
      throw new IllegalArgumentException("Invalid coordinate. ");
    }
    return image[row][col];
  }

  @Override
  public Pixel[][] flipImage(String axis) {
    Pixel[][] flippedImage = new Pixel[image.length][image[0].length];
    switch (axis) {
      case "vertical":
        for (int row = 0; row < image.length; row++) {
          for (int col = 0; col < image[0].length; col++) {
            flippedImage[row][col] = image[row][image[0].length - 1 - col];
          }
        }
        break;
      case "horizontal":
        for (int row = 0; row < image.length; row++) {
          for (int col = 0; col < image[0].length; col++) {
            flippedImage[row][col] = image[image.length - 1 - row][col];
          }
        }
        break;
      default:
        throw new IllegalArgumentException("The given axis is not valid");
    }
    return flippedImage;
  }
}

