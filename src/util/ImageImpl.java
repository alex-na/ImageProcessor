package util;

import java.util.Objects;

import model.Model;

/**
 * Implementation of an image that is represented as a 2D array of pixels with a height and width.
 */
public class ImageImpl implements Image {
  private Pixel[][] image;
  private int height;
  private int width;


  /**
   * Given a the file path of an image, creates the model of the image processor where that image
   * can be altered.
   *
   * @param filename a string that represents the file path of an image.
   * @throws NullPointerException if this file name is null.
   * @throws IllegalArgumentException if a file with the given file name doesn't exist.
   */
  public ImageImpl(String filename) throws NullPointerException {
    if (filename.equals(null)) {
      throw new NullPointerException("given filename is null.");
    }
    this.image = ImageUtil.readPPM(filename);
    this.height = this.image.length;
    this.width = this.image[0].length;

  }

  public Pixel[][] getImage() {
    return this.image;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
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
          for (int col = 0; col < this.width / 2; col++) {
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
    if (! (o instanceof ImageImpl)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    ImageImpl that = (ImageImpl) o;

    return this.image == that.image
            && this.height == that.height
            && this.width == that.width;
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, height, width);
  }
}
