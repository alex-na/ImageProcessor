package view;

import java.io.IOException;

/**
 *
 */
public interface ImageProcessingView {

  /**
   *
   */
  void displayImage();

  /**
   *
   * @param message
   */
  void renderMessage(String message) throws IOException;

}
