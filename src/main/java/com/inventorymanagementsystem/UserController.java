package com.inventorymanagementsystem;

import com.inventorymanagementsystem.config.Database;
import com.inventorymanagementsystem.entity.User;
import com.inventorymanagementsystem.DashboardController.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
public class UserController implements Initializable {

    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    @FXML
    private TextField usr_field_username;

    @FXML
    private TextField usr_field_email;

    @FXML
    private TextField usr_field_phone;

    @FXML
    private TextField usr_field_password1;

    @FXML
    private TextField usr_field_password2;

    @FXML
    private ComboBox<String> usr_comboRol;

    @FXML
    private Button btn_saveUser;

    @FXML
    private Button btn_exit;

    public void onExit(){
        btn_exit.getScene().getWindow().hide();
    }

    public void addNewUser(){
        String rol = usr_comboRol.getValue();
        if(checkForUserAvailability()&&checkForEmailAvailability()){
            if(checkPassword()){
                System.out.print("AGREGANDO NUEVO USUARIO");
                Connection connection = Database.getInstance().connectDB();
                String sql="INSERT INTO users(username,password,email,phone,rol) VALUES(?,?,?,?,?)";
                try{
                    preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,usr_field_username.getText());
                    preparedStatement.setString(2,usr_field_password1.getText());
                    preparedStatement.setString(3,usr_field_email.getText());
                    preparedStatement.setString(4,usr_field_phone.getText());
                    preparedStatement.setString(5,rol);
                    int result=preparedStatement.executeUpdate();

                    if(result>0){
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Message");
                        alert.setHeaderText(null);
                        alert.setContentText("USUARIO CREADO CORRECTAMENTE.");
                        alert.showAndWait();
                        onExit();

                    }else {

                    }
                }catch (Exception err) {
                    err.printStackTrace();
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText(err.getMessage());
                    alert.showAndWait();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Las password deben ser iguales.");
                alert.showAndWait();
            }
        }
    }


    public boolean checkPassword(){
        String pass1 = usr_field_password1.getText();
        String pass2 = usr_field_password2.getText();
        return pass1.equals(pass2);
    }

    public boolean checkForUserAvailability(){
        Connection connection = Database.getInstance().connectDB();
        String sql="SELECT username FROM users WHERE username=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,usr_field_username.getText());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Nombre de usuario ya registrado.");
                alert.showAndWait();
                return false;
            }else {
                return true;
            }
        }catch (Exception err){
            err.printStackTrace();
        }
        return false;
    }

    public boolean checkForEmailAvailability(){
        Connection connection = Database.getInstance().connectDB();
        String sql="SELECT email FROM users WHERE email=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,usr_field_email.getText());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Email ya esta registrado.");
                alert.showAndWait();
                return false;
            }else {
                return true;
            }
        }catch (Exception err){
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
