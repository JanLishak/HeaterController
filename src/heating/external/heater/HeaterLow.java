package heating.external.heater;

public class HeaterLow extends HeaterState
{
  public HeaterLow(Heater heater)
  {
    System.out.println("Heater Low");
    heater.setPower(1);
  }

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new HeaterMedium(heater));
  }

  @Override public void turnDown(Heater heater)
  {
    heater.setState(new HeaterOff(heater));
  }
}
