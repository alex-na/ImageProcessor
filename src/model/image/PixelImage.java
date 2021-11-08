package model.image;

import java.awt.*;
import java.util.Objects;

import model.kernel.ImageKernel;
import model.kernel.Kernel;

/**
 * Represents an image in the form of a 2D array of Colors.
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

  @Override
  public Color getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > getImageHeight() || col < 0 || col > getImageWidth()) {
      throw new IllegalArgumentException("Pixel is out of bounds.");
    }
    return image[row][col];
  }

  @Override
  public int getImageHeight() {
    return this.height;
  }

  @Override
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

  // Checking component + increment to ensure proper values are used
  private int componentWithinRange(int component, int increment) {
    if (component + increment < 0) {
      return 0;
    } else if (component + increment > 255) {
      return 255;
    } else {
      return component + increment;
    }
  }

  @Override
  public Color[][] displayGreyscale(String component) throws IllegalArgumentException {
    Color[][] greyscale = new Color[this.height][this.width];
    Color tempColor;
    float colorValue;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        tempColor = getPixelAt(row, col);

        switch (component) {
          case "red":
            colorValue = tempColor.getRed();
            break;
          case "green":
            colorValue = tempColor.getGreen();
            break;
          case "blue":
            colorValue = tempColor.getBlue();
            break;
          case "value":
            colorValue = Math.max(tempColor.getRed(),
                Math.max(tempColor.getGreen(), tempColor.getBlue()));
            break;
          case "intensity":
            colorValue = (float) ((tempColor.getRed()
                + tempColor.getGreen()
                + tempColor.getBlue()) / 3);
            break;
          case "luma":
            colorValue = (float) (tempColor.getRed() * 0.2126
                + tempColor.getGreen() * 0.7152
                + tempColor.getBlue() * 0.0722);
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
  public Color[][] transformImage(String type) throws IllegalArgumentException {
    Color[][] transformed = new Color[this.height][this.width];
    Kernel transformationMatrix;

    double[][] sepiaMatrix = {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    double[][] greyscaleMatrix = {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    switch (type) {
      case "sepia":
        transformationMatrix = new ImageKernel(sepiaMatrix);
        break;
      case "greyscale":
        transformationMatrix = new ImageKernel(greyscaleMatrix);
        break;
      default:
        throw new IllegalArgumentException("The given type was invalid.");
    }

    for (int row = 0; row < transformationMatrix.getHeight(); row++) {
      for (int col = 0; col < transformationMatrix.getWidth(); col++) {
        transformed[row][col] = transformColor(transformationMatrix);
      }
    }
    return transformed;
  }

  // Transforming a single color based on the matrix
  private Color transformColor(Kernel transformationMatrix) {
    float redPrime = 0;
    float greenPrime = 0;
    float bluePrime = 0;

    for (int row = 0; row < transformationMatrix.getHeight(); row++) {
      for (int col = 0; col < transformationMatrix.getWidth(); col++) {
        if (row == 0) {
          redPrime += transformationMatrix.getValueAt(row, col) * image[row][col].getRed();
        } else if (row == 1) {
          greenPrime += transformationMatrix.getValueAt(row, col) * image[row][col].getGreen();
        } else if (row == 2) {
          bluePrime += transformationMatrix.getValueAt(row, col) * image[row][col].getBlue();
        }
      }
    }
    return new Color(redPrime, greenPrime, bluePrime);
  }

  @Override
  public Color[][] filterImage(String type) throws IllegalArgumentException {
    Color[][] filtered = new Color[this.height][this.width];
    Kernel filterMatrix;

    double[][] blurMatrix = {
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};

    double[][] sharpenMatrix = {
        {-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};

    switch (type) {
      case "blur":
        filterMatrix = new ImageKernel(blurMatrix);
        break;
      case "sharpen":
        filterMatrix = new ImageKernel(sharpenMatrix);
        break;
      default:
        throw new IllegalArgumentException("Invalid type.");
    }

    for (int row = 0; row < filtered.length; row++) {
      for (int col = 0; col < filtered[0].length; col++) {
        filtered[row][col] = applyFilter(filterMatrix);
      }
    }

    return filtered;
  }

  // Applying the filter to a single pixel
  private Color applyFilter(Kernel matrix) {

    float red = 0;
    float green = 0;
    float blue = 0;

    int rowInc = matrix.getHeight() - (matrix.getHeight() / 2);
    int colInc = matrix.getWidth() - (matrix.getWidth() / 2);

    for (int r = -rowInc; r < rowInc; r++) {
      for (int c = -colInc; c < colInc; c++) {
        try {
          red += this.getPixelAt(r, c).getRed() * matrix.getValueAt(r, c);
          green += this.getPixelAt(r, c).getGreen() * matrix.getValueAt(r, c);
          blue += this.getPixelAt(r, c).getBlue() * matrix.getValueAt(r, c);
        } catch (ArrayIndexOutOfBoundsException ignore) {
          continue;
        }
      }
    }
    return new Color(red, green, blue);
  }


  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal
    if (!(o instanceof PixelImage)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed
    PixelImage that = (PixelImage) o;
    return (this.height == that.height && this.width == that.width && this.image == that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, height, width);
  }
}
