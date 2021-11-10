package util.kernel;

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
  public double getValueAt(int row, int col) throws IllegalArgumentException {
    if (row > matrix.length || row < 0 || col > matrix[0].length || col < 0) {
      throw new IllegalArgumentException("The given row and/or col are out of bounds.");
    }
    return this.matrix[row][col];
  }
}


