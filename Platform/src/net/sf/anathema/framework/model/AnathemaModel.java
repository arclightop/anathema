package net.sf.anathema.framework.model;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.messaging.AnathemaMessaging;
import net.sf.anathema.framework.messaging.IAnathemaMessageContainer;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.reporting.IReportRegistry;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;

public class AnathemaModel implements IAnathemaModel {

  private final IRegistry<String, IAnathemaExtension> extensionRegistry = new Registry<String, IAnathemaExtension>();
  private final IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry = new Registry<IItemType, IRepositoryItemPersister>();
  private final IItemManagementModel itemManagment = new ItemManagmentModel();
  private final IReportRegistry reportRegistry = new ReportRegistry();
  private final IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry = new Registry<IItemType, IItemViewFactory>();
  private final IItemTypeRegistry itemTypes = new ItemTypeRegistry();
  private final Repository repository;
  private final AnathemaMessaging messaging;
  private final ResourceLoader resourceLoader;

  public AnathemaModel(File repositoryFolder, IResources resources, ResourceLoader resourceLoader) {
    this.repository = new Repository(repositoryFolder, itemManagment);
    this.messaging = new AnathemaMessaging(resources);
    messaging.addMessage(new BasicMessage("Welcome to Anathema", MessageType.INFORMATION));
    this.resourceLoader = resourceLoader;
  }

  @Override
  public final IRepository getRepository() {
    return repository;
  }

  @Override
  public final IReportRegistry getReportRegistry() {
    return reportRegistry;
  }

  @Override
  public final IRegistry<String, IAnathemaExtension> getExtensionPointRegistry() {
    return extensionRegistry;
  }

  @Override
  public final IRegistry<IItemType, IRepositoryItemPersister> getPersisterRegistry() {
    return persisterRegistry;
  }

  @Override
  public IItemManagementModel getItemManagement() {
    return itemManagment;
  }

  @Override
  public IRegistry<IItemType, IItemViewFactory> getViewFactoryRegistry() {
    return viewFactoryRegistry;
  }

  @Override
  public IItemTypeRegistry getItemTypeRegistry() {
    return itemTypes;
  }

  @Override
  public IAnathemaMessaging getMessaging() {
    return messaging;
  }

  @Override
  public IAnathemaMessageContainer getMessageContainer() {
    return messaging;
  }
  
  @Override
  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}