package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import util.ImageUtil;
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
    if (component == null) {
      throw new IllegalArgumentException("component cannot be null.");
    }
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
    if (axis == null) {
      throw new IllegalArgumentException("axis cannot be null.");
    }
    if (!(axis.equals("horizontal") || axis.equals("vertical"))) {
      throw new IllegalArgumentException("The given axis was invalid.");
    }
    Color[][] flippedImage = getImage(imageName).flipImage(axis);
    loadMap.put(desiredName, new PixelImage(flippedImage));
  }

  @Override
  public void filterImage(String filterType, String imageName, String desiredName)
          throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!(filterType.equals("blur") || filterType.equals("sharpen"))) {
      throw new IllegalArgumentException("Invalid filter type entered.");
    }
    Color[][] filtered = getImage(imageName).filterImage(filterType);
    loadMap.put(desiredName, new PixelImage(filtered));

  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName)
          throws IllegalArgumentException {
    validNames(imageName, desiredName);
    if (!transformType.equals("greyscale") && !transformType.equals("sepia")) {
      throw new IllegalArgumentException("The given transformation type was invalid.");
    }
    Color[][] transformed = getImage(imageName).transformImage(transformType);
    loadMap.put(desiredName, new PixelImage(transformed));
  }
//
//  public void save(String fileName, String imageName) throws IllegalArgumentException {
//    if (!(fileName.contains(imageName))) {
//      throw new IllegalArgumentException("Specified path does not include imageName");
//    }
//
//    String[] splitAtPeriods = fileName.split("\\.");
//    if (splitAtPeriods.length <= 1) {
//      throw new IllegalArgumentException("Filepath does not contain a file type.");
//    }
//    int indexOfType = splitAtPeriods.length - 1;
//
//    if (splitAtPeriods[indexOfType].equals("ppm")) {
//      ImageUtil.writePPM(fileName, this.getImage(imageName));
//    } else {
//      int i = 0;
//      int j = 0;
//      try {
//        int height = this.getImageHeight(imageName);
//        int width = this.getImageWidth(imageName);
//        BufferedImage savedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//        for (i = 0; i < width; i++) {
//          for (j = 0; j < height; j++) {
//            int pixel = (this.getPixelAt(imageName, j, i).getRed() << 16)
//                    | (this.getPixelAt(imageName, j, i).getGreen() << 8)
//                    | (this.getPixelAt(imageName, j, i).getBlue());
//            savedImage.setRGB(i, j, pixel);
//          }
//        }
//
//        File newFile = new File(fileName);
//        ImageIO.write(savedImage, splitAtPeriods[indexOfType], newFile);
//      } catch (IOException e) {
//        throw new IllegalArgumentException(e);
//      } catch (NullPointerException e) {
//        System.out.println(i + "  " + j);
//
//      }
//    }
//  }
}

