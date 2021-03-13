package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor_task;

    @FXML
    private JFXTextField tf_title;

    @FXML
    private JFXTextArea tf_description;

    @FXML
    private JFXButton btn_save;

    @FXML
    private ImageView btn_back;

    @FXML
    void initialize() {
        btn_back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                AnchorPane formPane2 = FXMLLoader.load(getClass().getResource("/sample/view/todolist.fxml"));
                anchor_task.getChildren().setAll(formPane2);
            } catch (IOException d){
                d.printStackTrace();
            }
        });
    }
}
