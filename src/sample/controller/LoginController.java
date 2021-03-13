package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Resultset;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;
import sample.model.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btn_login;

    @FXML
    private JFXButton btn_signup;

    @FXML
    private JFXTextField tf_email_login;

    @FXML
    private JFXPasswordField tf_password_login;

    @FXML
    private Label lbl_errormsg;

    private DatabaseHandler db;

    @FXML
    void initialize() {


        btn_signup.setOnAction(actionEvent -> {
            Parent login = null;
            try {
                login = FXMLLoader.load(getClass().getResource("/sample/view/signup.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene signupScene = new Scene(login);
            Stage signStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            signStage.setScene(signupScene);
            signStage.show();
        });

        btn_login.setOnAction(actionEvent -> {
            String loginUser = tf_email_login.getText().trim();
            String loginPass = tf_password_login.getText().trim();

            User user = new User();
            user.setEmail(loginUser);
            user.setPass(loginPass);

            db = new DatabaseHandler();
            ResultSet userRow = db.getUser(user);
            int counter = 0;

            // .next = next item available
            try{
                while (userRow.next()){
                    counter++;
                }

                if(counter == 1){
                    Parent login2 = null;
                    try {
                        login2 = FXMLLoader.load(getClass().getResource("/sample/view/todolist.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene signupScene = new Scene(login2);
                    Stage signStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

                    signStage.setScene(signupScene);
                    signStage.show();
                } else {
                    lbl_errormsg.setText("Invalid email or password");
                    Shaker shaker = new Shaker(tf_email_login);
                    shaker.shake();
                    Shaker shaker2 = new Shaker(tf_password_login);
                    shaker2.shake();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        });



    }



}

