package FormController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEmployeePanelController {
    public Button addAllButton;
    public Button addHalfButton;
    public Button back;

    public void back(javafx.event.ActionEvent actionEvent)
    {
        back.getScene().getWindow().hide();
    }

    public void AddAll(ActionEvent actionEvent) throws IOException
    {
        Parent root1 = FXMLLoader.load(getClass().getResource("/FormController/AddAll.fxml"));
        Stage stage1 = new Stage();
        stage1.setTitle("Панель добавления");
        stage1.setScene(new Scene(root1, 600, 400));
        stage1.show();

    }

    public void AddHalf(ActionEvent actionEvent) throws IOException
    {
        Parent root1 = FXMLLoader.load(getClass().getResource("/FormController/AddHalf.fxml"));
        Stage stage1 = new Stage();
        stage1.setTitle("Панель добавления");
        stage1.setScene(new Scene(root1, 600, 400));
        stage1.show();

    }
}