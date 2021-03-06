package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.lib.util.Identified;

public enum FavorableState implements Identified {

  Default {
    @Override
    public void accept(IFavorableStateVisitor visitor) {
      visitor.visitDefault(this);
    }
  },

  Favored {
    @Override
    public void accept(IFavorableStateVisitor visitor) {
      visitor.visitFavored(this);
    }
  },

  Caste {
    @Override
    public void accept(IFavorableStateVisitor visitor) {
      visitor.visitCaste(this);
    }
  };
  @Override
  public String toString() {
    return getId();
  }

  @Override
  public String getId() {
    return name();
  }

  public abstract void accept(IFavorableStateVisitor visitor);
}