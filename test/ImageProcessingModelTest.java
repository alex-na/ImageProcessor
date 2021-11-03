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
    Pixel[][] dumbyPicture = {{new PixelImpl(0, 0, 0)},
            {new PixelImpl(5, 5, 5)},
            {new PixelImpl(100, 200, 300)},
            {new PixelImpl(250, 250, 250)},
            {new PixelImpl(255, 255, 255)}};
    testModel.getLoadMap().put("dumbyPicture", new PixelImage(dumbyPicture));
  }

  @Test
  public void createModel() {
    Model newModel = new Model();
    assertEquals(newModel.getLoadMap(), new HashMap<String, Image>());
  }

  @Test
  public void brighten10() {
    KoalaModel.brightenImage(10, "Koala", "Koala-Brighten");

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

//    for (int row = 0; row < KoalaBrighten.getHeight(); row++) {
//      for (int col = 0; col < KoalaBrighten.getWidth(); col++) {
//        sb1.append(KoalaBrighten.getImage()[row][col].getRed());
//        sb1.append(KoalaBrighten.getImage()[row][col].getGreen());
//        sb1.append(KoalaBrighten.getImage()[row][col].getBlue());
//      }
//    }
//
//    for (int row = 0; row < Koala.getHeight(); row++) {
//      for (int col = 0; col < KoalaBrighten.getWidth(); col++) {
//        sb1.append(KoalaBrighten.getImage()[row][col].getRed());
//        sb1.append(KoalaBrighten.getImage()[row][col].getGreen());
//        sb1.append(KoalaBrighten.getImage()[row][col].getBlue());
//      }
//    }

    BufferedImage image1 =
    assertEquals(sb1, sb2);
  }

}
