package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.image.Image;

public class GUIView extends JFrame implements ImageProcessingGUIView {

  private JMenuBar topMenuBar;
  private JMenu filterMenu;
  private JMenu transformMenu;
  private JMenu componentMenu;
  private JMenu flipMenu;

  private JMenuItem menuMessage;
  private JMenuItem blur;
  private JMenuItem sharpen;
  private JMenuItem greyscale;
  private JMenuItem sepia;
  private JMenuItem red;
  private JMenuItem green;
  private JMenuItem blue;
  private JMenuItem value;
  private JMenuItem intensity;
  private JMenuItem luma;
  private JMenuItem horizontal;
  private JMenuItem vertical;
  private JMenuItem brighten;

  private JPanel imagePanel;
  private JLabel image;
  private JLabel imageMessage;
  private JScrollPane imageScrollPane;
  private JLabel histogram;
  private JFormattedTextField brightenInputField;

  private JMenuBar bottomMenuBar;
  private JButton exit;
  private JButton save;
  private JButton load;

  public GUIView() {
    super();

    setTitle("Image Processor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // setting layout to BorderLayout
    this.setLayout(new BorderLayout());

    // Menu Bar
    topMenuBar = new JMenuBar();
    this.add(topMenuBar, BorderLayout.PAGE_START);

    menuMessage = new JMenuItem("Edit Image:");
    topMenuBar.add(menuMessage);

    // Filter Menu
    filterMenu = new JMenu("Filter");
    // Blur Button
    blur = new JCheckBoxMenuItem("Blur");
    blur.setActionCommand("Blur Button");
    filterMenu.add(blur);
    // Sharpen Button
    sharpen = new JCheckBoxMenuItem("Sharpen");
    sharpen.setActionCommand("Greyscale Button");
    filterMenu.add(sharpen);
    topMenuBar.add(filterMenu);

    // Transformation Menu
    transformMenu = new JMenu("Transform");
    // Sepia Button
    sepia = new JCheckBoxMenuItem("Sepia");
    sepia.setActionCommand("Sepia Button");
    transformMenu.add(sepia);
    // Greyscale Button
    greyscale = new JCheckBoxMenuItem("Greyscale");
    greyscale.setActionCommand("Greyscale Button");
    transformMenu.add(greyscale);
    topMenuBar.add(transformMenu);

    // Component Menu
    componentMenu = new JMenu("Component");
    // Red Component Button
    red = new JCheckBoxMenuItem("Red Component");
    red.setActionCommand("Red Component Button");
    componentMenu.add(red);
    // Green Component Button
    green = new JCheckBoxMenuItem("Green Component");
    green.setActionCommand("Green Component Button");
    componentMenu.add(green);
    // Blue Component Button
    blue = new JCheckBoxMenuItem("Blue Component");
    blue.setActionCommand("Blue Component Button");
    componentMenu.add(blue);
    // Value Component Button
    value = new JCheckBoxMenuItem("Value Component");
    value.setActionCommand("Value Component Button");
    componentMenu.add(value);
    // Intensity Component Button
    intensity = new JCheckBoxMenuItem("Intensity Component");
    intensity.setActionCommand("Intensity Component Button");
    componentMenu.add(intensity);
    // Luma Component Button
    luma = new JCheckBoxMenuItem("Luma Component");
    luma.setActionCommand("Luma Component Button");
    componentMenu.add(luma);
    topMenuBar.add(componentMenu);

    // Flip Menu
    flipMenu = new JMenu("Flip");
    // Vertical Button
    vertical = new JCheckBoxMenuItem("Vertical");
    vertical.setActionCommand("Vertical Button");
    flipMenu.add(vertical);
    // Horizontal Button
    horizontal = new JCheckBoxMenuItem("Horizontal");
    horizontal.setActionCommand("Horizontal Button");
    flipMenu.add(horizontal);
    topMenuBar.add(flipMenu);

    // Brighten Button
    // TODO figure out this functionality
    brighten = new JMenuItem("Brighten");
    brighten.setActionCommand("Brighten");
//    NumberFormat brightenFormat = NumberFormat.getIntegerInstance();
//    brightenInputField = new JFormattedTextField(brightenFormat);
//    brightenInputField.setName("Enter a value between (-250, 250) to brighten/darken the image: ");
//    brightenInputField.setColumns(3);
//    brighten.add(brightenInputField);
    topMenuBar.add(brighten);

    // TODO Adding an image to the center of the screen
    imagePanel = new JPanel();
    imagePanel.setBackground(Color.LIGHT_GRAY);
    image = new JLabel();
    imagePanel.add(image);
    imageMessage = new JLabel("Load an image using the Load New... button below.");
    imagePanel.add(imageMessage);
    this.add(imagePanel, BorderLayout.CENTER);

    // TODO Adding the histogram visualization to the right side of the screen
    histogram =  new JLabel("Histogram of RBG Value Distribution");
    histogram.setBackground(Color.LIGHT_GRAY);
    this.add(histogram, BorderLayout.LINE_END);

    // TODO Adding buttons to the bottom of the screen
    bottomMenuBar = new JMenuBar();
    this.add(bottomMenuBar, BorderLayout.PAGE_END);
    // Load Button
    load = new JButton("Load New...");
    load.setActionCommand("Load");
    bottomMenuBar.add(load);
    // Save Button
    save = new JButton("Save As...");
    save.setActionCommand("Save");
    bottomMenuBar.add(save);
    // Exit Button
    exit = new JButton("EXIT");
    exit.setActionCommand("Exit");
    bottomMenuBar.add(exit);

    pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    blur.addActionListener(evt -> features.filter("blur"));
    sharpen.addActionListener(evt -> features.filter("sharpen"));
    sepia.addActionListener(evt -> features.transform("sepia"));
    greyscale.addActionListener(evt -> features.transform("greyscale"));
    red.addActionListener(evt -> features.component("red"));
    green.addActionListener(evt -> features.component("green"));
    blue.addActionListener(evt -> features.component("blue"));
    value.addActionListener(evt -> features.component("value"));
    intensity.addActionListener(evt -> features.component("intensity"));
    luma.addActionListener(evt -> features.component("luma"));
    vertical.addActionListener(evt -> features.flip("vertical"));
    horizontal.addActionListener(evt -> features.flip("horizontal"));
    brighten.addActionListener(evt -> features.brighten(getBrightnessIncrement()));
    load.addActionListener(evt -> features.load(loadImage()));
    save.addActionListener(evt -> features.save(savePath()));
    exit.addActionListener(evt -> features.exit());
  }

  // load file path viz. retrieve string of file path from user clicks.
  private String loadImage() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Images", "jpg", "ppm", "jpeg", "bmp", "png");
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(GUIView.this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();
      try {
        BufferedImage newImage = ImageIO.read(file);
        image.setIcon(new ImageIcon(newImage));
        this.repaint();
        System.out.print("loadPath:" + filePath + "\n");
      } catch (IOException e) {
        System.out.print("File loading error");
      }
      return filePath;
    }
    System.out.print("loadPath: File path not retrieved\n");
    return "File path not retrieved";
  }

  // save file path viz. retrieve string of file path from user clicks.
  private String savePath() {
    final JFileChooser fileChooser = new JFileChooser(".");
    int returnValue = fileChooser.showSaveDialog(GUIView.this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    }
    return "";
  }

  // getting the brightness increment value from user input.
  private int getBrightnessIncrement() {
    return ((Number) brightenInputField.getValue()).intValue();
  }

  @Override
  public void displayImage(BufferedImage newImage) {
    image.setIcon(new ImageIcon(newImage));
//    this.image = new JLabel(new ImageIcon(image));
//    imageScrollPane = new JScrollPane(this.image);
//    this.add(imageScrollPane, BorderLayout.CENTER);
//    this.add(this.image, BorderLayout.CENTER);
    System.out.print("displayImage:" + loadImage() + "\n");
  }

  @Override
  public void displayHistogram(List<List<Integer>> lists) {

  }

  @Override
  public void displayMessage(String message) {

  }

  @Override
  public void resetFocus() {

  }
}
