import controller.commands.Brighten;
import model.ImageProcessingModel;
import model.Model;
import model.image.Image;
import model.image.PixelImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ImageProcessingModelTest {
  ImageProcessingModel KoalaModel;
  ImageProcessingModel testModel;

  @Before
  public void init() {

    testModel = new Model();
    Pixel[][] dumbyPicture = {{new PixelImpl(0, 0, 0), new PixelImpl(255, 255, 255)},
            {new PixelImpl(5, 5, 5), new PixelImpl(250, 250, 250)},
            {new PixelImpl(100, 100, 100), new PixelImpl(100, 100, 100)},
            {new PixelImpl(250, 250, 250), new PixelImpl(5, 5, 5)},
            {new PixelImpl(255, 255, 255), new PixelImpl(0, 0, 0)}};
    testModel.getLoadMap().put("dumby", new PixelImage(dumbyPicture));
    testModel.getLoadMap().put("nullImage", null);
  }

  @Test
  public void createModel() {
    Model newModel = new Model();
    assertEquals(newModel.getLoadMap(), new HashMap<String, Image>());
  }

  // Brighten Image Tests
  @Test (expected = IllegalArgumentException.class)
  public void imageNameNotInMap() {
    testModel.brightenImage(10, "notAnImage", "notAnImage2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void ImageInMapIsNull() {
    testModel.brightenImage(10, "nullImage", "nullImage2");
  }

  @Test
  public void add0() {
    testModel.brightenImage(0, "dumby", "dumbyBrighten");
    Pixel[][] dumbyBrighten = testModel.getLoadMap().get("dumbyBrighten").getImage();

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.length; row++) {
      for (int col = 0; col < dumbyBrighten[0].length; col++) {
        sb1.append(dumbyBrighten[row][col].getRed() + " ");
        sb1.append(dumbyBrighten[row][col].getGreen() + " ");
        sb1.append(dumbyBrighten[row][col].getBlue()+ " ");
      }
    }
    sb2.append("0 0 0 255 255 255 5 5 5 250 250 250 100 100 100"
            + " 100 100 100 250 250 250 5 5 5 255 255 255 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MaximumRBGComponentIs255() {
    testModel.brightenImage(10, "dumby", "dumbyBrighten");
    Pixel[][] dumbyBrighten = testModel.getLoadMap().get("dumbyBrighten").getImage();

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.length; row++) {
      for (int col = 0; col < dumbyBrighten[0].length; col++) {
        sb1.append(dumbyBrighten[row][col].getRed() + " ");
        sb1.append(dumbyBrighten[row][col].getGreen() + " ");
        sb1.append(dumbyBrighten[row][col].getBlue()+ " ");
      }
    }
    sb2.append("10 10 10 255 255 255 15 15 15 255 255 255 110 110 110"
            + " 110 110 110 255 255 255 15 15 15 255 255 255 10 10 10 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MinimumRBGComponentIs0() {
    testModel.brightenImage(-10, "dumby", "dumbyBrighten");
    Pixel[][] dumbyBrighten = testModel.getLoadMap().get("dumbyBrighten").getImage();

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.length; row++) {
      for (int col = 0; col < dumbyBrighten[0].length; col++) {
        sb1.append(dumbyBrighten[row][col].getRed() + " ");
        sb1.append(dumbyBrighten[row][col].getGreen() + " ");
        sb1.append(dumbyBrighten[row][col].getBlue()+ " ");
      }
    }
    sb2.append("0 0 0 245 245 245 0 0 0 240 240 240 90 90 90"
            + " 90 90 90 240 240 240 0 0 0 245 245 245 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  //FlipImage Tests
  @Test (expected = IllegalArgumentException.class)
  public void imageNameNotInMapFlip() {
    testModel.flipImage("vertical", "notAnImage", "notAnImage2");
  }

  @Test (expected = IllegalArgumentException.class)
  public void ImageInMapIsNullFlip() {
    testModel.flipImage("horizontal", "nullImage", "nullImage2");
  }

  @Test
  public void vertFlip() {
    testModel.flipImage("vertical", "dumby", "dumbyVert");
    Pixel[][] dumbyVert = testModel.getLoadMap().get("dumbyVert").getImage();

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyVert.length; row++) {
      for (int col = 0; col < dumbyVert[0].length; col++) {
        sb1.append(dumbyVert[row][col].getRed() + " ");
        sb1.append(dumbyVert[row][col].getGreen() + " ");
        sb1.append(dumbyVert[row][col].getBlue()+ " ");
      }
    }
    sb2.append("0 0 0 5 5 5 100 100 100 250 250 250 255 255 255 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void horizontalFlip() {
    testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    Pixel[][] dumbyHorizontal = testModel.getLoadMap().get("dumbyHorizontal").getImage();

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyHorizontal.length; row++) {
      for (int col = 0; col < dumbyHorizontal[0].length; col++) {
        sb1.append(dumbyHorizontal[row][col].getRed() + " ");
        sb1.append(dumbyHorizontal[row][col].getGreen() + " ");
        sb1.append(dumbyHorizontal[row][col].getBlue()+ " ");
      }
    }
    sb2.append("255 255 255 0 0 0 250 250 250 5 5 5 100 100 100 100"
            + " 100 100 5 5 5 250 250 250 0 0 0 255 255 255 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void vertThenHorizontalFlip() {
    testModel.flipImage("vertical", "dumby", "dumbyVert");
    Pixel[][] dumbyVert = testModel.getLoadMap().get("dumbyVert").getImage();

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    for (int row = 0; row < dumbyVert.length; row++) {
      for (int col = 0; col < dumbyVert[0].length; col++) {
        sb1.append(dumbyVert[row][col].getRed() + " ");
        sb1.append(dumbyVert[row][col].getGreen() + " ");
        sb1.append(dumbyVert[row][col].getBlue()+ " ");
      }
    }
    sb2.append("255 255 255 0 0 0 250 250 250 5 5 5 100 100 100"
            + " 100 100 100 5 5 5 250 250 250 0 0 0 255 255 255 ");

    assertEquals(sb1.toString(), sb2.toString());

    testModel.flipImage("vertical", "dumbyVert", "dumbyVertHorizontal");
    Pixel[][] dumbyVertHorizontal = testModel.getLoadMap().get("dumbyVertHorizontal").getImage();

    for (int row = 0; row < dumbyVert.length; row++) {
      for (int col = 0; col < dumbyVert[0].length; col++) {
        sb3.append(dumbyVertHorizontal[row][col].getRed() + " ");
        sb3.append(dumbyVertHorizontal[row][col].getGreen() + " ");
        sb3.append(dumbyVertHorizontal[row][col].getBlue()+ " ");
      }
    }
    sb4.append("0 0 0 255 255 255 5 5 5 250 250 250 100 100 100 "
            + "100 100 100 250 250 250 5 5 5 255 255 255 0 0 0 ");

    assertEquals(sb3.toString(), sb4.toString());

  }
}
