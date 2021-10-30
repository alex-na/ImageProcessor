package model;

import util.Pixel;

public class Model implements ImageProcessingModel {

  @Override
  public Pixel[][] getImage() {
    return new Pixel[0][];
  }

  @Override
  public void adjustImage(String adjType, int increment) {

  }

  @Override
  public void adjustGreyscale() {

  }

  @Override
  public void flipImage(int axis) {

  }

  @Override
  public void save(String filename) {

  }

  @Override
  public void load(String filename) {

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
}


package model;

import java.util.Arrays;
import java.util.Collections;

public class Model implements ImageProcessorModel {
  private Pixel[][] rawImage;
  private int imageHeight;
  private int imageWidth;

  public Model(String filename) {
    this.rawImage = ImageUtil.readPPM(filename);
    this.imageHeight = rawImage.length;
    this.imageWidth = rawImage[0].length;
  }


  @Override
  public Pixel getPixelAt(int row, int col) {
    if (row < 0 || row >= this.imageHeight || col < 0 || col >= this.imageWidth) {
      throw new IllegalArgumentException("The given row and/or column coordinates are invalid.");
    }
    return this.rawImage[row][col];
  }

  public void adjustBrightness(int increment, String adjustmentSpec) {
    for (int row = 0; row < this.imageHeight; row++) {
      for (int col = 0; col < this.imageWidth; col++) {
        //get to each pixel
        //obtain the highest
        switch (adjustmentSpec) {
          case "Value":
            //decide how to change the color value of the pixel.
            rawImage[row][col].adjustByValue(increment);
            break;
          case "Intensity":
            rawImage[row][col].adjustByIntensity(increment);
            break;
          case "Luma":
            rawImage[row][col].adjustByLuma(increment);
            break;
          default:
            throw new IllegalArgumentException("The given brightness adjustment mode is invalid.");
        }
      }
    }
  }

  //change to void method
  public void flipImage(String axis) {
    switch (axis) {
      case "vertical":
        for (int row = 0; row < this.imageHeight; row++) {
          Collections.reverse(Arrays.asList(rawImage[row]));
        }
        break;
      case "horizontally":
        Pixel tempPixel;
        for (int row = 0; row < this.imageHeight; row++) {
          for (int col = 0; col < this.imageWidth / 2; col++) {
            tempPixel = rawImage[row][col];
            rawImage[row][col] = rawImage[rawImage.length - 1 - row][col];
            rawImage[rawImage.length - 1 - row][col] = tempPixel;
          }
        }
        break;
      default:
        throw new IllegalArgumentException("The given axis is not valid");
    }
  }
}

