package com.erdemnayin.game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SummaryScreenController {

    @FXML
    private Label labelAFinalGold;

    @FXML
    private Label labelACollected;

    @FXML
    private Label labelATotalCost;

    @FXML
    private Label labelATotalSteps;

    @FXML
    private Label labelBFinalGold;

    @FXML
    private Label labelBCollected;

    @FXML
    private Label labelBTotalCost;

    @FXML
    private Label labelBTotalSteps;

    @FXML
    private Label labelCFinalGold;

    @FXML
    private Label labelCCollected;

    @FXML
    private Label labelCTotalCost;

    @FXML
    private Label labelCTotalSteps;

    @FXML
    private Label labelDFinalGold;

    @FXML
    private Label labelDCollected;

    @FXML
    private Label labelDTotalCost;

    @FXML
    private Label labelDTotalSteps;



    public void initialize(){

        getLabelAFinalGold().setText(Game.finalGoldPointA + "");
        getLabelACollected().setText(Game.collectedGoldPointA + "");
        getLabelATotalCost().setText(Game.totalCostsA + "");
        getLabelATotalSteps().setText(Game.totalStepsA + "");

        getLabelBFinalGold().setText(Game.finalGoldPointB + "");
        getLabelBCollected().setText(Game.collectedGoldPointB + "");
        getLabelBTotalCost().setText(Game.totalCostsB + "");
        getLabelBTotalSteps().setText(Game.totalStepsB + "");

        getLabelCFinalGold().setText(Game.finalGoldPointC + "");
        getLabelCCollected().setText(Game.collectedGoldPointC + "");
        getLabelCTotalCost().setText(Game.totalCostsC + "");
        getLabelCTotalSteps().setText(Game.totalStepsC + "");

        getLabelDFinalGold().setText(Game.finalGoldPointD + "");
        getLabelDCollected().setText(Game.collectedGoldPointD + "");
        getLabelDTotalCost().setText(Game.totalCostsD + "");
        getLabelDTotalSteps().setText(Game.totalStepsD + "");



    }


    public Label getLabelAFinalGold() {
        return labelAFinalGold;
    }

    public void setLabelAFinalGold(Label labelAFinalGold) {
        this.labelAFinalGold = labelAFinalGold;
    }

    public Label getLabelACollected() {
        return labelACollected;
    }

    public void setLabelACollected(Label labelACollected) {
        this.labelACollected = labelACollected;
    }

    public Label getLabelATotalCost() {
        return labelATotalCost;
    }

    public void setLabelATotalCost(Label labelATotalCost) {
        this.labelATotalCost = labelATotalCost;
    }

    public Label getLabelATotalSteps() {
        return labelATotalSteps;
    }

    public void setLabelATotalSteps(Label labelATotalSteps) {
        this.labelATotalSteps = labelATotalSteps;
    }

    public Label getLabelBFinalGold() {
        return labelBFinalGold;
    }

    public void setLabelBFinalGold(Label labelBFinalGold) {
        this.labelBFinalGold = labelBFinalGold;
    }

    public Label getLabelBCollected() {
        return labelBCollected;
    }

    public void setLabelBCollected(Label labelBCollected) {
        this.labelBCollected = labelBCollected;
    }

    public Label getLabelBTotalCost() {
        return labelBTotalCost;
    }

    public void setLabelBTotalCost(Label labelBTotalCost) {
        this.labelBTotalCost = labelBTotalCost;
    }

    public Label getLabelBTotalSteps() {
        return labelBTotalSteps;
    }

    public void setLabelBTotalSteps(Label labelBTotalSteps) {
        this.labelBTotalSteps = labelBTotalSteps;
    }

    public Label getLabelCFinalGold() {
        return labelCFinalGold;
    }

    public void setLabelCFinalGold(Label labelCFinalGold) {
        this.labelCFinalGold = labelCFinalGold;
    }

    public Label getLabelCCollected() {
        return labelCCollected;
    }

    public void setLabelCCollected(Label labelCCollected) {
        this.labelCCollected = labelCCollected;
    }

    public Label getLabelCTotalCost() {
        return labelCTotalCost;
    }

    public void setLabelCTotalCost(Label labelCTotalCost) {
        this.labelCTotalCost = labelCTotalCost;
    }

    public Label getLabelCTotalSteps() {
        return labelCTotalSteps;
    }

    public void setLabelCTotalSteps(Label labelCTotalSteps) {
        this.labelCTotalSteps = labelCTotalSteps;
    }

    public Label getLabelDFinalGold() {
        return labelDFinalGold;
    }

    public void setLabelDFinalGold(Label labelDFinalGold) {
        this.labelDFinalGold = labelDFinalGold;
    }

    public Label getLabelDCollected() {
        return labelDCollected;
    }

    public void setLabelDCollected(Label labelDCollected) {
        this.labelDCollected = labelDCollected;
    }

    public Label getLabelDTotalCost() {
        return labelDTotalCost;
    }

    public void setLabelDTotalCost(Label labelDTotalCost) {
        this.labelDTotalCost = labelDTotalCost;
    }

    public Label getLabelDTotalSteps() {
        return labelDTotalSteps;
    }

    public void setLabelDTotalSteps(Label labelDTotalSteps) {
        this.labelDTotalSteps = labelDTotalSteps;
    }
}
