package controller;

import model.ImageProcessingModel;
import view.ImageProcessingGUIView;
import view.ImageProcessingView;

/**
 *
 */
public class GUIController implements Features {

  private ImageProcessingModel model;
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
}
