package util;

import controller.Controller;
import controller.ImageProcessingController;

import java.io.InputStreamReader;

import model.ImageProcessingModel;
import model.Model;
import view.ImageProcessingView;
import view.View;

/**
 * Class utilized for running the ImageProcessingProgram.
 */
public final class ImageProcessingProgram {

  /**
   * Main method used to instantiate the class objects and run the controller's
   * processImage() method.
   *
   * @param args user inputs
   */
  public static void main(String[] args) {
    Readable readable = new InputStreamReader(System.in);
    Appendable appendable = new StringBuilder();

    ImageProcessingModel model = new Model();
    ImageProcessingView view = new View(appendable);
    ImageProcessingController controller = new Controller(model, view, readable);

    controller.processImage();
  }
}
