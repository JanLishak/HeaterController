package heating.external.heater;

public class HeaterMedium extends HeaterState
{
  public HeaterMedium(Heater heater)
  {
    System.out.println("Heater Medium");
    heater.setPower(2);
  }

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new HeaterHigh(heater));
  }

  @Override public void turnDown(Heater heater)
  {
    heater.setState(new HeaterLow(heater));
  }
}
