
package Createaccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import login.Bankings;

public class CreateaccountController implements Initializable {
    public void backToLogin(MouseEvent event) throws IOException{
        Bankings.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/login/loginScreen.fxml")));
        
    }
    public void closeApp(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
