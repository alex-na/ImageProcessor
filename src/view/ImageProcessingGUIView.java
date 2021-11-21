package view;

import controller.Features;
import java.awt.image.BufferedImage;
import util.image.Image;

public interface ImageProcessingGUIView {

  void displayImage(BufferedImage image);

  void displayHistogram(BufferedImage image);

  void resetFocus();

  void addFeatures(Features features);

  void displayMessage(String message);

}
