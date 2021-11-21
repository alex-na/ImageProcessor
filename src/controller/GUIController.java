package controller;

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

  }

  @Override
  public void transform(String type) {

  }

  @Override
  public void brighten(int increment) {

  }

  @Override
  public void flip(String axis) {

  }

  @Override
  public void component(String type) {

  }
}
