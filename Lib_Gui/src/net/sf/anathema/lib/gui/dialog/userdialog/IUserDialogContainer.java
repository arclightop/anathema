package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IDialogContainer;

public interface IUserDialogContainer extends IDialogContainer {

  /**
   * Executes this Dialog by showing a non-modal instance.
   * @see #show()
   * @see #showNonModal(IDialogCloseHandler)
   */
  void showNonModal();

  /**
   * Executes this Dialog by showing a non-modal instance. The given close handler will be informed on
   * cancel or ok right after the dialog has been disposed.
   * @see #showNonModal()
   * @see #show()
   */
  void showNonModal(IDialogCloseHandler dialogCloseHandler);

  void setVisible(boolean visible);

}