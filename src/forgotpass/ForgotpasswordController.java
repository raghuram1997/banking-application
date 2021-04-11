
package forgotpass;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import login.Bankings;


public class ForgotpasswordController implements Initializable {
      
    @FXML
    private TextField accountno;
    @FXML
    private ComboBox<String> sq;
    @FXML
    private TextField ans;
    ObservableList<String> list = FXCollections.observableArrayList("what is your pet name?", "what is your town?", "what is your nickname?");

    
    public void backToLogin(MouseEvent event) throws IOException{
        Bankings.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/login/login.fxml")));
        
    }
    public void closeApp(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
    public void recoverPassword(MouseEvent event){
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankings", "root", "");
            String sql = "SELECT * FROM userdata WHERE AccountNo=? and SecurityQuestion=? and Answer=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, accountno.getText());
            ps.setString(2, sq.getValue());
            ps.setString(3, ans.getText());

            rs = ps.executeQuery();
            if(rs.next()){
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Password Recovery");
                a.setHeaderText("Below is your password");
                a.setContentText("Account No: "+rs.getString("AccountNo")+"\nPIN:  "+rs.getString("PIN"));
                
                a.showAndWait();
                }
                else
               {
                   Alert a = new Alert(Alert.AlertType.ERROR);
                   a.setTitle("Error");
                   a.setHeaderText("Wrong Data");
                   a.setContentText("Please try again!!!");
                   a.showAndWait();
    }
                

    }catch(Exception e){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText("Error in recovery");
        a.setContentText("There is some error. Try again!!!"+e.getMessage());
        a.showAndWait();
    }
    }

        @Override
    public void initialize(URL url, ResourceBundle rb) {
             sq.setItems(list);
    }    
    
}
