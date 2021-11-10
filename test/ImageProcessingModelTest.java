import java.awt.*;
import java.io.IOException;

import model.ImageProcessingModel;
import model.Model;
import util.image.ImageState;
import util.image.Image;
import util.image.PixelImage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class utilized for testing the ImageProcessingModel class.
 */
public class ImageProcessingModelTest {
  ImageProcessingModel testModel;

  @Before
  public void init() throws IOException {
    this.testModel = new Model();
    Color[][] dumbyImage = {
            {new Color(0, 0, 0),
                    new Color(255, 255, 255), new Color(255, 255, 255)},
            {new Color(5, 5, 5),
                    new Color(250, 250, 250), new Color(250, 250, 250)},
            {new Color(100, 100, 100),
                    new Color(100, 100, 100), new Color(100, 100, 100)},
            {new Color(250, 250, 250),
                    new Color(5, 5, 5), new Color(5, 5, 5)},
            {new Color(255, 255, 255),
                    new Color(0, 0, 0), new Color(0, 0, 0)}};

    Color[][] colorfulImage = {
            {new Color(123, 92, 23),
                    new Color(25, 215, 205), new Color(215, 205, 21)},
            {new Color(0, 34, 1),
                    new Color(0, 252, 50), new Color(20, 50, 250)},
            {new Color(111, 16, 100),
                    new Color(16, 200, 15), new Color(11, 240, 50)},
            {new Color(255, 0, 0),
                    new Color(0, 255, 0), new Color(111, 24, 5)},
            {new Color(2, 90, 195),
                    new Color(100, 60, 0), new Color(80, 20, 100)}};

    this.testModel.load("dumby", new PixelImage(dumbyImage));
  }


  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMap() {
    this.testModel.brightenImage(10, "notAnImage", "notAnImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void ImageInMapIsNull() {
    this.testModel.brightenImage(10, "nullImage", "nullImage2");
  }


  @Test
  public void add0() {
    this.testModel.brightenImage(0, "dumby", "dumbyBrighten");
    ImageState dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 255 255 255 255 255 255 "
            + "5 5 5 250 250 250 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 5 5 5 5 5 5 "
            + "255 255 255 0 0 0 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MaximumRBGComponentIs255() {
    this.testModel.brightenImage(10, "dumby", "dumbyBrighten");
    Image dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("10 10 10 255 255 255 255 255 255"
            + " 15 15 15 255 255 255 255 255 255"
            + " 110 110 110 110 110 110 110 110 110"
            + " 255 255 255 15 15 15 15 15 15"
            + " 255 255 255 10 10 10 10 10 10 ");

    assertEquals(sb1.toString(), sb2.toString());
  }


  @Test
  public void MaximumRBGComponentIs255PNG() {
    this.testModel.brightenImage(40, "dumby", "dumbyBrighten");
    Image dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("40 40 40 255 255 255 255 255 255 "
            + "45 45 45 255 255 255 255 255 255 "
            + "140 140 140 140 140 140 140 140 140 "
            + "255 255 255 45 45 45 45 45 45 "
            + "255 255 255 40 40 40 40 40 40 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MinimumRBGComponentIs0() {
    this.testModel.brightenImage(-40, "dumby", "dumbyDarken");
    Image dumbyDarken = this.testModel.getImage("dumbyDarken");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyDarken.getImageHeight(); row++) {
      for (int col = 0; col < dumbyDarken.getImageWidth(); col++) {
        sb1.append(dumbyDarken.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 215 215 215 215 215 215"
            + " 0 0 0 210 210 210 210 210 210"
            + " 60 60 60 60 60 60 60 60 60"
            + " 210 210 210 0 0 0 0 0 0"
            + " 215 215 215 0 0 0 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MinimumRBGComponentIs255PNG() {
    this.testModel.brightenImage(-1000, "dumby", "dumbyDarken");
    Image dumbyDarken = this.testModel.getImage("dumbyDarken");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyDarken.getImageHeight(); row++) {
      for (int col = 0; col < dumbyDarken.getImageWidth(); col++) {
        sb1.append(dumbyDarken.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  //Tests the Flip method
  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMapFlip() {
    this.testModel.flipImage("vertical", "notAnImage", "notAnImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void ImageInMapIsNullFlip() {
    this.testModel.flipImage("horizontal", "nullImage", "nullImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void axisIsInvalid() {
    this.testModel.flipImage("fff", "dumby", "dumby2");
  }

  @Test
  public void vertFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    ImageState dumbyVert = this.testModel.getImage("dumbyVert");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyVert.getImageHeight(); row++) {
      for (int col = 0; col < dumbyVert.getImageWidth(); col++) {
        sb1.append(dumbyVert.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyVert.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyVert.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState dumby = this.testModel.getImage("dumby");
    for (int row = 0; row < dumby.getImageHeight(); row++) {
      int height = dumby.getImageHeight();
      for (int col = 0; col < dumby.getImageWidth(); col++) {
        int width = dumby.getImageWidth();
        sb2.append(dumby.getPixelAt
                (row, width - col - 1).getRed()).append(" ");
        sb2.append(dumby.getPixelAt
                (row, width - col - 1).getGreen()).append(" ");
        sb2.append(dumby.getPixelAt
                (row, width - col - 1).getBlue()).append(" ");
      }
    }
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void horizontalFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    ImageState dumbyHorizontal = this.testModel.getImage("dumbyHorizontal");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyHorizontal.getImageHeight(); row++) {
      for (int col = 0; col < dumbyHorizontal.getImageWidth(); col++) {
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState dumby = this.testModel.getImage("dumby");
    for (int row = 0; row < dumby.getImageHeight(); row++) {
      int height = dumby.getImageHeight();
      for (int col = 0; col < dumby.getImageWidth(); col++) {
        int width = dumby.getImageWidth();
        sb2.append(dumby.getPixelAt
                (height - row - 1, col).getRed()).append(" ");
        sb2.append(dumby.getPixelAt
                (height - row - 1, col).getGreen()).append(" ");
        sb2.append(dumby.getPixelAt
                (height - row - 1, col).getBlue()).append(" ");
      }
    }
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void doubleHorizontalFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    ImageState dumbyHorizontal = this.testModel.getImage("dumbyHorizontal");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    for (int row = 0; row < dumbyHorizontal.getImageHeight(); row++) {
      for (int col = 0; col < dumbyHorizontal.getImageWidth(); col++) {
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("255 255 255 0 0 0 0 0 0" +
            " 250 250 250 5 5 5 5 5 5" +
            " 100 100 100 100 100 100 100 100 100" +
            " 5 5 5 250 250 250 250 250 250" +
            " 0 0 0 255 255 255 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());

    this.testModel.flipImage("horizontal", "dumbyHorizontal",
            "dumbyHorizontalHorizontal");
    ImageState dumbyHorizontalHorizontal =
            this.testModel.getImage("dumbyHorizontalHorizontal");

    for (int row = 0; row < dumbyHorizontalHorizontal.getImageHeight(); ++row) {
      for (int col = 0; col < dumbyHorizontalHorizontal.getImageWidth(); ++col) {
        sb3.append(dumbyHorizontalHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb3.append(dumbyHorizontalHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb3.append(dumbyHorizontalHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb4.append("0 0 0 255 255 255 255 255 255 "
            + "5 5 5 250 250 250 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 5 5 5 5 5 5 "
            + "255 255 255 0 0 0 0 0 0 ");
    assertEquals(sb3.toString(), sb4.toString());
  }

  @Test
  public void doubleVertFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    this.testModel.flipImage("vertical", "dumbyVert", "doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("doubleFlip"); row++) {
      for (int col = 0; col < testModel.getImageWidth("doubleFlip"); col++) {
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getBlue()).append(" ");
      }
    }
    sb2.append("0 0 0 255 255 255 255 255 255 "
            + "5 5 5 250 250 250 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 5 5 5 5 5 5 "
            + "255 255 255 0 0 0 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void verticalThenHorizontalFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    this.testModel.flipImage("horizontal", "dumbyVert", "doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("doubleFlip"); row++) {
      for (int col = 0; col < testModel.getImageWidth("doubleFlip"); col++) {
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getBlue()).append(" ");
      }
    }


    sb2.append("0 0 0 0 0 0 255 255 255 "
            + "5 5 5 5 5 5 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 250 250 250 5 5 5 "
            + "255 255 255 255 255 255 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }


  @Test
  public void horizontalThenVertFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    this.testModel.flipImage("vertical", "dumbyHorizontal", "doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("doubleFlip"); row++) {
      for (int col = 0; col < testModel.getImageWidth("doubleFlip"); col++) {
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getBlue()).append(" ");
      }
    }


    sb2.append("0 0 0 0 0 0 255 255 255 "
            + "5 5 5 5 5 5 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 250 250 250 5 5 5 "
            + "255 255 255 255 255 255 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  //Greyscale tests
//  @Override
//  public void displayGreyscale(String component, String imageName, String desiredName)
//          throws IllegalArgumentException {
//    validNames(imageName, desiredName);
//    if (!(component.equals("red") || component.equals("green")
//            || component.equals("blue")
//            || component.equals("value")
//            || component.equals("intensity")
//            || component.equals("luma"))) {
//      throw new IllegalArgumentException("The given component is invalid.");
//    }
//    Color[][] greyscale = getImage(imageName).displayGreyscale(component);
//    loadMap.put(desiredName, new PixelImage(greyscale));
//  }
  @Test(expected = IllegalArgumentException.class)
  public void invalidComponent() {
    testModel.displayGreyscale("invalid", "dumby", "dumbyGreyscale");
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void nullComponent() {
//    testModel.displayGreyscale(null, "dumby", "dumbyGreyscale");
//  }


  // Load tests
  @Test(expected = IllegalArgumentException.class)
  public void nullImageName() {
    Color[][] colorfulImage = {
            {new Color(123, 92, 23),
                    new Color(25, 215, 205), new Color(215, 205, 21)},
            {new Color(0, 34, 1),
                    new Color(0, 252, 50), new Color(20, 50, 250)},
            {new Color(111, 16, 100),
                    new Color(16, 200, 15), new Color(11, 240, 50)},
            {new Color(255, 0, 0),
                    new Color(0, 255, 0), new Color(111, 24, 5)},
            {new Color(2, 90, 195),
                    new Color(100, 60, 0), new Color(80, 20, 100)}};

    this.testModel.load(null, new PixelImage(colorfulImage));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidImage() throws IOException {
    this.testModel.load("notARealFileName", null);
  }

  @Test
  public void loadDumby() {
    //in init(), the image "dumbyImage" has not yet been loaded into the model.
    Color[][] dumbyImageColor = {
            {new Color(11, 55, 223),
                    new Color(95, 218, 5), new Color(123, 25, 255)},
            {new Color(0, 255, 255),
                    new Color(20, 70, 20), new Color(20, 20, 220)},
            {new Color(150, 200, 70),
                    new Color(10, 180, 110), new Color(110, 110, 170)},
            {new Color(255, 20, 255),
                    new Color(11, 23, 55), new Color(213, 5, 5)},
            {new Color(255, 255, 255),
                    new Color(0, 0, 0), new Color(9, 0, 9)}};
    this.testModel.load("dumbyImageColor", new PixelImage(dumbyImageColor));

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("dumbyImageColor"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyImageColor"); col++) {
        sb1.append(testModel.getPixelAt("dumbyImageColor", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyImageColor", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyImageColor", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("11 55 223 95 218 5 123 25 255" +
            " 0 255 255 20 70 20 20 20 220 150" +
            " 200 70 10 180 110 110 110 170 255" +
            " 20 255 11 23 55 213 5 5 255 255" +
            " 255 0 0 0 9 0 9 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  // Tests for sepia
  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMapTransformation() {
    this.testModel.transformImage("sepia", "notAnImage", "notAnImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void ImageInMapIsNullTransformation() {
    this.testModel.transformImage("sepia", "nullImage", "nullImage2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTransformationType() {
    this.testModel.transformImage("invalid", "nullImage", "nullImage2");
  }

  @Test
  public void sepiaFilter() {
    this.testModel.transformImage("sepia", "dumby", "dumbySepia");
    Image dumbyPNGSepia = this.testModel.getImage("dumbySepia");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyPNGSepia.getImageHeight(); row++) {
      for (int col = 0; col < dumbyPNGSepia.getImageWidth(); col++) {
        sb1.append(dumbyPNGSepia.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyPNGSepia.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyPNGSepia.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("40 40 40 255 255 255 255 255 255 "
            + "45 45 45 255 255 255 255 255 255 "
            + "140 140 140 140 140 140 140 140 140 "
            + "255 255 255 45 45 45 45 45 45 "
            + "255 255 255 40 40 40 40 40 40 ");

    assertEquals(sb1.toString(), sb2.toString());

  }

}