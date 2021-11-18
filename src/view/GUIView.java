package view;

import controller.Features;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUIView extends JFrame implements ImageProcessingGUIView {

  private JMenuBar menuBar;
  private JMenu filterMenu;
  private JMenu transformMenu;
  private JMenu componentMenu;
  private JMenu flipMenu;
  private JMenu brightenMenu;
  private JLabel histogram;
  private JScrollPane mainScrollPane;
  private JButton exit;
  private JButton save;
  private JButton load;

  public GUIView() {
    super();

    setTitle("Image Processor");
    setSize(500,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setting layout to BorderLayout
    this.setLayout(new BorderLayout());
    // adding scroll pane
    //mainScrollPane = new JScrollPane();
    //this.add(mainScrollPane);

    // Menu Bar
    menuBar = new JMenuBar();

    // Filter Menu
    filterMenu = new JMenu("Filter");
    // Blur Button
    JMenuItem blur = new JCheckBoxMenuItem("Blur");
    blur.setActionCommand("Blur Button");
    filterMenu.add(blur);
    // Sharpen Button
    JMenuItem sharpen = new JCheckBoxMenuItem("Sharpen");
    sharpen.setActionCommand("Greyscale Button");
    filterMenu.add(sharpen);
    menuBar.add(filterMenu, BorderLayout.LINE_START);

    // Component Menu
    componentMenu = new JMenu("Component");
    // Red Component Button
    JMenuItem red = new JCheckBoxMenuItem("Red Component");
    red.setActionCommand("Red Component Button");
    componentMenu.add(red);
    // Green Component Button
    JMenuItem green = new JCheckBoxMenuItem("Green Component");
    green.setActionCommand("Green Component Button");
    componentMenu.add(green);
    // Blue Component Button
    JMenuItem blue = new JCheckBoxMenuItem("Blue Component");
    blue.setActionCommand("Blue Component Button");
    componentMenu.add(blue);
    // Value Component Button
    JMenuItem value = new JCheckBoxMenuItem("Value Component");
    value.setActionCommand("Value Component Button");
    componentMenu.add(value);
    // Intensity Component Button
    JMenuItem intensity = new JCheckBoxMenuItem("Intensity Component");
    intensity.setActionCommand("Intensity Component Button");
    componentMenu.add(intensity);
    // Luma Component Button
    JMenuItem luma = new JCheckBoxMenuItem("Luma Component");
    luma.setActionCommand("Luma Component Button");
    componentMenu.add(luma);
    menuBar.add(componentMenu, BorderLayout.LINE_START);

    // Transformation Menu
    transformMenu = new JMenu("Transformation");
    // Sepia Button
    JMenuItem sepia = new JCheckBoxMenuItem("Sepia");
    sepia.setActionCommand("Sepia Button");
    transformMenu.add(sepia);
    // Greyscale Button
    JMenuItem greyscale = new JCheckBoxMenuItem("Greyscale");
    greyscale.setActionCommand("Greyscale Button");
    transformMenu.add(greyscale);
    menuBar.add(transformMenu, BorderLayout.LINE_START);

    // Flip Menu
    flipMenu = new JMenu("Flip");
    // Vertical Button
    JMenuItem vertical = new JCheckBoxMenuItem("Vertical");
    vertical.setActionCommand("Vertical Button");
    flipMenu.add(vertical);
    // Horizontal Button
    JMenuItem horizontal = new JCheckBoxMenuItem("Horizontal");
    horizontal.setActionCommand("Horizontal Button");
    flipMenu.add(horizontal);
    menuBar.add(flipMenu, BorderLayout.LINE_START);

    // Brighten Button
    brightenMenu = new JMenu("Brighten");
    //TODO figure out this functionality
    menuBar.add(brightenMenu, BorderLayout.LINE_START);

  }

  @Override
  public void setImage() {

  }

  @Override
  public void displayHistogram() {

  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void addFeatures(Features features) {

  }

  @Override
  public void displayMessage(String message) {

  }
}
