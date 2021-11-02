package view;

import java.io.IOException;

/**
 *
 */
public class View implements ImageProcessingView {

  private Appendable appendable;

  /**
   *
   * @param appendable
   * @throws IllegalArgumentException
   */
  public View(Appendable appendable) throws IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException("Inputs cannot be null.");
    }
    this.appendable = appendable;
  }

  /**
   *
   * @throws IllegalArgumentException
   */
  public View() throws IllegalArgumentException {
    this.appendable = System.out;
  }

  @Override
  public void displayImage() {

  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

}
