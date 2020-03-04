package heating.external.heater;

public class HeaterHigh extends HeaterState
{
  private Thread timer;
  public HeaterHigh(Heater heater)
  {
    System.out.println("Heater High");
    heater.setPower(3);
    timer = new Thread(() -> {
      try
      {
        Thread.sleep(4000);
      }
      catch (InterruptedException e)
      {
      }
      heater.setState(new HeaterMedium(heater));
    });
    timer.start();
  }

  @Override public void turnUp(Heater heater) { }

  @Override public void turnDown(Heater heater)
  {
    timer.interrupt();
  }
}
