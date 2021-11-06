package model.image;

import java.awt.*;
import java.util.Objects;

/**
 * Represents an image in the form of a 2D array of Pixels.
 */
public class PixelImage implements Image {
  private final Color[][] image;
  private final int height;
  private final int width;

  /**
   * Given a 2D array of Colors, constructs a PixelImage object that contains the Color matrix,
   * along with the height and width of passed in matrix.
   *
   * @param image the image that will be stored in this PixelImage object.
   * @throws IllegalArgumentException when the given Color matrix is null.
   */
  public PixelImage(Color[][] image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("The given image is null.");
    }
    this.image = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  public Color getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > getImageHeight() || col < 0 || col > getImageWidth()) {
      throw new IllegalArgumentException("The given row and/or col is not within the bounds of the" +
              "image's height/width.");
    }
    return image[row][col];
  }

  public int getImageHeight() {
    return this.height;
  }

  public int getImageWidth() {
    return this.width;
  }

  @Override
  public Color[][] brightenImage(int increment) throws IllegalArgumentException {
    Color[][] brightened = new Color[height][width];
    Color tempColor;
    int r;
    int g;
    int b;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        tempColor = getPixelAt(row, col);
        r = componentWithinRange(tempColor.getRed(), increment);
        g = componentWithinRange(tempColor.getGreen(), increment);
        b = componentWithinRange(tempColor.getBlue(), increment);
        brightened[row][col] = new Color(r, g, b);
      }
    }
    return brightened;
  }

  private int componentWithinRange(int component, int increment) {
    if (component + increment < 0) {
      return 0;
    }
    else if (component + increment > 255) {
      return 255;
    }
    else  {
      return component + increment;
    }
  }

  @Override
  public Color[][] displayGreyscale(String component) throws IllegalArgumentException {
    Color[][] greyscale = new Color[this.height][this.width];
    Color tempColor;
    int colorValue;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        tempColor = getPixelAt(row, col);

        switch (component) {
          case "value":
            colorValue = Math.max(tempColor.getRed(),
                    Math.max(tempColor.getGreen(), tempColor.getBlue()));
            break;
          case "intensity":
            colorValue = (tempColor.getRed() + tempColor.getGreen()+ tempColor.getBlue()) / 3;
            break;
          case "luma":
            colorValue = (int) (Math.round(tempColor.getRed() * 0.2126)
                    + Math.round(tempColor.getGreen() * 0.7152)
                    + Math.round(tempColor.getBlue() * 0.0722));
            break;
          case "red":
            colorValue = tempColor.getRed();
            break;
          case "green":
            colorValue = tempColor.getGreen();
            break;
          case "blue":
            colorValue = tempColor.getBlue();
            break;
          default:
            throw new IllegalArgumentException("Invalid input.");
        }
        greyscale[row][col] = new Color(colorValue, colorValue, colorValue);
      }
    }
    return greyscale;
  }

  @Override
  public Color[][] flipImage(String axis) throws IllegalArgumentException {
    Color[][] flippedImage = new Color[this.height][this.width];
    switch (axis) {
      case "horizontal":
        for (int row = 0; row < height; row++) {
          for (int col = 0; col < width; col++) {
            flippedImage[row][col] = getPixelAt(row, width - 1 - col);
          }
        }
        break;
      case "vertical":
        for (int row = 0; row < image.length; row++) {
          for (int col = 0; col < image[0].length; col++) {
            flippedImage[row][col] = getPixelAt(height - 1 - row, col);
          }
        }
        break;
      default:
        throw new IllegalArgumentException("The given axis is not valid");
    }
    return flippedImage;
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (! (o instanceof PixelImage)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    PixelImage that = (PixelImage) o;
    return (this.height == that.height && this.width == that.width && this.image == that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, height, width);
  }
}
