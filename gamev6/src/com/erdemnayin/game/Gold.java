package com.erdemnayin.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gold extends Golden{


    public Gold(int point, Rectangle rectangle, int BLOCK_SIZE, double x, double y) {
        super(point, rectangle, BLOCK_SIZE, x, y);
        getGoldenRectangle().setFill(Color.web("#FF5722"));
    }


    // new Rectangle(BLOCK_SIZE,BLOCK_SIZE) olarak yolla aşağıya.sonra bu değer geri dönünce kontrol et var mı yok mu
    // eğer varsa yeni oluştursun.
    // pointler de random olcak 5 10 15 20 25 miydi neydi bunlardan biri.bi arrayliste atıp belki random olarak çekeriz

}
