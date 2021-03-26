package FormController;

import ClientWork.SocketStream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SignUpWindowController {
    public Button SignUpButton;
    public TextField newlogin;
    public TextField newpass;
    public TextField firstname;
    public TextField secondname;
    public TextField dolzhnost;
    public Button backButton;

    public void signUp(javafx.event.ActionEvent actionEvent) throws IOException {
        if(newlogin.getText().equals("") || newpass.getText().equals("") || firstname.getText().equals("")|| secondname.getText().equals("")|| dolzhnost.getText().equals("") )
        {
            JOptionPane.showMessageDialog(null,
                    "Не все поля заполнены", "Ошибка регистрации", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try
        {
            SocketStream socketStream = new SocketStream();
            socketStream.sendInt(1);
            socketStream.sendString(newlogin.getText());
            socketStream.sendString(newpass.getText());
            socketStream.sendString(firstname.getText());
            socketStream.sendString(secondname.getText());
            socketStream.sendString(dolzhnost.getText());

            if(socketStream.getString().equals("Error"))
            {
                JOptionPane.showMessageDialog(null, "Такой пользователь уже существует",
                        "Ошибка добавления",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        backToMenu(actionEvent);
    }

    public void backToMenu(javafx.event.ActionEvent actionEvent) throws IOException {

        backButton.getScene().getWindow().hide();
    }
}
