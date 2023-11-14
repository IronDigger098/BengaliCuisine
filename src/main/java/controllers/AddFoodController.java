package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.others.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddFoodController implements Initializable {
    @FXML
    private TextField txtdishname;
    @FXML
    private TextField txtspices;
    @FXML
    private ComboBox<String> choice;
    @FXML
    private TextField txtrecipe;
    @FXML
    private Button btndishadd;

    public void set(){
        ObservableList<String> accessLevel = FXCollections.observableArrayList();
        accessLevel.addAll("Veg", "Non-Veg","Desert");
        choice.setItems(accessLevel);
        choice.setValue("Veg");
    }

    @FXML
    void clkdishadd(){
        String cat = choice.getValue();
        String sql;
        if(cat.equals("Veg")){
            sql = "INSERT INTO foodveg VALUES(?, ?, ?)";
        } else if (cat.equals("Non-Veg")) {
            sql = "INSERT INTO foodnonveg VALUES(?, ?, ?)";
        }
        else{
            sql = "INSERT INTO fooddesert VALUES(?, ?, ?)";
        }
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,txtdishname.getText());
            preparedStatement.setString(2,txtspices.getText());
            preparedStatement.setString(3,txtrecipe.getText());
            preparedStatement.executeUpdate();
            new PromptDialogController("Operation Successful", "New Food Added");
            Stage dashboard = (Stage) btndishadd.getScene().getWindow(); //Getting current window

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
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        set();
    }
}
