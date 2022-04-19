package com.erdemnayin.game;

import javafx.scene.paint.Color;
import org.omg.CORBA.IRObject;

import java.util.ArrayList;

public class PlayerD extends Player {

    private ArrayList<Golden> opponentsTargets;
    private ArrayList<Double> opponentsPathSizes;

    public PlayerD(int goldPoint, int BLOCK_SIZE, int moveCost, int targetingCost) {

        super(goldPoint, BLOCK_SIZE, moveCost, targetingCost);
        getPlayerRectangle().setFill(Color.web("#607D8B"));
        opponentsTargets = new ArrayList<>();
        opponentsPathSizes = new ArrayList<>();

    }

    //çağrıldığı yerde arraylistleri ilk önce clearla, sonra null değillerse bu listelere ekle
    // ve işlemleri bu listeler üzerinden yap.
    public void setOpponentsTargetsAndLengths(Golden target, double targetPathLength){

        getOpponentsTargets().add(target);
        getOpponentsPathSizes().add(targetPathLength);

    }

    public ArrayList<Golden> getOpponentsTargets() {
        return opponentsTargets;
    }

    public ArrayList<Double> getOpponentsPathSizes() {
        return opponentsPathSizes;
    }



    /*Diğer oyuncuların yapacağı hamleleri önceden sezme yeteneği bulunur.
Diğer oyuncuların hedeflediği altınlara onlardan önce erişemiyorsa bu altınları hariç tutar
ve hedef olarak diğer altın kareler içerisinden en karlı olanı seçer.
Her hamle varsayılan 5, her hedef belirleme varsayılan 20 altın maliyetindedir.*/

    @Override
    public void findTarget(ArrayList<Golden> collectible) {

        //clone arraylists to use them

        ArrayList<Golden> cloneCollectible = (ArrayList<Golden>) collectible.clone();
        ArrayList<Golden> cloneOpponentsTargets = (ArrayList<Golden>) getOpponentsTargets().clone();
        ArrayList<Double> cloneOpponentsPathSizes = (ArrayList<Double>) getOpponentsPathSizes().clone();

        for (int i = 0; i < cloneOpponentsTargets.size() ; i++) {

            double manhattan = manhattanDistance(cloneOpponentsTargets.get(i).getGoldenRectangle());

            if (cloneOpponentsPathSizes.get(i) < manhattan ){
                cloneCollectible.remove(cloneOpponentsTargets.get(i));
            }

        }



        double distance,goldenPoint,profit = -999999999 ,cost;
        Golden golden = null;

        for (Golden temp:cloneCollectible) {

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

