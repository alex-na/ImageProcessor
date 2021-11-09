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
    this.testModel.load("images/dumby.ppm", "dumby");
    this.testModel.load("images/dumby.png", "dumbyPNG");
    this.testModel.load("images/bunny.jpeg", "bunny");
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
  public void add0toJPEG() {
    this.testModel.brightenImage(0, "bunny", "bunnyBrighten");
    ImageState dumbyBrighten = this.testModel.getImage("bunnyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }
    assertEquals(testModel.getImage("bunnyBrighten"),
            testModel.getImage("bunny"));
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
    this.testModel.brightenImage(40, "dumbyPNG", "dumbyPNGBrighten");
    Image dumbyPNGBrighten = this.testModel.getImage("dumbyPNGBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyPNGBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyPNGBrighten.getImageWidth(); col++) {
        sb1.append(dumbyPNGBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyPNGBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyPNGBrighten.getPixelAt(row, col).getBlue()).append(" ");
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
    this.testModel.brightenImage(-1000, "dumbyPNG", "dumbyPNGBrighten");
    Image dumbyPNGBrighten = this.testModel.getImage("dumbyPNGBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyPNGBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyPNGBrighten.getImageWidth(); col++) {
        sb1.append(dumbyPNGBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyPNGBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyPNGBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 "
            + "0 0 0 0 0 0 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

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
  public void vertFlipJPEG() {
    this.testModel.flipImage("vertical", "bunny", "bunnyVert");
    ImageState bunnyVert = this.testModel.getImage("bunnyVert");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < bunnyVert.getImageHeight(); row++) {
      for (int col = 0; col < bunnyVert.getImageWidth(); col++) {
        sb1.append(bunnyVert.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(bunnyVert.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(bunnyVert.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState bunny = this.testModel.getImage("bunny");
    for (int row = 0; row < bunny.getImageHeight(); row++) {
      int height = bunny.getImageHeight();
      for (int col = 0; col < bunny.getImageWidth(); col++) {
        int width = bunny.getImageWidth();
        sb2.append(bunny.getPixelAt
                (row, width - col - 1).getRed()).append(" ");
        sb2.append(bunny.getPixelAt
                (row, width - col - 1).getGreen()).append(" ");
        sb2.append(bunny.getPixelAt
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
  public void horizontalFlipJPEG() {
    this.testModel.flipImage("horizontal", "bunny", "bunnyHorizontal");
    ImageState bunnyHorizontal = this.testModel.getImage("bunnyHorizontal");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < bunnyHorizontal.getImageHeight(); row++) {
      for (int col = 0; col < bunnyHorizontal.getImageWidth(); col++) {
        sb1.append(bunnyHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(bunnyHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(bunnyHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState bunny = this.testModel.getImage("bunny");
    for (int row = 0; row < bunny.getImageHeight(); row++) {
      int height = bunny.getImageHeight();
      for (int col = 0; col < bunny.getImageWidth(); col++) {
        int width = bunny.getImageWidth();
        sb2.append(bunny.getPixelAt
                (height - row - 1, col).getRed()).append(" ");
        sb2.append(bunny.getPixelAt
                (height - row - 1, col).getGreen()).append(" ");
        sb2.append(bunny.getPixelAt
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
  public void doubleHorizontalFlipJPEG() {
    this.testModel.flipImage("horizontal", "bunny", "bunnyHorizontal");
    this.testModel.flipImage("horizontal", "bunnyHorizontal", "doubleFlip");
    ImageState doubleFlip = this.testModel.getImage("doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < doubleFlip.getImageHeight(); row++) {
      for (int col = 0; col < doubleFlip.getImageWidth(); col++) {
        sb1.append(doubleFlip.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(doubleFlip.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(doubleFlip.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState bunny = this.testModel.getImage("bunny");
    for (int row = 0; row < bunny.getImageHeight(); row++) {
      int height = bunny.getImageHeight();
      for (int col = 0; col < bunny.getImageWidth(); col++) {
        int width = bunny.getImageWidth();
        sb2.append(bunny.getPixelAt
                (row, col).getRed()).append(" ");
        sb2.append(bunny.getPixelAt
                (row, col).getGreen()).append(" ");
        sb2.append(bunny.getPixelAt
                (row, col).getBlue()).append(" ");
      }
    }
    assertEquals(sb1.toString(), sb2.toString());
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
  public void doubleVerticalFlipJPEG() {
    this.testModel.flipImage("vertical", "bunny", "bunnyVert");
    this.testModel.flipImage("vertical", "bunnyVert", "doubleFlip");
    ImageState doubleFlip = this.testModel.getImage("doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < doubleFlip.getImageHeight(); row++) {
      for (int col = 0; col < doubleFlip.getImageWidth(); col++) {
        sb1.append(doubleFlip.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(doubleFlip.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(doubleFlip.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState bunny = this.testModel.getImage("bunny");
    for (int row = 0; row < bunny.getImageHeight(); row++) {
      int height = bunny.getImageHeight();
      for (int col = 0; col < bunny.getImageWidth(); col++) {
        int width = bunny.getImageWidth();
        sb2.append(bunny.getPixelAt
                (row, col).getRed()).append(" ");
        sb2.append(bunny.getPixelAt
                (row, col).getGreen()).append(" ");
        sb2.append(bunny.getPixelAt
                (row, col).getBlue()).append(" ");
      }
    }
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

  // Load tests
  @Test(expected = IllegalArgumentException.class)
  public void nullFileName() throws IOException {
    this.testModel.load(null, "imageName");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidFileName() throws IOException {
    this.testModel.load("notARealFileName", "imageName");
  }

  @Test
  public void loadDumby() throws IOException {
    //in init(), the image "dumbyImage" has not yet been loaded into the model.
    this.testModel.load("images/dumby.ppm", "dumbyImage");

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("dumbyImage"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyImage"); col++) {
        sb1.append(testModel.getPixelAt("dumbyImage", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyImage", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyImage", row, col).getBlue()).append(" ");
      }
    }

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

    Image dumbyImageExpected = new PixelImage(dumbyImage);
    for (int row = 0; row < dumbyImageExpected.getImageHeight(); row++) {
      for (int col = 0; col < dumbyImageExpected.getImageWidth(); col++) {
        sb2.append(dumbyImageExpected.getPixelAt(row, col).getRed()).append(" ");
        sb2.append(dumbyImageExpected.getPixelAt(row, col).getGreen()).append(" ");
        sb2.append(dumbyImageExpected.getPixelAt(row, col).getBlue()).append(" ");
      }
    }
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
    this.testModel.transformImage("sepia", "dumbyPNG", "dumbyPNGSepia");
    Image dumbyPNGSepia = this.testModel.getImage("dumbyPNGSepia");
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