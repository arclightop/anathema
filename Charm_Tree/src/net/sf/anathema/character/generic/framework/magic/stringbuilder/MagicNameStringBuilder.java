package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.framework.magic.MagicDisplayLabeler;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.IResources;

public class MagicNameStringBuilder implements IMagicTooltipStringBuilder {
  private final MagicDisplayLabeler labeler;

  public MagicNameStringBuilder(IResources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    builder.append("<b>");
    builder.append(labeler.getLabelForMagic(magic));
    builder.append("</b>");
    builder.append(TooltipBuilder.HtmlLineBreak);
  }

}
