package heating.external.heater;

public class HeaterOff extends HeaterState
{
  public HeaterOff(Heater heater)
  {
    System.out.println("Heater Off");
    heater.setPower(0);
  }

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new HeaterLow(heater));
  }

  @Override public void turnDown(Heater heater)
  {
  }
}
