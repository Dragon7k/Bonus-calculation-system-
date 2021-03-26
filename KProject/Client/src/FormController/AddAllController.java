package FormController;

import ClientWork.SocketStream;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddAllController
{

    public TextField firstnameField;
    public Button BACK;
    public Button ADD;
    public TextField lastnameField;
    public TextField specnameField;

    public void back(ActionEvent actionEvent)
    {
        BACK.getScene().getWindow().hide();
    }

    public void ADD(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String fname = firstnameField.getText();
       String lname = lastnameField.getText();
       String spec = specnameField.getText();

        SocketStream socs = new SocketStream();

        socs.sendInt(10);
        socs.sendString(fname);
        socs.sendString(lname);
        socs.sendString(spec);


      ADD.getScene().getWindow().hide();


    }
}
