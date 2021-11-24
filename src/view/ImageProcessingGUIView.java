package view;

import controller.Features;
import java.awt.image.BufferedImage;
import java.util.List;

import util.image.Image;

public interface ImageProcessingGUIView {

  void displayImage(BufferedImage image);

  void displayHistogram(List<List<Integer>> histogram);

  void addFeatures(Features features);

  void displayMessage(String message);

    void updateHistogram(List<List<Integer>> histogram);
}
