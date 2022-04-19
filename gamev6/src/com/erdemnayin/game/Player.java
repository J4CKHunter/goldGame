package com.erdemnayin.game;

import javafx.collections.ObservableList;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Player {

    private int goldPoint;
    private int moveCost;
    private int targetingCost;


    private Rectangle playerRectangle;
    private int BLOCK_SIZE;
    private Rectangle target;
    private Golden targetGolden;
    private ArrayList<Direction> path;

    private boolean hasTarget = false;


    public enum Direction{
        UP,DOWN,LEFT,RIGHT
    }

    public Player(int goldPoint, int BLOCK_SIZE, int moveCost, int targetingCost) {
        this.goldPoint = goldPoint;
        this.moveCost = moveCost;
        this.targetingCost = targetingCost;
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.playerRectangle = new Rectangle(BLOCK_SIZE,BLOCK_SIZE);
        this.target=new Rectangle();
        target.setTranslateX(-1);
        target.setTranslateY(-1);
    }


    public Golden getTargetGolden() {
        return targetGolden;
    }

    public void setTargetGolden(Golden targetGolden) {
        this.targetGolden = targetGolden;
    }

    public void setPath(ArrayList<Direction> path) {
        this.path = path;
    }

    public boolean getHasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public int getGoldPoint() {
        return goldPoint;
    }

    public void setGoldPoint(int goldPoint) {
        this.goldPoint = goldPoint;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }

    public int getTargetingCost() {
        return targetingCost;
    }

    public void setTargetingCost(int targetingCost) {
        this.targetingCost = targetingCost;
    }

    public Rectangle getPlayerRectangle() {
        return this.playerRectangle;
    }

    public void setPlayerRectangle(Rectangle playerRectangle) {
        this.playerRectangle = playerRectangle;
    }

    public int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }

    public void setBLOCK_SIZE(int BLOCK_SIZE) {
        this.BLOCK_SIZE = BLOCK_SIZE;
    }


    public void move(Direction direction){
        switch (direction){

            case UP:
                getPlayerRectangle().setTranslateX(getPlayerRectangle().getTranslateX());
                getPlayerRectangle().setTranslateY(getPlayerRectangle().getTranslateY() - BLOCK_SIZE);
                setGoldPoint(getGoldPoint() - moveCost);
                break;
            case DOWN:
                getPlayerRectangle().setTranslateX(getPlayerRectangle().getTranslateX());
                getPlayerRectangle().setTranslateY(getPlayerRectangle().getTranslateY() + BLOCK_SIZE);
                setGoldPoint(getGoldPoint() - moveCost);
                break;
            case LEFT:
                getPlayerRectangle().setTranslateX(getPlayerRectangle().getTranslateX() - BLOCK_SIZE);
                getPlayerRectangle().setTranslateY(getPlayerRectangle().getTranslateY());
                setGoldPoint(getGoldPoint() - moveCost);
                break;
            case RIGHT:
                getPlayerRectangle().setTranslateX(getPlayerRectangle().getTranslateX() + BLOCK_SIZE);
                getPlayerRectangle().setTranslateY(getPlayerRectangle().getTranslateY());
                setGoldPoint(getGoldPoint() - moveCost);
                break;
        }
    }


    public Rectangle getTarget() {
        return target;
    }

    public void setTarget(Rectangle target) {
        this.target = target;
    }

    public abstract void findTarget(ArrayList<Golden> collectible);

    public ArrayList<Direction> getPath() {
        return path;
    }

    public void setPath() {
        ArrayList<Direction> pathList = new ArrayList<>();

        double x = this.playerRectangle.getTranslateX() - this.target.getTranslateX();
        double y = this.playerRectangle.getTranslateY() - this.target.getTranslateY();


        //oyuncu altının solunda. oyuncunun sağa gitmesi gerek
        if( x > 0 ){
            for(int i = 0; i < ((int)Math.abs(x) / BLOCK_SIZE); i++){
                pathList.add(Direction.LEFT);
            }
        }

        //oyuncu altının sağında. oyuncunun sola gitmesi gerek
        else if( x < 0 ){
            for(int i = 0; i < ((int)Math.abs(x) / BLOCK_SIZE); i++){
                pathList.add(Direction.RIGHT);
            }
        }

        //oyuncu altının üstünde. oyuncunun aşağı gitmesi gerek.
        if( y > 0 ){
            for(int i = 0; i < ((int)Math.abs(y) / BLOCK_SIZE); i++){
                pathList.add(Direction.UP);
            }
        }

        //oyuncu altının aşağısında. oyuncunun yukarı gitmesi gerek.
        else if( y < 0 ){
            for(int i = 0; i < ((int)Math.abs(y) / BLOCK_SIZE); i++){
                pathList.add(Direction.DOWN);
            }
        }


        Collections.shuffle(pathList);
        this.path = pathList;
    }

    public double manhattanDistance(Rectangle rectangle){

        return (Math.abs(rectangle.getTranslateX() - getPlayerRectangle().getTranslateX())/40)
                + (Math.abs(rectangle.getTranslateY() - getPlayerRectangle().getTranslateY())/40);

    }
}
