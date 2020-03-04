package heating.external.heater;

public abstract class HeaterState
{
  public abstract void turnUp(Heater heater);
  public abstract void turnDown(Heater heater);
}
