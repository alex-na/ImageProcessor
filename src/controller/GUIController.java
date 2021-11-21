package controller;

import java.awt.image.BufferedImage;
import model.ImageProcessingModel;
import view.ImageProcessingGUIView;
import view.ImageProcessingView;

/**
 *
 */
public class GUIController implements Features {

  private final ImageProcessingModel model;
  private ImageProcessingGUIView view;

  /**
   *
   * @param model
   */
  public GUIController(ImageProcessingModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model may not be null");
    }
    this.model = model;
  }

  /**
   *
   * @param view
   */
  public void setView(ImageProcessingGUIView view) {
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void load() {

  }

  @Override
  public void save() {

  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public void filter(String type) {
    //this.model.filterImage(type, ___, type);
    BufferedImage image = (BufferedImage) this.model.getImage(type);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void transform(String type) {
    //this.model.transformImage(type, ___, type);
    BufferedImage image = (BufferedImage) this.model.getImage(type);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void brighten(int increment) {
    //this.model.brightenImage(increment, ___, type);
    BufferedImage image = (BufferedImage) this.model.getImage("brightened-by-" + increment);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void flip(String axis) {
    //this.model.flipImage(type, ___, type);
    BufferedImage image = (BufferedImage) this.model.getImage(axis);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void component(String type) {
    //this.model.displayGreyscale(type, ___, type);
    BufferedImage image = (BufferedImage) this.model.getImage(type);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }
}
