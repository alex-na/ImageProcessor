import java.util.HashMap;
import model.ImageProcessingModel;
import model.Model;
import model.image.Image;
import model.image.PixelImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class utilized for testing the ImageProcessingModel class.
 */
public class ImageProcessingModelTest {

  ImageProcessingModel koalaModel;
  ImageProcessingModel testModel;

  @Before
  public void init() {
    this.testModel = new Model();
    Pixel[][] dumbyPicture = new Pixel[][]{
        {new PixelImpl(0, 0, 0),
            new PixelImpl(255, 255, 255),
            new PixelImpl(255, 255, 255)},
        {new PixelImpl(5, 5, 5),
            new PixelImpl(250, 250, 250),
            new PixelImpl(250, 250, 250)},
        {new PixelImpl(100, 100, 100),
            new PixelImpl(100, 100, 100),
            new PixelImpl(100, 100, 100)},
        {new PixelImpl(250, 250, 250),
            new PixelImpl(5, 5, 5),
            new PixelImpl(5, 5, 5)},
        {new PixelImpl(255, 255, 255),
            new PixelImpl(0, 0, 0),
            new PixelImpl(0, 0, 0)}};
    this.testModel.getLoadMap().put("dumby", new PixelImage(dumbyPicture));
    this.testModel.getLoadMap().put("nullImage", null);
  }

  @Test


  public void createModel() {
    Model newModel = new Model();
    Assert.assertEquals(newModel.getLoadMap(), new HashMap<>());
  }


  @Test(
          expected = IllegalArgumentException.class
        )


  public void imageNameNotInMap() {
    this.testModel.brightenImage(10, "notAnImage", "notAnImage2");
  }


  @Test(
          expected = IllegalArgumentException.class
        )


  public void ImageInMapIsNull() {
    this.testModel.brightenImage(10, "nullImage", "nullImage2");
  }


  @Test


  public void add0() {
    this.testModel.brightenImage(0, "dumby", "dumbyBrighten");
    Pixel[][] dumbyBrighten = ((Image) this.testModel.getLoadMap().get("dumbyBrighten")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.length; ++row) {
      for (int col = 0; col < dumbyBrighten[0].length; ++col) {
        int var10001 = dumbyBrighten[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyBrighten[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyBrighten[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "0 0 0 255 255 255 255 255 255 5 5 5 250 250 250"
            + " 250 250 250 100 100 100 100 100 100 100"
            + " 100 100 250 250 250 5 5 5 5 5 5 255 255"
            + " 255 0 0 0 0 0 0 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
  }


  @Test


  public void MaximumRBGComponentIs255() {
    this.testModel.brightenImage(10, "dumby", "dumbyBrighten");
    Pixel[][] dumbyBrighten = ((Image) this.testModel.getLoadMap().get("dumbyBrighten")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.length; ++row) {
      for (int col = 0; col < dumbyBrighten[0].length; ++col) {
        int var10001 = dumbyBrighten[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyBrighten[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyBrighten[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "10 10 10 255 255 255 255 255 255 15 15 15 255 255"
            + " 255 255 255 255 110 110 110 110 110 110 110"
            + " 110 110 255 255 255 15 15 15 15 15 15 255"
            + " 255 255 10 10 10 10 10 10 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
  }


  @Test


  public void MinimumRBGComponentIs0() {
    this.testModel.brightenImage(-10, "dumby", "dumbyBrighten");
    Pixel[][] dumbyBrighten = ((Image) this.testModel
        .getLoadMap().get("dumbyBrighten")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.length; ++row) {
      for (int col = 0; col < dumbyBrighten[0].length; ++col) {
        int var10001 = dumbyBrighten[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyBrighten[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyBrighten[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "0 0 0 245 245 245 245 245 245 0 0 0 240 240 240 240"
            + " 240 240 90 90 90 90 90 90 90 90 90 240 240 "
            + "240 0 0 0 0 0 0 245 245 245 0 0 0 0 0 0 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
  }


  @Test(
          expected = IllegalArgumentException.class
        )


  public void imageNameNotInMapFlip() {
    this.testModel.flipImage("vertical", "notAnImage", "notAnImage2");
  }


  @Test(
          expected = IllegalArgumentException.class
        )


  public void ImageInMapIsNullFlip() {
    this.testModel.flipImage("horizontal", "nullImage", "nullImage2");
  }


  @Test(
          expected = IllegalArgumentException.class
        )


  public void axisIsInvalid() {
    this.testModel.flipImage("fff", "dumby", "dumby2");
  }

  @Test
  public void horizontalFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyVert");
    Pixel[][] dumbyVert = ((Image) this.testModel.getLoadMap().get("dumbyVert")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyVert.length; ++row) {
      for (int col = 0; col < dumbyVert[0].length; ++col) {
        int var10001 = dumbyVert[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyVert[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyVert[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "255 255 255 255 255 255 0 0 0 250 250 250 250 250 250"
            + " 5 5 5 100 100 100 100 100 100 100 100 100 5 5 "
            + "5 5 5 5 250 250 250 0 0 0 0 0 0 255 255 255 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
  }


  @Test


  public void vertFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyHorizontal");
    Pixel[][] dumbyHorizontal = ((Image) this.testModel.
        getLoadMap().get("dumbyHorizontal")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyHorizontal.length; ++row) {
      for (int col = 0; col < dumbyHorizontal[0].length; ++col) {
        int var10001 = dumbyHorizontal[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyHorizontal[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyHorizontal[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "255 255 255 0 0 0 0 0 0 250 250 250 5 5 5 5 5 5 100"
            + " 100 100 100 100 100 100 100 100 5 5 5 250 250"
            + " 250 250 250 250 0 0 0 255 255 255 255 255 255 ");
    Assert.assertEquals(sb2.toString(), sb2.toString());
  }


  @Test


  public void doubleHorizontalFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyVert");
    Pixel[][] dumbyVert = ((Image) this.testModel.
        getLoadMap().get("dumbyVert")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    int var10001;
    for (int row = 0; row < dumbyVert.length; row++) {
      for (int col = 0; row < dumbyVert[0].length; col++) {
        var10001 = dumbyVert[row][row].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyVert[row][row].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyVert[row][row].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "255 255 255 255 255 255 0 0 0 250 250 250 250 250 250"
            + " 5 5 5 100 100 100 100 100 100 100 100 100 5 5 5"
            + " 5 5 5 250 250 250 0 0 0 0 0 0 255 255 255 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());

    this.testModel.flipImage("horizontal", "dumbyVert", "dumbyVertVert");
    Pixel[][] dumbyVertVert = ((Image) this.testModel.
        getLoadMap().get("dumbyVertVert")).getImage();

    for (int row = 0; row < dumbyVert.length; ++row) {
      for (int col = 0; col < dumbyVert[0].length; ++col) {
        var10001 = dumbyVertVert[row][col].getRed();
        sb3.append(var10001 + " ");
        var10001 = dumbyVertVert[row][col].getGreen();
        sb3.append(var10001 + " ");
        var10001 = dumbyVertVert[row][col].getBlue();
        sb3.append(var10001 + " ");
      }
    }

    sb4.append(
        "0 0 0 255 255 255 255 255 255 5 5 5 250 250 250"
            + " 250 250 250 100 100 100 100 100 100 100"
            + " 100 100 250 250 250 5 5 5 5 5 5 255 255"
            + " 255 0 0 0 0 0 0 ");
    Assert.assertEquals(sb3.toString(), sb4.toString());
  }


  @Test


  public void doubleVertFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyHorizontal");
    Pixel[][] dumbyHorizontal = ((Image) this.testModel.
        getLoadMap().get("dumbyHorizontal")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    int var10001;
    for (int row = 0; row < dumbyHorizontal.length; ++row) {
      for (int col = 0; row < dumbyHorizontal[0].length; ++row) {
        var10001 = dumbyHorizontal[row][row].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyHorizontal[row][row].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyHorizontal[row][row].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "255 255 255 0 0 0 0 0 0 250 250 250 5 5 5 5 5 5 100"
            + " 100 100 100 100 100 100 100 100 5 5 5 250 250 "
            + "250 250 250 250 0 0 0 255 255 255 255 255 255 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
    this.testModel.flipImage("vertical", "dumbyHorizontal", "dumbyHorizontalVert");
    Pixel[][] dumbyHorizontalVert = ((Image) this.testModel
        .getLoadMap().get("dumbyHorizontalVert")).getImage();

    for (int row = 0; row < dumbyHorizontalVert.length; ++row) {
      for (int col = 0; col < dumbyHorizontalVert[0].length; ++col) {
        var10001 = dumbyHorizontalVert[row][col].getRed();
        sb3.append(var10001 + " ");
        var10001 = dumbyHorizontalVert[row][col].getGreen();
        sb3.append(var10001 + " ");
        var10001 = dumbyHorizontalVert[row][col].getBlue();
        sb3.append(var10001 + " ");
      }
    }

    sb4.append(
        "0 0 0 255 255 255 255 255 255 5 5 5 250 250 250 250 250"
            + " 250 100 100 100 100 100 100 100 100 100 250 250"
            + " 250 5 5 5 5 5 5 255 255 255 0 0 0 0 0 0 ");
    Assert.assertEquals(sb3.toString(), sb4.toString());
  }


  @Test


  public void verticalThenHorizontalFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyHorizontal");
    Pixel[][] dumbyHorizontal = ((Image) this.testModel.
        getLoadMap().get("dumbyHorizontal")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    int var10001;
    for (int row = 0; row < dumbyHorizontal.length; ++row) {
      for (int col = 0; col < dumbyHorizontal[0].length; ++col) {
        var10001 = dumbyHorizontal[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyHorizontal[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyHorizontal[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append("255 255 255 0 0 0 0 0 0 250 250 250 5 5 5"
        + " 5 5 5 100 100 100 100 100 100 100 100 100 5 5"
        + " 5 250 250 250 250 250 250 0 0 0 255 255 255"
        + " 255 255 255 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
    this.testModel.flipImage("horizontal", "dumbyHorizontal", "dumbyHorizontalVert");
    Pixel[][] dumbyHorizontalVert = ((Image) this.
        testModel.getLoadMap().get("dumbyHorizontalVert")).getImage();

    for (int row = 0; row < dumbyHorizontal.length; ++row) {
      for (int col = 0; col < dumbyHorizontal[0].length; ++col) {
        var10001 = dumbyHorizontalVert[row][col].getRed();
        sb3.append(var10001 + " ");
        var10001 = dumbyHorizontalVert[row][col].getGreen();
        sb3.append(var10001 + " ");
        var10001 = dumbyHorizontalVert[row][col].getBlue();
        sb3.append(var10001 + " ");
      }
    }

    sb4.append(
        "0 0 0 0 0 0 255 255 255 5 5 5 5 5 5 250 250"
            + " 250 100 100 100 100 100 100 100 100 100"
            + " 250 250 250 250 250 250 5 5 5 255 255 255"
            + " 255 255 255 0 0 0 ");
    Assert.assertEquals(sb3.toString(), sb4.toString());
  }


  @Test


  public void horizontalThenVertFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyVert");
    Pixel[][] dumbyVert = ((Image) this.
        testModel.getLoadMap().get("dumbyVert")).getImage();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    int var10001;
    for (int row = 0; row < dumbyVert.length; ++row) {
      for (int col = 0; col < dumbyVert[0].length; ++col) {
        var10001 = dumbyVert[row][col].getRed();
        sb1.append(var10001 + " ");
        var10001 = dumbyVert[row][col].getGreen();
        sb1.append(var10001 + " ");
        var10001 = dumbyVert[row][col].getBlue();
        sb1.append(var10001 + " ");
      }
    }

    sb2.append(
        "255 255 255 255 255 255 0 0 0 250 250 250 250"
            + " 250 250 5 5 5 100 100 100 100 100 100 100"
            + " 100 100 5 5 5 5 5 5 250 250 250 0 0 0 0 "
            + "0 0 255 255 255 ");
    Assert.assertEquals(sb1.toString(), sb2.toString());
    this.testModel.flipImage("vertical", "dumbyVert", "dumbyVertHorizontal");
    Pixel[][] dumbyVertHorizontal = ((Image) this.
        testModel.getLoadMap().get("dumbyVertHorizontal")).getImage();

    for (int row = 0; row < dumbyVertHorizontal.length; ++row) {
      for (int col = 0; col < dumbyVertHorizontal[0].length; ++col) {
        var10001 = dumbyVertHorizontal[row][col].getRed();
        sb3.append(var10001 + " ");
        var10001 = dumbyVertHorizontal[row][col].getGreen();
        sb3.append(var10001 + " ");
        var10001 = dumbyVertHorizontal[row][col].getBlue();
        sb3.append(var10001 + " ");
      }
    }

    sb4.append(
        "0 0 0 0 0 0 255 255 255 5 5 5 5 5 5 250"
            + " 250 250 100 100 100 100 100 100"
            + " 100 100 100 250 250 250 250 250 "
            + "250 5 5 5 255 255 255 255 255 255 0 0 0 ");
    Assert.assertEquals(sb3.toString(), sb4.toString());
  }
}