package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import model.pixel.Pixel;
import model.pixel.PixelImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static PixelImpl[][] readPPM(String filename) {
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
    return pixelMatrix;
  }

  //demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "images/Koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }

  public static void writePPM(String filepath, Pixel[][] image) {
    if (filepath == null || image == null) {
      throw new IllegalArgumentException("filepath and/or given image are invalid");
    }

    File saveFile = new File(filepath);
    FileOutputStream saveFileOutputStream = null;

    try {
      saveFileOutputStream = new FileOutputStream(saveFile);

      StringBuilder sb = new StringBuilder();

      sb.append(image.length + " ");
      sb.append(image[0].length + " ");
      sb.append("255 ");

      for (int j = 0; j < image.length; j++) {
        for (int i = 0; i < image[0].length; i++) {
          int r = image[j][i].getRed();
          int g = image[j][i].getGreen();
          int b = image[j][i].getBlue();
          sb.append(String.format("%d %d %d ", r, g, b));
        }
      }
      byte[] strToBytes = sb.toString().stripTrailing().getBytes();

      try {
        saveFileOutputStream.write(strToBytes);
      }  catch (IOException e) {
        throw new IllegalStateException("Cannot write to file.");
      }

    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filepath + " not found!");
    }
  }
}


