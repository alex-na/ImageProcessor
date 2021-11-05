package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import model.image.Image;
import model.image.PixelImage;
import model.pixel.PixelImpl;

/**
 * This class contains utility methods to read and write to a PPM image from file.
 */
public class ImageUtil {

  /**
   * Reading any file into the program.
   *
   * @param filePath the location of the file
   * @return a BufferedImage reading the filePath
   * @throws IOException if the file could not be read
   */
  public static BufferedImage readFile(File filePath) throws IOException {
    try {
      BufferedImage image = ImageIO.read(filePath);
      return image;
    }
    catch (IOException e) {
      throw new IOException("File was not able to be read.");
    }
  }

  /**
   * Writing an Image to a file.
   *
   * @param image the iamge to be written
   * @param filePath the desired file path to store this image.
   */
  public static void writeFile(BufferedImage image, String filePath) {
    File file = new File(filePath);
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException when the given filename is an invalid path to an image file.
   */
  public static Image readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;
    PixelImpl[][] pixelMatrix = null;

    try {
      sc = new Scanner(new FileInputStream(filename));

      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }

      //now set up the scanner to read from the string we just built
      sc = new Scanner(builder.toString());

      String token;

      token = sc.next();
      if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      }
      int width = sc.nextInt();
      //System.out.println("Width of image: " + width);
      int height = sc.nextInt();
      //System.out.println("Height of image: " + height);
      int maxValue = sc.nextInt();

      pixelMatrix = new PixelImpl[height][width];

      //System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);
      for (int j = 0; j < height; j++) {
        for (int i = 0; i < width; i++) {

          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          pixelMatrix[j][i] = new PixelImpl(r, g, b);

          //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    return new PixelImage(pixelMatrix);
  }

  /**
   * Writing a 2D array of Pixels to a PPM file.
   *
   * @param filepath the file path of the image
   * @param image    the image to be written
   * @throws IllegalArgumentException if inputs are null
   */
  public static void writePPM(String filepath, Image image) throws IllegalArgumentException {
    if (filepath == null || image == null) {
      throw new IllegalArgumentException("filepath and/or given image are invalid");
    }

    File saveFile = new File(filepath);
    FileOutputStream saveFileOutputStream = null;

    try {
      saveFileOutputStream = new FileOutputStream(saveFile);

      StringBuilder sb = new StringBuilder();

      sb.append("P3\n");
      sb.append(image.getWidth() + " ");
      sb.append(image.getHeight() + "\n");
      sb.append("255\n");

      for (int j = 0; j < image.getHeight(); j++) {
        for (int i = 0; i < image.getWidth(); i++) {
          sb.append(String.format("%d\n%d\n%d\n",
              image.getImage()[j][i].getRed(),
              image.getImage()[j][i].getGreen(),
              image.getImage()[j][i].getBlue()));
        }
      }

      byte[] strToBytes = sb.toString().stripTrailing().getBytes();

      try {
        saveFileOutputStream.write(strToBytes);
      } catch (IOException e) {
        throw new IllegalStateException("Cannot write to file.");
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filepath + " not found!");
    }
  }
}


