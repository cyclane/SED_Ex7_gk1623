package fi.iki.jka;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.fail;

public class JPhotoFrameTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  private final DialogService dialogService = context.mock(DialogService.class);
  private JPhotoFrame frame;

  @Test
  public void slideshowActionNoPhotos() {
    buildJPhotoFrame(null, null, dialogService);
    context.checking(new Expectations() {{
      oneOf(dialogService).showMessageDialog(frame, JPhotoFrame.NO_PHOTOS_TO_SHOW, JPhotoFrame.APP_NAME, JOptionPane.ERROR_MESSAGE);
    }});
    frame.actionSlideshow();
  }

  private void buildJPhotoFrame(String frameName, JPhotoCollection photos, DialogService dialogService) {
    try {
      frame = new JPhotoFrame(frameName, photos, dialogService);
    } catch (Exception e) {
      fail("Failed to create JPhotoFrame: " + e);
    }
  }
}