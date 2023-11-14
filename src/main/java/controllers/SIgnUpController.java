package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.others.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SIgnUpController implements Initializable {
    @FXML
    private TextField txtsignupuserid;
    @FXML
    private TextField txtsignupname;
    @FXML
    private PasswordField txtsignuppassword;
    @FXML
    private TextField txtsignupphone;
    @FXML
    private TextField txtsignupcity;
    @FXML
    private Button btnusersignup;

    @FXML
    void clkusersignup(){
        String userid = txtsignupuserid.getText();
        String name = txtsignupname.getText();
        String phone = txtsignupphone.getText();
        String password = txtsignuppassword.getText();
        String city = txtsignupcity.getText();
        try{
            boolean st = ((!userid.isEmpty())&&(!name.isEmpty())&&(!phone.isEmpty())&&(!password.isEmpty())&&(!city.isEmpty()));
            if(st){
                String checkUsername = "SELECT * FROM user WHERE username = '" + userid + "'";
                Connection connection = DBConnection.getConnection();
                try{
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(checkUsername);
                    if(resultSet.next()){
                        new PromptDialogController("Opps!","The username is already taken");
                    }
                    else{
                        try{
                            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES(?, ?, ?, ?,?)");
                            ps.setString(1, txtsignupuserid.getText());
                            ps.setString(2, txtsignuppassword.getText());
                            ps.setString(3, txtsignupphone.getText());
                            ps.setString(4, txtsignupname.getText());
                            ps.setString(5,txtsignupcity.getText());
                            ps.executeUpdate();
                            new PromptDialogController("Operation Successful", "You can now log in with the given credentials.");
                            Stage login = (Stage) btnusersignup.getScene().getWindow(); //Getting current window
                            Stage base = new Stage();
                            Parent root = null;
                            try{
                                root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
                                Scene s = new Scene(root);
                                login.setScene(s);
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        catch (SQLException e){
                            new PromptDialogController("Opps","SQL exception occurred");
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                new PromptDialogController("Error!","One or more field may be empty");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
