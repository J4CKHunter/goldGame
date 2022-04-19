package com.erdemnayin.game;

import com.jfoenix.controls.JFXListView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PlayScreenController {


    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane pane;

    @FXML
    private VBox vBox;

    @FXML
    private Label labelPlayerAPoint;

    @FXML
    private Label labelPlayerBPoint;

    @FXML
    private Label labelPlayerCPoint;

    @FXML
    private Label labelPlayerDPoint;

    public Label getLabelPlayerAPoint() {
        return labelPlayerAPoint;
    }


    public void initialize(){


        borderPane.setPrefWidth(200 + SettingsScreenController.widht * SettingsScreenController.BlockSize);
        borderPane.setPrefHeight(SettingsScreenController.height * SettingsScreenController.BlockSize);

        Game game = new Game(pane, labelPlayerAPoint, labelPlayerBPoint, labelPlayerCPoint, labelPlayerDPoint, SettingsScreenController.BlockSize);
        Stage stage = Main.stage;
        game.createContent();
        Scene scene = stage.getScene();
        stage.setScene(scene);
        stage.show();
        game.setRunning(true);
        game.getTimeline().play();


    }


}
