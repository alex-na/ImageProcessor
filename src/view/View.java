package view;

import java.io.IOException;
import model.ImageProcessingModel;

/**
 *
 */
public class View implements ImageProcessingView {

  private final ImageProcessingModel model;
  private Appendable appendable;

  /**
   *
   * @param model
   * @param appendable
   * @throws IllegalArgumentException
   */
  public View(ImageProcessingModel model, Appendable appendable) throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Inputs cannot be null.");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   *
   * @param model
   * @throws IllegalArgumentException
   */
  public View(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
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
