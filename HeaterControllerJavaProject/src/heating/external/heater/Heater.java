package heating.external.heater;

import heating.utility.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Heater implements UnnamedPropertyChangeSubject
{
  private int power;
  private HeaterState state;
  PropertyChangeSupport property;

  public Heater()
  {
    this.property = new PropertyChangeSupport(this);
    this.state = new HeaterOff(this);
  }

  public void turnUp(){
    state.turnUp(this);
  }

  public void turnDown(){
    state.turnDown(this);
  }

  public void setPower(int power)
  {
    this.power = power;
    this.property.firePropertyChange("power", null, power);
  }

  public int getPower()
  {
    return power;
  }

  public String status(){
    return state.getClass().getSimpleName();
  }

  public void setState(HeaterState state){
    this.state = state;
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
