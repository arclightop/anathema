package net.sf.anathema.character.impl.view;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.impl.view.advantage.BackgroundSelectionView;
import net.sf.anathema.character.impl.view.advantage.EssencePanelView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class BasicAdvantageView extends AbstractInitializableContentView<IAdvantageViewProperties> implements
    IBasicAdvantageView {

  private final JPanel virtuePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel willpowerPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel backgroundSelectionPanel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel backgroundDisplayPanel = new JPanel(new GridDialogLayout(2, false));
  private final EssencePanelView essencePanelView;
  private final IntegerViewFactory guiConfiguration;
  private JPanel backgroundPanel;

  public BasicAdvantageView(IntegerViewFactory intValueDisplayFactory) {
    this.guiConfiguration = intValueDisplayFactory;
    essencePanelView = new EssencePanelView(intValueDisplayFactory);
  }

  @Override
  public void createContent(JPanel content, IAdvantageViewProperties properties) {
    content.setLayout(new FlowLayout(FlowLayout.LEFT));
    JPanel innerPanel = new JPanel(new GridDialogLayout(2, false));
    content.add(innerPanel);
    GridDialogLayoutData virtueData = new GridDialogLayoutData();
    virtueData.setVerticalSpan(2);
    virtueData.setVerticalAlignment(GridAlignment.FILL);
    addTitledPanel(properties.getVirtueTitle(), innerPanel, virtuePanel, virtueData);
    GridDialogLayoutData willpowerData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
    willpowerData.setVerticalAlignment(GridAlignment.BEGINNING);
    addTitledPanel(properties.getWillpowerTitle(), innerPanel, willpowerPanel, willpowerData);
    GridDialogLayoutData essenceData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
    essenceData.setVerticalAlignment(GridAlignment.END);
    addTitledPanel(properties.getEssenceTitle(), innerPanel, essencePanelView.getComponent(), essenceData);
    backgroundPanel = createBackgroundPanel(properties.getBackgroundTitle());
    innerPanel.add(backgroundPanel, GridDialogLayoutDataFactory.createHorizontalSpanData(
        2,
        GridDialogLayoutData.FILL_HORIZONTAL));
  }

  private JPanel createBackgroundPanel(String title) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    panel.add(backgroundSelectionPanel, BorderLayout.CENTER);
    panel.add(backgroundDisplayPanel, BorderLayout.SOUTH);
    return panel;
  }

  @Override
  public IIntValueView addVirtue(String labelText, int value, int maxValue) {
    SimpleTraitView virtueView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    virtueView.addComponents(virtuePanel);
    return virtueView;
  }

  @Override
  public IIntValueView addWillpower(String labelText, int value, int maxValue) {
    SimpleTraitView willpowerView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    willpowerView.addComponents(willpowerPanel);
    return willpowerView;
  }

  @Override
  public IButtonControlledComboEditView<Object> addBackgroundSelectionView(
	      String labelText,
	      ListCellRenderer backgroundRenderer,
	      ITextFieldComboBoxEditor backgroundEditor,
	      Icon addIcon) {
	    ButtonControlledComboEditView<Object> objectSelectionView = new BackgroundSelectionView<Object>(
	        addIcon,
	        labelText,
	        backgroundRenderer,
	        backgroundEditor);
	    backgroundSelectionPanel.add(objectSelectionView.getComponent());
	    return objectSelectionView;
	  }

  @Override
  public IRemovableTraitView<SimpleTraitView> addBackgroundView(
      Icon deleteIcon,
      String labelText,
      int value,
      int maxValue) {
    SimpleTraitView view = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    RearButtonTraitViewWrapper<SimpleTraitView> backgroundView = new RearButtonTraitViewWrapper<SimpleTraitView>(
        view,
        deleteIcon);
    backgroundView.addComponents(backgroundDisplayPanel);
    return backgroundView;
  }

  @Override
  public IIntValueView addEssenceView(String labelText, int value, int maxValue, IModifiableCapTrait trait) {
    return essencePanelView.addEssenceView(labelText, value, maxValue, trait);
  }

  @Override
  public IValueView<String> addPoolView(String labelText, String value) {
    return essencePanelView.addPoolView(labelText, value);
  }

  @Override
  public void setBackgroundPanelEnabled(boolean enabled) {
    net.sf.anathema.lib.gui.swing.GuiUtilities.setEnabled(backgroundPanel, enabled);
  }

  private JComponent addTitledPanel(
      String title,
      JPanel container,
      JComponent component,
      IGridDialogLayoutData constraint) {
    component.setBorder(new TitledBorder(title));
    container.add(component, constraint);
    return component;
  }
}