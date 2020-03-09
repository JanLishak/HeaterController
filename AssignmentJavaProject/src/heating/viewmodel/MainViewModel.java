package heating.viewmodel;

import heating.model.HeaterModel;
import heating.model.Temperature;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener
{
  private HeaterModel model;

  private SimpleStringProperty heaterPower;
  private SimpleDoubleProperty firstThermometerTemperature;
  private SimpleDoubleProperty secondThermometerTemperature;
  private SimpleDoubleProperty outsideTemperature;

  private SimpleStringProperty firstThermometerWarning;
  private SimpleStringProperty secondThermometerWarning;
  private SimpleStringProperty inputError;

  private SimpleDoubleProperty setMaxTemperature;
  private SimpleDoubleProperty setMinTemperature;
  private SimpleDoubleProperty maxTemperature;
  private SimpleDoubleProperty minTemperature;

  public MainViewModel(HeaterModel model)
  {
    this.model = model;

    heaterPower = new SimpleStringProperty();
    firstThermometerTemperature = new SimpleDoubleProperty();
    secondThermometerTemperature = new SimpleDoubleProperty();
    outsideTemperature = new SimpleDoubleProperty();

    firstThermometerWarning = new SimpleStringProperty();
    secondThermometerWarning = new SimpleStringProperty();
    inputError = new SimpleStringProperty();

    setMaxTemperature = new SimpleDoubleProperty();
    setMinTemperature = new SimpleDoubleProperty();

    maxTemperature = new SimpleDoubleProperty();
    minTemperature = new SimpleDoubleProperty();

    getAll();
    model.addListener(this);
  }

  public void getAll(){
    updateHeaterPower(model.getHeaterPower());
    firstThermometerTemperature.set(model.getFirstThermometerTemperature());
    secondThermometerTemperature.set(model.getFirstThermometerTemperature());
    outsideTemperature.set(model.getOutsideTemperature());

    setMinTemperature.set(model.getCriticalLowTemperature().getValue());
    setMaxTemperature.set(model.getCriticalHighTemperature().getValue());
    minTemperature.set(model.getCriticalLowTemperature().getValue());
    maxTemperature.set(model.getCriticalHighTemperature().getValue());

    checkWarnings();
  }

  private void updateHeaterPower(int heaterPowerValue){
    switch (heaterPowerValue){
      case 0:
        heaterPower.set("Off");break;
      case 1:
        heaterPower.set("Low");break;
      case 2:
        heaterPower.set("Medium");break;
      case 3:
        heaterPower.set("High");break;
    }
  }

  public void heaterUp(){
    model.heaterTurnUp();
  }

  public void heaterDown(){
    model.heaterTurnDown();
  }

  public void applySettings(){
    model.setCriticalHighTemperature(setMaxTemperature.getValue());
    model.setCriticalLowTemperature(setMinTemperature.getValue());
  }

  private void checkWarnings()
  {
    if(model.getFirstThermometerTemperature() > model.getCriticalHighTemperature().getValue())
      firstThermometerWarning.set("Too High!");
    else if(model.getFirstThermometerTemperature() < model.getCriticalLowTemperature().getValue())
      firstThermometerWarning.set("Too Low!");
    else
      firstThermometerWarning.set("");

    if(model.getSecondThermometerTemperature() > model.getCriticalHighTemperature().getValue())
      secondThermometerWarning.set("Too High!");
    else if(model.getSecondThermometerTemperature() < model.getCriticalLowTemperature().getValue())
      secondThermometerWarning.set("Too Low!");
    else
      secondThermometerWarning.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      switch (event.getPropertyName()){
        case "ThermometerTemperature":
          Temperature tmp = (Temperature)event.getNewValue();
          if (tmp.getId().equals("th1")){
            firstThermometerTemperature.set(tmp.getValue());
          }
          else if (tmp.getId().equals("th2")){
            secondThermometerTemperature.set(tmp.getValue());
          }
          checkWarnings();
          break;

        case "outsideTemperature":
          outsideTemperature.set(((Temperature)event.getNewValue()).getValue());
          break;

        case "power":
          updateHeaterPower((Integer) event.getNewValue());
          break;

        case "criticalTemperatureChange":
          Temperature temp = (Temperature)event.getNewValue();
          if (temp.getId().equals("criticalLow")){
            minTemperature.set(temp.getValue());
          }
          else if (temp.getId().equals("criticalHigh")){
            maxTemperature.set(temp.getValue());
          }
          checkWarnings();
          break;

        default:
          System.out.println("Error, unknown property " + event.getPropertyName());
      }
    });
  }


  public SimpleStringProperty heaterPowerProperty()
  {
    return heaterPower;
  }


  public SimpleDoubleProperty firstThermometerTemperatureProperty()
  {
    return firstThermometerTemperature;
  }


  public SimpleDoubleProperty secondThermometerTemperatureProperty()
  {
    return secondThermometerTemperature;
  }


  public SimpleDoubleProperty outsideTemperatureProperty()
  {
    return outsideTemperature;
  }


  public SimpleStringProperty firstThermometerWarningProperty()
  {
    return firstThermometerWarning;
  }


  public SimpleStringProperty secondThermometerWarningProperty()
  {
    return secondThermometerWarning;
  }


  public SimpleStringProperty inputErrorProperty()
  {
    return inputError;
  }


  public SimpleDoubleProperty setMaxTemperatureProperty()
  {
    return setMaxTemperature;
  }

  public SimpleDoubleProperty setMinTemperatureProperty()
  {
    return setMinTemperature;
  }


  public SimpleDoubleProperty maxTemperatureProperty()
  {
    return maxTemperature;
  }


  public SimpleDoubleProperty minTemperatureProperty()
  {
    return minTemperature;
  }
}
