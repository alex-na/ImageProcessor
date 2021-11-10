package util;

import controller.Controller;
import controller.ImageProcessingController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
   * Main method used to instantiate the class objects and run the controller's processImage()
   * method.
   *
   * @param args user inputs
   */
  public static void main(String[] args) {

    Readable read = null;

    if (args.length == 2) {
      try {
        read = new FileReader(args[1]);
      } catch (FileNotFoundException e) {
        System.out.printf("Invalid file. Please re-enter.");
      }
    } else {
      read = new InputStreamReader(System.in);
    }

    Appendable appendable = new StringBuilder();

    ImageProcessingModel model = new Model();
    ImageProcessingView view = new View(appendable);
    ImageProcessingController controller = new Controller(model, view, read);

    controller.processImage();
  }
}
