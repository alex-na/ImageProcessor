package model;

import util.ImageUtil;
import util.Pixel;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO decide on consistent return types for methods that deal with the Pixel matrix

/**
 * Represents an ImageProcessor for an image, along with its height and width.
 */
public class Model implements ImageProcessingModel {
  // imagename : Image
  private Map<String, Pixel[][]> loadMap = new HashMap<String, Pixel[][]>();

  private Pixel[][] image;

  /**
   * Given a the file path of an image, creates the model of the image processor where that image
   * can be altered.
   *
   * @param  filePath string that represents the name of an image.
   * @throws NullPointerException if the image name is null.
   * @throws IllegalArgumentException if an image with the give image name doesn't exist.
   */
  public Model(String filePath) throws NullPointerException, IllegalArgumentException {
    if (filePath.equals(null)) {
      throw new NullPointerException("The imageName cannot be null");
    }

    if (!(loadMap.containsKey(filePath)) || loadMap.get(filePath) == null)  {
      throw new IllegalArgumentException ("The given imageName doesn't correspond to an image");
    }

    this.image = ImageUtil.readPPM(filePath);

    loadMap.get(filePath)

  }

  /**
   * Returns the image that is contained within this model.
   *
   * @return a 2D array of pixels that represents an image.
   */
  @Override
  public Image getImage() {
    return this.image;
  }

  @Override
  public Image adjustImage(String adjType, int increment) {
    return null;
  }

  @Override
  //TODO finish load method implementation
  public void load(String filename, String imageName) {
    this.loadMap.put(imageName, ImageUtil.readPPM(filename));
  }

  /**
   * @param filepath the path of the file.
   */
  @Override
  //TODO finish save method implementation
  public void save(String filepath, String imageName)throws IllegalArgumentException {
    if(!(filepath.contains(imageName))) {
      throw new IllegalArgumentException("specified path does not include imageName");
    }
    if(filepath == null || imageName == null) {
      throw new IllegalArgumentException("filepath and/or imageName is invalid");
    }
    ImageUtil.writePPM(filepath, this.loadMap.get(imageName));
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return this.image[row][col];
  }

  @Override
  public int getHeight() {
    return this.image.length;
  }

  @Override
  public int getWidth() {
    return this.image[0].length;
  }

  //TODO edit functionality to include value, intensity, luma
  public Pixel[][] adjustGreyscale(String component) throws NullPointerException, IllegalArgumentException {
    if (component.equals(null)) {
      throw new NullPointerException("The given component is null");
    }
    if (!(component.equals("Red")) || !(component.equals("Green")) || !(component.equals("Blue"))) {
      throw  new IllegalArgumentException("Given RGB component is invalid.");
    }
    Color[][] greyScaleImage = new Pixel[this.getHeight()][this.getWidth()];

    for(int row = 0; row < this.getHeight());
    for()
      greyScaleImage[row][col]
              = new Color(image[row][col].getRed(), image[row][col].getGreen(), image[row][col].getBlue();
    return greyScaleImage;
  }

  /**
   * Given an increment value, adjusts the brightness of each pixel of this model's image
   * using the given desired brightening method
   *
   * @param adjustmentType whether the image will be brightened or darkened.
   * @param brightnessMode the brightening method that will be used (intensity, value, or luma.)
   * @param increment      the increment value that will be used to adjust the image's brightness.
   * @throws IllegalArgumentException when the given brightness adjustment mode is not
   *                                  Value, Intensity, or Luma
   */
  //TODO edit method to match with interface and delete
  // adjustmentType (component implementation moved to adjustGreyscale),
  // brightnessMode (darken is just negative brightness, functionality stays the same.)
  public Pixel[][] adjustBrightness(String adjustmentType, String brightnessMode, int increment)
          throws IllegalArgumentException {
    return this.image.adjustBrightness(adjustmentType, brightnessMode, increment);
  }

  /**
   * Rearranges the pixels of an image
   *
   * @param axis the axis that the image will be flipped across.
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  @Override
  public Pixel[][] flipImage(String axis) throws IllegalArgumentException {
    if(!(axis.equals("vertically")) || !(axis.equals("horizontally"))) {
      throw new IllegalArgumentException("The given axis is invalid.");
    }
    return this.image.flipImage(axis);
  }

  /**
   * Returns the pixel of an image at a particular row,column grid coordinate.
   *
   * @param row the row of a row,col grid coordinate.
   * @param col the column of a row,col grid coordinate.
   * @return the Pixel at the row and column of an image's 2D array of pixels.
   * @throws IllegalArgumentException when the given row and/or column is out
   *                                  of the range of an image's width and height.
   */
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IllegalArgumentException("The given row and/or column coordinates are invalid.");
    }
    return this.image[row][col];
  }

  /**
   * Adjusts the model's image to a greyscale representation of the image.
   *
   * @param component the component of an image's pixel that will
   *                  be used to set the greyscale value of that pixel.
   * @throws IllegalArgumentException if the given rgb component is not Red, Green, or Blue.
   */
  public Pixel[][] adjustGreyscale(String component) throws IllegalArgumentException {
    Pixel[][] greyScaleImage = new Pixel[height][width];

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        greyScaleImage[row][col] = this.image[row][col].adjustPixelGreyscale(component);
      }
    }
    return greyScaleImage;
  }

  /**
   * Rearranges the pixels of an image across a given axis to flip the image.
   *
   * @param axis the axis that the image will be flipped across.
   * @return a Pixel[][] that represent a flipped version of this model's image.
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  public Pixel[][] flipImage(String axis) throws IllegalArgumentException {
    Pixel[][] flippedImage = new Pixel[height][width];
    switch (axis) {
      case "vertically":
        for (int row = 0; row < this.height; row++) {
          for (int col = 0; col < this.width; col++) {
            flippedImage[row][col] = image[row][width - 1 - col];
          }
        }
        break;
      case "horizontally":
        Pixel tempPixel;
        for (int row = 0; row < this.height; row++) {
          for (int col = 0; col < this.width; col++) {
            flippedImage[row][col] = image[height - 1 - row][col];
          }
        }
        break;
      default:
        throw new IllegalArgumentException("The given axis is not valid");
    }
    return flippedImage;
  }

  /**
   * Given an increment value, adjusts the brightness of each pixel of this model's image
   * using the given desired brightening method
   *
   * @param adjustmentType whether the image will be brightened or darkened.
   * @param brightnessMode the brightening method that will be used (intensity, value, or luma.)
   * @param increment      the increment value that will be used to adjust the image's brightness.
   * @throws IllegalArgumentException when the given brightness adjustment mode is not
   *                                  Value, Intensity, or Luma
   */
  public Pixel[][] adjustBrightness(String adjustmentType, String brightnessMode, int increment)
          throws IllegalArgumentException {
    Pixel[][] adjustedImage = new Pixel[height][width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        if (adjustmentType.equals("Brighten")) {
          switch (brightnessMode) {
            case "Value":
              adjustedImage[row][col] = this.image[row][col].adjustByValue(increment);
              break;
            case "Intensity":
              adjustedImage[row][col] = this.image[row][col].adjustByIntensity(increment);
              break;
            case "Luma":
              adjustedImage[row][col] = this.image[row][col].adjustByLuma(increment);
              break;
            default: throw new IllegalArgumentException("The given brightness mode was invalid");
          }
        } else if (adjustmentType.equals("Darken")) {
          switch (brightnessMode) {
            case "Value":
              adjustedImage[row][col]
                      = this.image[row][col].adjustByValue(increment * -1);
              break;
            case "Intensity":
              adjustedImage[row][col]
                      = this.image[row][col].adjustByIntensity(increment * -1);
              break;
            case "Luma":
              adjustedImage[row][col]
                      = this.image[row][col].adjustByLuma(increment * -1);
              break;
            default:
              throw new IllegalArgumentException("The given brightness mode was invalid");
          }
        } else {
          throw new IllegalArgumentException("The adjustment type was invalid");
        }
      }
    }
    return adjustedImage;
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (! (o instanceof Model)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Model that = (Model) o;

    return  this.loadMap == that.loadMap && this.image == that.image;
  }

  @Override
  public int hashCode() {
    return Objects.hash(loadMap, image);
  }
}

