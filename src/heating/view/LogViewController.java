package heating.view;

import javafx.scene.layout.Region;

public class LogViewController
{
  private ViewHandler viewHandler;
  private LogViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LogViewModel logViewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = logViewModel;
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }
}
