package util.image;
import java.awt.Color;

/**
 * This interface represents operations that can be used to monitor the state of an Image,
 * without changing it.
 */
public interface ImageState {
  /**
   * Gets the height of an Image.
   *
   * @return an int that represents the height of this image.
   */
  int getImageHeight();

  /**
   * Gets the width of an Image.
   *
   * @return an int that represents the width of this image.
   */
  int getImageWidth();

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */

  /**
   * Gets the Pixel of an Image, at a specified (row, col) coordinate.
   *
   * @param row the row of a (row, col) grid coordinate.
   * @param col the col of a (row, col) grid coordinate.
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board.
   * @return a Color that represents a Pixel of this Image.
   */
  Color getPixelAt(int row, int col) throws IllegalArgumentException;
}
