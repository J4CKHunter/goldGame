package com.erdemnayin.game;

import javafx.scene.shape.Rectangle;

public abstract class Golden {

    private int point;
    private Rectangle goldenRectangle;
    private int BLOCK_SIZE;
    private double x, y;

    public Golden(int point, Rectangle rectangle, int BLOCK_SIZE, double x, double y) {
        this.point = point;
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.goldenRectangle = rectangle;
        this.x = x;
        this.y = y;
        getGoldenRectangle().setTranslateX(x);
        getGoldenRectangle().setTranslateY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Rectangle getGoldenRectangle() {
        return goldenRectangle;
    }

    public void setGoldenRectangle(Rectangle goldenRectangle) {
        this.goldenRectangle = goldenRectangle;
    }

    public int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }

    public void setBLOCK_SIZE(int BLOCK_SIZE) {
        this.BLOCK_SIZE = BLOCK_SIZE;
    }
}
