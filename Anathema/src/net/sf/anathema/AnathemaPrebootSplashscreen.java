package net.sf.anathema;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class AnathemaPrebootSplashscreen {

  public void displayStatusMessage(String message) {
    if (System.getProperty("java.version").startsWith("1.5")) { //$NON-NLS-1$ //$NON-NLS-2$
      return;
    }
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen == null || !splashScreen.isVisible()) {
      return;
    }
    Graphics2D graphics = SplashScreen.getSplashScreen().createGraphics();
    TextLayout layout = new TextLayout(message, graphics.getFont().deriveFont(Font.BOLD), new FontRenderContext(
        null,
        true,
        false));
    layout.draw(graphics, 105, 333);
    splashScreen.update();
  }
}