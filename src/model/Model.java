package model;

import model.image.Image;
import model.image.PixelImage;
import model.pixel.Pixel;
import util.ImageUtil;


import java.util.HashMap;
import java.util.Map;

//TODO decide on consistent return types for methods that deal with the Pixel matrix

/**
 * Represents an ImageProcessor for an image, along with its height and width.
 */
public class Model implements ImageProcessingModel {
  private Map<String, Image> loadMap;

  /**
   * Instantiates a Map of Image-names and Images that stores images.
   * Images can be retrieved by their names.
   */
  public Model() {
    this.loadMap = new HashMap<String, Image>();
  }

  /**
   *
   * @param filePath
   * @param imageName
   * @throws IllegalArgumentException
   */
  public void load(String filePath, String imageName) throws IllegalArgumentException {
    if (filePath == null || imageName == null) {
      throw new IllegalArgumentException("Given parameters are invalid.");
    }
    this.loadMap.put(imageName, ImageUtil.readPPM(filePath));
  }

  /**
   *
   * @param filePath
   * @param imageName
   * @throws IllegalArgumentException
   */
  public void save(String filePath, String imageName) throws IllegalArgumentException {
    if (!(filePath.contains(imageName))) {
      throw new IllegalArgumentException("Specified path does not include imageName");
    }
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("Image name is not associated with an image.");
    }
    ImageUtil.writePPM(filePath, this.loadMap.get(imageName));
  }

  /**
   *
   * @param increment
   * @param imageName
   * @param desiredName
   * @throws IllegalArgumentException
   */
  public void brightenImage(int increment, String imageName, String desiredName) throws IllegalArgumentException {
    validNames(imageName, desiredName);

    Pixel[][] brightened = loadMap.get(imageName).brightenImage(increment);
    this.loadMap.put(desiredName, new PixelImage(brightened));
  }

  /**
   *
   * @param component
   * @param imageName
   * @param desiredName
   * @throws IllegalArgumentException
   */
  @Override
  public void displayGreyscale(String component, String imageName, String desiredName) throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(component.equals("red") || component.equals("green") || component.equals("blue")
            || component.equals("value") || component.equals("intensity") || component.equals("luma"))) {
      throw new IllegalArgumentException("The given component is invalid.");
    }
    Pixel[][] greyscale = loadMap.get(imageName).displayGreyscale(component);
    this.loadMap.put(desiredName, new PixelImage(greyscale));
  }

  /**
   * @param axis        the axis that the image will be flipped across.
   * @param imageName
   * @param desiredName
   * @throws IllegalArgumentException
   */
  public void flipImage(String axis, String imageName, String desiredName) throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(axis.equals("horizontal") || axis.equals("vertical"))) {
      throw new IllegalArgumentException("The axis must be vertical or horizontal");
    }

    Pixel[][] flippedImage = loadMap.get(imageName).flipImage(axis);
    loadMap.put(desiredName, new PixelImage(flippedImage));
  }

  /**
   * @param imageName
   * @param desiredImage
   * @throws IllegalArgumentException
   */
  private void validNames(String imageName, String desiredImage) throws IllegalArgumentException {
    if (imageName == null || desiredImage == null) {
      throw new IllegalArgumentException("The given image name and/or desired image name are null.");
    }
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("The given image name isn't associated with an image.");
    }
  }
}

