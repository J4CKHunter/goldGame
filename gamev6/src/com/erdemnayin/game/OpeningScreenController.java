package com.erdemnayin.game;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpeningScreenController{

    @FXML
    private JFXButton startButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    void buttonClick(ActionEvent event) throws IOException {


        if(event.getSource().equals(settingsButton)){
            //switch screen to the SettingsScreen
            Parent root = FXMLLoader.load(getClass().getResource("SettingsScreen.fxml"));
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



        }else{
            //switch screen to the PlayScreen and start the game with default values
            Parent root = FXMLLoader.load(getClass().getResource("PlayScreen.fxml"));
            Stage stage = (Stage) startButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }



}

