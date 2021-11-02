package controller;

import controller.commands.Brighten;
import controller.commands.Horizontal;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Vertical;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 *
 */
public class Controller implements ImageProcessingController {

  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable input;
  Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;

  /**
   *
   * @param model
   * @param view
   * @param input
   * @throws IllegalArgumentException
   */
  public Controller(ImageProcessingModel model, ImageProcessingView view, Readable input)
      throws IllegalArgumentException {

    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Inputs may not be null.");
    }

    this.model = model;
    this.view = view;
    this.input = input;

  }

  @Override
  public void processImage() throws IllegalStateException {

    Scanner scan = new Scanner(this.input);

    knownCommands = new HashMap<>();
    knownCommands.put("load", s -> new Load(s.next()));
    knownCommands.put("save", s -> new Save(s.next()));
    knownCommands.put("brighten", s -> new Brighten(s.nextInt()));
    knownCommands.put("horizontal-flip", s -> new Horizontal());
    knownCommands.put("vertical-flip", s -> new Vertical());

    while(scan.hasNext()) {
      ImageProcessingCommand c;
      String in = scan.next();
      Function<Scanner, ImageProcessingCommand> cmd =
          knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(scan);
        c.go(this.model, this.view);
      }

    }

  }
}
