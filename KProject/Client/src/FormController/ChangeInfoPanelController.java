package FormController;

import ClientWork.SocketStream;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeInfoPanelController implements Initializable {
    public TextField okladField;
    public TextField premPercentField;
    public TextField allowance_nameField;
    public TextField allowance_percentField;


    public Button change;
    public Button back;
    public AnchorPane changeTable;


    public void change(javafx.event.ActionEvent actionEvent)
    {
        int empID = UsersPanelController.emp.getId();
        String strOkl = okladField.getText();
        String strOklPercent = premPercentField.getText();
        String strAllowance = allowance_nameField.getText();
        String strAllowancePercent = allowance_percentField.getText();

        SocketStream s = new SocketStream();
        s.sendInt(4);
        s.sendInt(empID);
        s.sendString(strOkl);
        s.sendString(strOklPercent);
        s.sendString(strAllowance);
        s.sendString(strAllowancePercent);
        change.getScene().getWindow().hide();

    }

    public void back(javafx.event.ActionEvent actionEvent)
    {
        back.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okladField.setText(Float.toString(UsersPanelController.emp.getOklad()));
        premPercentField.setText(Float.toString(UsersPanelController.emp.getPremierPercent()));
        allowance_nameField.setText(UsersPanelController.emp.getName_of_allowance());
        allowance_percentField.setText(Float.toString(UsersPanelController.emp.getPercent_of_allowance()));


    }
}
