package util.kernel;

public interface Kernel {

  /**
   * Gets the height of the Kernel.
   *
   * @return an int that represents the height of the Kernel.
   */
  int getHeight();

  /**
   * Gets the width of the Kernel.
   *
   * @return an int that represents the width of the Kernel.
   */
  int getWidth();

  /**
   * Gets the value at a given row,col position of a Kernel.
   *
   * @param row the row of a row,col position of a Kernel.
   * @param col the col of a row,col position of a Kernel.
   * @return a double that represents a value of a particular space in a Kernel matrix.
   * @throws IllegalArgumentException if the given row and/or column are out of bounds.
   */
  double getValueAt(int row, int col) throws IllegalArgumentException;
}
