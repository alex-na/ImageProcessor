package model;

import util.Image;
import util.ImageUtil;
import util.Pixel;
import util.PixelImpl;

import java.util.HashMap;
import java.util.Map;

//TODO decide on consistent return types for methods that deal with the Pixel matrix

/**
 * Represents an ImageProcessor for an image, along with its height and width.
 */
public class Model implements ImageProcessingModel {
  // imagename : Image
  private static Map<String, Image> images = new HashMap<String, Image>();

  // filename : Image
  private static Map<String, Image> filePaths = new HashMap<String, Image>();

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
    if (!(images.containsKey(filePath)) || images.get(filePath) == null)  {
      throw new IllegalArgumentException ("The given imageName doesn't correspond to an image");
    }
    this.image = ImageUtil.readPPM(filePath);
  }

  /**
   * Returns the image that is contained within this model.
   *
   * @return a 2D array of pixels that represents an image.
   */
  @Override
  public Pixel[][] getImage() {
    return this.image;
  }

  @Override
  public Pixel[][] brightenImage(int increment) {
    Pixel[][] brightened = new Pixel[image.length][image[0].length];
    for (int row = 0; row < brightened.length; row++) {
      for (int col = 0; col < brightened[0].length; col++) {
        brightened[row][col] = brightenImage(increment);
      }
    }
    return brightened;
  }

  @Override
  public Pixel[][] displayGreyscale(String component) {
    return new Pixel[0][];
  }

  @Override
  public Pixel[][] adjustImage(String adjType, int increment) {
    return null;
  }

  @Override
  //TODO finish load method implementation
  public void load() {
    //images.put(filePath, this.image);
  }

  /**
   * @param filename the path of the file.
   */
  @Override
  //TODO finish save method implementation
  public void save(String filename) {

  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return null;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  //TODO edit functionality to include value, intensity, luma
  public Pixel[][] adjustGreyscale(String component) throws NullPointerException, IllegalArgumentException {
    if (component.equals(null)) {
      throw new NullPointerException("The given component is null");
    }
    if (!(component.equals("Red")) || !(component.equals("Green")) || !(component.equals("Blue"))) {
      throw  new IllegalArgumentException("Given RGB component is invalid.");
    }
    return this.image.adjustGreyscale(component);
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
}

