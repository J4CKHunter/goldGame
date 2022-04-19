package com.erdemnayin.game;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayerA extends Player {

    public PlayerA(int goldPoint, int BLOCK_SIZE, int moveCost, int targetingCost) {

        super(goldPoint, BLOCK_SIZE, moveCost, targetingCost);
        getPlayerRectangle().setFill(Color.web("#d50000"));

    }

    @Override
    public void findTarget(ArrayList<Golden> collectible) {

        double distance = 999999999;
        Golden golden = null;

        for (Golden temp:collectible) {

            double x = manhattanDistance(temp.getGoldenRectangle());
            if(x < distance){
                distance = x;
                golden = temp;
            }

        }

        try {
            setTarget(golden.getGoldenRectangle());
            setTargetGolden(golden);
            setPath();
            setGoldPoint(getGoldPoint() - getTargetingCost());
        }catch (Exception e ){
            System.out.println("Hedef yok,null");
        }



    }
}
