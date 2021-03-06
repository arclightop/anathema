package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

public class AnathemaNullDataItem extends AbstractAnathemaItem {

  public AnathemaNullDataItem(IItemType type, Identified identificate) {
    super(type, identificate);
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  @Override
  public IItemData getItemData() {
    return null;
  }

  @Override
  public boolean isDirty() {
    return false;
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  @Override
  public void setClean() {
    // nothing to do
  }
}