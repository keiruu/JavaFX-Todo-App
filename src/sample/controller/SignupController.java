package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btn_signup;

    @FXML
    private JFXPasswordField tf_password_signup;

    @FXML
    private JFXTextField tf_firstname;

    @FXML
    private JFXTextField tf_lastname;

    @FXML
    private JFXTextField tf_email_signup;

    @FXML
    private JFXCheckBox cb_male;

    @FXML
    private JFXCheckBox cb_female;

    @FXML
    private JFXCheckBox cb_nonbinary;

    @FXML
    private JFXButton btn_signin_fromsignup;

    @FXML
    private Label lbl_error;

    @FXML
    void initialize() {

        btn_signup.setOnAction(actionEvent -> {
            createUser();
        });

        btn_signin_fromsignup.setOnAction(actionEvent -> {
            Parent signup = null;
            try {
                signup = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene signupScene = new Scene(signup);
            Stage signStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            signStage.setScene(signupScene);
            signStage.show();
        });
    }

    private void createUser(){
        DatabaseHandler db = new DatabaseHandler();
        String gender = "";

        String name = tf_firstname.getText();
        String lastname = tf_lastname.getText();
        String email = tf_email_signup.getText();
        String pass = tf_password_signup.getText();

        if (cb_female.isSelected()){
            gender = "Female";
        } else if (cb_male.isSelected()){
            gender = "Male";
        } else if (cb_nonbinary.isSelected()){
            gender = "Non-binary";
        } else {
            gender = "None";
        }

        System.out.println(pass);
        User user = new User(name, lastname, email, pass, gender);

        if(!tf_firstname.getText().equals("") || !tf_lastname.getText().equals("")|| !tf_email_signup.getText().equals("") || !tf_password_signup.getText().equals("") || !gender.equals("None")){
            db.signupUser(user);
        } else {
            lbl_error.setText("Please fill out all fields");
        }
    }

}

