package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.SelectedItemActionEnabler;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.message.MessageUserDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.YesNoDialogButtonConfiguration;
import net.sf.anathema.lib.message.IMessage;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;
import java.awt.Cursor;

public abstract class AbstractAnathemaCloseAction extends SmartAction {

  private static final long serialVersionUID = 231060390775642729L;
  private final IItemManagementModel management;
  private final IResources resources;

  public AbstractAnathemaCloseAction(IItemManagementModel management, IResources resources) {
    this.management = management;
    this.resources = resources;
    management.addListener(new SelectedItemActionEnabler(this, management.getSelectedItem()));
  }

  protected final IItemManagementModel getManagement() {
    return management;
  }

  protected abstract IItem getItemToClose();

  @Override
  protected final void execute(Component parentComponent) {
    IItem selectedItem = getItemToClose();
    if (selectedItem == null) {
      return;
    }
    if (selectedItem.isDirty()) {
      String messageText = resources.getString("AnathemaCore.Tools.Close.DirtyQuestion"); //$NON-NLS-1$
      IMessage message = new Message(messageText, MessageType.WARNING);
      DialogButtonConfiguration buttonConfiguration = new YesNoDialogButtonConfiguration(resources);
      MessageUserDialogConfiguration configuration = new MessageUserDialogConfiguration(message, buttonConfiguration);
      UserDialog userDialog = new UserDialog(parentComponent, configuration);
      IDialogResult result = userDialog.show();
      if (result.isCanceled()) {
        return;
      }
    }
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    management.removeItem(selectedItem);
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}
