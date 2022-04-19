package com.erdemnayin.game;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayerB extends Player {

    public PlayerB(int goldPoint, int BLOCK_SIZE, int moveCost, int targetingCost) {

        super(goldPoint, BLOCK_SIZE, moveCost, targetingCost);
        getPlayerRectangle().setFill(Color.web("#00C853"));

    }

    @Override
    public void findTarget(ArrayList<Golden> collectible) {

        double distance,goldenPoint,profit = -999999999 ,cost;
        Golden golden = null;

        for (Golden temp:collectible) {

            goldenPoint = temp.getPoint();
            distance = manhattanDistance(temp.getGoldenRectangle());
            cost = distance * getMoveCost();
            double x = temp.getPoint() - cost;

            if(x > profit){
                profit = x;
                golden=temp;
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
