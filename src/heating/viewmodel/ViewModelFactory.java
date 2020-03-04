package heating.viewmodel;

import heating.mediator.HeaterModel;

public class ViewModelFactory
{
  private MainViewModel mainViewModel;

  public ViewModelFactory(HeaterModel model)
  {
    this.mainViewModel = new MainViewModel(model);
  }

  public MainViewModel getMainViewModel()
  {
    return mainViewModel;
  }
}
