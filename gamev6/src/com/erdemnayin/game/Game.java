package com.erdemnayin.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {


    public enum Direction{
        UP,DOWN,LEFT,RIGHT
    }

    private int BLOCK_SIZE ;

    private int APP_W;
    private int APP_H;

    private double TOTAL_GOLD_COUNT;
    private double HIDDEN_GOLD_COUNT;
    private double GOLD_COUNT;



    int order = 1;

    //for summary screen data
    public static int totalStepsA;
    public static int totalStepsB;
    public static int totalStepsC;
    public static int totalStepsD;

    public static int finalGoldPointA;
    public static int finalGoldPointB;
    public static int finalGoldPointC;
    public static int finalGoldPointD;

    public static int collectedGoldPointA;
    public static int collectedGoldPointB;
    public static int collectedGoldPointC;
    public static int collectedGoldPointD;

    public static int totalCostsA;
    public static int totalCostsB;
    public static int totalCostsC;
    public static int totalCostsD;


    private Pane pane;

    private Label labelPlayerAPoint;

    private Label labelPlayerBPoint;

    private Label labelPlayerCPoint;

    private Label labelPlayerDPoint;

    private Direction direction;
    private boolean moved = false;
    private boolean running = false;

    private Timeline timeline = new Timeline();

    private ArrayList<Player> playersArrayList = new ArrayList<>();
    private ArrayList<Gold> goldsArraylist = new ArrayList<>();
    private ArrayList<HiddenGold> hiddenGoldsArraylist = new ArrayList<>();
    private ArrayList<Golden> collectibleArrayList = new ArrayList<>();
    private ArrayList<Integer> pointsList = new ArrayList<>();
    private ArrayList<Player> eliminated = new ArrayList<>();

    Random rand = new Random();

    public Game(Pane pane, Label labelPlayerAPoint, Label labelPlayerBPoint, Label labelPlayerCPoint, Label labelPlayerDPoint, int blocksize) {

        this.BLOCK_SIZE = blocksize;
        this.APP_W = SettingsScreenController.widht * BLOCK_SIZE;
        this.APP_H = SettingsScreenController.height * BLOCK_SIZE;

        TOTAL_GOLD_COUNT = SettingsScreenController.widht * SettingsScreenController.height * SettingsScreenController.goldenPer / 100;
        HIDDEN_GOLD_COUNT = TOTAL_GOLD_COUNT * SettingsScreenController.hidGoldPer / 100;
        GOLD_COUNT= TOTAL_GOLD_COUNT - HIDDEN_GOLD_COUNT;

        this.pane = pane;
        this.labelPlayerAPoint = labelPlayerAPoint;
        this.labelPlayerBPoint = labelPlayerBPoint;
        this.labelPlayerCPoint = labelPlayerCPoint;
        this.labelPlayerDPoint = labelPlayerDPoint;

        pointsList.add(5);
        pointsList.add(10);
        pointsList.add(15);
        pointsList.add(20);

        totalStepsA = 0;
        totalStepsB = 0;
        totalStepsC = 0;
        totalStepsD = 0;

        collectedGoldPointA = 0;
        collectedGoldPointB = 0;
        collectedGoldPointC = 0;
        collectedGoldPointD = 0;

        totalCostsA = 0;
        totalCostsB = 0;
        totalCostsC = 0;
        totalCostsD = 0;

        System.out.println("game cons app w" + APP_W);
        System.out.println("game cons settingscontroller app w" + SettingsScreenController.widht);

    }

    public Parent createContent(){


        Pane root = pane;

        root.setPrefSize(APP_W,APP_H);

        System.out.println(getTOTAL_GOLD_COUNT());
        System.out.println("app w" + APP_W);
        System.out.println("app h" + APP_H);

        // txt içindeki bir önceki oyundan kalan verileri siler.
        DataOutput.getInstance().resetPlayerA();
        DataOutput.getInstance().resetPlayerB();
        DataOutput.getInstance().resetPlayerC();
        DataOutput.getInstance().resetPlayerD();


        // create golds
        for(int i = 0; i < GOLD_COUNT ; i++){
            System.out.println(i+1);
            Gold temp = new Gold(pointsList.get(rand.nextInt(4)),new Rectangle(BLOCK_SIZE,BLOCK_SIZE),BLOCK_SIZE,
                    rand.nextInt(SettingsScreenController.widht)*BLOCK_SIZE,rand.nextInt(SettingsScreenController.height)*BLOCK_SIZE);

            for(int j = 0; j < goldsArraylist.size() ; j++){
                //check the arraylist
                if(goldsArraylist.get(j).getX() == temp.getX() && goldsArraylist.get(j).getY() == temp.getY()){
                    temp = new Gold(pointsList.get(rand.nextInt(4)),new Rectangle(BLOCK_SIZE,BLOCK_SIZE),BLOCK_SIZE,
                            rand.nextInt(SettingsScreenController.widht)*BLOCK_SIZE,rand.nextInt(SettingsScreenController.height)*BLOCK_SIZE);
                    j = -1;//yeni atanan değeri kontrol etsin
                }

            }

            goldsArraylist.add(temp);
            collectibleArrayList.add(temp);
            System.out.println("altın basariyla eklendi");

        }


        //create hidden golds
        for(int i = 0; i < HIDDEN_GOLD_COUNT ; i++){
            System.out.println(i+1);
            HiddenGold temp = new HiddenGold(pointsList.get(rand.nextInt(4)),new Rectangle(BLOCK_SIZE,BLOCK_SIZE),BLOCK_SIZE,
                    rand.nextInt(SettingsScreenController.widht)*BLOCK_SIZE,rand.nextInt(SettingsScreenController.height)*BLOCK_SIZE);

            for(int j = 0; j < hiddenGoldsArraylist.size() ; j++){
                //check the arraylist
                if(hiddenGoldsArraylist.get(j).getX() == temp.getX() && hiddenGoldsArraylist.get(j).getY() == temp.getY()){
                    temp = new HiddenGold(pointsList.get(rand.nextInt(4)),new Rectangle(BLOCK_SIZE,BLOCK_SIZE),BLOCK_SIZE,
                            rand.nextInt(SettingsScreenController.widht)*BLOCK_SIZE,rand.nextInt(SettingsScreenController.height)*BLOCK_SIZE);
                    j = -1;//yeni atanan değeri kontrol etsin
                }

            }

            hiddenGoldsArraylist.add(temp);
            System.out.println("gizli altin basariyla eklendi");
        }


        //create players

        //locate playerA -> top left
        PlayerA playerA = new PlayerA(SettingsScreenController.startingPoint,BLOCK_SIZE,SettingsScreenController.A_stepCost,SettingsScreenController.A_tarCost);
        playerA.getPlayerRectangle().setTranslateX(0);
        playerA.getPlayerRectangle().setTranslateY(0);
        playersArrayList.add(playerA);
        if(playerA.getTarget().getTranslateX() == -1){
            System.out.println("playerA yeni yaratıldı. Target yok.");
            DataOutput.getInstance().writePlayerA("playerA yeni yaratıldı. Target yok.");
        }
        System.out.println("playerA yeni yaratildi, puan : " + playerA.getGoldPoint() + "\n");
        DataOutput.getInstance().writePlayerA("playerA yeni yaratildi, puan : " + playerA.getGoldPoint() + "\n");
        labelPlayerAPoint.setText(playerA.getGoldPoint() + "");


        //locate playerB -> top right
        PlayerB playerB = new PlayerB(SettingsScreenController.startingPoint,BLOCK_SIZE,SettingsScreenController.B_stepCost,SettingsScreenController.B_tarCost);
        playerB.getPlayerRectangle().setTranslateX(APP_W - BLOCK_SIZE);
        playerB.getPlayerRectangle().setTranslateY(0);
        playersArrayList.add(playerB);
        if(playerB.getTarget().getTranslateX() == -1){
            System.out.println("playerB yeni yaratıldı. Target yok.");
            DataOutput.getInstance().writePlayerB("playerB yeni yaratıldı. Target yok.");
        }
        System.out.println("playerB yeni yaratildi, puan : " + playerB.getGoldPoint() + "\n");
        DataOutput.getInstance().writePlayerB("playerB yeni yaratildi, puan : " + playerB.getGoldPoint() + "\n");
        labelPlayerBPoint.setText(playerB.getGoldPoint() + "");


        //locate playerC -> bottom left
        PlayerC playerC = new PlayerC(SettingsScreenController.startingPoint,BLOCK_SIZE,SettingsScreenController.C_stepCost,SettingsScreenController.C_tarCost);
        playerC.getPlayerRectangle().setTranslateX(0);
        playerC.getPlayerRectangle().setTranslateY(APP_H - BLOCK_SIZE);
        playersArrayList.add(playerC);
        if(playerC.getTarget().getTranslateX() == -1){
            System.out.println("playerC yeni yaratıldı. Target yok.");
            DataOutput.getInstance().writePlayerC("playerC yeni yaratıldı. Target yok.");
        }
        System.out.println("playerC yeni yaratildi, puan : " + playerC.getGoldPoint() + "\n");
        DataOutput.getInstance().writePlayerC("playerC yeni yaratildi, puan : " + playerC.getGoldPoint() + "\n");
        labelPlayerCPoint.setText(playerC.getGoldPoint() + "");

        //locate playerD -> bottom right
        PlayerD playerD = new PlayerD(SettingsScreenController.startingPoint,BLOCK_SIZE,SettingsScreenController.D_stepCost,SettingsScreenController.D_tarCost);
        playerD.getPlayerRectangle().setTranslateX(APP_W - BLOCK_SIZE);
        playerD.getPlayerRectangle().setTranslateY(APP_H - BLOCK_SIZE);
        playersArrayList.add(playerD);
        if(playerD.getTarget().getTranslateX() == -1){
            System.out.println("playerD yeni yaratıldı. Target yok.");
            DataOutput.getInstance().writePlayerD("playerD yeni yaratıldı. Target yok.");
        }
        System.out.println("playerD yeni yaratildi, puan : " + playerD.getGoldPoint() + "\n");
        DataOutput.getInstance().writePlayerD("playerD yeni yaratildi, puan : " + playerD.getGoldPoint() + "\n");
        labelPlayerDPoint.setText(playerD.getGoldPoint() + "");


        //frame for playerA
        KeyFrame frame1 = new KeyFrame(Duration.millis(750),event -> {

            if(!running) return;

            if(order !=1) return;

            //oyuncu elenenlerde varsa ve oyun daha bitmemişse(elenenler 4 kişi değilse) frameleri oynatmaya sıradan devam et)
            if(eliminated.contains(playerA) && eliminated.size() != 4){
                order = 2;
                return;
            }

            //elenenler 4 kişiyse oyun bitmiş demektir. sonlandır frame göstermeyi.
            if(eliminated.size() ==4){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);
                switchSceneToSummaryScreen();

                return;
            }

            System.out.println("---------------turn basladi----------------\n");
            DataOutput.getInstance().writePlayerA("---------------turn basladi----------------\n");

            //oyuncunun altını bittiyse elenir.
            if(playerA.getGoldPoint() <= 0 ){
                labelPlayerAPoint.setText(playerA.getGoldPoint() + "");
                eliminated.add(playerA);
                playerA.getPlayerRectangle().setVisible(false);
                order = 2;
                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerA("\n\n---------------turn bitti----------------");
                return;
            }

            // hedefimiz yoksa, hedeflenecek altın var mı kontrol et, yoksa sonlandır oyunu.
            if(!playerA.getHasTarget() && collectibleArrayList.isEmpty()){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);

                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerA("\n\n---------------turn bitti----------------");
                switchSceneToSummaryScreen();
                return;
            }

            // hedeflenecek altın varsa ve bizim hedefimizi yoksa , hedef belirle.
            if(!playerA.getHasTarget() && !collectibleArrayList.isEmpty()){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerA.getGoldPoint());
                DataOutput.getInstance().writePlayerA("\ntarget belirlenmeden önce puan : " + playerA.getGoldPoint());

                playerA.findTarget(collectibleArrayList);
                totalCostsA += playerA.getTargetingCost();
                System.out.println("target yoktu, belirlendi x : " + playerA.getTarget().getTranslateX() + " y : " + playerA.getTarget().getTranslateY() + "   " + playerA.getTarget().hashCode());
                DataOutput.getInstance().writePlayerA("target yoktu, belirlendi x : " + playerA.getTarget().getTranslateX() + " y : " + playerA.getTarget().getTranslateY() + "   " + playerA.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerA.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerA("target belirlendikten sonra puan : " + playerA.getGoldPoint() + "\n");

                playerA.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerA.getGoldPoint() <= 0 ){
                    labelPlayerAPoint.setText(playerA.getGoldPoint() + "");
                    eliminated.add(playerA);
                    playerA.getPlayerRectangle().setVisible(false);
                    order = 2;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerA("\n\n---------------turn bitti----------------");
                    return;
                }
            }


            //hedefimiz hala yerinde duruyor mu kontrol et, başkası aldıysa yeni hedef belirle.
            if(playerA.getHasTarget() && !collectibleArrayList.contains(playerA.getTargetGolden()) && !collectibleArrayList.isEmpty()){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerA.getGoldPoint());
                DataOutput.getInstance().writePlayerA("\ntarget belirlenmeden önce puan : " + playerA.getGoldPoint());

                playerA.findTarget(collectibleArrayList);
                totalCostsA += playerA.getTargetingCost();

                System.out.println("target yoktu, belirlendi x : " + playerA.getTarget().getTranslateX() + " y : " + playerA.getTarget().getTranslateY() + "   " + playerA.getTarget().hashCode());
                DataOutput.getInstance().writePlayerA("target yoktu, belirlendi x : " + playerA.getTarget().getTranslateX() + " y : " + playerA.getTarget().getTranslateY() + "   " + playerA.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerA.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerA("target belirlendikten sonra puan : " + playerA.getGoldPoint() + "\n");

                playerA.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerA.getGoldPoint() <= 0 ){
                    labelPlayerAPoint.setText(playerA.getGoldPoint() + "");
                    eliminated.add(playerA);
                    playerA.getPlayerRectangle().setVisible(false);
                    order = 2;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerA("\n\n---------------turn bitti----------------");
                    return;
                }
            }



            //hamle yap
            for(int i = 0; i < SettingsScreenController.numOfStep ; i++){

                System.out.println("\nplayer konum x: " + playerA.getPlayerRectangle().getTranslateX() + " y : " + playerA.getPlayerRectangle().getTranslateY());
                DataOutput.getInstance().writePlayerA("\nplayer konum x: " + playerA.getPlayerRectangle().getTranslateX() + " y : " + playerA.getPlayerRectangle().getTranslateY());

                //path de hamle varsa yap, yoksa yapma.
                if(playerA.getPath().size() != 0 && !collectibleArrayList.isEmpty()){
                    playerA.move(playerA.getPath().get(0));
                    playerA.getPath().remove(0);
                    totalStepsA++;
                    System.out.println(totalStepsA);
                    totalCostsA += playerA.getMoveCost();

                    //oyuncunun altını bittiyse elenir.
                    if(playerA.getGoldPoint() <= 0 ){
                        labelPlayerAPoint.setText(playerA.getGoldPoint() + "");
                        eliminated.add(playerA);
                        playerA.getPlayerRectangle().setVisible(false);
                        order = 2;
                        System.out.println("\n\n---------------turn bitti----------------");
                        DataOutput.getInstance().writePlayerA("\n\n---------------turn bitti----------------");
                        return;
                    }
                }

                //olduğumuz konumda gizli altın var mı kontrol et, varsa ve gizliyse onu toplanabilir hale getir.
                ArrayList<HiddenGold> tempHG = (ArrayList<HiddenGold>) hiddenGoldsArraylist.clone();
                for (HiddenGold temp:tempHG) {

                    if(playerA.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                         playerA.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                            collectibleArrayList.add(temp);
                            temp.setCollectible(true);
                            temp.getGoldenRectangle().setVisible(true);
                            hiddenGoldsArraylist.remove(temp);

                    }
                }

                //olduğumuz konumda hedefimizin dışında olan başka bir altın var mı kontrol et, varsa al.
                ArrayList<Golden> tempAL = (ArrayList<Golden>) collectibleArrayList.clone();
                tempAL.remove(playerA.getTargetGolden());
                for (Golden temp:tempAL) {

                    if(playerA.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerA.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        System.out.println("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerA.getGoldPoint());
                        DataOutput.getInstance().writePlayerA("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerA.getGoldPoint());
                        playerA.setGoldPoint(playerA.getGoldPoint() + temp.getPoint());
                        collectedGoldPointA += temp.getPoint();
                        System.out.println("puan eklendi, oyuncunun puanı : " + playerA.getGoldPoint());
                        DataOutput.getInstance().writePlayerA("puan eklendi, oyuncunun puanı : " + playerA.getGoldPoint());

                        temp.getGoldenRectangle().setVisible(false);
                        collectibleArrayList.remove(temp);

                    }
                }

                //hedefimizin konumuna geldiysek puanı al, altını kapat görselden,yeni bi hedef ata(belirleme)
                if(playerA.getPlayerRectangle().getTranslateX() ==  playerA.getTarget().getTranslateX() &&
                        playerA.getPlayerRectangle().getTranslateY() ==  playerA.getTarget().getTranslateY()){

                    System.out.println("\naltın alındı.eklenecek puan : " + playerA.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerA.getGoldPoint());
                    DataOutput.getInstance().writePlayerA("\naltın alındı.eklenecek puan : " + playerA.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerA.getGoldPoint());
                    playerA.setGoldPoint(playerA.getGoldPoint() + playerA.getTargetGolden().getPoint());
                    collectedGoldPointA += playerA.getTargetGolden().getPoint();
                    System.out.println("puan eklendi, oyuncunun puanı : " + playerA.getGoldPoint());
                    DataOutput.getInstance().writePlayerA("puan eklendi, oyuncunun puanı : " + playerA.getGoldPoint());

                    playerA.getTargetGolden().getGoldenRectangle().setVisible(false);
                    collectibleArrayList.remove(playerA.getTargetGolden());
                    playerA.setTarget(new Rectangle(BLOCK_SIZE,BLOCK_SIZE));
                    playerA.getTarget().setTranslateX(-1);
                    playerA.getTarget().setTranslateY(-1);
                    playerA.setTargetGolden(null);
                    playerA.setHasTarget(false);
                    break;

                }


            }

            System.out.println("\nplayerA score "+playerA.getGoldPoint());
            DataOutput.getInstance().writePlayerA("\nplayerA score "+playerA.getGoldPoint());
            labelPlayerAPoint.setText(playerA.getGoldPoint() + "");
            System.out.println("player konum x: " + playerA.getPlayerRectangle().getTranslateX() + " y : " + playerA.getPlayerRectangle().getTranslateY());
            DataOutput.getInstance().writePlayerA("player konum x: " + playerA.getPlayerRectangle().getTranslateX() + " y : " + playerA.getPlayerRectangle().getTranslateY());
            System.out.println("\n\n---------------turn bitti----------------");
            DataOutput.getInstance().writePlayerA("\n\n---------------turn bitti----------------");

            order = 2;


        });



        //frame for playerB
        KeyFrame frame2 = new KeyFrame(Duration.millis(1500),event -> {

            if(!running) return;

            if(order != 2) return;

            //oyuncu elenenlerde varsa ve oyun daha bitmemişse(elenenler 4 kişi değilse, frameleri oynatmaya sıradan devam et)
            if(eliminated.contains(playerB) && eliminated.size() != 4){
                order = 3;
                return;
            }

            //elenenler 4 kişiyse oyun bitmiş demektir. sonlandır frame göstermeyi.
            if(eliminated.size() == 4){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);
                switchSceneToSummaryScreen();
                return;
            }

            System.out.println("---------------turn basladi----------------\n");
            DataOutput.getInstance().writePlayerB("---------------turn basladi----------------\n");

            //oyuncunun altını bittiyse elenir.
            if(playerB.getGoldPoint() <= 0 ){
                labelPlayerBPoint.setText(playerB.getGoldPoint() + "");
                eliminated.add(playerB);
                playerB.getPlayerRectangle().setVisible(false);
                order = 3;
                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerB("\n\n---------------turn bitti----------------");
                return;
            }

            // hedeflenecek altın var mı kontrol et, yoksa sonlandır.
            if(!playerB.getHasTarget() && collectibleArrayList.isEmpty()){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);

                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerB("\n\n---------------turn bitti----------------");
                switchSceneToSummaryScreen();
                return;
            }

            // hedeflenecek altın varsa ve bizim hedefimizi yoksa , hedef belirle.
            if(!playerB.getHasTarget() && !collectibleArrayList.isEmpty()){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerB.getGoldPoint());
                DataOutput.getInstance().writePlayerB("\ntarget belirlenmeden önce puan : " + playerB.getGoldPoint());

                playerB.findTarget(collectibleArrayList);
                totalCostsB += playerB.getTargetingCost();

                System.out.println("target yoktu, belirlendi x : " + playerB.getTarget().getTranslateX() + " y : " + playerB.getTarget().getTranslateY() + "   " + playerB.getTarget().hashCode());
                DataOutput.getInstance().writePlayerB("target yoktu, belirlendi x : " + playerB.getTarget().getTranslateX() + " y : " + playerB.getTarget().getTranslateY() + "   " + playerB.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerB.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerB("target belirlendikten sonra puan : " + playerB.getGoldPoint() + "\n");

                playerB.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerB.getGoldPoint() <= 0 ){
                    labelPlayerBPoint.setText(playerB.getGoldPoint() + "");
                    eliminated.add(playerB);
                    playerB.getPlayerRectangle().setVisible(false);
                    order = 3;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerB("\n\n---------------turn bitti----------------");
                    return;
                }
            }

            //hedefimiz hala yerinde duruyor mu kontrol et, başkası aldıysa hedef belirle.
            if(playerB.getHasTarget() && !collectibleArrayList.contains(playerB.getTargetGolden()) && !collectibleArrayList.isEmpty()){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerB.getGoldPoint());
                DataOutput.getInstance().writePlayerB("\ntarget belirlenmeden önce puan : " + playerB.getGoldPoint());

                playerB.findTarget(collectibleArrayList);
                totalCostsB += playerB.getTargetingCost();

                System.out.println("target yoktu, belirlendi x : " + playerB.getTarget().getTranslateX() + " y : " + playerB.getTarget().getTranslateY() + "   " + playerB.getTarget().hashCode());
                DataOutput.getInstance().writePlayerB("target yoktu, belirlendi x : " + playerB.getTarget().getTranslateX() + " y : " + playerB.getTarget().getTranslateY() + "   " + playerB.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerB.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerB("target belirlendikten sonra puan : " + playerB.getGoldPoint() + "\n");

                playerB.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerB.getGoldPoint() <= 0 ){
                    labelPlayerBPoint.setText(playerB.getGoldPoint() + "");
                    eliminated.add(playerB);
                    playerB.getPlayerRectangle().setVisible(false);
                    order = 3;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerB("\n\n---------------turn bitti----------------");
                    return;
                }
            }


            //hamle yap
            for(int i = 0; i < SettingsScreenController.numOfStep ; i++) {
                System.out.println("\nplayer konum x: " + playerB.getPlayerRectangle().getTranslateX() + " y : " + playerB.getPlayerRectangle().getTranslateY());
                DataOutput.getInstance().writePlayerB("\nplayer konum x: " + playerB.getPlayerRectangle().getTranslateX() + " y : " + playerB.getPlayerRectangle().getTranslateY());

                //path de yapılacak hamle varsa yap, yoksa yapma.
                if (playerB.getPath().size() != 0  && !collectibleArrayList.isEmpty()) {
                    playerB.move(playerB.getPath().get(0));
                    playerB.getPath().remove(0);
                    totalStepsB++;
                    System.out.println(totalStepsB);
                    totalCostsB += playerB.getMoveCost();

                    //oyuncunun altını bittiyse elenir.
                    if(playerB.getGoldPoint() <= 0 ){
                        labelPlayerBPoint.setText(playerB.getGoldPoint() + "");
                        eliminated.add(playerB);
                        playerB.getPlayerRectangle().setVisible(false);
                        order = 3;
                        System.out.println("\n\n---------------turn bitti----------------");
                        DataOutput.getInstance().writePlayerB("\n\n---------------turn bitti----------------");
                        return;
                    }
                }

                //olduğumuz konumda gizli altın var mı kontrol et, varsa ve gizliyse onu toplanabilir hale getir.
                ArrayList<HiddenGold> tempHG = (ArrayList<HiddenGold>) hiddenGoldsArraylist.clone();
                for (HiddenGold temp:tempHG) {

                    if(playerB.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerB.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        collectibleArrayList.add(temp);
                        temp.setCollectible(true);
                        temp.getGoldenRectangle().setVisible(true);
                        hiddenGoldsArraylist.remove(temp);

                    }
                }

                //olduğumuz konumda hedefimizin dışında olan başka bir altın var mı kontrol et, varsa al.
                ArrayList<Golden> tempAL = (ArrayList<Golden>) collectibleArrayList.clone();
                tempAL.remove(playerB.getTargetGolden());
                for (Golden temp:tempAL) {

                    if(playerB.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerB.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        System.out.println("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerB.getGoldPoint());
                        DataOutput.getInstance().writePlayerB("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerB.getGoldPoint());
                        playerB.setGoldPoint(playerB.getGoldPoint() + temp.getPoint());
                        collectedGoldPointB += temp.getPoint();
                        System.out.println("puan eklendi, oyuncunun puanı : " + playerB.getGoldPoint());
                        DataOutput.getInstance().writePlayerB("puan eklendi, oyuncunun puanı : " + playerB.getGoldPoint());

                        temp.getGoldenRectangle().setVisible(false);
                        collectibleArrayList.remove(temp);

                    }
                }

                //hedefimizin konumuna geldiysek puanı al, altını kapat görselden,yeni bi hedef ata(belirleme)
                if (playerB.getPlayerRectangle().getTranslateX() == playerB.getTarget().getTranslateX() &&
                        playerB.getPlayerRectangle().getTranslateY() == playerB.getTarget().getTranslateY()) {

                    System.out.println("\naltın alındı.eklenecek puan : " + playerB.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerB.getGoldPoint());
                    DataOutput.getInstance().writePlayerB("\naltın alındı.eklenecek puan : " + playerB.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerB.getGoldPoint());
                    playerB.setGoldPoint(playerB.getGoldPoint() + playerB.getTargetGolden().getPoint());
                    collectedGoldPointB += playerB.getTargetGolden().getPoint();
                    System.out.println("puan eklendi, oyuncunun puanı : " + playerB.getGoldPoint());
                    DataOutput.getInstance().writePlayerB("puan eklendi, oyuncunun puanı : " + playerB.getGoldPoint());

                    playerB.getTargetGolden().getGoldenRectangle().setVisible(false);
                    collectibleArrayList.remove(playerB.getTargetGolden());
                    playerB.setTarget(new Rectangle(BLOCK_SIZE, BLOCK_SIZE));
                    playerB.getTarget().setTranslateX(-1);
                    playerB.getTarget().setTranslateY(-1);
                    playerB.setTargetGolden(null);
                    playerB.setHasTarget(false);
                    break;

                }
            }


            System.out.println("\nplayerB score "+playerB.getGoldPoint());
            DataOutput.getInstance().writePlayerB("\nplayerB score "+playerB.getGoldPoint());
            labelPlayerBPoint.setText(playerB.getGoldPoint() + "");
            System.out.println("playerB konum x: " + playerB.getPlayerRectangle().getTranslateX() + " y : " + playerB.getPlayerRectangle().getTranslateY());
            DataOutput.getInstance().writePlayerB("playerB konum x: " + playerB.getPlayerRectangle().getTranslateX() + " y : " + playerB.getPlayerRectangle().getTranslateY());
            System.out.println("\n\n---------------turn bitti----------------");
            DataOutput.getInstance().writePlayerB("\n\n---------------turn bitti----------------");



            order = 3;


        });



        //frame for playerC
        KeyFrame frame3 = new KeyFrame(Duration.millis(2250),event -> {

            if(!running) return;

            if(order != 3) return;;

            //oyuncu elenenlerde varsa ve oyun daha bitmemişse(elenenler 4 kişi değilse, frameleri oynatmaya sıradan devam et)
            if(eliminated.contains(playerC) && eliminated.size() != 4){
                order = 4;
                return;
            }

            //elenenler 4 kişiyse oyun bitmiş demektir. sonlandır frame göstermeyi.
            if(eliminated.size() == 4){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);
                switchSceneToSummaryScreen();
                return;
            }

            System.out.println("---------------turn basladi----------------\n");
            DataOutput.getInstance().writePlayerC("---------------turn basladi----------------\n");

            //oyuncunun altını bittiyse elenir.
            if(playerC.getGoldPoint() <= 0 ){
                labelPlayerCPoint.setText(playerC.getGoldPoint() + "");
                eliminated.add(playerC);
                playerC.getPlayerRectangle().setVisible(false);
                order = 4;
                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerC("\n\n---------------turn bitti----------------");
                return;
            }

            // hedeflenecek altın var mı kontrol et, yoksa sonlandır.
            if(!playerC.getHasTarget() && collectibleArrayList.isEmpty()){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);

                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerC("\n\n---------------turn bitti----------------");
                switchSceneToSummaryScreen();
                return;
            }

            // hedeflenecek altın varsa ve bizim hedefimizi yoksa , hedef belirle.
            if(!playerC.getHasTarget() && !collectibleArrayList.isEmpty()){
                System.out.println("\ntarget belirlenmeden önce puan : " + playerC.getGoldPoint());
                DataOutput.getInstance().writePlayerC("\ntarget belirlenmeden önce puan : " + playerC.getGoldPoint());
                playerC.openHiddenGolds(collectibleArrayList,hiddenGoldsArraylist);
                playerC.findTarget(collectibleArrayList);
                totalCostsC += playerC.getTargetingCost();
                System.out.println("target yoktu, belirlendi x : " + playerC.getTarget().getTranslateX() + " y : " + playerC.getTarget().getTranslateY() + "   " + playerC.getTarget().hashCode());
                DataOutput.getInstance().writePlayerC("target yoktu, belirlendi x : " + playerC.getTarget().getTranslateX() + " y : " + playerC.getTarget().getTranslateY() + "   " + playerC.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerC.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerC("target belirlendikten sonra puan : " + playerC.getGoldPoint() + "\n");
                playerC.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerC.getGoldPoint() <= 0 ){
                    labelPlayerCPoint.setText(playerC.getGoldPoint() + "");
                    eliminated.add(playerC);
                    playerC.getPlayerRectangle().setVisible(false);
                    order = 4;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerC("\n\n---------------turn bitti----------------");
                    return;
                }
            }


            //hedefimiz hala yerinde duruyor mu kontrol et, başkası aldıysa hedef belirle.
            if(playerC.getHasTarget() && !collectibleArrayList.contains(playerC.getTargetGolden()) && !collectibleArrayList.isEmpty()){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerC.getGoldPoint());
                DataOutput.getInstance().writePlayerC("\ntarget belirlenmeden önce puan : " + playerC.getGoldPoint());

                playerC.openHiddenGolds(collectibleArrayList,hiddenGoldsArraylist);
                playerC.findTarget(collectibleArrayList);
                totalCostsC += playerC.getTargetingCost();

                System.out.println("target yoktu, belirlendi x : " + playerC.getTarget().getTranslateX() + " y : " + playerC.getTarget().getTranslateY() + "   " + playerC.getTarget().hashCode());
                DataOutput.getInstance().writePlayerC("target yoktu, belirlendi x : " + playerC.getTarget().getTranslateX() + " y : " + playerC.getTarget().getTranslateY() + "   " + playerC.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerC.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerC("target belirlendikten sonra puan : " + playerC.getGoldPoint() + "\n");

                playerC.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerC.getGoldPoint() <= 0 ){
                    labelPlayerCPoint.setText(playerC.getGoldPoint() + "");
                    eliminated.add(playerC);
                    playerC.getPlayerRectangle().setVisible(false);
                    order = 4;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerC("\n\n---------------turn bitti----------------");
                    return;
                }
            }


            //hamle yap
            for(int i = 0; i < SettingsScreenController.numOfStep ; i++){
                System.out.println("\nplayer konum x: " + playerC.getPlayerRectangle().getTranslateX() + " y : " + playerC.getPlayerRectangle().getTranslateY());
                DataOutput.getInstance().writePlayerC("\nplayer konum x: " + playerC.getPlayerRectangle().getTranslateX() + " y : " + playerC.getPlayerRectangle().getTranslateY());

                //path de hamle varsa yap, yoksa yapma.
                if(playerC.getPath().size() != 0 && !collectibleArrayList.isEmpty()){
                    playerC.move(playerC.getPath().get(0));
                    playerC.getPath().remove(0);
                    totalStepsC++;
                    System.out.println(totalStepsC);
                    totalCostsC += playerC.getMoveCost();

                    //oyuncunun altını bittiyse elenir.
                    if(playerC.getGoldPoint() <= 0 ){
                        labelPlayerCPoint.setText(playerC.getGoldPoint() + "");
                        eliminated.add(playerC);
                        playerC.getPlayerRectangle().setVisible(false);
                        order = 4;
                        System.out.println("\n\n---------------turn bitti----------------");
                        DataOutput.getInstance().writePlayerC("\n\n---------------turn bitti----------------");
                        return;
                    }
                }

                //olduğumuz konumda gizli altın var mı kontrol et, varsa ve gizliyse onu toplanabilir hale getir.
                ArrayList<HiddenGold> tempHG = (ArrayList<HiddenGold>) hiddenGoldsArraylist.clone();
                for (HiddenGold temp:tempHG) {

                    if(playerC.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerC.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        collectibleArrayList.add(temp);
                        temp.setCollectible(true);
                        temp.getGoldenRectangle().setVisible(true);
                        hiddenGoldsArraylist.remove(temp);

                    }
                }

                //olduğumuz konumda hedefimizin dışında olan başka bir altın var mı kontrol et, varsa al.
                ArrayList<Golden> tempAL = (ArrayList<Golden>) collectibleArrayList.clone();
                tempAL.remove(playerC.getTargetGolden());
                for (Golden temp:tempAL) {

                    if(playerC.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerC.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        System.out.println("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerC.getGoldPoint());
                        DataOutput.getInstance().writePlayerC("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerC.getGoldPoint());

                        playerC.setGoldPoint(playerC.getGoldPoint() + temp.getPoint());
                        collectedGoldPointC += temp.getPoint();

                        System.out.println("puan eklendi, oyuncunun puanı : " + playerC.getGoldPoint());
                        DataOutput.getInstance().writePlayerC("puan eklendi, oyuncunun puanı : " + playerC.getGoldPoint());

                        temp.getGoldenRectangle().setVisible(false);
                        collectibleArrayList.remove(temp);

                    }
                }

                //hedefimizin konumuna geldiysek puanı al, altını kapat görselden,yeni bi hedef ata(belirleme)
                if(playerC.getPlayerRectangle().getTranslateX() ==  playerC.getTarget().getTranslateX() &&
                        playerC.getPlayerRectangle().getTranslateY() ==  playerC.getTarget().getTranslateY()){

                    System.out.println("\naltın alındı.eklenecek puan : " + playerC.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerC.getGoldPoint());
                    DataOutput.getInstance().writePlayerC("\naltın alındı.eklenecek puan : " + playerC.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerC.getGoldPoint());
                    playerC.setGoldPoint(playerC.getGoldPoint() + playerC.getTargetGolden().getPoint());
                    collectedGoldPointC += playerC.getTargetGolden().getPoint();
                    System.out.println("puan eklendi, oyuncunun puanı : " + playerC.getGoldPoint());
                    DataOutput.getInstance().writePlayerC("puan eklendi, oyuncunun puanı : " + playerC.getGoldPoint());

                    playerC.getTargetGolden().getGoldenRectangle().setVisible(false);
                    collectibleArrayList.remove(playerC.getTargetGolden());
                    playerC.setTarget(new Rectangle(BLOCK_SIZE,BLOCK_SIZE));
                    playerC.getTarget().setTranslateX(-1);
                    playerC.getTarget().setTranslateY(-1);
                    playerC.setTargetGolden(null);
                    playerC.setHasTarget(false);
                    break;

                }

            }

            System.out.println("\nplayerC score "+playerC.getGoldPoint());
            DataOutput.getInstance().writePlayerC("\nplayerC score "+playerC.getGoldPoint());
            labelPlayerCPoint.setText(playerC.getGoldPoint() + "");
            System.out.println("playerC konum x: " + playerC.getPlayerRectangle().getTranslateX() + " y : " + playerC.getPlayerRectangle().getTranslateY());
            DataOutput.getInstance().writePlayerC("playerC konum x: " + playerC.getPlayerRectangle().getTranslateX() + " y : " + playerC.getPlayerRectangle().getTranslateY());
            System.out.println("\n\n---------------turn bitti----------------");
            DataOutput.getInstance().writePlayerC("\n\n---------------turn bitti----------------");


            order = 4;


        });


        //frame for playerD
        KeyFrame frame4 = new KeyFrame(Duration.millis(3000),event -> {

            if(!running) return;

            if(order != 4) return;

            //oyuncu elenenlerde varsa ve oyun daha bitmemişse(elenenler 4 kişi değilse, frameleri oynatmaya sıradan devam et)
            if(eliminated.contains(playerD) && eliminated.size() != 4){
                order = 1;
                return;
            }

            //elenenler 4 kişiyse oyun bitmiş demektir. sonlandır frame göstermeyi.
            if(eliminated.size() == 4){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);
                switchSceneToSummaryScreen();
                return;
            }

            System.out.println("---------------turn basladi----------------\n");
            DataOutput.getInstance().writePlayerD("---------------turn basladi----------------\n");

            //oyuncunun altını bittiyse elenir.
            if(playerD.getGoldPoint() <= 0 ){
                labelPlayerDPoint.setText(playerD.getGoldPoint() + "");
                eliminated.add(playerD);
                playerD.getPlayerRectangle().setVisible(false);
                order = 1;
                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerD("\n\n---------------turn bitti----------------");
                return;
        }

            // hedeflenecek altın var mı kontrol et, yoksa sonlandır.
            if( !playerD.getHasTarget() && collectibleArrayList.isEmpty() ){
                running = false;
                timeline.stop();

                endgameDataWrite(playerA,playerB,playerC,playerD);

                System.out.println("\n\n---------------turn bitti----------------");
                DataOutput.getInstance().writePlayerD("\n\n---------------turn bitti----------------");
                switchSceneToSummaryScreen();
                return;
            }

            // hedeflenecek altın varsa ve bizim hedefimizi yoksa , hedef belirle.
            if( !playerD.getHasTarget() && !collectibleArrayList.isEmpty() ){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerD.getGoldPoint());
                DataOutput.getInstance().writePlayerD("\ntarget belirlenmeden önce puan : " + playerD.getGoldPoint());

                if(playerA.getTargetGolden() != null){
                    playerD.setOpponentsTargetsAndLengths(playerA.getTargetGolden(),playerA.getPath().size());
                }

                if(playerB.getTargetGolden() != null){
                    playerD.setOpponentsTargetsAndLengths(playerB.getTargetGolden(),playerB.getPath().size());
                }

                if(playerC.getTargetGolden() != null){
                    playerD.setOpponentsTargetsAndLengths(playerC.getTargetGolden(),playerC.getPath().size());
                }

                playerD.findTarget(collectibleArrayList);
                totalCostsD += playerD.getTargetingCost();

                System.out.println("target yoktu, belirlendi x : " + playerD.getTarget().getTranslateX() + " y : " + playerD.getTarget().getTranslateY() + "   " + playerD.getTarget().hashCode());
                DataOutput.getInstance().writePlayerD("target yoktu, belirlendi x : " + playerD.getTarget().getTranslateX() + " y : " + playerD.getTarget().getTranslateY() + "   " + playerD.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerD.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerD("target belirlendikten sonra puan : " + playerD.getGoldPoint() + "\n");

                playerD.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerD.getGoldPoint() <= 0 ){
                    labelPlayerDPoint.setText(playerD.getGoldPoint() + "");
                    eliminated.add(playerD);
                    playerD.getPlayerRectangle().setVisible(false);
                    order = 1;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerD("\n\n---------------turn bitti----------------");
                    return;
                }
            }


            //hedefimiz hala yerinde duruyor mu kontrol et, başkası aldıysa hedef belirle.
            if(playerD.getHasTarget() && !collectibleArrayList.contains(playerD.getTargetGolden()) && !collectibleArrayList.isEmpty()){

                System.out.println("\ntarget belirlenmeden önce puan : " + playerD.getGoldPoint());
                DataOutput.getInstance().writePlayerD("\ntarget belirlenmeden önce puan : " + playerD.getGoldPoint());

                if(playerA.getTargetGolden() != null){
                    playerD.setOpponentsTargetsAndLengths(playerA.getTargetGolden(),playerA.getPath().size());
                }

                if(playerB.getTargetGolden() != null){
                    playerD.setOpponentsTargetsAndLengths(playerB.getTargetGolden(),playerB.getPath().size());
                }

                if(playerC.getTargetGolden() != null){
                    playerD.setOpponentsTargetsAndLengths(playerC.getTargetGolden(),playerC.getPath().size());
                }

                playerD.findTarget(collectibleArrayList);
                totalCostsD += playerD.getTargetingCost();

                System.out.println("target yoktu, belirlendi x : " + playerD.getTarget().getTranslateX() + " y : " + playerD.getTarget().getTranslateY() + "   " + playerD.getTarget().hashCode());
                DataOutput.getInstance().writePlayerD("target yoktu, belirlendi x : " + playerD.getTarget().getTranslateX() + " y : " + playerD.getTarget().getTranslateY() + "   " + playerD.getTarget().hashCode());
                System.out.println("target belirlendikten sonra puan : " + playerD.getGoldPoint() + "\n");
                DataOutput.getInstance().writePlayerD("target belirlendikten sonra puan : " + playerD.getGoldPoint() + "\n");


                playerD.setHasTarget(true);

                //oyuncunun altını bittiyse elenir.
                if(playerD.getGoldPoint() <= 0 ){
                    labelPlayerDPoint.setText(playerD.getGoldPoint() + "");
                    eliminated.add(playerD);
                    playerD.getPlayerRectangle().setVisible(false);
                    order = 1;
                    System.out.println("\n\n---------------turn bitti----------------");
                    DataOutput.getInstance().writePlayerD("\n\n---------------turn bitti----------------");
                    return;
                }
            }

            //hamle yap
            for(int i = 0; i < SettingsScreenController.numOfStep ; i++){
                System.out.println("\nplayer konum x: " + playerD.getPlayerRectangle().getTranslateX() + " y : " + playerD.getPlayerRectangle().getTranslateY());
                DataOutput.getInstance().writePlayerD("\nplayer konum x: " + playerD.getPlayerRectangle().getTranslateX() + " y : " + playerD.getPlayerRectangle().getTranslateY());

                //path de hamle varsa yap, yoksa yapma.
                if(playerD.getPath().size() != 0 && !collectibleArrayList.isEmpty()){
                    playerD.move(playerD.getPath().get(0));
                    playerD.getPath().remove(0);
                    totalStepsD++;
                    System.out.println(totalStepsD);
                    totalCostsD += playerD.getMoveCost();

                    //oyuncunun altını bittiyse elenir.
                    if(playerD.getGoldPoint() <= 0 ){
                        labelPlayerDPoint.setText(playerD.getGoldPoint() + "");
                        eliminated.add(playerD);
                        playerD.getPlayerRectangle().setVisible(false);
                        order = 1;
                        System.out.println("\n\n---------------turn bitti----------------");
                        DataOutput.getInstance().writePlayerD("\n\n---------------turn bitti----------------");
                        return;
                    }
                }

                //olduğumuz konumda gizli altın var mı kontrol et, varsa ve gizliyse onu toplanabilir hale getir.
                ArrayList<HiddenGold> tempHG = (ArrayList<HiddenGold>) hiddenGoldsArraylist.clone();
                for (HiddenGold temp:tempHG) {

                    if(playerD.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerD.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        collectibleArrayList.add(temp);
                        temp.setCollectible(true);
                        temp.getGoldenRectangle().setVisible(true);
                        hiddenGoldsArraylist.remove(temp);

                    }
                }

                //olduğumuz konumda hedefimizin dışında olan başka bir altın var mı kontrol et, varsa al.
                ArrayList<Golden> tempAL = (ArrayList<Golden>) collectibleArrayList.clone();
                tempAL.remove(playerD.getTargetGolden());
                for (Golden temp:tempAL) {

                    if(playerD.getPlayerRectangle().getTranslateX() == temp.getGoldenRectangle().getTranslateX() &&
                            playerD.getPlayerRectangle().getTranslateY() == temp.getGoldenRectangle().getTranslateY()){

                        System.out.println("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerD.getGoldPoint());
                        DataOutput.getInstance().writePlayerD("\naltın alındı.eklenecek puan : " + temp.getPoint() + " oyuncunun puanı : " + playerD.getGoldPoint());

                        playerD.setGoldPoint(playerD.getGoldPoint() + temp.getPoint());
                        collectedGoldPointD += temp.getPoint();

                        System.out.println("puan eklendi, oyuncunun puanı : " + playerD.getGoldPoint());
                        DataOutput.getInstance().writePlayerD("puan eklendi, oyuncunun puanı : " + playerD.getGoldPoint());

                        temp.getGoldenRectangle().setVisible(false);
                        collectibleArrayList.remove(temp);

                    }
                }

                //hedefimizin konumuna geldiysek puanı al, altını kapat görselden,yeni bi hedef ata(belirleme)
                if(playerD.getPlayerRectangle().getTranslateX() ==  playerD.getTarget().getTranslateX() &&
                        playerD.getPlayerRectangle().getTranslateY() ==  playerD.getTarget().getTranslateY()){

                    System.out.println("\naltın alındı.eklenecek puan : " + playerD.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerD.getGoldPoint());
                    DataOutput.getInstance().writePlayerD("\naltın alındı.eklenecek puan : " + playerD.getTargetGolden().getPoint() + " oyuncunun puanı : " + playerD.getGoldPoint());

                    playerD.setGoldPoint(playerD.getGoldPoint() + playerD.getTargetGolden().getPoint());
                    collectedGoldPointD += playerD.getTargetGolden().getPoint();

                    System.out.println("puan eklendi, oyuncunun puanı : " + playerD.getGoldPoint());
                    DataOutput.getInstance().writePlayerD("puan eklendi, oyuncunun puanı : " + playerD.getGoldPoint());

                    playerD.getTargetGolden().getGoldenRectangle().setVisible(false);
                    collectibleArrayList.remove(playerD.getTargetGolden());
                    playerD.setTarget(new Rectangle(BLOCK_SIZE,BLOCK_SIZE));
                    playerD.getTarget().setTranslateX(-1);
                    playerD.getTarget().setTranslateY(-1);

                    playerD.setHasTarget(false);
                    break;

                }

            }

            System.out.println("\nplayerD score "+playerD.getGoldPoint());
            DataOutput.getInstance().writePlayerD("\nplayerD score "+playerD.getGoldPoint());
            labelPlayerDPoint.setText(playerD.getGoldPoint() + "");
            System.out.println("playerD konum x: " + playerD.getPlayerRectangle().getTranslateX() + " y : " + playerD.getPlayerRectangle().getTranslateY());
            DataOutput.getInstance().writePlayerD("playerD konum x: " + playerD.getPlayerRectangle().getTranslateX() + " y : " + playerD.getPlayerRectangle().getTranslateY());
            System.out.println("\n\n---------------turn bitti----------------");
            DataOutput.getInstance().writePlayerD("\n\n---------------turn bitti----------------");


            order = 1;


        });



        timeline.getKeyFrames().addAll(frame1,frame2,frame3,frame4);
        timeline.setCycleCount(Timeline.INDEFINITE);


        //add goldRectangles to the root
        for (Gold temp:goldsArraylist) {
            root.getChildren().add(temp.getGoldenRectangle());
        }

        //add hiddenGoldRectangles to the root
        for (HiddenGold temp:hiddenGoldsArraylist) {
            root.getChildren().add(temp.getGoldenRectangle());
        }

        //add playerRectangles to the root
        for (Player temp:playersArrayList) {
            root.getChildren().add(temp.getPlayerRectangle());
        }

        return root;

    }



    public void endgameDataWrite(Player pA, Player pB, Player pC, Player pD){

        finalGoldPointA = pA.getGoldPoint();
        System.out.println("\nfinal puanı A: " + finalGoldPointA);
        System.out.println("final toplam adim sayisi A: " + totalStepsA);
        System.out.println("final collected gold points A: " + collectedGoldPointA);
        System.out.println("final total costed gold points A: " + totalCostsA);
        DataOutput.getInstance().writePlayerA("\nfinal puanı A: " + finalGoldPointA);
        DataOutput.getInstance().writePlayerA("final toplam adim sayisi A: " + totalStepsA);
        DataOutput.getInstance().writePlayerA("final collected gold points A : " + collectedGoldPointA);
        DataOutput.getInstance().writePlayerA("final total costed gold points A: " + totalCostsA);

        finalGoldPointB = pB.getGoldPoint();
        System.out.println("\nfinal puanı B: " + finalGoldPointB);
        System.out.println("final toplam adim sayisi B: " + totalStepsB);
        System.out.println("final collected gold points B : " + collectedGoldPointB);
        System.out.println("final total costed gold points B: " + totalCostsB);
        DataOutput.getInstance().writePlayerB("\nfinal puanı B: " + finalGoldPointB);
        DataOutput.getInstance().writePlayerB("final toplam adim sayisi B: " + totalStepsB);
        DataOutput.getInstance().writePlayerB("final collected gold points B : " + collectedGoldPointB);
        DataOutput.getInstance().writePlayerB("final total costed gold points B: " + totalCostsB);

        finalGoldPointC = pC.getGoldPoint();
        System.out.println("\nfinal puanı C: " + finalGoldPointC);
        System.out.println("final toplam adim sayisi C: " + totalStepsC);
        System.out.println("final collected gold points C : " + collectedGoldPointC);
        System.out.println("final total costed gold points C: " + totalCostsC);
        DataOutput.getInstance().writePlayerC("\nfinal puanı C: " + finalGoldPointC);
        DataOutput.getInstance().writePlayerC("final toplam adim sayisi C: " + totalStepsC);
        DataOutput.getInstance().writePlayerC("final collected gold points C : " + collectedGoldPointC);
        DataOutput.getInstance().writePlayerC("final total costed gold points C: " + totalCostsC);

        finalGoldPointD = pD.getGoldPoint();
        System.out.println("\nfinal puanı D: " + finalGoldPointD);
        System.out.println("final toplam adim sayisi D: " + totalStepsD);
        System.out.println("final collected gold points D : " + collectedGoldPointD);
        System.out.println("final total costed gold points D: " + totalCostsD);
        DataOutput.getInstance().writePlayerD("\nfinal puanı D: " + finalGoldPointD);
        DataOutput.getInstance().writePlayerD("final toplam adim sayisi D: " + totalStepsD);
        DataOutput.getInstance().writePlayerD("final collected gold points D : " + collectedGoldPointD);
        DataOutput.getInstance().writePlayerD("final total costed gold points D: " + totalCostsD);

    }

    public void switchSceneToSummaryScreen(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("SummaryScreen.fxml"));
            Stage stage = Main.stage;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }


    }



    public double getTOTAL_GOLD_COUNT() {
        return TOTAL_GOLD_COUNT;
    }

    public void setTOTAL_GOLD_COUNT(double TOTAL_GOLD_COUNT) {
        this.TOTAL_GOLD_COUNT = TOTAL_GOLD_COUNT;
    }

    public double getHIDDEN_GOLD_COUNT() {
        return HIDDEN_GOLD_COUNT;
    }

    public void setHIDDEN_GOLD_COUNT(double HIDDEN_GOLD_COUNT) {
        this.HIDDEN_GOLD_COUNT = HIDDEN_GOLD_COUNT;
    }

    public double getGOLD_COUNT() {
        return GOLD_COUNT;
    }

    public void setGOLD_COUNT(double GOLD_COUNT) {
        this.GOLD_COUNT = GOLD_COUNT;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public ArrayList<Player> getPlayersArrayList() {
        return playersArrayList;
    }

    public void setPlayersArrayList(ArrayList<Player> playersArrayList) {
        this.playersArrayList = playersArrayList;
    }

    public ArrayList<Gold> getGoldsArraylist() {
        return goldsArraylist;
    }

    public void setGoldsArraylist(ArrayList<Gold> goldsArraylist) {
        this.goldsArraylist = goldsArraylist;
    }

    public ArrayList<HiddenGold> getHiddenGoldsArraylist() {
        return hiddenGoldsArraylist;
    }

    public void setHiddenGoldsArraylist(ArrayList<HiddenGold> hiddenGoldsArraylist) {
        this.hiddenGoldsArraylist = hiddenGoldsArraylist;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }

    public void setBLOCK_SIZE(int BLOCK_SIZE) {
        this.BLOCK_SIZE = BLOCK_SIZE;
    }

    public int getAPP_W() {
        return APP_W;
    }

    public void setAPP_W(int APP_W) {
        this.APP_W = APP_W;
    }

    public int getAPP_H() {
        return APP_H;
    }

    public void setAPP_H(int APP_H) {
        this.APP_H = APP_H;
    }
}
