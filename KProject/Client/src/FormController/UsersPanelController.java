package FormController;

import ClientWork.SocketStream;
import Data.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersPanelController implements Initializable {
    public Button change;
    public Button calculate;
    public TableView<Employee> table_employee;
    public TableColumn<Employee, Integer> col_id;
    public TableColumn<Employee, String> col_firstname;
    public TableColumn<Employee, String> col_Secondname;
    public TableColumn<Employee, String> col_status;
    public TableColumn<Employee, Double> col_oklad;
    public TableColumn<Employee, Integer> col_premierPercent;
    public TableColumn<Employee, String> col_allowance;
    public TableColumn<Employee, Double> col_allowanceCount;
    public TableColumn<Employee, Double> col_Premier;
    public static Employee emp;
    public Button find;
    public TextField findtarget;
    public Button diagram;
    private ObservableList<Employee> observableList;

    public void UpdateData() throws SQLException, ClassNotFoundException{
        observableList = getEmployeeInformation("");
        table_employee.setItems(observableList);
    }
    public void ChangeInfo(ActionEvent ev) throws IOException, SQLException, ClassNotFoundException {

       emp = table_employee.getSelectionModel().getSelectedItem();

        if(emp!=null) {

            Parent root1 = FXMLLoader.load(getClass().getResource("/FormController/ChangeInfoPanel.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Панель пользователя");
            stage1.setScene(new Scene(root1, 600, 400));
            stage1.show();
        }else
        {
            UpdateData();
        }

        UpdateData();
    }

    public void CalculatePremier(ActionEvent av) throws IOException, SQLException, ClassNotFoundException {

        emp=table_employee.getSelectionModel().getSelectedItem();

        if(emp!=null) {
            Parent root1 = FXMLLoader.load(getClass().getResource("/FormController/Retention.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Удержание");
            stage1.setScene(new Scene(root1, 430, 230));
            stage1.show();

        }
        else{
            UpdateData();
        }
        UpdateData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        col_Secondname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        col_status.setCellValueFactory(new PropertyValueFactory<Employee, String>("employee_status"));
        col_oklad.setCellValueFactory(new PropertyValueFactory<Employee, Double>("oklad"));
        col_premierPercent.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("premierPercent"));
        col_allowance.setCellValueFactory(new PropertyValueFactory<Employee, String>("name_of_allowance"));
        col_allowanceCount.setCellValueFactory(new PropertyValueFactory<Employee, Double>("percent_of_allowance"));
        col_Premier.setCellValueFactory(new PropertyValueFactory<Employee, Double>("to_issue"));

        try
        {
            UpdateData();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ObservableList<Employee> getEmployeeInformation(String info)
    {

        ObservableList<Employee> observableList = FXCollections.observableArrayList();
        SocketStream socketStream = new SocketStream();
        socketStream.sendInt(3);
        socketStream.sendString(info);
        try
        {
            int rowAmount = Integer.parseInt(socketStream.getString());
            for (int i = 0; i < rowAmount; i++)
            {
                int id = socketStream.getInt();
                String firstname = socketStream.getString();
                String lastname = socketStream.getString();
                String employee_status = socketStream.getString();
                float oklad = socketStream.getFloat();
                float premierPercent =  socketStream.getFloat();
                String allowance = socketStream.getString();
                float percent_of_allowance = socketStream.getFloat();
                float rezult = socketStream.getFloat();
                observableList.add(new Employee(id, firstname, lastname, employee_status,
                        oklad, premierPercent,allowance,percent_of_allowance,rezult));
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return observableList;
    }

  public void FindData(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String info = findtarget.getText();

        if(info!="")
        {
            observableList = getEmployeeInformation(info);
            table_employee.setItems(observableList);
        }
        else
        {
            UpdateData();
        }

    }

    public void viewDiagramm(ActionEvent actionEvent) throws IOException {

        SocketStream socketStream = new SocketStream();
        int  four, three, two, one, zero;

        socketStream.sendInt(11);
        four = socketStream.getInt();
        three = socketStream.getInt();
        two = socketStream.getInt();
        one = socketStream.getInt();
        zero = socketStream.getInt();

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("0-300", zero),
                        new PieChart.Data("300-500", one),
                        new PieChart.Data("500-700", two),
                        new PieChart.Data("700-1000", three),
                        new PieChart.Data(">1000", four)
                );

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Премии");

        stage.setTitle("");
        stage.setWidth(500);
        stage.setHeight(500);
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
}
