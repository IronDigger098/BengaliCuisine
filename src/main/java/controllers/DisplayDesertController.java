package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.others.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DisplayDesertController implements Initializable {
    @FXML
    private Label desertlabel;
    @FXML
    private Button btndisplaydeserttodashboard;
    @FXML
    void clkdisplayvegtodashboard() {
        Stage dashboard = (Stage) btndisplaydeserttodashboard.getScene().getWindow(); //Getting current window

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
    public void fetchdata(){
        String dish = DesertController.desert;
        String sql = "SELECT description FROM fooddesert WHERE name = ?";
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,dish);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String recipe = rs.getString("description");
                desertlabel.setText("Recipe for " + dish + ": " + recipe);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchdata();
    }
}

