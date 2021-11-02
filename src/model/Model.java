package model;

import util.ImageUtil;
import util.Pixel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO decide on consistent return types for methods that deal with the Pixel matrix

/**
 * Represents an ImageProcessor for an image, along with its height and width.
 */
public class Model implements ImageProcessingModel {
  private static Map<String, Pixel[][]> loadMap;
  private Pixel[][] image;


  /**
   * Given a the file path of an image, creates the model of the image processor where that image
   * can be altered.
   *
   * @param  filePath string that represents the name of an image.
   * @throws NullPointerException if the image name is null.
   * @throws IllegalArgumentException if an image with the give image name doesn't exist.
   */
  public Model(String filePath, String imageName) throws NullPointerException, IllegalArgumentException {
    if (filePath.equals(null)) {
      throw new NullPointerException("The imageName cannot be null");
    }

    if (!(loadMap.containsKey(filePath)) || loadMap.get(filePath) == null)  {
      throw new IllegalArgumentException ("The given imageName doesn't correspond to an image");
    }

    this.image = ImageUtil.readPPM(filePath);

    loadMap = new HashMap<String, Pixel[][]>();
    loadMap.put(imageName, this.image);
  }

  @Override
  public Pixel[][] getImage() {
    return this.image;
  }

  @Override
  //TODO finish load method implementation
  public void load(String filePath, String imageName) {
    //this.loadMap.put(imageName, new ImageImpl(filename));
  }

  @Override
  //TODO finish save method implementation
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
  public int getHeight() {
    return image.length;
  }

  @Override
  public int getWidth() {
    return image[0].length;
  }

  @Override
  public Pixel[][] brightenImage(int increment) {
    Pixel[][] brightened = new Pixel[image.length][image[0].length];
    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[0].length; col++) {
        brightened[row][col] = image[row][col].adjustBrightness(increment);
      }
    }
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

