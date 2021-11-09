package util;

import java.awt.*;
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

/**
 * This class contains utility methods to read and write to a PPM image from file.
 */
public class ImageUtil {

  /**
   * Reading an image into the program.
   *
   * @param filePath the file path of the image
   * @return a BufferedImage object
   * @throws IOException if the image could not be loaded
   */
  public static BufferedImage readFile(String filePath) throws IOException {
   BufferedImage image;
    try {
      image = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      throw new IOException("Image could not be loaded.");
    }
    return image;
  }

  /**
   * Writing an image to a file and saving it locally.
   *
   * @param filePath the filePath in which to store this image
   * @param imageName the name of the image
   */
  public static void writeFile(String filePath, String formatName, BufferedImage imageName)
      throws IOException {
    File file = new File(filePath);
    try {
      ImageIO.write(imageName, formatName, file);
    } catch (IOException e) {
      throw new IOException("Could not write the given file.");
    }
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException when the given filename is an invalid path to an image file.
   */
  public static Image readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;
    Color[][] pixelMatrix;

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
      int height = sc.nextInt();
      int maxValue = sc.nextInt();

      pixelMatrix = new Color[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          pixelMatrix[i][j] = new Color(r, g, b);
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
      throw new IllegalArgumentException("filepath and/or given cannot be null.");
    }

    File saveFile = new File(filepath);
    FileOutputStream saveFileOutputStream;

    try {
      saveFileOutputStream = new FileOutputStream(saveFile);

      StringBuilder sb = new StringBuilder();

      sb.append("P3\n");
      sb.append(image.getImageWidth()).append(" ");
      sb.append(image.getImageHeight()).append("\n");
      sb.append("255\n");

      for (int i = 0; i < image.getImageHeight(); i++) {
        for (int j = 0; j < image.getImageWidth(); j++) {
          sb.append(String.format("%d\n%d\n%d\n",
                  image.getPixelAt(i, j).getRed(),
                  image.getPixelAt(i, j).getGreen(),
                  image.getPixelAt(i, j).getBlue()));
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


