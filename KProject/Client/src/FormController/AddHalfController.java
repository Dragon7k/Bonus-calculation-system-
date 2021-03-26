package FormController;

import ClientWork.SocketStream;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;

public class AddHalfController {
    public Button ADDBUTTON;
    public Button backButton;
    public TextField loginField;
    public TextField passwordField;
    public TextField firstnameField;
    public TextField lastnameField;

    public void addInfo(ActionEvent actionEvent) throws IOException {
        String password = passwordField.getText();
        String name = firstnameField.getText();
        String lname=lastnameField.getText();
        String login =loginField.getText();


        SocketStream socketStream = new SocketStream();
        socketStream.sendInt(9);
        socketStream.sendString(name);
        socketStream.sendString(lname);
        socketStream.sendString(login);
        socketStream.sendString(password);

       int fre = socketStream.getInt();
        if(fre == 1)
        {
            JOptionPane.showMessageDialog(null, "такой логин уже существует",
                    "Ошибка добавления",JOptionPane.ERROR_MESSAGE);

            return;
        }
        else
        {

            ADDBUTTON.getScene().getWindow().hide();
        }




    }

    public void back(ActionEvent actionEvent) {
        backButton.getScene().getWindow().hide();
    }
}
