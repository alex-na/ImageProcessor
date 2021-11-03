import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.ImageProcessingController;
import java.io.StringReader;
import mocks.MockImageProcessingModel;
import mocks.MockImageProcessingView;
import model.ImageProcessingModel;
import model.Model;
import org.junit.Test;
import view.ImageProcessingView;
import view.View;

/**
 * Class utilized for testing the ImageProcessingController.
 */
public class ImageProcessingControllerTest {

    ImageProcessingModel model = new Model();
    Appendable appendable =  new StringBuilder();
    ImageProcessingView view = new View(appendable);
    Readable readable = new StringReader("");
    ImageProcessingController controller = new Controller(model, view, readable);
    ImageProcessingModel mockModel = new MockImageProcessingModel(new StringBuilder());
    ImageProcessingView mockView = new MockImageProcessingView(new StringBuilder());

  // Testing invalid constructors
  @Test(expected = IllegalArgumentException.class)
  public void testConst1() {
    ImageProcessingController c = new Controller(model, view, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConst2() {
    ImageProcessingController c = new Controller(null, view, readable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConst3() {
    ImageProcessingController c = new Controller(model, null, readable);
  }
  @Test
  public void testConst4() {

  }

  // Testing load command
  @Test
  public void testLoad() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("load Koala.ppm koala");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "load method called with parameters: Koala.ppm, koala";

    assertEquals(message, mLog.toString());
  }

  // Testing save command
  @Test
  public void testSave() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("save images/koala-brighten.ppm koala-brighten");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "save method called with parameters: images/koala-brighten.ppm, koala-brighten";

    assertEquals(message, mLog.toString());

  }

  // Testing brighten command
  @Test
  public void testBrighten() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("brighten 10 koala koala-brighten");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "brightenImage method called with parameters: 10, koala, koala-brighten";

    assertEquals(message, mLog.toString());
  }

  // Testing horizontal-flip command
  @Test
  public void testHorizontalFlip() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("horizontal-flip koala koala-horizontal");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "flipImage method called with parameters: horizontal, koala, koala-horizontal";

    assertEquals(message, mLog.toString());
  }

  // Testing vertical-flip command
  @Test
  public void testVerticalFlip() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("vertical-flip koala koala-vertical");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "flipImage method called with parameters: vertical, koala, koala-vertical";

    assertEquals(message, mLog.toString());
  }

  // Testing value-component command
  @Test
  public void testValueComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("value-component koala koala-value");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: value, koala, koala-value";

    assertEquals(message, mLog.toString());
  }

  // Testing intensity-component command
  @Test
  public void testIntensityComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("intensity-component koala koala-intensity");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: intensity, koala, koala-intensity";

    assertEquals(message, mLog.toString());

  }

  // Testing luma-component command
  @Test
  public void testLumaComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("luma-component koala koala-luma");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: luma, koala, koala-luma";

    assertEquals(message, mLog.toString());
  }
  // Testing red-component command
  @Test
  public void testRedComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("red-component koala koala-red");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: red, koala, koala-red";

    assertEquals(message, mLog.toString());

  }
  // Testing green-component command
  @Test
  public void testGreenComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("green-component koala koala-green");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: green, koala, koala-green";

    assertEquals(message, mLog.toString());
  }

  // Testing blue-component command
  @Test
  public void testBlueComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("blue-component koala koala-blue");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: blue, koala, koala-blue";

    assertEquals(message, mLog.toString());

  }
}
