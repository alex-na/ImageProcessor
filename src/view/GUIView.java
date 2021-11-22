package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import util.image.Image;

public class GUIView extends JFrame implements ImageProcessingGUIView {

  private JMenuBar topMenuBar;
  private JMenu filterMenu;
  private JMenu transformMenu;
  private JMenu componentMenu;
  private JMenu flipMenu;
  private JMenuItem brighten;

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
  private JMenuItem imageMessage;

  private JLabel image;
  private JLabel histogram;

  private JMenuBar bottomMenuBar;
  private JButton exit;
  private JButton save;
  private JButton load;

  public GUIView() {

    setTitle("Image Processor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // setting layout to BorderLayout
    this.setLayout(new BorderLayout());

    // Menu Bar
    topMenuBar = new JMenuBar();
    this.add(topMenuBar, BorderLayout.PAGE_START);

    imageMessage = new JMenuItem("Edit Image:");
    topMenuBar.add(imageMessage);

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
    brighten = new JMenuItem("Brighten");
    //TODO figure out this functionality
    topMenuBar.add(brighten);

    //TODO Adding an image to the center of the screen
    this.add(image, BorderLayout.CENTER);
//    BufferedImage myPicture = null;
//    try {
//      myPicture = ImageIO.read(new File("res/bunny.jpeg"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//    this.add(picLabel, BorderLayout.CENTER);

    //TODO Adding the histogram visualization to the right side of the screen
    histogram =  new JLabel("Histogram");
    this.add(histogram, BorderLayout.LINE_END);

    //TODO Adding buttons to the bottom of the screen
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
    //TODO update brighten
    brighten.addActionListener(evt -> features.brighten(0));
    //TODO update load/save
//    load.addActionListener(evt -> features.load());
//    save.addActionListener(evt -> features.save());
    exit.addActionListener(evt -> features.exit());
  }

  @Override
  public void displayImage(BufferedImage image) {
    this.image = new JLabel(new ImageIcon(image));
  }

  @Override
  public void displayHistogram(BufferedImage image) {

  }

  @Override
  public void displayMessage(String message) {

  }

  @Override
  public void resetFocus() {

  }
}
