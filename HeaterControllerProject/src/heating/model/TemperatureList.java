package heating.model;

import java.util.ArrayList;

public class TemperatureList
{
   private ArrayList<Temperature> list;

   public TemperatureList()
   {
      this.list = new ArrayList<>();
   }

   public void addTemperature(Temperature temperature)
   {
      list.add(temperature);
   }

   public Temperature getTemperature(int index)
   {
      if (index >= 0 && index < list.size())
      {
         return list.get(index);
      }
      return null;
   }

   public Temperature getLastTemperature()
   {
      if (list.size() < 1)
      {
         return null;
      }
      else
      {
         return list.get(list.size()-1);
      }
   }

   public int getSize()
   {
      return list.size();
   }

   public String toString()
   {
      String s = "{";
      for (int i = 0; i < list.size(); i++)
      {
         s += list.get(i);
         if (i < list.size() - 1)
         {
            s += ", ";
         }
      }
      return s;
   }

   public ArrayList<Temperature> getAll()
   {
      return list;
   }
}
