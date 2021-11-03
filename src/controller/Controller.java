package controller;

import controller.commands.Brighten;
import controller.commands.Component;
import controller.commands.Flip;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
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
    knownCommands.put("save", s -> new Save(s.next(), s.next()));
    knownCommands.put("brighten", s -> new Brighten(s.nextInt()));
    knownCommands.put("horizontal-flip", s -> new Flip("horizontal"));
    knownCommands.put("vertical-flip", s -> new Flip("vertical"));
    knownCommands.put("value-component", s -> new Component("value"));
    knownCommands.put("intensity-component", s -> new Component("intensity"));
    knownCommands.put("luma-component", s -> new Component("luma"));
    knownCommands.put("red-component", s -> new Component("red"));
    knownCommands.put("green-component", s -> new Component("green"));
    knownCommands.put("blue-component", s -> new Component("blue"));

    while(scan.hasNext()) {
      ImageProcessingCommand c;
      String in = scan.next();
      Function<Scanner, ImageProcessingCommand> cmd =
          knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        throw new IllegalArgumentException("Invalid command entered.");
      } else {
        c = cmd.apply(scan);
        c.go(this.model, this.view);
      }
    }
  }
}
