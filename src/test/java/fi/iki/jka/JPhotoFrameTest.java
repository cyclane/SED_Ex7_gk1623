package fi.iki.jka;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static org.junit.Assert.fail;

public class JPhotoFrameTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  private final DialogService dialogService = context.mock(DialogService.class);
  private final JPhotoShowFactory photoShowFactory = context.mock(JPhotoShowFactory.class);
  private JPhotoFrame frame;

  @Test
  public void slideshowActionNoPhotos() {
    buildJPhotoFrame(null, null);
    context.checking(new Expectations() {{
      oneOf(dialogService).showMessageDialog(frame, JPhotoFrame.NO_PHOTOS_TO_SHOW, JPhotoFrame.APP_NAME, JOptionPane.ERROR_MESSAGE);
    }});
    frame.actionPerformed(new ActionEvent(this, 0, JPhotoMenu.A_SLIDESHOW));
  }

  @Test
  public void previewSlideshowActionNoPhotos() {
    buildJPhotoFrame(null, null);
    context.checking(new Expectations() {{
      oneOf(dialogService).showMessageDialog(frame, JPhotoFrame.NO_PHOTOS_TO_SHOW, JPhotoFrame.APP_NAME, JOptionPane.ERROR_MESSAGE);
    }});
    frame.actionPerformed(new ActionEvent(this, 0, JPhotoMenu.A_PREVIEW_SLIDESHOW));
  }

  @Test
  public void slideshowActionWithPhotos() {
    buildJPhotoFrame(null, null);
    frame.insertPhotos(new String[]{"src/test/resources/JPhotoAlbum64.png"});
    context.checking(new Expectations() {{
      oneOf(photoShowFactory).create(frame.photos, 5000, frame.list);
    }});
    frame.actionPerformed(new ActionEvent(this, 0, JPhotoMenu.A_SLIDESHOW));
  }

  @Test
  public void previewSlideshowActionWithPhotos() {
    buildJPhotoFrame(null, null);
    frame.insertPhotos(new String[]{"src/test/resources/JPhotoAlbum64.png"});
    context.checking(new Expectations() {{
      oneOf(photoShowFactory).create(frame.photos, 750, frame.list);
    }});
    frame.actionPerformed(new ActionEvent(this, 0, JPhotoMenu.A_PREVIEW_SLIDESHOW));
  }

  private void buildJPhotoFrame(String frameName, JPhotoCollection photos) {
    try {
      frame = new JPhotoFrame(frameName, photos, dialogService, photoShowFactory);
    } catch (Exception e) {
      fail("Failed to create JPhotoFrame: " + e);
    }
  }
}