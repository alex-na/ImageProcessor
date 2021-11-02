import model.ImageProcessingModel;
import model.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ImageProcessingModelTest {
  ImageProcessingModel KoalaModel;

  @Before
  public void init() {
    KoalaModel =
        new Model("/Users/ryankii/Downloads/image_processing-main/images/Koala.ppm");
  }

  @Test (expected = NullPointerException.class)
  public void nullFileName() {
    new Model(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void fileNameDoesntExist() {
    new Model("koala");
  }

  // Tests for greyscale method
  @Test
  public void redGreyscaleImage() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.adjustGreyscale("Red").image, KoalaModel.getImage());
      }
    }
  }

  // Tests for greyscale method
  @Test
  public void greenGreyscaleImage() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.adjustGreyscale("Green").image, KoalaModel.getImage());
      }
    }
  }

  // Tests for greyscale method
  @Test
  public void blueGreyscaleImage() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.adjustGreyscale("Blue").image, KoalaModel.getImage());
      }
    }
  }


  // Tests for greyscale method
  @Test
  public void brightenImage() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.brightenImage(10).image, KoalaModel.getImage());
      }
    }
  }

   Tests for flip method
  @Test
  public void flipHorizontally() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {

        assertEquals(KoalaModel.flipImage("vertically").image, KoalaModel.getImage());
      }
    }
  }

  // Tests for flip method
  @Test
  public void flipVertically() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.flipImage("vertically").image, KoalaModel.getImage());
      }
    }
  }

  // Tests for flip method
  @Test
  public void flipHorizontallyVertically() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.flipImage("vertically")..image, KoalaModel.getImage());
      }
    }
  }

  // Tests for flip method
  @Test
  public void flipVerticallyHorizontally() {
    for (int row = 0; row < KoalaModel.getHeight(); row++) {
      for (int col = 0; col < KoalaModel.getWidth(); col++) {
        assertEquals(KoalaModel.flipImage("vertically").flipImage("vertically"), KoalaModel.getImage());
      }
    }
  }


}
