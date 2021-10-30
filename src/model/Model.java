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
