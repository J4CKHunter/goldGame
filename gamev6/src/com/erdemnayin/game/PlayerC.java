package com.erdemnayin.game;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlayerC extends Player {

    public PlayerC(int goldPoint, int BLOCK_SIZE, int moveCost, int targetingCost) {

        super(goldPoint, BLOCK_SIZE, moveCost, targetingCost);
        getPlayerRectangle().setFill(Color.web("#2962FF"));

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

    //opens closest 2 hidden golds every round
    public void openHiddenGolds(ArrayList<Golden> collectible, ArrayList <HiddenGold> hiddenGoldArrayList){

        double distance = 999999999;
        HiddenGold hiddenGold = null;

        for(int i = 0; i < 2 ; i++){

            if(hiddenGoldArrayList.isEmpty())
                break;

            for (HiddenGold temp:hiddenGoldArrayList) {

                double x = manhattanDistance(temp.getGoldenRectangle());
                if(x < distance){
                    distance = x;
                    hiddenGold = temp;
                }

            }

            collectible.add(hiddenGold);
            hiddenGold.setCollectible(true);
            hiddenGold.getGoldenRectangle().setVisible(true);
            hiddenGoldArrayList.remove(hiddenGold);

            distance = 999999999;
            hiddenGold = null;
        }

    }

}
