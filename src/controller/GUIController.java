package controller;

import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.ImageProcessingModel;
import view.ImageProcessingGUIView;
import view.ImageProcessingView;

/**
 *
 */
public class GUIController implements Features {

  private final ImageProcessingModel model;
  private ImageProcessingGUIView view;
  private List<String> imageNames;

  /**
   *
   * @param model
   */
  public GUIController(ImageProcessingModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model may not be null");
    }
    this.model = model;
    this.imageNames = new ArrayList<>();
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
  public void load(String filePath) {
    ImageProcessingCommand load = new Load(filePath, "default");
  }

  // Getting most recent image in Map.
  private String getLatestImage() {
    //String str = this.model.getImage();
    return "";
  }

  @Override
  public void save(String filePath) {

  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public void filter(String type) {
    this.model.filterImage(type, getLatestImage(), getLatestImage() + "-" + type);
    BufferedImage image = (BufferedImage) this.model.getImage(type);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void transform(String type) {
    String name = getLatestImage() + type;
    this.model.transformImage(type, getLatestImage(), name);
    BufferedImage image = (BufferedImage) this.model.getImage(name);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void brighten(int increment) {
    this.model.brightenImage(increment, getLatestImage(), getLatestImage() + "-" + ("brightened-by-" + increment));
    BufferedImage image = (BufferedImage) this.model.getImage("brightened-by-" + increment);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void flip(String axis) {
    this.model.flipImage(axis, getLatestImage(), getLatestImage() + "-" + axis);
    BufferedImage image = (BufferedImage) this.model.getImage(axis);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }

  @Override
  public void component(String type) {
    this.model.displayGreyscale(type, getLatestImage(), getLatestImage() + "-" + type);
    BufferedImage image = (BufferedImage) this.model.getImage(type);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
  }
}
