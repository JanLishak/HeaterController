package heating.model;

import heating.external.heater.Heater;
import heating.external.outside.OutsideTemperature;
import heating.external.thermometer.Thermometer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HeaterModelManager implements HeaterModel
{
  private OutsideTemperature outsideTemperature;
  private Heater heater;
  private TemperatureList temperatureList;
  private Thermometer firstThermometer;
  private Thermometer secondThermometer;
  private Temperature criticalLowTemperature;
  private Temperature criticalHighTemperature;

  private PropertyChangeSupport property;


  public HeaterModelManager()
  {
    heater = new Heater();

    outsideTemperature = new OutsideTemperature(10, -20, 20);
    Thread outsideTemperatureThread = new Thread(outsideTemperature);
    outsideTemperatureThread.setDaemon(true);
    outsideTemperatureThread.start();

    firstThermometer = new Thermometer(10, "th1", 1, this);
    secondThermometer = new Thermometer(10, "th2", 7, this);
    Thread firstThermometerThread = new Thread(firstThermometer);
    Thread secondThermometerThread = new Thread(secondThermometer);
    firstThermometerThread.setDaemon(true);
    secondThermometerThread.setDaemon(true);
    firstThermometerThread.start();
    secondThermometerThread.start();

    temperatureList = new TemperatureList();
    property = new PropertyChangeSupport(this);

    outsideTemperature.addListener(this);
    firstThermometer.addListener(this);
    secondThermometer.addListener(this);

    criticalLowTemperature = new Temperature("criticalLow", 12);
    criticalHighTemperature = new Temperature("criticalHigh", 18);
  }

  @Override public Temperature getCriticalLowTemperature()
  {
    return criticalLowTemperature;
  }

  @Override public Temperature getCriticalHighTemperature()
  {
    return criticalHighTemperature;
  }

  @Override public void setCriticalLowTemperature(double criticalLowTemperatureValue)
  {
    criticalLowTemperature = new Temperature("criticalLow", criticalLowTemperatureValue);
    property.firePropertyChange("criticalTemperatureChange",null, criticalLowTemperature);
  }

  @Override public void setCriticalHighTemperature(double criticalHighTemperatureValue)
  {
    criticalHighTemperature = new Temperature("criticalHigh", criticalHighTemperatureValue);
    property.firePropertyChange("criticalTemperatureChange", null, criticalHighTemperature);
  }

  @Override public Temperature getTemperature(int index)
  {
    return temperatureList.getTemperature(index);
  }

  @Override public void addTemperature(Temperature temperature)
  {
    temperatureList.addTemperature(temperature);
  }

  @Override public int getTemperatureListSize()
  {
    return temperatureList.getSize();
  }

  @Override public double getOutsideTemperature()
  {
    return outsideTemperature.getCurrentTemperature();
  }

  @Override public double getFirstThermometerTemperature()
  {
    return firstThermometer.getCurrentTemperature();
  }

  @Override public double getSecondThermometerTemperature()
  {
    return secondThermometer.getCurrentTemperature();
  }

  @Override public int getHeaterPower()
  {
    return heater.getPower();
  }

  @Override public void heaterTurnUp()
  {
    heater.turnUp();
  }

  @Override public void heaterTurnDown()
  {
    heater.turnDown();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
    firstThermometer.addListener(listener);
    secondThermometer.addListener(listener);
    outsideTemperature.addListener(listener);
    heater.addListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
    firstThermometer.removeListener(listener);
    secondThermometer.removeListener(listener);
    outsideTemperature.removeListener(listener);
    heater.removeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    if (event.getPropertyName().equals("ThermometerTemperature") || event.getPropertyName().equals("outsideTemperature")){
      temperatureList.addTemperature((Temperature) event.getNewValue());
    }
  }
}
