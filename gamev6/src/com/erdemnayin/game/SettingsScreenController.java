package com.erdemnayin.game;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsScreenController {

    @FXML
    private JFXTextField tfWidht;

    @FXML
    private JFXTextField tfHeight;

    @FXML
    private JFXTextField tfGoldPercentage;

    @FXML
    private JFXTextField tfHiddenGoldPercentage;

    @FXML
    private JFXTextField tfGoldQuantity;

    @FXML
    private JFXTextField tfNumberOfSteps;

    @FXML
    private JFXTextField tf_A_stepCost;

    @FXML
    private JFXTextField tf_A_targetingCost;

    @FXML
    private JFXTextField tf_B_stepCost;

    @FXML
    private JFXTextField tf_B_targetingCost;

    @FXML
    private JFXTextField tf_C_stepCost;

    @FXML
    private JFXTextField tf_C_targetingCost;

    @FXML
    private JFXTextField tf_D_stepCost;

    @FXML
    private JFXTextField tf_D_targetingCost;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton startButton;

    @FXML
    private JFXButton backButton;

    private boolean error = false;

    //game's default values
    static int widht = 20;
    static int height = 20;
    static int goldenPer = 20;
    static int hidGoldPer = 10;
    static int startingPoint = 200;
    static int numOfStep = 3;
    static int A_stepCost = 5;
    static int A_tarCost = 5;
    static int B_stepCost = 5;
    static int B_tarCost = 10;
    static int C_stepCost = 5;
    static int C_tarCost = 15;
    static int D_stepCost = 5;
    static int D_tarCost = 20;
    static int BlockSize = 40;

    @FXML
    public void initialize(){
        startButton.setDisable(true);
        backButton.setDisable(true);
    }

    @FXML
    public void saveButtonClicked(ActionEvent event) {

        //save the values and show the start and the back button or show the error screen if an error occurs

        //boş veya string harici integer bir değer girin uyarısı kontrolü
        if(tfWidht.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tfHeight.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tfGoldPercentage.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tfHiddenGoldPercentage.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tfGoldQuantity.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tfNumberOfSteps.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_A_stepCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_A_targetingCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_B_stepCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_B_targetingCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_C_stepCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_C_targetingCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_D_stepCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else if(tf_D_targetingCost.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OOOOOPS");
            alert.setHeaderText("Invalid data entry!");
            alert.setContentText("Ooops, theres an error!\nFill the blanks.");
            alert.showAndWait();
            error = true;
        }else{
            try {

                widht = Integer.parseInt(tfWidht.getText().trim());
                height = Integer.parseInt(tfHeight.getText().trim());
                goldenPer = Integer.parseInt(tfGoldPercentage.getText().trim());
                hidGoldPer = Integer.parseInt(tfHiddenGoldPercentage.getText().trim());
                startingPoint = Integer.parseInt(tfGoldQuantity.getText().trim());
                numOfStep = Integer.parseInt(tfNumberOfSteps.getText().trim());
                A_stepCost = Integer.parseInt(tf_A_stepCost.getText().trim());
                A_tarCost = Integer.parseInt(tf_A_targetingCost.getText().trim());
                B_stepCost = Integer.parseInt(tf_B_stepCost.getText().trim());
                B_tarCost = Integer.parseInt(tf_B_targetingCost.getText().trim());
                C_stepCost = Integer.parseInt(tf_C_stepCost.getText().trim());
                C_tarCost = Integer.parseInt(tf_C_targetingCost.getText().trim());
                D_stepCost = Integer.parseInt(tf_D_stepCost.getText().trim());
                D_tarCost = Integer.parseInt(tf_D_targetingCost.getText().trim());

                // controlleri yap ve block size belirle
                if(widht < 2 && height < 2 ){
                    widht = 2;
                    height = 2;
                    BlockSize = 40;
                }
                else if(widht <= 42 && height <= 25 ){
                    BlockSize = 40;
                }else if(widht <= 56 && height <= 33 ){
                    BlockSize = 30;
                }else if(widht <= 84 && height <= 49 ){
                    BlockSize = 20;
                }else{
                    widht = 84  ;
                    height = 49 ;
                    BlockSize = 20 ;
                }

                error = false;

            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("OOOOOPPPSS");
                alert.setHeaderText("Invalid data entry!");
                alert.setContentText("Ooops, theres an error!\nFill the blanks with one integer value.");
                alert.showAndWait();
                error = true;
            }
        }

        if(error == false){
            tfHeight.setDisable(true);
            saveButton.setDisable(true);
            backButton.setDisable(false);
            startButton.setDisable(false);
        }

    }

    @FXML
    void buttonClicked(ActionEvent event) throws IOException {

        if(event.getSource().equals(backButton)){

            //switch screen to the OpeningScreen
            Parent root = FXMLLoader.load(getClass().getResource("OpeningScreen.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else{

            // switch screen to the PlayScreen
            Parent root = FXMLLoader.load(getClass().getResource("PlayScreen.fxml"));
            Stage stage = (Stage) startButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

}
