import heating.model.HeaterModelManager;
import heating.view.ViewHandler;
import heating.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    HeaterModelManager model = new HeaterModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
