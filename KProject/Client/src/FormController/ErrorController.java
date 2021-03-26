package FormController;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ErrorController {
    public Button ok;

    public void ok(ActionEvent event)
    {
        ok.getScene().getWindow().hide();
    }
}
