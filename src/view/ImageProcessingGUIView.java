package view;

import controller.Features;
import java.awt.image.BufferedImage;
import util.image.Image;

public interface ImageProcessingGUIView {

  void displayImage(BufferedImage image);

  void displayHistogram();

  void resetFocus();

  void addFeatures(Features features);

  void displayMessage(String message);

}
