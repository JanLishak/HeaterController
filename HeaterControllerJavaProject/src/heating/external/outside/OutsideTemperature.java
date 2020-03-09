package heating.external.outside;

import heating.model.Temperature;
import heating.utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OutsideTemperature implements Runnable,
    UnnamedPropertyChangeSubject
{
  private double currentTemperature;
  private double minTemperature;
  private double maxTemperature;
  private PropertyChangeSupport property;

  public OutsideTemperature(double currentTemperature, double minTemperature, double maxTemperature)
  {
    this.currentTemperature = currentTemperature;
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
    property = new PropertyChangeSupport(this);
  }

  /**
   * Calculating the external temperature.
   * Values are only valid if the temperature is being measured
   * approximately every 10th second.
   *
   * @param t0 the last measured external temperature
   * @param min a lower limit (may temporally be deceeded)
   * @param max an upper limit (may temporally be exceeded)
   * @return an updated external temperature
   */
  public double externalTemperature(double t0, double min, double max)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    return t0;
  }

  @Override public void run()
  {
    while (true)
    {
      currentTemperature = externalTemperature(currentTemperature, minTemperature, maxTemperature);
      property.firePropertyChange("outsideTemperature", null, new Temperature("OutsideTemperature", currentTemperature));
      System.out.printf("Outside temperature: %.2f Min:%.2f Max:%.2f\n", currentTemperature, minTemperature, maxTemperature);
      try
      {
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public double getCurrentTemperature()
  {
    return currentTemperature;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
}

