package heating.viewmodel;

import heating.model.HeaterModel;

public class ViewModelFactory
{
  private MainViewModel mainViewModel;
  private LogViewModel logViewModel;

  public ViewModelFactory(HeaterModel model)
  {
    this.mainViewModel = new MainViewModel(model);
    this.logViewModel = new LogViewModel(model);
  }

  public MainViewModel getMainViewModel()
  {
    return mainViewModel;
  }

  public LogViewModel getLogViewModel()
  {
    return logViewModel;
  }
}
