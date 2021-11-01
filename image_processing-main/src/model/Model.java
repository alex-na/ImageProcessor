package model;

import util.Image;
import util.ImageImpl;
import util.ImageUtil;
import util.Pixel;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an ImageProcessor for an image, along with its height and width.
 */
public class Model implements ImageProcessingModel {
  // imagename : Image
  private static Map<String, Image> images = new HashMap<String, Image>();

  // filename : Image
  private static Map<String, Image> filePaths = new HashMap<String, Image>();

  private Image image;

  /**
   * Given a the file path of an image, creates the model of the image processor where that image
   * can be altered.
   *
   * @param  imageName string that represents the name of an image.
   * @throws NullPointerException if the image name is null.
   * @throws IllegalArgumentException if an image with the give image name doesn't exist.
   */
  public Model(String imageName) throws NullPointerException, IllegalArgumentException {
    if (imageName.equals(null)) {
      throw new NullPointerException("The imageName cannot be null");
    }
    if (!(images.containsKey(imageName)) || images.get(imageName) == null)  {
      throw new IllegalArgumentException ("The given imageName doesn't correspond to an image");
    }
    this.image = images.get(imageName);
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


  /**
   * @param filename the path of the file.
   * @param imageName the name that the image will be referred to.
   */
  @Override
  public void load(String filename, String imageName) throws IllegalArgumentException {
    if (imageName.equals(null) || filename.equals(null)) {
      throw new IllegalArgumentException("The given fileName and/or imageName cannot be null.");
    }
    images.put(imageName, new ImageImpl(filename));
  }

  /**
   * @param filename the path of the file.
   * @param imageName the name of the image.
   */
  @Override
  public void save(String filename, String imageName) {

  }

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

