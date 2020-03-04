package heating.mediator;

import heating.external.heater.Heater;
import heating.external.outside.OutsideTemperature;
import heating.external.thermometer.Thermometer;
import heating.model.Temperature;
import heating.model.TemperatureList;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HeaterModelManager implements HeaterModel
{
  private OutsideTemperature outsideTemperature;
  private Heater heater;
  private TemperatureList temperatureList;
  private Thermometer firstThermometer;
  private Thermometer secondThermometer;

  public HeaterModelManager()
  {
    heater = new Heater();

    outsideTemperature = new OutsideTemperature(10, -20, 20);
    Thread outsideTemperatureThread = new Thread();
    outsideTemperatureThread.setDaemon(true);
    outsideTemperatureThread.start();

    firstThermometer = new Thermometer(10, "th1", 1,heater, outsideTemperature);
    secondThermometer = new Thermometer(10, "th2", 7, heater, outsideTemperature);
    Thread firstThermometerThread = new Thread(firstThermometer);
    Thread secondThermometerThread = new Thread(secondThermometer);
    firstThermometerThread.setDaemon(true);
    secondThermometerThread.setDaemon(true);
    firstThermometerThread.start();
    secondThermometerThread.start();

    temperatureList = new TemperatureList();
  }

  @Override public Temperature getTemperature(int index)
  {
    return temperatureList.getTemperature(index);
  }

  @Override public void addTemperature(Temperature temperature)
  {
    temperatureList.addTemperature(temperature);
  }

  @Override public ArrayList<Temperature> getAllTemperatures()
  {
    return temperatureList.getAll();
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

  @Override public void HeaterTurnUp()
  {
    heater.turnUp();
  }

  @Override public void HeaterTurnDown()
  {
    heater.turnDown();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    firstThermometer.addListener(listener);
    secondThermometer.addListener(listener);
    outsideTemperature.addListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    firstThermometer.removeListener(listener);
    secondThermometer.removeListener(listener);
    outsideTemperature.removeListener(listener);
  }
}
