
package Createaccount;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import login.Bankings;


public class CreateaccountController implements Initializable {
    private FileChooser filechooser = new FileChooser();
    private File file;
    private FileInputStream fis;
    @FXML
    private ImageView pic;
    @FXML
    private TextField name;
    @FXML
    private TextField idcardno;
    @FXML
    private TextField mobileno;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField accountno;
    @FXML
    private TextField pin;
    @FXML
    private TextField balance;
    @FXML
    private TextField answer;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<String> martialstatus;
    @FXML
    private ComboBox<String> religion;
    @FXML
    private ComboBox<String> accounttype;
    @FXML
    private ComboBox<String> questions;
    
    ObservableList<String> list = FXCollections.observableArrayList("Male", "Female","other");
    ObservableList<String> list1 = FXCollections.observableArrayList("Single", "Married");
    ObservableList<String> list2= FXCollections.observableArrayList("Islam", "Christian","Hindu", "others");
    ObservableList<String> list3 = FXCollections.observableArrayList("Saving", "Current");
    ObservableList<String> list4 = FXCollections.observableArrayList("what is your pet name?", "what is your town?", "what is your nickname?");



    public void backToLogin(MouseEvent event) throws IOException{
        Bankings.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/login/login.fxml")));
        
    }
    public void closeApp(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
    public void setUpPic(MouseEvent event){
        filechooser.getExtensionFilters().add(new ExtensionFilter("Images Files", "*.png", "*.jpg"));
        file = filechooser.showOpenDialog(null);
        if(file!=null){
            Image img = new Image(file.toURI().toString(), 150,150, true, true);
            pic.setImage(img);
            pic.setPreserveRatio(true);
                  
        }
    }
    public void newAccount(MouseEvent event){
        Connection con =null;
        PreparedStatement ps = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            String sql = "INSERT INTO userdata(Name, ICN, MobileNo, Gender, Religion, MaritialStatus, DOB, city, Address, AccountNo, PIN, AccountType, Balance, SecurityQuestion, Answer, ProfilePic) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, name.getText());
            ps.setString(2, idcardno.getText());
            ps.setString(3, mobileno.getText());
            ps.setString(4, gender.getValue());
            ps.setString(5, religion.getValue());
            ps.setString(6, martialstatus.getValue());
            ps.setString(7, ((TextField)dob.getEditor()).getText());
            ps.setString(8, city.getText());
            ps.setString(9, address.getText());
            ps.setString(10, accountno.getText());
            ps.setString(11, pin.getText());
            ps.setString(12, accounttype.getValue());
            ps.setString(13, balance.getText());
            ps.setString(14, questions.getValue());
            ps.setString(15, answer.getText());
            fis = new FileInputStream(file);
            ps.setBinaryStream(16, (InputStream)fis, (int)file.length());
            int i = ps.executeUpdate();
            if(i>0){
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Account Created");
                a.setHeaderText("Account Created Successfully.");
                a.setContentText("Your account has been created Successfully. You can login with your account no and pin.");
                a.showAndWait();
                }
                else
               {
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Account Not created");
                a.setContentText("Your account is not created. There is some error. TRY AGAIN!!!");
                a.showAndWait();
                }

    }catch(Exception e){
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText("Error in creating account");
        a.setContentText("Your account is not created. There is some technical issue." + e.getMessage());
        a.showAndWait();
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.setItems(list);
        martialstatus.setItems(list1);
        religion.setItems(list2);
        accounttype.setItems(list3);
        questions.setItems(list4);

    }    
    
}
