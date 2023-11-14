package main.java.controllers;

import javafx.event.ActionEvent;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnsignup;
    @FXML
    void clklogin(ActionEvent event){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if(username.isEmpty()||password.isEmpty()){
            new PromptDialogController("Error!","Incorrect Username/Password");
        }
        else{
            String selectData = "SELECT * FROM user WHERE " + "username = ? and password = ?";
            Connection connection = DBConnection.getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectData);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    new PromptDialogController("OK","Login Successful");
                    Stage dashboard = (Stage) btnLogIn.getScene().getWindow(); //Getting current window

                    Stage base = new Stage();
                    Parent root = null;
                    try{
                        root = FXMLLoader.load(getClass().getResource("/main/resources/view/dashboard.fxml"));
                        Scene s = new Scene(root);
                        dashboard.setScene(s);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    new PromptDialogController("Error","Incorrect username/password");
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    void clksignup(ActionEvent event){
        Stage useradd = (Stage) btnsignup.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/signup.fxml"));
            Scene s = new Scene(root);
            useradd.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
