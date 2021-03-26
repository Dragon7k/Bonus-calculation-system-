package FormController;

import ClientWork.SocketStream;
import Data.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AdminMenuController implements Initializable
{

    public TableView <Employee>table_admin;
    public TableColumn<Employee, Integer> col_id;
    public TableColumn<Employee, String> col_firstname;
    public TableColumn<Employee, String> col_Secondname;
    public TableColumn<Employee, String>col_access_level;
    public TableColumn<Employee, String> col_status;
    public TableColumn<Employee, Double> col_oklad;
    public TableColumn<Employee, Integer> col_premierPercent;
    public TableColumn<Employee, String> col_allowance;
    public TableColumn<Employee, Double> col_allowanceCount;
    public TableColumn<Employee, Double> col_Premier;
    public TextField nameField;
    public TextField lastnameField;
    public TextField access_level_Field;
    public TextField specnameField;
    public TextField wageField;
    public TextField stavkaField;
    public TextField allowancevalueField;
    public TextField allowancenameField;
    public Button CHANGEBUTTON;
    public Button ADDBUTTON;
    public Button DELETEBUTTON;

    public static Employee emp;
    public Button select_button;

    private ObservableList<Employee> observableList;

    public  void UpdateData() throws SQLException, ClassNotFoundException{
        observableList = getEmployeeInformation();
        table_admin.setItems(observableList);
    }

    public ObservableList<Employee> getEmployeeInformation()
    {
        ObservableList<Employee> observableList = FXCollections.observableArrayList();
        SocketStream s = new SocketStream();
        s.sendInt(6);
        try
        {
            int rowAmount = Integer.parseInt(s.getString());
            for (int i = 0; i < rowAmount; i++)
            {
                int access_level = s.getInt();
                int id = s.getInt();
                String firstname = s.getString();
                String lastname = s.getString();

                String employee_status = s.getString();
                float oklad = s.getFloat();
                float premierPercent =  s.getFloat();
                String allowance = s.getString();
                float percent_of_allowance = s.getFloat();
                float rezult = s.getFloat();
                observableList.add(new Employee(id, firstname, lastname,access_level, employee_status,
                        oklad, premierPercent,allowance,percent_of_allowance,rezult));
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }




        return observableList;
    }
    public void select(ActionEvent actionEvent)
    {
        emp =table_admin.getSelectionModel().getSelectedItem();

        if(emp!=null)
        {
            nameField.setText(emp.getFirstname());
            lastnameField.setText(emp.getLastname());
            access_level_Field.setText(Integer.toString(emp.getAccess_level()));
            specnameField.setText(emp.getEmployee_status());
            wageField.setText(Float.toString(emp.getOklad()));
            stavkaField.setText(Float.toString(emp.getPremierPercent()));
            allowancenameField.setText(emp.getName_of_allowance());
            allowancevalueField.setText(Float.toString(emp.getPercent_of_allowance()));

        }
    }
    public void editAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        emp =table_admin.getSelectionModel().getSelectedItem();

        if(emp!=null) {
            int empID = emp.getId();
            String name = nameField.getText();
            String lname = lastnameField.getText();
            int access = Integer.parseInt(access_level_Field.getText());
            String specn = specnameField.getText();
            float wage = Float.parseFloat(wageField.getText());
            float stavka = Float.parseFloat(stavkaField.getText());
            String allo = allowancenameField.getText();
            float allo_val = Float.parseFloat(allowancevalueField.getText());
            SocketStream s = new SocketStream();
            s.sendInt(7);
            s.sendInt(empID);
            s.sendString(name);
            s.sendString(lname);
            s.sendInt(access);
            s.sendString(specn);
            s.sendString(Float.toString(wage));
            s.sendString(Float.toString(stavka));
            s.sendString(allo);
            s.sendString(Float.toString(allo_val));

        }



        try {
            UpdateData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

            Parent root1 = FXMLLoader.load(getClass().getResource("/FormController/AddEmployeePanel.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("Панель добавления");
            stage1.setScene(new Scene(root1, 600, 400));
            stage1.show();

        UpdateData();


    }


    public void deleteAction(ActionEvent actionEvent)
    {
        emp =table_admin.getSelectionModel().getSelectedItem();
        if(emp != null)
        {
            int empID =emp.getId();
            SocketStream s=new SocketStream();
            s.sendInt(8);
            s.sendInt(empID);

        }
        try {
            UpdateData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        col_id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        col_Secondname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        col_access_level.setCellValueFactory(new PropertyValueFactory<Employee, String>("access_level"));
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


}
