package heating.model;

import heating.model.Temperature;
import heating.utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface HeaterModel extends PropertyChangeListener, UnnamedPropertyChangeSubject
{
  Temperature getTemperature(int index);
  void addTemperature(Temperature temperature);
  int getTemperatureListSize();

  double getOutsideTemperature();
  double getFirstThermometerTemperature();
  double getSecondThermometerTemperature();

  Temperature getCriticalLowTemperature();
  Temperature getCriticalHighTemperature();
  void setCriticalLowTemperature(double criticalLowTemperatureValue);
  void setCriticalHighTemperature(double criticalHighTemperatureValue);

  int getHeaterPower();
  void heaterTurnUp();
  void heaterTurnDown();
}
