package heating.view;

import heating.viewmodel.MainViewModel;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class MainViewController
{
  @FXML public Label powerLabel;
  @FXML public Label firstThermometerTemperatureLabel;
  @FXML public Label secondThermometerTemperatureLabel;
  @FXML public Label outsideTemperatureLabel;
  @FXML public Label secondThermometerWaringLabel;
  @FXML public Label firstThermometerWarningLabel;
  @FXML public Label criticalLowLabel;
  @FXML public Label criticalHighLabel;
  @FXML public TextField maxTemperatureField;
  @FXML public TextField minTemperatureField;

  private Region root;
  private MainViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, MainViewModel mainViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewModel = mainViewModel;

    powerLabel.textProperty().bind(viewModel.heaterPowerProperty());

    firstThermometerWarningLabel.textProperty().bind(viewModel.firstThermometerWarningProperty());
    secondThermometerWaringLabel.textProperty().bind(viewModel.secondThermometerWarningProperty());

    Bindings.bindBidirectional(firstThermometerTemperatureLabel.textProperty(), viewModel.firstThermometerTemperatureProperty(), new StringDoubleConverter());
    Bindings.bindBidirectional(secondThermometerTemperatureLabel.textProperty(), viewModel.secondThermometerTemperatureProperty(), new StringDoubleConverter());
    Bindings.bindBidirectional(outsideTemperatureLabel.textProperty(), viewModel.outsideTemperatureProperty(), new StringDoubleConverter());

    Bindings.bindBidirectional(criticalLowLabel.textProperty(), viewModel.minTemperatureProperty(), new StringDoubleConverter());
    Bindings.bindBidirectional(criticalHighLabel.textProperty(), viewModel.maxTemperatureProperty(), new StringDoubleConverter());

    Bindings.bindBidirectional(maxTemperatureField.textProperty(), viewModel.setMaxTemperatureProperty(), new StringDoubleConverter());
    Bindings.bindBidirectional(minTemperatureField.textProperty(), viewModel.setMinTemperatureProperty(), new StringDoubleConverter());
    //Bindings.bindBidirectional(maxTemperatureField.textProperty(), viewModel.setMinTemperatureProperty(), new StringDoubleConverter());
    //Bindings.bindBidirectional(minTemperatureField.textProperty(), viewModel.setMaxTemperatureProperty(), new StringDoubleConverter());
  }

  public void reset()
  {
    viewModel.getAll();
  }

  public Region getRoot()
  {
    return root;
  }

  public void HeaterDown(ActionEvent actionEvent)
  {
    viewModel.heaterDown();
  }

  public void HeaterUp(ActionEvent actionEvent)
  {
    viewModel.heaterUp();
  }

  public void ApplySettings(ActionEvent actionEvent)
  {
    viewModel.applySettings();
  }

  public void TemperatureLog(ActionEvent actionEvent)
  {
    viewHandler.openView("logView");
  }
}
