package model;

import model.pixel.Pixel;
import util.ImageUtil;

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
  private Map<String, Pixel[][]> loadMap;


  /**
   * Given a the file path of an image, creates the model of the image processor where that image
   * can be altered.
   *
   * @throws NullPointerException if the image name is null.
   * @throws IllegalArgumentException if an image with the give image name doesn't exist.
   */
  public Model() {
    this.loadMap = new HashMap<String, Pixel[][]>();
  }


  //TODO finish load method implementation
  public void load(String filePath, String imageName) throws IllegalArgumentException {
    if (filePath == null || imageName == null) {
      throw new IllegalArgumentException("Given parameters are invalid.");
    }
    this.loadMap.put(imageName, ImageUtil.readPPM(filePath));
  }

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


  public void brightenImage(int increment, String imageName, String desiredName) {
    if (inputHelper(imageName, desiredName)) {
      throw new IllegalArgumentException("The given parameters are invalid.");
    }
    Pixel[][] image = loadMap.get(imageName);
    Pixel[][] brightened = new Pixel[image.length][image[0].length];
    for (int row = 0; row < brightened.length; row++) {
      for (int col = 0; col < brightened[0].length; col++) {
        brightened[row][col] = image[row][col].adjustBrightness(increment);
      }
    }
    this.loadMap.put(desiredName, brightened);
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName) {
    if (inputHelper(imageName, desiredName)) {
      throw new IllegalArgumentException("The given parameters are invalid.");
    }

    Pixel[][] image = loadMap.get(imageName);
    Pixel[][] greyscale = new Pixel[image.length][image[0].length];
    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[0].length; col++) {
        greyscale[row][col] = image[row][col].displayComponent(component);
      }
    }
    loadMap.put(desiredName, greyscale);
  }

//  @Override
//  public Pixel getPixelAt(int row, int col) {
//    if (row > image.length || row < 0 || col > image[0].length || col < 0) {
//      throw new IllegalArgumentException("Invalid coordinate. ");
//    }
//    return image[row][col];
//  }

  public void flipImage(String axis, String imageName, String desiredName) {
    if (inputHelper(imageName, desiredName)) {
      throw new IllegalArgumentException("The given parameters are invalid.");
    }
    Pixel[][] image = loadMap.get(imageName);
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
    loadMap.put(desiredName, flippedImage);
  }

  private boolean inputHelper(String imageName, String desiredImage) {
    if(imageName == null || desiredImage == null) {
      return false;
    }
    if(!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      return false;
    }
    return true;
  }
}

