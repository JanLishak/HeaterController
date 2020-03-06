package heating.view;

import heating.model.Temperature;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class TableRowData
{
  private SimpleStringProperty id;
  private SimpleDoubleProperty value;
  private SimpleObjectProperty<Date> date;

  public TableRowData(Temperature temperature)
  {
    id = new SimpleStringProperty(temperature.getId());
    value = new SimpleDoubleProperty(temperature.getValue());
    date = new SimpleObjectProperty<Date>(temperature.getTime().getDate());
  }

  public SimpleStringProperty idProperty()
  {
    return id;
  }

  public SimpleDoubleProperty valueProperty()
  {
    return value;
  }

  public SimpleObjectProperty<Date> dateProperty()
  {
    return date;
  }
}
