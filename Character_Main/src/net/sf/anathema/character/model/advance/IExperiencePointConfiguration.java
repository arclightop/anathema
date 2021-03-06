package net.sf.anathema.character.model.advance;

public interface IExperiencePointConfiguration {

  IExperiencePointEntry[] getAllEntries();

  IExperiencePointEntry addEntry();

  void addEntry(String description, int xpCost);

  void removeEntry(IExperiencePointEntry entry);

  int getTotalExperiencePoints();

  void addExperiencePointConfigurationListener(IExperiencePointConfigurationListener listener);

  int getExtraSpendings();
}