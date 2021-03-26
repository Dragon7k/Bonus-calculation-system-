package FormController;

import ClientWork.SocketStream;
import Data.Employee;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RetentionController  {
    public Button Enter;
    public Button back;
    public TextField retention;

    public void Okey(ActionEvent actionEvent)
    {

        int empID = UsersPanelController.emp.getId();
        String ret = retention.getText();
        SocketStream s = new SocketStream();
        s.sendInt(5);
        s.sendInt(empID);
        s.sendString(ret);
        Enter.getScene().getWindow().hide();
    }

    public void Back(ActionEvent actionEvent)
    {
        back.getScene().getWindow().hide();
    }
}
