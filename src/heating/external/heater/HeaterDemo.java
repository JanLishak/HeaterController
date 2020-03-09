package heating.external.heater;

import java.util.Scanner;

public class HeaterDemo
{
  public static void main(String[] args)
  {
    Heater heater = new Heater();
    Scanner kb = new Scanner(System.in);
    while(true){
      switch (kb.nextInt()){
        case 1:
          heater.turnUp();break;
        case 2:
          heater.turnDown();break;
        case 3:
          System.out.println(heater.status()); break;
      }
    }
  }
}
