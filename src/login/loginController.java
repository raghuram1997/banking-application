package login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static login.Bankings.stage;

public class loginController implements Initializable {
    
    public static Stage stage = null;
    public static String acc;
    @FXML
    private Pane main_area;
    @FXML
    private TextField accountno;
    @FXML
    private PasswordField pin;
    
    
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
    @FXML
    private void forgotPassword(MouseEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/forgotpass/forgotpassword.fxml"));
        
        main_area.getChildren().removeAll();
        main_area.getChildren().addAll(fxml);
    }
    public void loginAccount(MouseEvent event){
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankings", "root", "");
            String sql = "SELECT * FROM userdata WHERE AccountNo=? and PIN=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, accountno.getText());
            ps.setString(2, pin.getText());
            acc = accountno.getText();
            JOptionPane.showMessageDialog(null, acc);
            rs = ps.executeQuery();
            if(rs.next()){
                 ((Node)event.getSource()).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/design/design.css").toExternalForm());
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
                this.stage = stage;
                }
                else
               {
                   Alert a = new Alert(Alert.AlertType.ERROR);
                   a.setTitle("Error");
                   a.setHeaderText("Error in Login");
                   a.setContentText("Your account number or password is wrong.Enter again!!!");
                   a.showAndWait();
    }
                

    }catch(Exception e){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText("Error in Login");
        a.setContentText("There is some error. Try again!!!");
        a.showAndWait();
    }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
