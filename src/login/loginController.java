package login;

import com.sun.media.jfxmediaimpl.platform.Platform;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class loginController implements Initializable {
   
    @FXML
    private Pane main_area;
    
    
   @FXML
    private void closeApp(MouseEvent event) {
        System.exit(0);
    }
    @FXML
    private void createAccount(MouseEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/Createaccount/Createaccount.fxml"));
        
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
