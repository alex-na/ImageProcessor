package controller;

import java.util.List;

/**
 * Representing the features that our graphical user interface supports.
 */
public interface Features {

  void load(String filePath);

  void save(String filePath);

  void exit();

  void filter(String type);

  void transform(String type);

  void brighten(int increment);

  void flip(String axis);

  void component(String type);
}
