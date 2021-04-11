
package dashboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import login.loginController;


public class MainScreenController implements Initializable {
   
      @FXML
      private Label name;
      @FXML
      private Label body;
      
      public void setInfo(){
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankings", "root", "");
            String sql = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, loginController.acc);
              
            rs = ps.executeQuery();
            if(rs.next()){
                name.setText(rs.getString("Name"));
               
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
        body.setText("Durga Bank Limited is the fifth largest commercial bank in Pakistan and is a subsidiary\n of Ibrahim Group. Durga Bank, with its registered\n Office in india, is one of the largest banks within the country\n with over 1350 branches and ATMs.");
        setInfo();
    }    
    
}
