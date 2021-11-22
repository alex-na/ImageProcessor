package controller;

import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
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
  private String desiredName;

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
    load.apply(this.model);
  }

  // Getting most recent image in Map.
  private String getLatestImage() {
    return this.imageNames.get(imageNames.size() - 1);
  }

  @Override
  public void save(String filePath) {
    ImageProcessingCommand save = new Save(filePath, getLatestImage());
    save.apply(this.model);
  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public void filter(String type) {
    desiredName = getLatestImage() + "-" + type;
    this.model.filterImage(type, getLatestImage(), desiredName);
    BufferedImage image = (BufferedImage) this.model.getImage(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
    this.imageNames.add(desiredName);
  }

  @Override
  public void transform(String type) {
    desiredName = getLatestImage() + "-" + type;
    this.model.transformImage(type, getLatestImage(), desiredName);
    BufferedImage image = (BufferedImage) this.model.getImage(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
    this.imageNames.add(desiredName);
  }

  @Override
  public void brighten(int increment) {
    desiredName = getLatestImage() + "-" + ("brightened-by-" + increment);
    this.model.brightenImage(increment, getLatestImage(), desiredName);
    BufferedImage image = (BufferedImage) this.model.getImage(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
    this.imageNames.add(desiredName);
  }

  @Override
  public void flip(String axis) {
    desiredName = getLatestImage() + "-" + axis;
    this.model.flipImage(axis, getLatestImage(), desiredName);
    BufferedImage image = (BufferedImage) this.model.getImage(axis);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
    this.imageNames.add(desiredName);
  }

  @Override
  public void component(String type) {
    desiredName = getLatestImage() + "-" + type;
    this.model.displayGreyscale(type, getLatestImage(), desiredName);
    BufferedImage image = (BufferedImage) this.model.getImage(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(image);
    this.imageNames.add(desiredName);
  }
}