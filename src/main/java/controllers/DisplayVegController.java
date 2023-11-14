package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.others.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DisplayVegController implements Initializable {
    @FXML
    private Label displayveg;
    @FXML
    private Button btndisplayvegtodashboard;
    @FXML
    void clkdisplayvegtodashboard(){
        Stage dashboard = (Stage) btndisplayvegtodashboard.getScene().getWindow(); //Getting current window

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
        String dish = VegController.veg;
        String sql = "SELECT description FROM foodveg WHERE name = ?";
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,dish);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String recipe = rs.getString("description");
                displayveg.setText("Recipe for " + dish + ": " + recipe);
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
