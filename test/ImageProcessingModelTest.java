import java.awt.*;
import java.util.HashMap;
import model.ImageProcessingModel;
import model.Model;
import model.image.Image;
import model.image.ImageState;
import model.image.PixelImage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class utilized for testing the ImageProcessingModel class.
 */
public class ImageProcessingModelTest {
  ImageProcessingModel testModel;

  @Before
  public void init() {
    this.testModel = new Model();
    this.testModel.load("images/dumby.ppm", "dumby");
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

    sb2.append("10 10 10 255 255 255 255 255 255 "
            + "15 15 15 255 255 255 255 255 255 "
            + "110 110 110 110 110 110 110 110 110 "
            + "255 255 255 15 15 15 15 15 15 "
            + "255 255 255 10 10 10 10 10 10 ");
    assertEquals(sb1.toString(), sb2.toString());
  }


  @Test
  public void MinimumRBGComponentIs0() {
    this.testModel.brightenImage(-10, "dumby", "dumbyBrighten");
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
    sb2.append("0 0 0 245 245 245 245 245 245 "
            + "0 0 0 240 240 240 240 240 240 "
            + "90 90 90 90 90 90 90 90 90 "
            + "240 240 240 0 0 0 0 0 0 "
            + "245 245 245 0 0 0 0 0 0 ");
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

    sb2.append("255 255 255 255 255 255 0 0 0 "
            + "250 250 250 250 250 250 5 5 5 "
            + "100 100 100 100 100 100 100 100 100 "
            + "5 5 5 5 5 5 250 250 250 "
            + "0 0 0 0 0 0 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());
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

    sb2.append("255 255 255 0 0 0 0 0 0 "
            + "250 250 250 5 5 5 5 5 5 "
            + "100 100 100 100 100 100 100 100 100 "
            + "5 5 5 250 250 250 250 250 250 "
            + "0 0 0 255 255 255 255 255 255 ");
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

    sb2.append("255 255 255 255 255 255 0 0 0 "
            + "250 250 250 250 250 250 5 5 5 "
            + "100 100 100 100 100 100 100 100 100 "
            + "5 5 5 5 5 5 250 250 250 "
            + "0 0 0 0 0 0 255 255 255 ");
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
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("dumbyVert"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyVert"); col++) {
        sb1.append(testModel.getPixelAt("dumbyVert", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyVert", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyVert", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("255 255 255 0 0 0 0 0 0 "
            + "250 250 250 5 5 5 5 5 5 "
            + "100 100 100 100 100 100 100 100 100 "
            + "5 5 5 250 250 250 250 250 250 "
            + "0 0 0 255 255 255 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());

    this.testModel.flipImage("vertical", "dumbyVert", "dumbyVertVert");

    for (int row = 0; row < testModel.getImageHeight("dumbyVertVert"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyVertVert"); col++) {
        sb3.append(testModel.getPixelAt("dumbyVertVert", row, col).getRed()).append(" ");
        sb3.append(testModel.getPixelAt("dumbyVertVert", row, col).getGreen()).append(" ");
        sb3.append(testModel.getPixelAt("dumbyVertVert", row, col).getBlue()).append(" ");
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
  public void verticalThenHorizontalFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("dumbyVert"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyVert"); col++) {
        sb1.append(testModel.getPixelAt("dumbyVert", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyVert", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyVert", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("255 255 255 0 0 0 0 0 0 "
            + "250 250 250 5 5 5 5 5 5 "
            + "100 100 100 100 100 100 100 100 100 "
            + "5 5 5 250 250 250 250 250 250 "
            + "0 0 0 255 255 255 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());

    this.testModel.flipImage("horizontal", "dumbyVert",
            "dumbyVertHorizontal");

    for (int row = 0; row < testModel.getImageHeight("dumbyVertHorizontal"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyVertHorizontal"); col++) {
        sb3.append(testModel.getPixelAt("dumbyVertHorizontal", row, col).getRed()).append(" ");
        sb3.append(testModel.getPixelAt("dumbyVertHorizontal", row, col).getGreen()).append(" ");
        sb3.append(testModel.getPixelAt("dumbyVertHorizontal", row, col).getBlue()).append(" ");
      }
    }

    sb4.append("0 0 0 0 0 0 255 255 255 "
            + "5 5 5 5 5 5 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 250 250 250 5 5 5 "
            + "255 255 255 255 255 255 0 0 0 ");
    assertEquals(sb3.toString(), sb4.toString());
  }


  @Test
  public void horizontalThenVertFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("dumbyHorizontal"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyHorizontal"); col++) {
        sb1.append(testModel.getPixelAt("dumbyHorizontal", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyHorizontal", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyHorizontal", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("255 255 255 255 255 255 0 0 0 "
            + "250 250 250 250 250 250 5 5 5 "
            + "100 100 100 100 100 100 100 100 100 "
            + "5 5 5 5 5 5 250 250 250 "
            + "0 0 0 0 0 0 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());
    this.testModel.flipImage("vertical", "dumbyHorizontal",
            "dumbyHorizontalVert");

    for (int row = 0; row < testModel.getImageHeight("dumbyHorizontalVert"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyHorizontalVert"); col++) {
        sb3.append(testModel.getPixelAt("dumbyHorizontalVert", row, col).getRed()).append(" ");
        sb3.append(testModel.getPixelAt("dumbyHorizontalVert", row, col).getGreen()).append(" ");
        sb3.append(testModel.getPixelAt("dumbyHorizontalVert", row, col).getBlue()).append(" ");
      }
    }

    sb4.append("0 0 0 0 0 0 255 255 255 "
            + "5 5 5 5 5 5 250 250 250 "
            + "100 100 100 100 100 100 100 100 100 "
            + "250 250 250 250 250 250 5 5 5 "
            + "255 255 255 255 255 255 0 0 0 ");
    assertEquals(sb3.toString(), sb4.toString());
  }

  // Load tests
  @Test (expected = IllegalArgumentException.class)
  public void nullFileName() {
    this.testModel.load(null,"imageName");
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidFileName() {
    this.testModel.load("notARealFileName","imageName");
  }

  @Test
  public void loadDumby() {
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
}