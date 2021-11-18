package view;

import controller.Features;

public interface ImageProcessingGUIView {

  void setImage();

  void displayHistogram();

  void resetFocus();

  void addFeatures(Features features);

  void displayMessage(String message);

}
