package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button btnveg;
    @FXML
    private Button btnnonveg;
    @FXML
    private Button btndesert;
    @FXML
    private Button btnadduser;
    @FXML
    private Button btnlogout;
    @FXML
    private Button btnaddfood;
    @FXML
    void clkveg(){
        Stage vegg = (Stage) btnveg.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/veg.fxml"));
            Scene s = new Scene(root);
            vegg.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void clknonveg(){
        Stage nonvegg = (Stage) btnnonveg.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/nonveg.fxml"));
            Scene s = new Scene(root);
            nonvegg.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void clkdesert(){
        Stage desertt = (Stage) btndesert.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/desert.fxml"));
            Scene s = new Scene(root);
            desertt.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void clklogout(){
        Stage loggedout = (Stage) btnlogout.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/login.fxml"));
            Scene s = new Scene(root);
            loggedout.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void clkaddfood(){
        Stage addfood = (Stage) btnaddfood.getScene().getWindow(); //Getting current window

        Stage base = new Stage();
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/main/resources/view/addfood.fxml"));
            Scene s = new Scene(root);
            addfood.setScene(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
