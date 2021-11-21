package controller;

/**
 * Representing the features that our graphical user interface supports.
 */
public interface Features {

  void load();

  void save();

  void exit();

  void filter(String type);

  void transform(String type);

  void brighten(int increment);

  void flip(String axis);

  void component(String type);
}
