package mocks;

import java.io.IOException;
import view.ImageProcessingView;

public class MockImageProcessingView implements ImageProcessingView {

  private StringBuilder log;

  public MockImageProcessingView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void displayImage() {
    log.append(String.format("displayImage method called."));
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(String.format("renderMessage method called with message: %s", message));
  }
}
