package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import util.image.Image;
import util.image.PixelImage;

/**
 * Representing an ImageProcessingModel that contains the versions of an Image and their supporting
 * operations.
 */
public class Model implements ImageProcessingModel {

  private Map<String, Image> loadMap;

  /**
   * Instantiates a Map of Image-names and Images that stores images. Images can be retrieved by
   * their names.
   */
  public Model() {
    this.loadMap = new HashMap<>();
  }

  @Override
  public Color getPixelAt(String imageName, int row, int col) throws IllegalArgumentException {
    return getImage(imageName).getPixelAt(row, col);
  }

  @Override
  public int getImageHeight(String imageName) throws IllegalArgumentException {
    return getImage(imageName).getImageHeight();
  }

  @Override
  public int getImageWidth(String imageName) throws IllegalArgumentException {
    return getImage(imageName).getImageWidth();
  }

  @Override
  public void load(String imageName, Image image) throws IllegalArgumentException {
    if (imageName == null || image == null) {
      throw new IllegalArgumentException("The imageName and/or image are null");
    }
    loadMap.put(imageName, image);
  }

  // Helper method for verifying image names within the loadMap
  private void validNames(String imageName, String desiredImage)
      throws IllegalArgumentException {
    if (imageName == null || desiredImage == null) {
      throw new
          IllegalArgumentException("The given image name and/or desired image name are null.");
    }
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("The given image name isn't associated with an image.");
    }
  }

  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("Image name is not associated with an image.");
    }
    return loadMap.get(imageName);
  }

  @Override
  public void brightenImage(int increment, String imageName, String desiredName)
      throws IllegalArgumentException {
    validNames(imageName, desiredName);

    Color[][] brightened = loadMap.get(imageName).brightenImage(increment);
    loadMap.put(desiredName, new PixelImage(brightened));
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName)
      throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(component.equals("red") || component.equals("green")
        || component.equals("blue")
        || component.equals("value")
        || component.equals("intensity")
        || component.equals("luma"))) {
      throw new IllegalArgumentException("The given component is invalid.");
    }
    Color[][] greyscale = getImage(imageName).displayGreyscale(component);
    loadMap.put(desiredName, new PixelImage(greyscale));
  }

  @Override
  public void flipImage(String axis, String imageName, String desiredName)
      throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(axis.equals("horizontal") || axis.equals("vertical"))) {
      throw new IllegalArgumentException("The axis must be vertical or horizontal");
    }
    try {
      Color[][] flippedImage = getImage(imageName).flipImage(axis);
      loadMap.put(desiredName, new PixelImage(flippedImage));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The axis was null.");
    }
  }

  @Override
  public void filterImage(String filterType, String imageName, String desiredName)
      throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(filterType.equals("blur") || filterType.equals("sharpen"))) {
      throw new IllegalArgumentException("Invalid filter type entered.");
    }
    try {
      Color[][] filtered = getImage(imageName).filterImage(filterType);
      loadMap.put(desiredName, new PixelImage(filtered));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The given input was not found.");
    }

  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName)
      throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (! transformType.equals("greyscale") && ! transformType.equals("sepia")) {
      throw new IllegalArgumentException("The given transformation type was invalid.");
    }
    try {
      Color[][] transformed = getImage(imageName).transformImage(transformType);
      loadMap.put(desiredName, new PixelImage(transformed));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The given input was not found.");
    }
  }
}

