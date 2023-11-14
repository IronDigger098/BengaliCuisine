package main.java.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class DesertController implements Initializable{
    @FXML
    private Button btndesertsearch;
    @FXML
    private Button btnbackfromdesert;
    @FXML
    public TextField txtdesertsearch;
    public static String desert = "";

    @FXML
    void clkdesertsearch(){
        String dishdesert = txtdesertsearch.getText();
        String src = "SELECT * FROM fooddesert WHERE name = ?";
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement(src);
            ps.setString(1,dishdesert);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                new PromptDialogController("Success","Dish Found!");
                desert = txtdesertsearch.getText();
                Stage display = (Stage) btndesertsearch.getScene().getWindow(); //Getting current window
                Stage base = new Stage();
                Parent root = null;
                try{
                    root = FXMLLoader.load(getClass().getResource("/main/resources/view/displaydesert.fxml"));
                    Scene s = new Scene(root);
                    display.setScene(s);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else{
                new PromptDialogController("Sorry","Dish not found!\nYou can add this dish anytime using the add food option");
                Stage dashboard = (Stage) btndesertsearch.getScene().getWindow(); //Getting current window

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
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    void clkbackfromdesert(){
        Stage back = (Stage) btnbackfromdesert.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/dashboard.fxml"));
            Scene s = new Scene(root);
            back.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
