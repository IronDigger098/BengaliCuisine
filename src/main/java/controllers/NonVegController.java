package main.java.controllers;
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
public class NonVegController implements Initializable{
    @FXML
    private Button btnnonvegsearch;
    @FXML
    private Button btnbackfromnonveg;
    @FXML
    private TextField txtnonvegsearch;
    public static String nonveg="";
    @FXML
    void clkbackfromnonveg(){
        Stage back = (Stage) btnbackfromnonveg.getScene().getWindow(); //Getting current window

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

    @FXML
    void clksearchnonveg(){
        String dishnonveg = txtnonvegsearch.getText();
        String src = "SELECT * FROM foodveg WHERE name = ?";
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement(src);
            ps.setString(1,dishnonveg);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                new PromptDialogController("Success","Dish Found!");
                nonveg=txtnonvegsearch.getText();
                Stage display = (Stage) btnnonvegsearch.getScene().getWindow(); //Getting current window
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
                Stage dashboard = (Stage) btnnonvegsearch.getScene().getWindow(); //Getting current window

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
