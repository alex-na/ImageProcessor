package util;

import controller.Controller;
import controller.ImageProcessingController;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import model.ImageProcessingModel;
import model.Model;
import view.ImageProcessingView;
import view.View;

/**
 *
 */
public final class ImageProcessingProgram {

  /**
   *
   * @param args
   */
  public static void main(String args[]) {
    Readable readable = new InputStreamReader(System.in);
    Appendable appendable = new StringBuilder();

    //TODO fix filePath to find the input.
    ImageProcessingModel model =  new Model("");
    ImageProcessingView view = new View(model, appendable);
    ImageProcessingController controller = new Controller(model, view, readable);

    controller.processImage();
  }
}
