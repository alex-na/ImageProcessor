package model;

import model.image.Image;
import model.image.PixelImage;
import util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

//TODO abstract common functionality into helpers
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
  public void load(String filePath, String imageName) throws IllegalArgumentException {
    if (filePath == null || imageName == null) {
      throw new IllegalArgumentException("Given parameters are invalid.");
    }
    if(filePath.endsWith(".ppm")) {
      this.loadMap.put(imageName, ImageUtil.readPPM(filePath));
    }
    else {
      try {
        File file = new File(filePath);

        BufferedImage image = ImageIO.read(file);

        int height = image.getWidth();
        int width = image.getHeight();

        Color[][] pixelMatrix = new Color[height][width];

        for (int row = 0; row < height; row++) {
          for (int col = 0; col < width; col++) {
            int pixel = image.getRGB(row, col); //why does java switch the coordinate order?
            pixelMatrix[row][col] = new Color((pixel & 0xff0000) >> 16, (pixel & 0xff00) >> 8,
                    pixel & 0xff);
          }
        }

        loadMap.put(imageName, new PixelImage(pixelMatrix));
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  @Override
  public void save(String filePath, String imageName) throws IllegalArgumentException {
    if (!(filePath.contains(imageName))) {
      throw new IllegalArgumentException("Specified path does not include imageName");
    }

    String[] splitAtPeriods = filePath.split("\\.");
    if (splitAtPeriods.length <= 1) {
      throw new IllegalArgumentException("Filepath does not contain a file type.");
    }
    int indexOfType = splitAtPeriods.length - 1;

    if (splitAtPeriods[indexOfType].equals("ppm")) {
      ImageUtil.writePPM(filePath, this.loadMap.get(imageName));
    }
    else {
      try {

      BufferedImage savedImage = new BufferedImage(getImageHeight(imageName),
              getImageWidth(imageName), BufferedImage.TYPE_INT_RGB);

      for (int row = 0; row < getImageHeight(imageName); row++) {
        for (int col = 0; col < getImageWidth(imageName); col++) {
          int pixel = (getPixelAt(imageName, row, col).getRed() << 16)
                  | (getPixelAt(imageName, row, col).getGreen() << 8)
                  | (getPixelAt(imageName, row, col).getBlue());
          savedImage.setRGB(row, col, pixel);
        }
      }

      File newFile = new File(filePath);
      ImageIO.write(savedImage, splitAtPeriods[indexOfType], newFile);
    } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
    }
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
    this.loadMap.put(desiredName, new PixelImage(brightened));
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
    this.loadMap.put(desiredName, new PixelImage(greyscale));
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
    Color[][] filtered = getImage(imageName).filterImage(filterType);
    this.loadMap.put(desiredName, new PixelImage(filtered));
  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName)
      throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(transformType.equals("greyscale") || transformType.equals("sepia"))) {
      throw new IllegalArgumentException("Invalid filter type entered.");
    }
    Color[][] transformed = getImage(imageName).filterImage(transformType);
    this.loadMap.put(desiredName, new PixelImage(transformed));
  }
}

