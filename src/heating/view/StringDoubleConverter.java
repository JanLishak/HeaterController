package heating.view;

import javafx.util.StringConverter;

public class StringDoubleConverter extends StringConverter<Number>
{
  @Override public String toString(Number number)
  {
    return String.format("%.2f", number);
  }

  @Override public Number fromString(String s)
  {
    try
    {
      return Double.parseDouble(s);
    }
    catch (Exception e){
      return 0;
    }
  }
}
