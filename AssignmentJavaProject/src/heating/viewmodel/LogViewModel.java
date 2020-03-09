package heating.viewmodel;

import heating.model.HeaterModel;
import heating.model.Temperature;
import heating.view.TableRowData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogViewModel implements PropertyChangeListener
{
  private HeaterModel model;
  private ObservableList<TableRowData> list;

  public LogViewModel(HeaterModel model)
  {
    this.model = model;
    list = loadList();
    model.addListener(this);
  }

  public ObservableList<TableRowData> loadList()
  {
    ObservableList<TableRowData> observableList = FXCollections.observableArrayList();
    for (int i = 0; i < model.getTemperatureListSize(); i++)
    {
      observableList.add(new TableRowData(model.getTemperature(i)));
    }
    return observableList;
  }

  public ObservableList<TableRowData> getList()
  {
    return list;
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    if (event.getPropertyName().equals("ThermometerTemperature") || event.getPropertyName().equals("outsideTemperature")){
      list.add(new TableRowData((Temperature) event.getNewValue()));
    }
  }
}
