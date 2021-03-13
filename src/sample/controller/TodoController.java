package sample.controller;

import com.jfoenix.controls.JFXButton;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class TodoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXButton btn_additems;

    @FXML
    private Label lbl_noitems;

    @FXML
    private StackPane grid;


    @FXML
    void initialize() {


        btn_additems.setOnAction(actionEvent -> {

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), btn_additems);

            lbl_noitems.relocate(0, 20);
            lbl_noitems.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/additem.fxml"));
                anchor.getChildren().setAll(formPane);
            }catch(IOException e){
                e.printStackTrace();
            }

        });




    }
}

