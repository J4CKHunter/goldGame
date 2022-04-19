package com.erdemnayin.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HiddenGold extends Golden {

    private boolean collectible = false;

    public HiddenGold(int point, Rectangle rectangle, int BLOCK_SIZE, double x, double y) {
        super(point, rectangle, BLOCK_SIZE, x, y);
        getGoldenRectangle().setFill(Color.web("#FFC107"));
        getGoldenRectangle().setVisible(false);
        this.collectible = false;
    }

    public boolean isCollectible(){
        return collectible;
    }

    public void setCollectible(Boolean value){
        this.collectible = value;
    }
}
