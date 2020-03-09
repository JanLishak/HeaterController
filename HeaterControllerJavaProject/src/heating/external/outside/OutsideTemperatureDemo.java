package heating.external.outside;


public class OutsideTemperatureDemo
{
  public static void main(String[] args)
  {
    OutsideTemperature outsideTemperature = new OutsideTemperature(10, -20, 20);
    Thread thread = new Thread(outsideTemperature);
    thread.start();
  }
}
