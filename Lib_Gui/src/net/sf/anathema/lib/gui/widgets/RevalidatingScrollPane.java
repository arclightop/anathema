package net.sf.anathema.lib.gui.widgets;

import javax.swing.JScrollPane;
import java.awt.Component;

public class RevalidatingScrollPane extends JScrollPane {

  private static final long serialVersionUID = 3738627177957841545L;

  public RevalidatingScrollPane(Component view) {
    super(view);
  }

  @Override
  public void revalidate() {
    net.sf.anathema.lib.gui.swing.GuiUtilities.revalidateTree(this);
  }
}