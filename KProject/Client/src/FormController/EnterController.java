package FormController;

import ClientWork.SocketStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class EnterController {

    public static String name;
    public static String surname;
    public Button EnterInSystem;
    public Button loadRegWindow;
    public TextField loginField;
    public PasswordField passwordField;


    public void loadRegisrationWindow(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FormController/SignUpWindow.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void Enter(ActionEvent actionEvent) throws IOException, SQLException {

        String login = loginField.getText();
        String password = passwordField.getText();

        try {
            SocketStream socs = new SocketStream();
            socs.sendInt(2);
            socs.sendString(login);
            socs.sendString(password);
            String getStatus = socs.getString();

            if(getStatus.equals("Error"))
            {
                JOptionPane.showMessageDialog(null, "Неверно введены логин или пароль",
                        "Ошибка авторизации",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                switch (getStatus)
                {
                    case "1":
                        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Панель админинистратора");
                        stage.setScene(new Scene(root, 1250, 700));
                        stage.show();
                        break;
                    case "2":
                        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                        Parent root1 = FXMLLoader.load(getClass().getResource("/FormController/UsersPanel.fxml"));
                        Stage stage1 = new Stage();
                        stage1.setTitle("Панель пользователя");
                        stage1.setScene(new Scene(root1, 1075, 500));
                        stage1.show();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
