package heating.mediator;

import heating.model.Temperature;
import heating.utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface HeaterModel extends UnnamedPropertyChangeSubject
{
  Temperature getTemperature(int index);
  void addTemperature(Temperature temperature);
  ArrayList<Temperature> getAllTemperatures();

  double getOutsideTemperature();
  double getFirstThermometerTemperature();
  double getSecondThermometerTemperature();

  int getHeaterPower();
  void HeaterTurnUp();
  void HeaterTurnDown();
}
