import model.ImageProcessingModel;
import model.Model;
import org.junit.Before;
import org.junit.Test;

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

  @Test(expected = NullPointerException.class)
  public void nullFileName() {
    new Model(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void fileNameDoesntExist() {
    new Model("koala");
  }

}
