package net.sf.anathema.character.view.concept;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.AbstractButton;
import javax.swing.ListCellRenderer;

public interface ICharacterConceptAndRulesView extends
    IInitializableContentView<ICharacterConceptAndRulesViewProperties> {

  <V> IObjectSelectionView<V> addObjectSelectionView(
      String labelText,
      V[] objects,
      ListCellRenderer renderer,
      boolean editable);

  ITextView addLabelTextView(String labelText);
  
  void addSpinner(String labelText, IntegerSpinner spinner);

  void addRulesLabel(String labelText);

  AbstractButton addAction(SmartAction action, int row);
}