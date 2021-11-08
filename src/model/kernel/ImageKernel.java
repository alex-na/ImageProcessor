package model.kernel;

import java.awt.*;

public class ImageKernel implements Kernel {

  private double[][] matrix;

  public ImageKernel(double[][] matrix) {
    if (matrix.length % 2 != 1 || matrix[0].length % 2 != 1) {
      throw new IllegalArgumentException("Dimensions must be odd.");
    }
    this.matrix = matrix;
  }

  @Override
  public int getHeight() {
    return matrix.length;
  }

  @Override
  public int getWidth() {
    return matrix[0].length;
  }

  @Override
  public double getValueAt(int row, int col) {
    return this.matrix[row][col];
  }
}


