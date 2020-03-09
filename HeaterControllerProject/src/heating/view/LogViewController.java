package heating.view;

import heating.viewmodel.LogViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

import java.util.Date;

public class LogViewController
{
  @FXML public TableView<TableRowData> table;
  @FXML public TableColumn<TableRowData, String> type;
  @FXML public TableColumn<TableRowData, Number> value;
  @FXML public TableColumn<TableRowData, Date> date;

  private ViewHandler viewHandler;
  private LogViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LogViewModel logViewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = logViewModel;

    type.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    value.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
    date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    table.setItems(viewModel.getList());

    table.setSelectionModel(null);
    type.setSortable(false);
    date.setSortable(false);
    value.setSortable(false);
  }

  public void reset()
  {
    System.out.println("reset");
    table.setItems(viewModel.getList());
  }

  public Region getRoot()
  {
    return root;
  }

  public void goBack(ActionEvent actionEvent)
  {
    viewHandler.openView("mainView");
  }
}
